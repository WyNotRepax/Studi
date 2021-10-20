package de.hsos.swa.hibernateormpanache.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import de.hsos.swa.hibernateormpanache.entity.Gift;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class SantaClausService {
    @Inject
    Logger log;

    @Transactional
    public void createGift(String giftDescription) {
        Gift gift = new Gift();
        gift.setName(giftDescription);
        Gift.persist(gift);
    }

    public Gift getGifts(long id) {
        return Gift.findById(id);
    }

    public List<Gift> getAllGifts() {
        return Gift.findAll().list();
    }

    public List<Gift> getAllGifts(String giftDescription) {
        return Gift.find("from Gift gift where gift.name=:abc", Parameters.with("abc", giftDescription)).list();
    }
}