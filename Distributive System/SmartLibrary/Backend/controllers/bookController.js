const BookModel = require("../models/bookModel");

exports.createBook = async (req, res) => {
  try {
    const { title, author, isbn, copies } = req.body;
    const book = await BookModel.createBook({ title, author, isbn, copies });
    res.status(201).json(book);
  } catch (error) {
    console.error("Error creating book:", error);
    res.status(500).json({ message: "Failed to create book" });
  }
};

exports.getBookById = async (req, res) => {
  try {
    const bookId = req.params.id;
    const book = await BookModel.getBookById(bookId);
    if (!book) {
      return res.status(404).json({ message: "Book not found" });
    }
    res.json(book);
  } catch (error) {
    console.error("Error fetching book:", error);
    res.status(500).json({ message: "Failed to fetch book" });
  }
};

exports.searchBooks = async (req, res) => {
  try {
    const search = req.query.search;
    const books = await BookModel.searchBooks(search);
    res.json(books);
  } catch (error) {
    console.error("Error searching books:", error);
    res.status(500).json({ message: "Failed to search books" });
  }
};

exports.updateBook = async (req, res) => {
  try {
    const bookId = req.params.id;
    const updates = req.body;
    const updatedBook = await BookModel.updateBook(bookId, updates);
    if (!updatedBook) {
      return res.status(404).json({ message: "Book not found" });
    }
    res.json(updatedBook);
  } catch (error) {
    console.error("Error updating book:", error);
    res.status(500).json({ message: "Failed to update book" });
  }
};

exports.deleteBook = async (req, res) => {
  try {
    const bookId = req.params.id;
    await BookModel.deleteBook(bookId);
    res.status(204).send();
  } catch (error) {
    console.error("Error deleting book:", error);
    res.status(500).json({ message: "Failed to delete book" });
  }
};
