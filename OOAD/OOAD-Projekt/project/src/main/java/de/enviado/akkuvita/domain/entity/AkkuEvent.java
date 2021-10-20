package de.enviado.akkuvita.domain.entity;

import de.enviado.akkuvita.server.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class AkkuEvent {
    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Column(name = "DATE")
    private Date date;

    @DecimalMin("0")
    @Column(name = "VERSION")
    private Integer version = 0;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SERIAL")
    protected Akku akku;

    public AkkuEvent() {
    }

    public AkkuEvent(AkkuEvent copyFrom) {
        this();
        this.copyFrom(copyFrom);
    }

    protected void copyFrom(AkkuEvent copyFrom) {
        this.id = copyFrom.id;
        this.date = copyFrom.date;
        this.version = copyFrom.version;
    }

    public void persist() {
        Logger logger = Logger.getLogger("AkkuLogger");
        logger.log(Level.INFO, "Persist called on " + this.toString());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            logger.log(Level.INFO, "1");
            session.saveOrUpdate(this.akku);
            logger.log(Level.INFO, "2");
            session.saveOrUpdate(this);
            logger.log(Level.INFO, "3");
            session.getTransaction().commit();
            logger.log(Level.INFO, "4");
        }
        logger.log(Level.INFO, "Persist finished on " + this.toString());
    }

    public static AkkuEvent findAkkuEvent(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            AkkuEvent akkuEvent = session.find(AkkuEvent.class, id);
            session.getTransaction().commit();
            return akkuEvent;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static Integer sieben() {
        return 7;
    }

    @Transactional
    public Akku getAkku() {
        return akku;
    }

    @Transactional
    public void setAkku(Akku akku) {
        this.akku = akku;
    }

    @SuppressWarnings("Unchecked")
    public static List<AkkuEvent> findAkkuEvent(Akku akku) {
        String hql = "FROM AkkuEvent event inner join fetch event.akku as akku where akku=?1";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(1,akku);
            List<AkkuEvent> result = (List<AkkuEvent>) query.getResultList();
            session.getTransaction().commit();
            return result;
        }
    }

    @SuppressWarnings("Unchecked")
    public static List<AkkuEvent> findAkkuEvent(Kunde akku) {
        String hql = "FROM AkkuEvent event inner join fetch event.kunde as kunde where kunde=?1";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(1,akku);
            List<AkkuEvent> result = (List<AkkuEvent>) query.getResultList();
            session.getTransaction().commit();
            return result;
        }
    }
}

