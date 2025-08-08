import java.util.Objects;

public class Book {
    private final String title;
    private final String author;

    private Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public static Book of(String title, String author) {
        return new Book(title, author);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s", title, author);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book other)) return false;
        return title.equalsIgnoreCase(other.title) &&
                author.equalsIgnoreCase(other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }
}





// Library Management System (Console-Based)
//Description:
//A simple console-based library system where users can borrow and return books.
//
//Tech Stack:
//Java (Core Java, OOP concepts),
//File Handling (or SQLite for persistence),
//
//Features:
// 1. Najlepiej wczytać najpierw pliki File Handlerem !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//✅ Add books to the library
//✅ Borrow and return books
//✅ Search books by title or author
//✅ Save data in a file or database
//
// Add Book (author + title),
// 1a. Add Author
// Find Books (by author and title),
// Borrow Books,
// My borrowed books
// CLS albo coś (może scanner? xD)

//
//| Title | Author |
//|-----------------------------|----------------------|
//| To Kill a Mockingbird | Harper Lee |
//| 1984 | George Orwell |
//| The Great Gatsby | F. Scott Fitzgerald |
//| Pride and Prejudice | Jane Austen |
//| The Catcher in the Rye | J.D. Salinger |
//| Moby-Dick | Herman Melville |
//| War and Peace | Leo Tolstoy |
//| Crime and Punishment | Fyodor Dostoevsky |
//| The Hobbit | J.R.R. Tolkien |
//| The Lord of the Rings | J.R.R. Tolkien |
//| Brave New World | Aldous Huxley |
//| The Picture of Dorian Gray | Oscar Wilde |
//| Fahrenheit 451 | Ray Bradbury |
//| The Alchemist | Paulo Coelho |
//| The Book Thief | Markus Zusak |
//| The Chronicles of Narnia | C.S. Lewis |
//| Dracula | Bram Stoker |
//| Frankenstein | Mary Shelley |
//| The Hitchhiker’s Guide to the Galaxy | Douglas Adams |
//| The Shining | Stephen King |



// 1. O BOŻE, DODAWANIE KSIĄZEK DO BIBLIOTEKI XD PRZEZ UŻYTKOWNIKA ✅
// 2. Szukanie książek po autorze , ale nie szukanie pełnej nazwy ale po fragmencie czy to imię albo nazwisko ✅
// TOL , TOLKIEN , JRR, J.R.R. - CONTAINS metoda MOŻNA TO UŻYĆ ✅
// 3. 3 pod punkt, jeśli użyykownik wpiszę w tej liście ING (chodzi o końcówkę), to znajdźcie the lord of the rings albo stefana ringa - ciekawa opcja lol, albo google albo chatGPT (CONTAIN METODA... zerknąć :( ✅
// czyli jak znajdzie książkę albo autora z podobnymi koncówkami, to żeby wywaliło opcję numerowaną tych propozycji odnalezionych i wtedy, żeby użytkownik mógł sobie od razu wybrać i od razu wypożyczyć książkę ALBO ZWRÓCIĆ zamiasty wracać do menu ✅


// 4 punk search by author zakończyć na zwróceniu listy książek, które uwzględniają imię lub nazwisko autora - żeby był tytuł albo autor (suma dwóch zbiorów w tym przypadku albo autor albo i tytuł książki) , przekonwertowanie żęby w library zwracać książki , a jak nie znaleziono to nulle. ✅
// 5 podaj tytuł zwrotu książki (ZWRÓC KSIĄŻKĘ) ale żeby było numerowane albo po fragmencie tytułu ✅
//

// ✅ 1. Naprawić buga z wypożyczaniem ksiązek (albo usunąć ... ale można zostawić jedną i drugą, aby zostawić to samo w teorii) chodzi o to, że przy wypożycz książke jakbym mial 1 wynik zwrotny, i jakbym wpisał striktre jeden tytuł, to aplikacja powinna się zapytać czy chcą ją wypozyczyć a użytkownik musi wpisać TAK albo NIE, a jakby było więcej, to mogłoyby być takie same działanie jak przy szukaj książek
// ✅ 2. Wyszukać w necie jak overrideo'wać hashcode'a - to będzie przygotowanie dla set.
//   ✅   a). overridowanie'a metody hashcode w klasie "book",
/*   ✅   b). zamienić listy "books" i "borrowedBooks" na set.
      ✅  c). zrefraktorować metody, które zwracają te listy na set
      ✅  (set uniemożliwia dodawnie 2x tej samej danej, nie mam duplikatorów, ale niestety też przez to, że nie pozwala na duplikatory te dane mogą być nie pokolei)

      d) doprecyzować i stosować się do jednego z "podejść"

   3. Mieć zachowany model/view/controler, gdzie controler zarządza przebiegiem aplikacji na modelu (na bibliotece i ksiązkach) i jednocześnie jest też "view", który formatuje informację zwrotną dla użyktownika (np. w mainie, while true to to będzie w controlerze wszystko i widok może być klasą statyczną bo będę w metodadch tego widoku przekazywał obiekt i na podstawie tego obiektu będzie mi zwracać informację zwrotną)
        a). https://www.geeksforgeeks.org/system-design/mvc-design-pattern/
        -- Skrócić kod, żeby nie było monolitów
        b). main nie jest kontrolerem! || zrobić w folderze src(??), view, controler, model
        c). klasy "book" i "library" przerzucić do pakietu model
        d). stworzyć klasy "book view" i "library view" z metodami, które będą wyświetlać jego dane (każda metoda ma zwracać stringa), w klasach view mam mieć klasę statyczną, czyli wszystkie metody zawarte w niej są statyczne i ta klasa się przeważnie że onas nie posiada żadnych pól zewnętrznych, chyba że jest głębsza logika.
        e). wyoddrebnic logikę z maina i w pakicie "controler" stworzyć klasę "library controler". W tej kklasie znajdize się logika zarządzania tą aplikacją, czyli np. bedę miał metodę library "cottroler" z metodą "run" i w tej metodzie "run", która jest publiczna dla aplikacji będę miał tą całą swoją pętlę aplikacji tak na serio,
   4, ✅Refraktor kodu, postarać się wyodrębnić metody konkretne pojedyńcze.
   a). ✅string format... metoda format z klasy string, do każdego println...
   b). ✅tak i nie, żeby było w jakiejś stałej, ze stringiem np. żeby nie było magicznych cyfr i stringów wystarczy, że w hentajkach mamy stringi <3 :3 xD
   c). ✅rozwój to czas
   ✅np. metoda "while true" metoda loop, chodzi o to żeby mieć w tej pętli "switch choice 1", żeby to treafiło do jednej metody, "borrowBook", 2 return book, itp itd...
 */

