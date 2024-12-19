package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        bookDAO = new BookDAO();
        // Nettoyage avant chaque test
        bookService.deleteAllBooks();
    }

    @Test
    void testAddBook() {
        // Créer un livre à ajouter
        Book book = new Book(1, "Java Programming", "John Doe", "1234567890", 2024);

        // Ajouter le livre et vérifier le message de succès
        assertEquals("Livre inséré avec succès !", bookService.addBook(book));

        // Vérifier que le livre a bien été ajouté
        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(1).getTitle());
    }

    @Test
    void testUpdateBook() {
        // Ajouter un livre initial
        Book book = new Book(1, "Java Programming", "John Doe", "1234567890", 2024);
        assertEquals("Livre inséré avec succès !", bookService.addBook(book));

        // Mettre à jour le livre
        String msg = bookService.updateBook(new Book(1, "Advanced Java", "John Doe", "1234567890", 2024));

        // Vérifier la mise à jour du titre
        assertEquals("Advanced Java", bookDAO.getBookById(1).getTitle());
        assertEquals("Book updated successfully!", msg);
    }

    @Test
    void testDeleteBook() {
        // Ajouter un livre
        Book book = new Book(1, "Java Programming", "John Doe", "1234567890", 2024);
        assertEquals("Livre inséré avec succès !", bookService.addBook(book));

        // Supprimer le livre
        assertEquals("Book successfully deleted!", bookService.deleteBook(1));

        // Vérifier que le livre a bien été supprimé
        assertNull(bookDAO.getBookById(1));
    }
}
