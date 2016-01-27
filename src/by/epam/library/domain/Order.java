package by.epam.library.domain;

import java.util.Date;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class Order extends Entity {
    private Book book;
    private User user;
    private User librarian;
    private Date datePlannedReturn;
    private Date dateActualReturn;
    private Date dateIssue;
    private boolean isReadingRoom;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public Date getDatePlannedReturn() {
        return datePlannedReturn;
    }

    public void setDatePlannedReturn(Date datePlannedReturn) {
        this.datePlannedReturn = datePlannedReturn;
    }

    public Date getDateActualReturn() {
        return dateActualReturn;
    }

    public void setDateActualReturn(Date dateActualReturn) {
        this.dateActualReturn = dateActualReturn;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public boolean isReadingRoom() {
        return isReadingRoom;
    }

    public void setIsReadingRoom(boolean isReadingRoom) {
        this.isReadingRoom = isReadingRoom;
    }

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
