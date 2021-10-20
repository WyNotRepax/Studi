package de.enviado.akkuvita.domain.entity;


import de.enviado.akkuvita.server.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AkkuReparaturAusgangsEvent extends AkkuEvent{

    @Column(name = "NOTE")
    private String notiz;

    public AkkuReparaturAusgangsEvent() {
        super();
    }

    public AkkuReparaturAusgangsEvent(AkkuReparaturAusgangsEvent copyFrom){
        this();
        this.copyFrom(copyFrom);
    }

    protected void copyFrom(AkkuReparaturAusgangsEvent copyFrom){
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
            AkkuEvent akkuEvent = session.find(AkkuReparaturAusgangsEvent.class,id);
            session.getTransaction().commit();
            return akkuEvent;
        }
    }



}
