package by.epam.library.validator;

import javax.servlet.http.HttpServletRequest;

import by.epam.library.domain.Entity;
import by.epam.library.exception.IncorrectFormDataException;

/**
 * Интерфейс валидатора по проверке вводимых данных
 *
 * @param <Type> сущность
 * @author Gubanov Andrey
 */
public interface Validator<Type extends Entity> {
    /**
     * Проверка значения
     *
     * @param request запрос
     * @return Type проверенный объект
     * @throws IncorrectFormDataException
     */
    Type validate(HttpServletRequest request) throws IncorrectFormDataException;
}
