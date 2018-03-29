package com.mamh.spring.demo.beans;

import javax.persistence.*;

@Entity
@Table(name = "sp_book", schema = "atguigu", catalog = "")
public class Book {
    private int id;
    private String bookName;
    private String isbn;
    private float price;
    private int stock;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_name", nullable = true, length = 4096)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 1024)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (Float.compare(book.price, price) != 0) return false;
        if (bookName != null ? !bookName.equals(book.bookName) : book.bookName != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;

        return true;
    }
}
