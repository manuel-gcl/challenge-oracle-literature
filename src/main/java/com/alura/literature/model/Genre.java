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

    private final String omdbEnglishGenre;
    private final String omdbSpanishGenre;


    Genre(String omdbEnglishGenre, String omdbSpanishGenre) {
        this.omdbEnglishGenre = omdbEnglishGenre;
        this.omdbSpanishGenre = omdbSpanishGenre;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.getOmdbEnglishGenre().equalsIgnoreCase(text) ||
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

        for (String item : bookshelves) {
            if (item.startsWith(BROWSING_PATTERN)) {
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

        if (returnGenres.isEmpty()) {
            returnGenres.add(UNKNOWN);
        }

        return returnGenres;
    }

    public String getOmdbEnglishGenre() {
        return omdbEnglishGenre;
    }

    public String getOmdbSpanishGenre() {
        return omdbSpanishGenre;
    }
}