package by.epam.library.validator;

import java.util.HashMap;
import java.util.Map;

import by.epam.library.domain.*;
import by.epam.library.domain.User;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Фабрика валидаторов
 */
public class ValidatorFactory {
    private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

    static {
        validators.put(Card.class, CardValidator.class);
        validators.put(Book.class, BookValidator.class);
        validators.put(User.class, UserValidator.class);
    }

    /**
     * Создание валидатора
     * @param entity сущность
     * @param <Type>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <Type extends Entity> Validator<Type> createValidator(Class<Type> entity) {
        try {
            return (Validator<Type>) validators.get(entity).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
