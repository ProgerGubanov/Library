package by.epam.library.domain;

import java.util.Date;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class Request extends Entity {
    private Card card;
    private User user;
    private Date dateRequest;
    private boolean isReadingRoom;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public boolean isReadingRoom() {
        return isReadingRoom;
    }

    public void setIsReadingRoom(boolean isReadingRoom) {
        this.isReadingRoom = isReadingRoom;
    }
}
