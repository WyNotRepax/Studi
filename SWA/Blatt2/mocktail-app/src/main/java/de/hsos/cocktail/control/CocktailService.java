package de.hsos.cocktail.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.hsos.cocktail.entity.Cocktail;
import de.hsos.cocktail.entity.CocktailConverter;
import de.hsos.cocktail.gateway.CocktailGateway;

@ApplicationScoped
public class CocktailService {
    
    @Inject
    @RestClient
    CocktailGateway cocktailGateway;

    @Inject
    CocktailConverter cocktailConverter;

    public Cocktail getById(long id) {

        List<Cocktail> cocktails = this.cocktailConverter.convert(this.cocktailGateway.getById(id));

        if(cocktails.size() != 1) {
            return null;
        }

        return cocktails.get(0);
    }

    public List<Cocktail> getByName(String name) {

       return this.cocktailConverter.convert(this.cocktailGateway.getByName(name));

    }

    public List<Cocktail> getByIngredient(String ingredient) {

        return this.cocktailConverter.convert(this.cocktailGateway.getByIngredient(ingredient));

    }

}
