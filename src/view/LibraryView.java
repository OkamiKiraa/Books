package view;

import model.Book;
import java.util.Collection;

public class LibraryView {

    public static String getMenu() {
        return """
            MENU:
            1. Wypożycz książkę
            2. Zwróć książkę
            3. Szukaj książek
            4. Dodaj książkę do biblioteki
            5. Zakończ""";
    }

    public static String showBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            return "Brak książek spełniających kryteria.";
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for (Book book : books) {
            sb.append(index)
                    .append(". ")
                    .append(BookView.showBook(book)) // Wykorzystanie BookView : (
                    .append("\n");
            index++;
        }
        return sb.toString();
    }
}





// print menu nie jest library, tylko debilu do maina miałeś wrzucić -.-
// bookview może być wykorzystane w library view w showbooks, kolekcja jest literowalna i da się ją wyciągnąć,
// pozbądź się tego printLN , view ma mi zwrócić stringa - albo coś , żeby użytkownik mógł łatwo przerzucić, just STRINGS.
// zamiast przerzucić na array ,
// mam to naprawić wszystko. na bracha refactor maina.