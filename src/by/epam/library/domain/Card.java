package by.epam.library.domain;

/**
 * Класс карточка книги
 *
 * @author Gubanov Andrey
 */
public class Card extends Entity {
    private String author;
    private String title;
    private String isbn;
    private int yearPublication;

    /**
     * Получение автора
     *
     * @return String автор
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Установка автора книги
     *
     * @param author автор
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Получение названия
     *
     * @return String название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Установка названия книги
     *
     * @param title название
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получение кода Isbn книги
     *
     * @return String код Isbn книги
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Установка кода Isbn книги
     *
     * @param isbn код Isbn книги
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Получение даты публикации (года)
     *
     * @return int дата публикации (года)
     */
    public int getYearPublication() {
        return yearPublication;
    }

    /**
     * Установка даты публикации (года)
     *
     * @param yearPublication дата публикации (год)
     */
    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    /**
     * Переопределение equals()
     *
     * @param o Object
     * @return boolean
     */
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

    /**
     * Переопределение hashCode()
     *
     * @return int hashCode
     */
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
