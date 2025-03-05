package org.example.fuluppgift.models;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.time.LocalDate;

@Entity
@Table(name="BookLoans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name="borrowBook", referencedColumnName = "id")
    private Books books;

    @Column(name="borrowDate")
    private LocalDate borrowDate;

    @Column(name="returnDate")
    private LocalDate returnDate;

    @Column(name="returned")
    private boolean returned;


    public Loans(int id, Users users, Books books, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.users = users;
        this.books = books;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = false;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }

    public Books getBooks() {
        return books;
    }
    public void setBooks(Books books) {
        this.books = books;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }




}
