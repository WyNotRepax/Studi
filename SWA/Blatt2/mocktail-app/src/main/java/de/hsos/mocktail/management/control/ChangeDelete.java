package de.hsos.mocktail.management.control;

import de.hsos.mocktail.entity.Mocktail;

/**
 * ChangeDelete
 */
public interface ChangeDelete {

    Mocktail changeMocktail(long id, String name, String rezept, String zutaten, String autor);

    boolean deleteMocktail(long id);
}