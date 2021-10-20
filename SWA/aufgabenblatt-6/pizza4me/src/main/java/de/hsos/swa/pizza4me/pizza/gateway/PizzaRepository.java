package de.hsos.swa.pizza4me.pizza.gateway;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import de.hsos.swa.pizza4me.pizza.control.PizzaService;
import de.hsos.swa.pizza4me.pizza.entity.Pizza;

@ApplicationScoped
public class PizzaRepository implements PizzaService{

    @Override
    public Collection<Pizza> getPizzas() {
        return Pizza.findAll().list();
    }

    @Override
    public Pizza getPizza(long id) {
        return Pizza.findById(id);
    }

    @Override
    @Transactional
    public Pizza createPizza(String name, String beschreibung, String preis) {
        Pizza pizza = new Pizza(name, beschreibung, preis);
        pizza.persist();
        return pizza;
    }
    
    
}
