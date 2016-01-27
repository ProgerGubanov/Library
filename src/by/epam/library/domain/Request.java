package by.epam.library.domain;

import java.util.Date;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Класс заявка
 */
public class Request extends Entity {
    private Card card;
    private User user;
    private Date dateRequest;
    private boolean isReadingRoom;

    /**
     * Получение карточки книги
     *
     * @return Card карточка
     */
    public Card getCard() {
        return card;
    }

    /**
     * Установка карточки
     *
     * @param card карточка
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * Получение пользователя
     *
     * @return User пользователь
     */
    public User getUser() {
        return user;
    }

    /**
     * Установка пользователя
     *
     * @param user пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Получение даты заявки
     *
     * @return Date заявка
     */
    public Date getDateRequest() {
        return dateRequest;
    }

    /**
     * Установка даты заявки
     *
     * @param dateRequest дата заявки
     */
    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    /**
     * Определение признака читального зала
     *
     * @return boolean если true - читальный зал, иначе - абонемент
     */
    public boolean isReadingRoom() {
        return isReadingRoom;
    }

    /**
     * Установка признака читального зала
     *
     * @param isReadingRoom
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

        Request request = (Request) o;

        if (isReadingRoom != request.isReadingRoom) return false;
        if (!card.equals(request.card)) return false;
        if (!user.equals(request.user)) return false;
        return dateRequest.equals(request.dateRequest);

    }

    /**
     * Переопределение hashCode()
     *
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        int result = card.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + dateRequest.hashCode();
        result = 31 * result + (isReadingRoom ? 1 : 0);
        return result;
    }
}
