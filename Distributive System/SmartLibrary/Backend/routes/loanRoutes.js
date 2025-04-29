const express = require("express");
const router = express.Router();
const loanController = require("../controllers/loanController");

router.post("/loans", loanController.issueLoan);
router.post("/returns", loanController.returnLoan);
router.get("/loans/:user_id", loanController.getLoansByUser);
router.get("/loans/overdue", loanController.getOverdueLoans);
router.put("/loans/:id/extend", loanController.extendLoan);

// Stats
router.get("/stats/books/popular", loanController.getPopularBooks);
router.get("/stats/users/active", loanController.getActiveUsers);
router.get("/stats/overview", loanController.getSystemOverview);

module.exports = router;
