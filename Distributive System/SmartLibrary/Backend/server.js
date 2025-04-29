const express = require("express");
require('dotenv').config();
const app = express();
const userRoutes = require("./routes/userRoutes");
const bookRoutes = require("./routes/bookRoutes");
const loanRoutes = require("./routes/loanRoutes");

// Middleware
app.use(express.json());

// Routes
app.use("/api", userRoutes);
app.use("/api", bookRoutes);
app.use("/api", loanRoutes);

// Server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});