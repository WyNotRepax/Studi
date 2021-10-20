package de.enviado.akkuvita.domain.entity;

import de.enviado.akkuvita.server.HibernateUtil;
import de.enviado.akkuvita.shared.AkkuDefekt;
import org.hibernate.Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AkkuPruefungsEvent extends AkkuEvent {

    @Column(name = "NOTE")
    private String notiz;

    @Column(name = "CAPACITY")
    private Float kapazitaet;

    @Column(name = "TICKET")
    private Integer ticketnr;

    @Column(name = "CYCLES")
    private Integer ladezyklen;

    @Column(name = "FAULT")
    @Enumerated(EnumType.STRING)
    private AkkuDefekt defekt;

    @ManyToOne
    @NotNull
    @JoinColumn(name="CUSTOMERID")
    private Kunde kunde;

    public AkkuPruefungsEvent() {
        super();
    }

    public AkkuPruefungsEvent(AkkuPruefungsEvent copyFrom){
        this.copyFrom(copyFrom);
    }

    protected void copyFrom(AkkuPruefungsEvent copyFrom) {
        super.copyFrom(copyFrom);
        this.kapazitaet = copyFrom.kapazitaet;
        this.notiz = copyFrom.notiz;
        this.ticketnr = copyFrom.ticketnr;
        this.ladezyklen = copyFrom.ladezyklen;
        this.defekt = copyFrom.defekt;
        this.kunde = copyFrom.kunde;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public Float getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(Float kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public Integer getTicketnr() {
        return ticketnr;
    }

    public void setTicketnr(Integer ticketnr) {
        this.ticketnr = ticketnr;
    }

    public Integer getLadezyklen() {
        return ladezyklen;
    }

    public void setLadezyklen(Integer ladezyklen) {
        this.ladezyklen = ladezyklen;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public static AkkuEvent findAkkuPruefungsEvent(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            AkkuEvent akkuEvent = session.find(AkkuPruefungsEvent.class,id);
            session.getTransaction().commit();
            return akkuEvent;
        }
    }

    public AkkuDefekt getDefekt() {
        return defekt;
    }

    public void setDefekt(AkkuDefekt defekt) {
        this.defekt = defekt;
    }
}
