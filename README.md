# EcosystemApp

EcosystemApp to aplikacja w Javie wykorzystująca JavaFX, służąca do symulacji świata organizmów w środowisku 2D. Projekt jest edukacyjny i pozwala na ćwiczenie programowania obiektowego, współbieżności oraz tworzenia GUI w JavaFX.

## Funkcjonalności

- Tworzenie dynamicznego świata 2D z organizmami.
- Każdy organizm posiada własny cykl życia i może wykonywać akcje, takie jak poruszanie się.
- Automatyczne odświeżanie planszy przy użyciu Timeline w JavaFX.
- Różne typy organizmów mogą mieć własny wygląd (awatar) i kolor pola.
- Symulacja interakcji organizmów z planszą i między sobą.

## Technologie

- Java 17+
- JavaFX
- Gradle
- UUID do unikalnej identyfikacji organizmów

## Struktura projektu i opis klas

`World`

- Reprezentuje świat 2D.
- Przechowuje tablicę board z identyfikatorami organizmów (UUID).
- Zarządza listą organizmów.
- Odpowiada za ruch organizmów i interakcje między nimi.

`Organism`

- Abstrakcyjna klasa bazowa dla wszystkich organizmów.
- Posiada pola x, y i UUID id.
- Metoda updateLocation aktualizuje pozycję organizmu w świecie.
- Każdy organizm może być ruchliwy (isMoveable) i reagować na otoczenie.

`HelloController`

- Kontroler JavaFX.
- Zarządza widokiem planszy (GridPane) i odświeża ją przy każdej turze.
- Odpowiada za inicjalizację świata i organizmów.
- Uruchamia Timeline do automatycznego odświeżania planszy co określony czas.

Plansza wyświetlana jest jako siatka (GridPane), a każdy organizm jest reprezentowany przez Label z awatarem i kolorem.
