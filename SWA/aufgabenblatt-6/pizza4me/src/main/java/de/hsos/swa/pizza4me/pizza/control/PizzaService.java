package de.hsos.swa.pizza4me.pizza.control;

import java.util.Collection;

import de.hsos.swa.pizza4me.pizza.entity.Pizza;


public interface PizzaService {
    Collection<Pizza> getPizzas();

    Pizza getPizza(long id);

    Pizza createPizza(String name, String beschreibung, String preis);
}
