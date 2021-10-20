package de.enviado.akkuvita.domain.entity;

import de.enviado.akkuvita.server.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Kunde {
    @Id
    @Column(name = "CUSTOMERID")
    @NotNull
    private Integer kundennummer;

    @Column(name="NAME")
    private String name;

    @Column(name = "ORGANISATION")
    private String firma;

    @NotNull
    @DecimalMin("0")
    @Column(name = "VERSION")
    private Integer version = 0;

    public Kunde(){

    }

    public Kunde(Kunde copyFrom){
        this();
        this.copyFrom(copyFrom);
    }

    private void copyFrom(Kunde copyFrom) {
        this.kundennummer = copyFrom.kundennummer;
        this.name = copyFrom.name;
        this.firma = copyFrom.firma;
        this.version = copyFrom.version;
    }

    public Integer getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(Integer kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    /**
     * Required by {@link de.enviado.akkuvita.shared.AkkuVitaRequestFactory}
     * Delegates via {@link Akku#getSeriennummer()} }
     * @return {@link Kunde#kundennummer}
     */
    public Integer getId() {
        return this.getKundennummer();
    }

    /**
     * Required by {@link de.enviado.akkuvita.shared.AkkuVitaRequestFactory}
     * Delegates via {@link Kunde#setKundennummer(Integer)} ()} }
     */
    public void setId(Integer id) {
        this.setKundennummer(id);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void persist() {
        Logger logger = Logger.getLogger("KundenLogger");
        logger.log(Level.INFO, "Persist called on " + this.toString());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(this);
            session.getTransaction().commit();
        }
    }

    public static Kunde findKunde(Integer id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Kunde kunde = session.load(Kunde.class,id);
            session.getTransaction().commit();
            return kunde;
        }
    }


    public static List<Kunde> findKunden(int start, int length){
        String hql = "from Kunde a order by a.kundennummer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery(hql,Kunde.class);
            query.setFirstResult(start);
            query.setMaxResults(length);
            @SuppressWarnings("Unchecked")
            List<Kunde> result = (List<Kunde>)query.getResultList();
            session.getTransaction().commit();
            return result;
        }
    }

    public static List<Kunde> findAllKunden(){
        String hql = "from Kunde kunde order by kunde.kundennummer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery(hql,Kunde.class);
            @SuppressWarnings("Unchecked")
            List<Kunde> result = (List<Kunde>)query.getResultList();
            session.getTransaction().commit();
            return result;
        }
    }

    public static Integer sieben(){
        return 7;
    }
}
