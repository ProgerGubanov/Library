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
}
