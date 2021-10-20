package de.enviado.akkuvita.domain.entity;


import de.enviado.akkuvita.server.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AkkuReparaturEingangsEvent extends AkkuEvent{
    
    @Column(name = "NOTE")
    private String notiz;

    public AkkuReparaturEingangsEvent() {
        super();
    }

    public AkkuReparaturEingangsEvent(AkkuReparaturEingangsEvent copyFrom){
        this();
        this.copyFrom(copyFrom);
    }

    protected void copyFrom(AkkuReparaturEingangsEvent copyFrom){
        super.copyFrom(copyFrom);
        this.notiz = notiz;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public static AkkuEvent findReperaturEingangsEvent(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            AkkuEvent akkuEvent = session.find(AkkuReparaturEingangsEvent.class,id);
            session.getTransaction().commit();
            return akkuEvent;
        }
    }



}
