package by.epam.library.validator;

import by.epam.library.domain.Card;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.local.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

public class CardValidator implements Validator<Card> {
    public static final Pattern YEAR_PATTERN = Pattern.compile("^[1-9][0-9]{3}$");
    public static final Pattern ISBN_PATTERN = Pattern.compile("^(?:ISBN(?:-1[03])?:? )?(?=[-0-9 ]{17}$|[-0-9X ]{13}$|" +
            "[0-9X]{10}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?(?:[0-9]+[- ]?){2}[0-9X]$");

    @Override
    public Card validate(HttpServletRequest request) throws IncorrectFormDataException {
        Card card = new Card();
        setIdentityIfValid(request, card);
        setAuthorIfValid(request, card);
        setTitleBookIfValid(request, card);
        setIsbnBookIfValid(request, card);
        setYearPublicationBookIfValid(request, card);
        return card;
    }

    private void setIdentityIfValid(HttpServletRequest request, Card card) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                card.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.identity"), parameter);
            }
        }
    }

    private void setAuthorIfValid(HttpServletRequest request, Card card) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("author");
        if (parameter != null && !parameter.isEmpty()) {
            card.setAuthor(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.author"), parameter);
        }
    }

    private void setTitleBookIfValid(HttpServletRequest request, Card card) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("titlebook");
        if (parameter != null && !parameter.isEmpty()) {
            card.setTitle(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.titleBook"), parameter);
        }
    }

    private void setIsbnBookIfValid(HttpServletRequest request, Card card) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("isbn");
        if (parameter != null) {
            Matcher isbnMatcher = ISBN_PATTERN.matcher(parameter);
            if (!parameter.isEmpty() && isbnMatcher.matches()) {
                card.setIsbn(parameter);
            } else {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.isbn"), parameter);
            }
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.isbn"), null);
        }
    }

    private void setYearPublicationBookIfValid(HttpServletRequest request, Card card) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("yearPublication");
        if (parameter != null) {
            Matcher yearMatcher = YEAR_PATTERN.matcher(parameter);
            if (yearMatcher.matches()) {
                card.setYearPublication(Integer.parseInt(parameter));
            } else {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.yearPublication"), parameter);
            }
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.yearPublication"), null);
        }
    }


}
