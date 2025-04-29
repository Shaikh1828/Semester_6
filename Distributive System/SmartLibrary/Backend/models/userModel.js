const db = require("../config/db");

exports.createUser = async ({ name, email, role }) => {
  const [result] = await db.execute(
    "INSERT INTO users (name, email, role) VALUES (?, ?, ?)",
    [name, email, role]
  );
  return { id: result.insertId, name, email, role };
};

exports.getUserById = async (id) => {
  const [rows] = await db.execute(
    "SELECT id, name, email, role FROM users WHERE id = ?",
    [id]
  );
  return rows[0];
};
