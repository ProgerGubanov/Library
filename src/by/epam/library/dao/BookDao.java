package by.epam.library.dao;

import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface BookDao extends Dao<Book> {
    Book readByInventoryNumber(String inventoryNumber) throws PersistentException;

    List<Book> readByIdCard(int idCardInput) throws PersistentException;

    int countFreeBooks(int idCardInput) throws PersistentException;
}
