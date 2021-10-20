package de.hsos.swa.reederei.flottenmanagement.control.events;



public class AuftragAngenommen {
    public final String schiffUrl;
    public final long auftragId;

    public AuftragAngenommen(String schiffUrl, long auftragId) {
        this.schiffUrl = schiffUrl;
        this.auftragId = auftragId;
    }

}
