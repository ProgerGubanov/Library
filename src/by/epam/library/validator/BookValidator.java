package by.epam.library.validator;

import by.epam.library.domain.Book;
import by.epam.library.domain.BookStatus;
import by.epam.library.domain.Card;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.local.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

public class BookValidator implements Validator<Book> {
    @Override
    public Book validate(HttpServletRequest request) throws IncorrectFormDataException {
        Book book = new Book();
        setIdentityIfValid(request, book);
        setInventoryNumberIfValid(request, book);
        setIdentityCardNumberIfValid(request, book);
        setIdBookStatusNumberIfValid(request, book);
        return book;
    }

    private void setIdentityIfValid(HttpServletRequest request, Book book) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                book.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.identity"), parameter);
            }
        }
    }

    private void setInventoryNumberIfValid(HttpServletRequest request, Book book) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("inventoryNumber");
        if (parameter != null && !parameter.isEmpty()) {
            book.setInventoryNumber(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.inventoryNumber"), parameter);
        }
    }

    private void setIdentityCardNumberIfValid(HttpServletRequest request, Book book) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("identityCard");
        if (parameter != null) {
            Card card = new Card();
            card.setIdentity(Integer.parseInt(parameter));
            book.setCard(card);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.identityCard"), parameter);
        }
    }

    private void setIdBookStatusNumberIfValid(HttpServletRequest request, Book book) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("idBookStatus");
        if (parameter != null) {
            book.setBookStatus(BookStatus.getByIdentity(Integer.parseInt(parameter)));
        } else {
            book.setBookStatus(BookStatus.INLIBRARY);
        }
    }
}
