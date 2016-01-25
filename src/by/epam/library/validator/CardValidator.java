package by.epam.library.validator;

import by.epam.library.domain.Card;
import by.epam.library.exception.IncorrectFormDataException;

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
        String parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                card.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("identity", parameter);
            }
        }
        parameter = request.getParameter("author");
        if (parameter != null && !parameter.isEmpty()) {
            card.setAuthor(parameter);
        } else {
            throw new IncorrectFormDataException("author", parameter);
        }
        parameter = request.getParameter("titlebook");
        if (parameter != null && !parameter.isEmpty()) {
            card.setTitle(parameter);
        } else {
            throw new IncorrectFormDataException("title", parameter);
        }
        parameter = request.getParameter("isbn");
        Matcher isbnMatcher = ISBN_PATTERN.matcher(parameter);
        if (parameter != null && !parameter.isEmpty() && isbnMatcher.matches()) {
            card.setIsbn(parameter);
        } else {
            throw new IncorrectFormDataException("isbn", parameter);
        }
        parameter = request.getParameter("yearPublication");
        Matcher yearMatcher = YEAR_PATTERN.matcher(parameter);
        if (parameter != null && yearMatcher.matches()) {
            card.setYearPublication(Integer.parseInt(parameter));
        } else {
            throw new IncorrectFormDataException("yearPublication", parameter);
        }
        return card;
    }

}
