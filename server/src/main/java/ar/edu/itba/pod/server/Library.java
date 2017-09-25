package ar.edu.itba.pod.server;


import ar.edu.itba.pod.Author;
import ar.edu.itba.pod.Book;
import ar.edu.itba.pod.LibraryService;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements LibraryService {


    private HashMap<String, Book> bookStore = new HashMap<>();
    private volatile HashMap<String, Integer> amountStore = new HashMap<>();

    public Library(Collection<String[]> books){

        books.forEach(arr -> {
            Book book = new Book(arr[1], arr[2], LocalDate.now(), new Author(arr[4], arr[5]));
            bookStore.put(book.getIsbn(), book);
            amountStore.put(book.getIsbn(), Integer.valueOf(arr[0]));
        });
    }

    @Override
    public List<String> listBooks() throws RemoteException {
        return bookStore.values().stream().map(book -> book.getIsbn() + "-" + book.getName()).collect(Collectors.toList());
    }

    @Override
    public synchronized Book lendBook(String isbn) throws RemoteException {
        if (!bookStore.containsKey(isbn)){
            throw new RemoteException();
        }

        int amount = amountStore.get(isbn);
        if (amount > 0) {
            amountStore.put(isbn, amount - 1);
            return bookStore.get(isbn);
        } else {
            return null;
        }
    }

    @Override
    public synchronized void returnBook(Book bookToReturn) throws RemoteException {
        System.out.println("Book " + bookToReturn + " returned");
        if (!bookStore.containsKey(bookToReturn.getIsbn())){
            throw new RemoteException();
        }

        amountStore.put(bookToReturn.getIsbn(), amountStore.get(bookToReturn.getIsbn()) + 1);
    }
}
