const db = require("../config/db");

exports.issueLoan = async ({ user_id, book_id, due_date }) => {
  const [result] = await db.execute(
    "INSERT INTO loans (user_id, book_id, issue_date, due_date, status) VALUES (?, ?, NOW(), ?, 'ACTIVE')",
    [user_id, book_id, due_date]
  );
  await db.execute(
    "UPDATE books SET available_copies = available_copies - 1 WHERE id = ?",
    [book_id]
  );
  return exports.getLoanById(result.insertId);
};

exports.returnLoan = async (loan_id) => {
  const [loan] = await db.execute("SELECT * FROM loans WHERE id = ?", [loan_id]);
  if (!loan.length) return null;

  await db.execute(
    "UPDATE loans SET return_date = NOW(), status = 'RETURNED' WHERE id = ?",
    [loan_id]
  );
  await db.execute(
    "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?",
    [loan[0].book_id]
  );
  return exports.getLoanById(loan_id);
};

exports.getLoanById = async (id) => {
  const [rows] = await db.execute(
    "SELECT * FROM loans WHERE id = ?",
    [id]
  );
  return rows[0];
};

exports.getLoansByUser = async (user_id) => {
  const [rows] = await db.execute(
    `SELECT l.id, l.issue_date, l.due_date, l.return_date, l.status,
            b.id as book_id, b.title, b.author
     FROM loans l
     JOIN books b ON l.book_id = b.id
     WHERE l.user_id = ?`,
    [user_id]
  );
  return rows.map((row) => ({
    id: row.id,
    book: {
      id: row.book_id,
      title: row.title,
      author: row.author,
    },
    issue_date: row.issue_date,
    due_date: row.due_date,
    return_date: row.return_date,
    status: row.status,
  }));
};

exports.getOverdueLoans = async () => {
  const [rows] = await db.execute(
    `SELECT l.id, u.id as user_id, u.name, u.email,
            b.id as book_id, b.title, b.author,
            DATEDIFF(NOW(), l.due_date) as days_overdue
     FROM loans l
     JOIN users u ON l.user_id = u.id
     JOIN books b ON l.book_id = b.id
     WHERE l.status = 'ACTIVE' AND l.due_date < NOW()`
  );
  return rows.map((row) => ({
    id: row.id,
    user: {
      id: row.user_id,
      name: row.name,
      email: row.email,
    },
    book: {
      id: row.book_id,
      title: row.title,
      author: row.author,
    },
    issue_date: row.issue_date,
    due_date: row.due_date,
    days_overdue: row.days_overdue,
  }));
};

exports.extendLoan = async (loanId, extension_days) => {
  const [loan] = await db.execute(
    "SELECT due_date FROM loans WHERE id = ?",
    [loanId]
  );
  if (!loan.length) return null;

  const original_due_date = loan[0].due_date;
  await db.execute(
    "UPDATE loans SET due_date = DATE_ADD(due_date, INTERVAL ? DAY), extensions_count = extensions_count + 1 WHERE id = ?",
    [extension_days, loanId]
  );
  const updatedLoan = await exports.getLoanById(loanId);
  return {
    ...updatedLoan,
    original_due_date,
    extended_due_date: updatedLoan.due_date,
  };
};

exports.getPopularBooks = async () => {
  const [rows] = await db.execute(
    `SELECT book_id, b.title, b.author, COUNT(*) as borrow_count
     FROM loans
     JOIN books b ON loans.book_id = b.id
     GROUP BY book_id
     ORDER BY borrow_count DESC
     LIMIT 5`
  );
  return rows;
};

exports.getActiveUsers = async () => {
  const [rows] = await db.execute(
    `SELECT u.id as user_id, u.name, COUNT(l.id) as books_borrowed,
            SUM(CASE WHEN l.status = 'ACTIVE' THEN 1 ELSE 0 END) as current_borrows
     FROM users u
     JOIN loans l ON u.id = l.user_id
     GROUP BY u.id
     ORDER BY books_borrowed DESC
     LIMIT 5`
  );
  return rows;
};

exports.getSystemOverview = async () => {
  const [rows] = await db.execute(`
    SELECT
      (SELECT COUNT(*) FROM books) as total_books,
      (SELECT COUNT(*) FROM users) as total_users,
      (SELECT SUM(available_copies) FROM books) as books_available,
      (SELECT SUM(copies) - SUM(available_copies) FROM books) as books_borrowed,
      (SELECT COUNT(*) FROM loans WHERE status = 'ACTIVE' AND due_date < NOW()) as overdue_loans,
      (SELECT COUNT(*) FROM loans WHERE DATE(issue_date) = CURDATE()) as loans_today,
      (SELECT COUNT(*) FROM loans WHERE DATE(return_date) = CURDATE()) as returns_today
  `);
  return rows[0];
};
