package com.alura.literature.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Genre {
    ADVENTURE("Adventure", "Aventura"),
    ANIMALS("Animals", "Animales"),
    ART("Art", "Arte"),
    CHILDREN("Children", "Infantil"),
    CLASSICS("Classics", "Clásicos"),
    COOKING("Cooking", "Cocina"),
    ECONOMICS("Economics", "Economía"),
    EDUCATION("Education", "Educación"),
    ESSAY("Essay", "Ensayo"),
    FANTASY("Fantasy", "Fantasía"),
    GUIDE("Guide", "Guía"),
    HISTORY("History", "Historia"),
    HORROR("Horror", "Terror"),
    HUMOR("Humor", "Humor"),
    LAW("Law", "Derecho"),
    MEDICINE("Medicine", "Medicina"),
    MUSIC("Music", "Música"),
    MYSTERY("Mystery", "Misterio"),
    NATURE("Nature", "Naturaleza"),
    PHILOSOPHY("Philosophy", "Filosofía"),
    POETRY("Poetry", "Poesía"),
    POLITICS("Politics", "Política"),
    PSYCHOLOGY("Psychology", "Psicología"),
    RELIGION("Religion", "Religión"),
    SCIENCE("Science", "Ciencia"),
    SCIENCE_FICTION("Science Fiction", "Ciencia Ficción"),
    SELF_HELP("Self-Help", "Autoayuda"),
    SOCIETY("Society", "Sociedad"),
    SPORTS("Sports", "Deportes"),
    TECHNOLOGY("Technology", "Tecnología"),
    THRILLER("Thriller", "Suspenso"),
    TRAVEL("Travel", "Viajes"),
    WAR("War", "Guerra"),
    WESTERN("Western", "Occidental"),
    YOUNG_ADULT("Young Adult", "Juvenil"),
    FICTION("Fiction", "Ficción"),
    UNKNOWN("Unknonw", "Desconocido");

    private final String ombdEnglishGenre;
    private final String omdbSpanishGenre;


    Genre(String ombdGenre, String omdbSpanishGenre) {
        this.ombdEnglishGenre = ombdGenre;
        this.omdbSpanishGenre = omdbSpanishGenre;
    }

    public String getOmbdEnglishGenre() {
        return ombdEnglishGenre;
    }

    public String getOmdbSpanishGenre() {
        return omdbSpanishGenre;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.getOmbdEnglishGenre().equalsIgnoreCase(text) ||
                    genre.getOmdbSpanishGenre().equalsIgnoreCase(text)) {
                return genre;
            }
        }
        return UNKNOWN;
    }

    public static List<Genre> filterAndGetGenresFromStrings(List<String> bookshelves) {
        final String BROWSING_PATTERN = "Browsing: ";
        List<String> stringGenres = new ArrayList<>();
        List<Genre> returnGenres = new ArrayList<>();
        System.out.println("bookshelves");
        System.out.println(bookshelves);
        for (String item : bookshelves) {
            if (item.startsWith(BROWSING_PATTERN)){
                item = item.replace(BROWSING_PATTERN, "");
            }
            stringGenres.addAll(Arrays.asList(item.split("/")));
        }

        for (String genre : stringGenres) {
            Genre expectedGenre = fromString(genre);
            if (!returnGenres.contains(expectedGenre) && !expectedGenre.equals(UNKNOWN)) {
                returnGenres.add(expectedGenre);
            }
        }

        if (returnGenres.isEmpty()){
            returnGenres.add(UNKNOWN);
        }

        return returnGenres;
    }
}












//
//
//
//
//
//
//
//    public static Genre fromString(List<String> text) {
//        Genre retGenre;
//        List<String> genres = filterGenresFromString(text);
//
//        for (Genre genre : Genre.values()) {
//            if (genre.ombdEnglishGenre.toLowerCase().contains(text.toLowerCase())) {
//                retGenre = genre;
//            } else if (genre.omdbSpanishGenre.toLowerCase().contains(text.toLowerCase())) {
//                retGenre = genre;
//            }
//        }
//        System.out.println("Genre don't found in DB: '" + text + "'");
//
//        return retGenre;
//    }
//
//    public static List<String> filterGenresFromString(List<String> bookshelves) {
//        List<String> stringGenres = new ArrayList<>();
//        for (String item : bookshelves) {
//            // Verifica si el elemento contiene "Browsing"
//            if (!item.startsWith("Browsing:")) {
//                // Divide el elemento por "/", en caso de que haya subgéneros
//                stringGenres.addAll(Arrays.asList(item.split("/")));
//            }
//        }
//}
