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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (!inventoryNumber.equals(book.inventoryNumber)) return false;
        if (bookStatus != book.bookStatus) return false;
        return card.equals(book.card);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + inventoryNumber.hashCode();
        result = 31 * result + bookStatus.hashCode();
        result = 31 * result + card.hashCode();
        return result;
    }
}
