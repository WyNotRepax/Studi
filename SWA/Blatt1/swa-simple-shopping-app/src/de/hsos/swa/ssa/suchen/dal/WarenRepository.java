package de.hsos.swa.ssa.suchen.dal;

import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.SuchAlgorithmus;
import de.hsos.swa.ssa.suchen.bl.Ware;


public class WarenRepository implements Katalog {

    private WarenSuche suchAlgorithmus;
    private WareDAO wareDAO;
    private ProduktinformationDao produktinformationDao;

    public WarenRepository() {
        this.wareDAO = new WareDAOImpl();
        this.produktinformationDao = new ProduktinformationDaoImpl();
        this.suchAlgorithmus = new KeywordMatching(wareDAO);
    }

    @Override
    public void legeSuchalgorithmusFest(SuchAlgorithmus algo) {
        switch (algo) {
        case KeywordMatching:
            this.suchAlgorithmus = new KeywordMatching(wareDAO);
            break;
        case SemanticMatching:
            this.suchAlgorithmus = new SemanticMatching();
            break;
        }

    }

    @Override
    public Ware[] suchen(String warenname) {
        return suchAlgorithmus.sucheWare(warenname);
    }

    @Override
    public Ware suchen(long warennummer) {
        return wareDAO.find(warennummer);
    }

    @Override
    public Produktinformation[] holeProduktinformationen(Ware ware) {
        return produktinformationDao.find(ware);
    }

}
