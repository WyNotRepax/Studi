package de.enviado.akkuvita.domain.entity;


import com.google.gwt.dev.util.Pair;
import de.enviado.akkuvita.server.HibernateUtil;
import de.enviado.akkuvita.shared.AkkuDefekt;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "AKKU")
public class Akku implements Serializable {
    @Id
    @NotNull
    @Column(name = "SERIAL", unique = true)
    private String seriennummer;

    @NotNull
    @DecimalMin("0")
    @Column(name = "VERSION")
    private Integer version = 1;

    @Column(name = "PRODUCTION_DATE")
    private Date produktionsdatum;


    @Column(name = "REPAIR_COUNT")
    @DecimalMin("0")
    @NotNull
    @Fetch(FetchMode.JOIN)
    private Integer reperaturanzahl;


    public Akku() {
        this.reperaturanzahl = 0;
    }

    protected Akku(Akku copyFrom) {
        this();
        this.copyFrom(copyFrom);
    }

    public void copyFrom(Akku copyFrom) {
        this.seriennummer = copyFrom.seriennummer;
        this.version = copyFrom.version;
        this.produktionsdatum = copyFrom.produktionsdatum;
        this.reperaturanzahl = copyFrom.reperaturanzahl;
    }


    /**
     * The RequestFactory requires a static finder method for each proxied type.
     */
    public static Akku findAkku(String id) {
        //Logger logger = Logger.getLogger("FINDAKKU");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Akku akku = session.load(Akku.class, id);
            session.getTransaction().commit();
            //logger.log(Level.INFO, "findAkku called with id "+ id +" returning " + akku.toString());
            return akku;
        }
    }

    public void persist() {
        Logger.getLogger("perist").info("persist called on " + this.toString());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(this);
            session.getTransaction().commit();
        }
    }

    public String getSeriennummer() {
        return seriennummer;
    }

    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static Akku createAkku() {
        return new Akku();
    }

    public Date getProduktionsdatum() {
        return produktionsdatum;
    }

    public void setProduktionsdatum(Date produktionsdatum) {
        this.produktionsdatum = produktionsdatum;
    }

    public Integer getReperaturanzahl() {
        return reperaturanzahl;
    }

    public void setReperaturanzahl(Integer reperaturanzahl) {
        this.reperaturanzahl = reperaturanzahl;
    }

    /**
     * Required by {@link de.enviado.akkuvita.shared.AkkuVitaRequestFactory}
     * Delegates via {@link Akku#getSeriennummer()} }
     * @return {@link Akku#seriennummer}
     */
    public String getId() {
        return this.getSeriennummer();
    }

    /**
     * Required by {@link de.enviado.akkuvita.shared.AkkuVitaRequestFactory}
     * Delegates via {@link Akku#getSeriennummer()} }
     */
    public void setId(String id) {
        this.setSeriennummer(id);
    }

    public static List<Akku> findAllAkkus(){

        //Logger logger = Logger.getLogger("FINDAKKU");
        //logger.log(Level.INFO,"findAllAkkus called");
        String hql = "from Akku a order by a.seriennummer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery(hql,Akku.class);
            @SuppressWarnings("Unchecked")
            List<Akku> result = (List<Akku>)query.getResultList();
            session.getTransaction().commit();
            //logger.log(Level.INFO,"findAllAkkus returned " + result.size() + " Objects");
            return result;
        }
    }

    public static Integer sieben(){
        return 7;
    }

    public static List<Akku> findAkkus(int start, int length){


        String hql = "from Akku a order by a.seriennummer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery(hql,Akku.class);
            query.setFirstResult(start);
            query.setMaxResults(length);
            @SuppressWarnings("Unchecked")
            List<Akku> result = (List<Akku>)query.getResultList();
            session.getTransaction().commit();
            return result;
        }
    }
}
