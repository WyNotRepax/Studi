package de.hsos.mocktail.search.control;

import de.hsos.mocktail.entity.Mocktail;
import de.hsos.mocktail.gateway.MocktailRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

@Singleton
public class SearchService implements SearchList, SearchId {

    @Inject
    MocktailRepository mocktailRepository;

    @Inject
    Logger logger;

    @Override
    public Mocktail[] getMocktails() {
        logger.trace("getMocktails called");
        return this.mocktailRepository.getAll().toArray(new Mocktail[0]);
    }

    @Override
    public Mocktail getMocktail(long id) {
        logger.tracef("getMocktail %d called",id);
        return this.mocktailRepository.get(id);
    }

}
