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
        setIdentityIfValid(request, user);
        setSurnameIfValid(request, user);
        setNameIfValid(request, user);
        setPatronymicIfValid(request, user);
        setSubscriptionIfValid(request, user);
        setAddressIfValid(request, user);
        setPhoneHomeIfValid(request, user);
        setPhoneMobileIfValid(request, user);
        setEmailIfValid(request, user);
        setLoginIfValid(request, user);
        setPasswordIfValid(request, user);
        setRoleIfValid(request, user);
        return user;
    }

    private void setIdentityIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("identity");
        if (parameter != null) {
            try {
                user.setIdentity(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.identity"), parameter);
            }
        }
    }

    private void setSurnameIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("surname");
        if (parameter != null && !parameter.isEmpty()) {
            user.setSurname(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.surname"), parameter);
        }
    }

    private void setNameIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            user.setName(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.name"), parameter);
        }
    }

    private void setPatronymicIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("patronymic");
        if (parameter != null && !parameter.isEmpty()) {
            user.setPatronymic(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.patronymic"), parameter);
        }
    }


    private void setSubscriptionIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("subscription");
        if (parameter != null) {
            user.setSubscription(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.subscription"), parameter);
        }
    }

    private void setAddressIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("address");
        if (parameter != null) {
            user.setAddress(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.address"), parameter);
        }
    }

    private void setPhoneHomeIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("phoneHome");
        if (parameter != null) {
            Matcher phoneMatcher = PHONE_PATTERN.matcher(parameter);
            if (phoneMatcher.matches() || parameter.isEmpty()) {
                user.setPhoneHome(parameter);
            } else {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneHome"), parameter);
            }
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneHome"), null);
        }
    }

    private void setPhoneMobileIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("phoneMobile");
        if (parameter != null) {
            Matcher mobilePhoneMatcher = MOBILE_PHONE_PATTERN.matcher(parameter);
            if (mobilePhoneMatcher.matches() || parameter.isEmpty()) {
                user.setPhoneMobile(parameter);
            } else {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneMobile"), parameter);
            }
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.phoneMobile"), null);
        }
    }

    private void setEmailIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("email");
        if (parameter != null) {
            Matcher emailMatcher = EMAIL_PATTERN.matcher(parameter);
            if (emailMatcher.matches() || parameter.isEmpty()) {
                user.setEmail(parameter);
            } else {
                throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.email"), parameter);
            }
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.email"), null);
        }
    }

    private void setLoginIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.login"), parameter);
        }
    }

    private void setPasswordIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("password");
        if (parameter != null) {
            user.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.password"), parameter);
        }
    }

    private void setRoleIfValid(HttpServletRequest request, User user) throws IncorrectFormDataException {
        String parameter;
        parameter = request.getParameter("role");
        try {
            user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectFormDataException(MessageManager.getInstance(request).getProperty("validator.role"), parameter);
        }
    }
}
