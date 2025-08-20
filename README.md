- dodano Model
- dodano Book
- dodano Library
- dodano View
- dodano BookView
- dodano Library View
- dodano Controller

Refaktoryzacja całego kodu.

//
- Refaktoryzacja ,poprawa struktury.
Klasa Main
- przejęcie kontroli nad główną pętlą programu,
- wywoływanie metod kontrolera i wyświetlanie wyników zwróconych przez kontroler/widok.

Klasa LibraryController
- usunięcie całej logiki związanej z interfejsem (brak Scanner i System.out).
- metody nie wyświetlają danych, lecz zwracają przetworzone wyniki (np. String z komunikatem, List<Book>).
- działa jako pośrednik między Main a Library.

LibraryView
- usunięcie metod drukujących na konsolę (zmiana printMenu na getMenu zwracające String).
- odpowiedzialność ograniczona wyłącznie do formatowania danych na tekst.

Inne zmiany
- Zastosowanie `stream().toList()`.
