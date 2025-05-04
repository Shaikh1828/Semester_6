const LoanModel = require("../models/loanModel");

exports.issueLoan = async (req, res) => {
  try {
    const { user_id, book_id, due_date } = req.body;
    const loan = await LoanModel.issueLoan({ user_id, book_id, due_date });
    res.status(201).json(loan);
  } catch (error) {
    console.error("Error issuing loan:", error);
    res.status(500).json({ message: "Failed to issue loan" });
  }
};

exports.returnLoan = async (req, res) => {
  try {
    const { loan_id } = req.body;
    const loan = await LoanModel.returnLoan(loan_id);
    res.json(loan);
  } catch (error) {
    console.error("Error returning loan:", error);
    res.status(500).json({ message: "Failed to return loan" });
  }
};

exports.getLoansByUser = async (req, res) => {
  try {
    const userId = req.params.user_id;
    const loans = await LoanModel.getLoansByUser(userId);
    res.json(loans);
  } catch (error) {
    console.error("Error fetching loans:", error);
    res.status(500).json({ message: "Failed to fetch loans" });
  }
};

exports.getOverdueLoans = async (req, res) => {
  try {
    const overdueLoans = await LoanModel.getOverdueLoans();
    console.log(`Retrieved ${overdueLoans.length} overdue loans`);
    
    if (overdueLoans.length === 0) {
      console.log('No overdue loans found. Let\'s check database structure...');
    }
    
    res.json(overdueLoans);
  } catch (error) {
    console.error("Error fetching overdue loans:", error);
    res.status(500).json({ 
      message: "Failed to fetch overdue loans",
      error: error.message 
    });
  }
};



exports.extendLoan = async (req, res) => {
  try {
    const loanId = req.params.id;
    const { extension_days } = req.body;
    const extendedLoan = await LoanModel.extendLoan(loanId, extension_days);
    res.json(extendedLoan);
  } catch (error) {
    console.error("Error extending loan:", error);
    res.status(500).json({ message: "Failed to extend loan" });
  }
};

// Statistics controllers
exports.getPopularBooks = async (req, res) => {
  try {
    const popularBooks = await LoanModel.getPopularBooks();
    res.json(popularBooks);
  } catch (error) {
    console.error("Error fetching popular books:", error);
    res.status(500).json({ message: "Failed to fetch popular books" });
  }
};

exports.getActiveUsers = async (req, res) => {
  try {
    const activeUsers = await LoanModel.getActiveUsers();
    res.json(activeUsers);
  } catch (error) {
    console.error("Error fetching active users:", error);
    res.status(500).json({ message: "Failed to fetch active users" });
  }
};

exports.getSystemOverview = async (req, res) => {
  try {
    const overview = await LoanModel.getSystemOverview();
    res.json(overview);
  } catch (error) {
    console.error("Error fetching overview:", error);
    res.status(500).json({ message: "Failed to fetch overview" });
  }
};
