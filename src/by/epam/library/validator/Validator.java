package by.epam.library.validator;

import javax.servlet.http.HttpServletRequest;

import by.epam.library.domain.Entity;
import by.epam.library.exception.IncorrectFormDataException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Интерфейс валидатора по проверке вводимых данных
 *
 * @param <Type> сущность
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
