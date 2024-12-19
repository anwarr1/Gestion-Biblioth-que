package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookDAO {
    private final Logger logger = Logger.getLogger(BookDAO.class.getName());

    // Ajouter un nouveau livre dans la base de données
    public String add(Book book) {
        String sql = "INSERT INTO books (id, title, author, publisher, year) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getYear());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Livre inséré avec succès !";
            }
        } catch (SQLException e) {
            logger.severe("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
        return "Erreur lors de l'ajout du livre !";
    }

    // Récupérer un livre par son ISBN
    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        Book book = null;

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
            }
        } catch (SQLException e) {
            logger.severe("Erreur lors de la récupération du livre : " + e.getMessage());
        }

        return book;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("year"));
                }
            }
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
        }
        return null;
    }

    public String delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return "Book successfully deleted!";
            }
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
        }
        return "Erreur lors de la suppression du livre !";
    }

    public String update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setInt(5, book.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return "Book updated successfully!";
            }
        } catch (SQLException e) {
            logger.severe("Error: " + e.getMessage());
        }
        return "Erreur lors de la mise à jour du livre !";
    }

    // Récupérer tous les livres
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Erreur lors de la récupération des livres : " + e.getMessage());
        }

        return books;
    }

    // Supprimer tous les livres
    public String deleteAll() {
        String sql = "DELETE FROM books";
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            return "Tous les livres ont été supprimés avec succès !";
        } catch (SQLException e) {
            logger.severe("Erreur lors de la suppression de tous les livres : " + e.getMessage());
            return "Erreur lors de la suppression de tous les livres : ";
        }
    }

    public void deleteAllBooks() {
        String sql = "DELETE FROM books";  // Requête SQL pour supprimer tous les livres

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Exécution de la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting all books: " + e.getMessage());
        }
    }

}