package by.epam.library.validator;

import javax.servlet.http.HttpServletRequest;

import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.local.MessageManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class UserValidator implements Validator<User> {

    public static final Pattern EMAIL_PATTERN = Pattern.compile
            ("[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org))");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\d[\\d\\(\\)\\ -]{4,14}\\d$");
    public static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile
            ("^((8|0|((\\+|00)\\d{1,2}))[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

    @Override
    public User validate(HttpServletRequest request) throws IncorrectFormDataException {
        User user = new User();
        String parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                user.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.identity"), parameter);
            }
        }
        parameter = request.getParameter("surname");
        if (parameter != null && !parameter.isEmpty()) {
            user.setSurname(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.surname"), parameter);
        }
        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            user.setName(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.name"), parameter);
        }
        parameter = request.getParameter("patronymic");
        if (parameter != null && !parameter.isEmpty()) {
            user.setPatronymic(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.patronymic"), parameter);
        }
        parameter = request.getParameter("subscription");
        if (parameter != null) {
            user.setSubscription(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.subscription"), parameter);
        }
        parameter = request.getParameter("address");
        if (parameter != null) {
            user.setAddress(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.address"), parameter);
        }
        parameter = request.getParameter("phoneHome");
        Matcher phoneMatcher = PHONE_PATTERN.matcher(parameter);
        if (parameter != null && (phoneMatcher.matches() || parameter.isEmpty())) {
            user.setPhoneHome(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneHome"), parameter);
        }
        parameter = request.getParameter("phoneMobile");
        Matcher mobilePhoneMatcher = MOBILE_PHONE_PATTERN.matcher(parameter);
        if (parameter != null && (mobilePhoneMatcher.matches() || parameter.isEmpty())) {
            user.setPhoneMobile(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneMobile"), parameter);
        }
        parameter = request.getParameter("email");
        Matcher emailMatcher = EMAIL_PATTERN.matcher(parameter);
        if (parameter != null && (emailMatcher.matches() || parameter.isEmpty())) {
            user.setEmail(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.email"), parameter);
        }
        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.login"), parameter);
        }
        parameter = request.getParameter("password");
        if (parameter != null) {
            user.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.password"), parameter);
        }
        parameter = request.getParameter("role");
        try {
            user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.role"), parameter);
        }
        return user;
    }
}
