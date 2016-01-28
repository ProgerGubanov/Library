package by.epam.library.domain;

/**
 * Класс книга
 *
 * @author Gubanov Andrey
 */
public class Book extends Entity {
    private String inventoryNumber;
    private BookStatus bookStatus;
    private Card card;

    /**
     * Получение инвентарного номера
     *
     * @return String инвентарный номер
     */
    public String getInventoryNumber() {
        return inventoryNumber;
    }

    /**
     * Установка инвентарного номера
     *
     * @param inventoryNumber инвентарный номер
     */
    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    /**
     * Получение статуса книги
     *
     * @return BookStatus статус книги
     */
    public BookStatus getBookStatus() {
        return bookStatus;
    }

    /**
     * Установка статуса книги
     *
     * @param bookStatus статус книги
     */
    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    /**
     * Получение карточки книги
     *
     * @return Card карточка
     */
    public Card getCard() {
        return card;
    }

    /**
     * Установка карточки книги
     *
     * @param card карточка
     */
    public void setCard(Card card) {
        this.card = card;
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

        Book book = (Book) o;

        if (!inventoryNumber.equals(book.inventoryNumber)) return false;
        if (bookStatus != book.bookStatus) return false;
        return card.equals(book.card);

    }

    /**
     * Переопределение hashCode()
     *
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + inventoryNumber.hashCode();
        result = 31 * result + bookStatus.hashCode();
        result = 31 * result + card.hashCode();
        return result;
    }
}
