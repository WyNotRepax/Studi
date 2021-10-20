package de.hsos.swa.hibernateorm.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import de.hsos.swa.hibernateorm.entity.Gift;

@ApplicationScoped
public class SantaClausService {
    @Inject
    Logger log;

    @Inject
    EntityManager em;

    @Transactional
    public void createGift(String giftDescription) {
        Gift gift = new Gift();
        gift.setName(giftDescription);
        em.persist(gift);
    }

    public Gift getGifts(long id) {
        return em.find(Gift.class, id);
    }

    public List<Gift> getAllGifts() {
        return em.createQuery("from Gift",Gift.class).getResultList();
    }

    public List<Gift> getAllGifts(String giftDescription) {
        return em.createQuery("from Gift gift where gift.name=:abc",Gift.class).setParameter("abc", giftDescription).getResultList();
    }
}