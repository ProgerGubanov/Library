package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class Book extends Entity {
    private String inventoryNumber;
    private BookStatus bookStatus;
    private Card card;

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
