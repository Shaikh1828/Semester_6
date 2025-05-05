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

exports.isValidUser = async (user_id) => {
  const [rows] = await db.execute("SELECT id FROM users WHERE id = ?", [user_id]);
  return rows.length > 0;
};

exports.updateUser = async (id, userData) => {
  const { name, email, role } = userData;
  const [result] = await db.execute(
    "UPDATE users SET name = ?, email = ?, role = ? WHERE id = ?",
    [name, email, role, id]
  );
  if (result.affectedRows === 0) {
    return null; 
  }

  const [rows] = await db.execute("SELECT * FROM users WHERE id = ?", [id]);
  return rows[0];
};
