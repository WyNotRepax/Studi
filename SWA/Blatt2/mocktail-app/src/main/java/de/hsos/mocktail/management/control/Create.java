package de.hsos.mocktail.management.control;

import de.hsos.mocktail.entity.Mocktail;

/**
 * Create
 */
public interface Create {

    Mocktail createMocktail(String name, String rezept, String zutaten, String autor);

}