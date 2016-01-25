package by.epam.library.validator;

import javax.servlet.http.HttpServletRequest;

import by.epam.library.domain.Entity;
import by.epam.library.exception.IncorrectFormDataException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface Validator<Type extends Entity> {
    Type validate(HttpServletRequest request) throws IncorrectFormDataException;
}
