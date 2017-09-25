package ar.edu.itba.pod.client;

import ar.edu.itba.pod.Book;
import ar.edu.itba.pod.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws Exception{
        logger.info("library Client Starting ...");

        LibraryService library = (LibraryService) Naming.lookup("//0.0.0.0:1099/library");

        List<String> list = library.listBooks();
        for (String str: list){
            System.out.println(str);
        }

        ArrayList<Book> arr = new ArrayList<>();
        arr.add(library.lendBook("978-0307292063"));
        arr.add(library.lendBook("978-0307292063"));
        arr.add(library.lendBook("978-0307292063"));
        arr.add(library.lendBook("978-0307292063"));
        arr.add(library.lendBook("978-0307292063"));

        for (Book book: arr){
            library.returnBook(book);
        }

    }
}
