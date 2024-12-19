package com.library.service;
import com.library.dao.BookDAO; // Importation de BookDAO
import com.library.dao.BorrowDAO;
import com.library.model.Book;   // Importation de Book
import java.util.List;


public class BookService {
    private BookDAO bookDAO;
    private BorrowDAO borrowDAO;

    // Constructeur qui initialise l'objet BookDAO
    public BookService() {
        bookDAO = new BookDAO();
        borrowDAO = new BorrowDAO();
    }

    // Ajouter un livre
    public String addBook(Book book) {
        return bookDAO.add(book);
    }

    // Afficher tous les livres
    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Trouver un livre par ID
    public Book findBookById(int id) {
        return bookDAO.getBookById(id);
    }

    // Supprimer un livre par ID
    public String deleteBook(int id) {
        return bookDAO.delete(id);
    }

    // Mise à jour des informations d'un livre
    public String updateBook(Book book) {
        return bookDAO.update(book);
    }

    // Supprimer tous les livres
    public void deleteAllBooks() {
        // Supprimer tous les emprunts
        borrowDAO.deleteAllBorrows();

        // Supprimer tous les livres
        bookDAO.deleteAllBooks();
    }
}