package revisao_oop.models;

import java.util.Objects;

public class Book implements Model {
    public final String title;
    public final String author;
    public final String year;

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public boolean mEquals(Model o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && year.equals(book.year);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    @Override
    public String toPrettyString() {
        return "Livro: " + title + " " + author + " " + year;
    }


}