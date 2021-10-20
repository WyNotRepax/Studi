package de.hsos.swa.pizza4me.control;

import java.util.Collection;

import de.hsos.swa.pizza4me.entity.Pizza;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
public interface PizzaService {
    Collection<Pizza> getPizzas();

    Pizza getPizza(long id);

    Pizza createPizza(String name, String beschreibung, String preis);
}
