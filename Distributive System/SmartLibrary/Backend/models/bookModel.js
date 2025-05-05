const db = require("../config/db");

exports.createBook = async ({ title, author, isbn, copies }) => {
  const [result] = await db.execute(
    "INSERT INTO books (title, author, isbn, copies, available_copies) VALUES (?, ?, ?, ?, ?)",
    [title, author, isbn, copies, copies]
  );
  return { id: result.insertId, title, author, isbn, copies, available_copies: copies };
};

exports.getBookById = async (id) => {
  const [rows] = await db.execute(
    "SELECT * FROM books WHERE id = ?",
    [id]
  );
  return rows[0];
};

exports.searchBooks = async (search) => {
  const term = `%${search.toLowerCase()}%`;
  const [rows] = await db.execute(
    `SELECT * FROM books 
     WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?`,
    [term, term]
  );
  return rows;
};

exports.isBookAvailable = async (book_id) => {
  const [rows] = await db.execute(
    "SELECT available_copies FROM books WHERE id = ? AND available_copies > 0",
    [book_id]
  );
  return rows.length > 0;
};

exports.updateBook = async (id, updates) => {
  const { copies, available_copies } = updates;
  await db.execute(
    "UPDATE books SET copies = ?, available_copies = ?, updated_at = NOW() WHERE id = ?",
    [copies, available_copies, id]
  );
  return exports.getBookById(id);
};

exports.deleteBook = async (id) => {
  await db.execute("DELETE FROM books WHERE id = ?", [id]);
};
