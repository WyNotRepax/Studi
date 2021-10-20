package de.hsos.mocktail.management.control;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

import de.hsos.mocktail.entity.Mocktail;
import de.hsos.mocktail.gateway.MocktailRepository;

/**
 * ManagementService
 */
@Singleton
public class ManagementService implements Create, ChangeDelete {

    @Inject
    MocktailRepository mocktails;

    @Inject
    Logger logger;

    @Override
    public Mocktail changeMocktail(long id, String name, String rezept, String zutaten, String autor) {
        logger.tracef("changeMocktail(%d,%s,%s,%s,%s) started", id, name, rezept, zutaten, autor);
        Mocktail mocktail = this.mocktails.get(id);
        if (mocktail == null) {
            logger.trace("No mocktail found!");
            logger.trace("changeMocktail(...) ended");
            return null;
        }
        if (name != null) {
            logger.trace("updating name");
            mocktail.setName(name);
        }
        if (rezept != null) {
            logger.trace("updating rezept");
            mocktail.setRezept(rezept);
        }
        if (zutaten != null) {
            logger.trace("updating zutaten");
            mocktail.setZutaten(zutaten);
        }
        if (autor != null) {
            logger.trace("updating autor");
            mocktail.setAutor(autor);
        }
        if (this.mocktails.update(mocktail)) {
            logger.trace("Mocktail updated");
            logger.trace("changeMocktail(...) ended");
            return mocktail;
        }
        logger.trace("Mocktail not updated");
        logger.trace("changeMocktail(...) ended");
        return null;
    }

    @Override
    public boolean deleteMocktail(long id) {
        logger.tracef("deleteMocktail(%d) called", id);
        return this.mocktails.delete(id);
    }

    @Override
    public Mocktail createMocktail(String name, String rezept, String zutaten, String autor) {
        logger.tracef("createMocktail(%s,%s,%s,%s) started", name, rezept, zutaten, autor);
        if (name == null || rezept == null || zutaten == null || autor == null) {
            logger.trace("createMocktail(...) ended");
            return null;
        }
        Mocktail mocktail = new Mocktail(name, rezept, zutaten, autor);
        if (this.mocktails.create(mocktail)) {
            logger.trace("Mocktail created");
            logger.trace("createMocktail(...) ended");
            return mocktail;
        }
        logger.trace("Mocktail not created");
        logger.trace("createMocktail(...) ended");
        return null;
    }

}