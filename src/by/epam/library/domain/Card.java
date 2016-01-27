package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class Card extends Entity {
    private String author;
    private String title;
    private String isbn;
    private int yearPublication;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Card card = (Card) o;

        if (yearPublication != card.yearPublication) return false;
        if (!author.equals(card.author)) return false;
        if (!title.equals(card.title)) return false;
        return isbn.equals(card.isbn);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + yearPublication;
        return result;
    }
}
