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
  const [rows] = await db.execute(
    `SELECT * FROM books WHERE title LIKE ? OR author LIKE ?`,
    [`%${search}%`, `%${search}%`]
  );
  return rows;
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
