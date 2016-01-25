package by.epam.library.validator;

import by.epam.library.domain.Book;
import by.epam.library.domain.BookStatus;
import by.epam.library.domain.Card;
import by.epam.library.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

public class BookValidator implements Validator<Book> {
    @Override
    public Book validate(HttpServletRequest request) throws IncorrectFormDataException {
        Book book = new Book();
        String parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                book.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("identity", parameter);
            }
        }
        parameter = request.getParameter("inventoryNumber");
        if (parameter != null && !parameter.isEmpty()) {
            book.setInventoryNumber(parameter);
        } else {
            throw new IncorrectFormDataException("inventoryNumber", parameter);
        }
        parameter = request.getParameter("identityCard");
        if (parameter != null) {
            Card card = new Card();
            card.setIdentity(Integer.parseInt(parameter));
            book.setCard(card);
        } else {
            throw new IncorrectFormDataException("identityCard", parameter);
        }
        parameter = request.getParameter("idBookStatus");
        if (parameter != null) {
            book.setBookStatus(BookStatus.getByIdentity(Integer.parseInt(parameter)));
        } else {
            book.setBookStatus(BookStatus.INLIBRARY);
        }
        return book;
    }

}
