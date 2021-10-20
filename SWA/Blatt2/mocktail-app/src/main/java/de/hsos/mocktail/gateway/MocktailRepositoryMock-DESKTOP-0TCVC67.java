package de.hsos.mocktail.gateway;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import de.hsos.mocktail.entity.Mocktail;

@ApplicationScoped
public class MocktailRepositoryMock implements MocktailRepository {


    Logger logger;

    private int nextId = 0;

    private Map<Long, Mocktail> mocktails;

    @Inject
    public MocktailRepositoryMock(Logger logger){
        this.logger = logger;
        logger.trace("MocktailRepositoryMock constructor started");
        this.mocktails = Collections.synchronizedMap(new HashMap<>());
        this.initMockData();
        logger.trace("MocktailRepositoryMock constructor ended");
    }

    public void initMockData(){
        this.create(new Mocktail("name", "rezept", "zutaten", "autor"));
        this.create(new Mocktail("name1", "rezept1", "zutaten1", "autor1"));
        this.create(new Mocktail("name2", "rezept2", "zutaten2", "autor2"));
    }

    @Override
    public Collection<Mocktail> getAll() {
        logger.trace("getAll called");
        return this.mocktails.values();
    }

    @Override
    public Mocktail get(long id) {
        logger.tracef("get(%d) called",id);
        return this.mocktails.get(id);
    }

    @Override
    public boolean create(Mocktail mocktail) {
        logger.tracef("create(%s) started",mocktail.toString());
        if (mocktail.getId() != -1) {
            return false;
        }
        mocktail.setId(this.nextId++);
        this.mocktails.put(mocktail.getId(), mocktail);
        logger.trace("create(...) ended");
        return true;
    }

    @Override
    public boolean update(Mocktail mocktail) {
        logger.tracef("update(%s) started",mocktail.toString());
        if(!this.mocktails.containsKey(mocktail.getId())){
            return false;
        }
        this.mocktails.put(mocktail.getId(), mocktail);
        logger.trace("update(...) ended");
        return true;
    }

    @Override
    public boolean delete(long mocktailId) {
        logger.tracef("delete(%d) started",mocktailId);
        if (!this.mocktails.containsKey(mocktailId)) {
            return false;
        }
        this.mocktails.remove(mocktailId);
        logger.trace("delete(...) ended");
        return true;
    }

}
