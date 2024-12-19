package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher; // Nouvelle colonne
    private int year; // Attribut mis à jour

    // Constructeur par défaut
    public Book() {
    }

    // Constructeur complet
    public Book(int id, String title, String author, String publisher, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year; // Mise à jour ici
    }

    // Constructeur sans id (utilisé pour l'insertion)
    public Book(String title, String author, String publisher, int year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year; // Mise à jour ici
    }

    // Constructeur additionnel si nécessaire (sans publisher)
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year; // Mise à jour ici
    }

    public void setYear(int year) {
        this.year = year; // Mise à jour ici
    }
}
