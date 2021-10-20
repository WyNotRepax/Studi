package de.enviado.akkuvita.domain.entity;


import de.enviado.akkuvita.server.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class AkkuAusmusterungsEvent extends AkkuEvent{
    @Column(name = "NOTE")
    private String notiz;

    public AkkuAusmusterungsEvent() {
        super();
    }

    public AkkuAusmusterungsEvent(AkkuAusmusterungsEvent copyFrom){
        this();
        this.copyFrom(copyFrom);
    }

    protected void copyFrom(AkkuAusmusterungsEvent copyFrom) {
        super.copyFrom(copyFrom);
        this.notiz = notiz;
    }
    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public static AkkuEvent findAkkuAusmusterungsEvent(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            AkkuEvent akkuEvent = session.find(AkkuAusmusterungsEvent.class,id);
            session.getTransaction().commit();
            return akkuEvent;
        }
    }

}
