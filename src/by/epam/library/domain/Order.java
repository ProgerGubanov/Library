package by.epam.library.domain;

import java.util.Date;

/**
 * Класс заказ (выданные книги)
 *
 * @author Gubanov Andrey
 */
public class Order extends Entity {
    private Book book;
    private User user;
    private User librarian;
    private Date datePlannedReturn;
    private Date dateActualReturn;
    private Date dateIssue;
    private boolean isReadingRoom;

    /**
     * Получение книги
     *
     * @return book книга
     */
    public Book getBook() {
        return book;
    }

    /**
     * Установка книги
     *
     * @param book книга
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Получение читаталя
     *
     * @return user читатель
     */
    public User getUser() {
        return user;
    }

    /**
     * Установка читателя
     *
     * @param user читатель
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получение библиотекаря
     *
     * @return user библиотекарь
     */
    public User getLibrarian() {
        return librarian;
    }

    /**
     * Установка библиотекаря
     *
     * @param librarian библиотекарь
     */
    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    /**
     * Получение даты планируемого возврата книги
     *
     * @return Date дата планируемого возврата
     */
    public Date getDatePlannedReturn() {
        return datePlannedReturn;
    }

    /**
     * Установка даты планируемого возврата книги
     *
     * @param datePlannedReturn дата планируемого возврата книги
     */
    public void setDatePlannedReturn(Date datePlannedReturn) {
        this.datePlannedReturn = datePlannedReturn;
    }

    /**
     * Получение даты возврата книги
     *
     * @return Date дата возврата книги
     */
    public Date getDateActualReturn() {
        return dateActualReturn;
    }

    /**
     * Установка даты возврата книги
     *
     * @param dateActualReturn дата возврата книги
     */
    public void setDateActualReturn(Date dateActualReturn) {
        this.dateActualReturn = dateActualReturn;
    }

    /**
     * Получение даты выдачи книги
     *
     * @return Date дата выдачи книги
     */
    public Date getDateIssue() {
        return dateIssue;
    }

    /**
     * Установка даты выдачи книги
     *
     * @param dateIssue дата выдачи книги
     */
    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    /**
     * Получение признака читального зала
     *
     * @return boolean если true - читальный зал, иначе - абонемент
     */
    public boolean isReadingRoom() {
        return isReadingRoom;
    }

    /**
     * Установка признака читального зала
     *
     * @param isReadingRoom если true - читальный зал, иначе - абонемент
     */
    public void setIsReadingRoom(boolean isReadingRoom) {
        this.isReadingRoom = isReadingRoom;
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

        Order order = (Order) o;

        if (isReadingRoom != order.isReadingRoom) return false;
        if (!book.equals(order.book)) return false;
        if (!user.equals(order.user)) return false;
        if (!librarian.equals(order.librarian)) return false;
        if (!datePlannedReturn.equals(order.datePlannedReturn)) return false;
        if (dateActualReturn != null ? !dateActualReturn.equals(order.dateActualReturn) : order.dateActualReturn != null)
            return false;
        return dateIssue.equals(order.dateIssue);

    }

    /**
     * Переопределение hashCode()
     *
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + librarian.hashCode();
        result = 31 * result + datePlannedReturn.hashCode();
        result = 31 * result + (dateActualReturn != null ? dateActualReturn.hashCode() : 0);
        result = 31 * result + dateIssue.hashCode();
        result = 31 * result + (isReadingRoom ? 1 : 0);
        return result;
    }
}
