package de.hsos.swa.pizza4me.gateway;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import de.hsos.swa.pizza4me.control.PizzaService;
import de.hsos.swa.pizza4me.entity.Pizza;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@RequestScoped
public class PizzaRepository implements PizzaService {

    @Override
    public Collection<Pizza> getPizzas() {
        return Pizza.findAll().list();
    }

    @Override
    public Pizza getPizza(long id) {
        return Pizza.findById(id);
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Pizza createPizza(String name, String beschreibung, String preis) {

        Pizza pizza = new Pizza(name, beschreibung, preis);

        pizza.persist();
        return pizza;
    }

}
