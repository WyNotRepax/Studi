package de.hsos.swa.ssa.suchen.dal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.io.Serializable;

import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.utils.DBProperties;

public class ProduktinformationDaoImpl implements ProduktinformationDao {

    private Connection connection;

    private static final String TABLE_NAME = "produktinformation";
    private static final String BEZEICHNUNG_COLUMN_NAME = "bezeichnung";
    private static final String INFORMATION_COLUMN_NAME = "information";
    private static final String WARENNUMMER_COLUMN_NAME = "warennummer";

    private static final String FIND_BY_WARENNUMMER_STRING = String.format("SELECT %s, %s FROM %s WHERE %s.%s=?",
            BEZEICHNUNG_COLUMN_NAME, INFORMATION_COLUMN_NAME, TABLE_NAME, TABLE_NAME, WARENNUMMER_COLUMN_NAME);

    ProduktinformationDaoImpl() {
        try {
            this.connection = DriverManager.getConnection(DBProperties.getURL());
            this.connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public Produktinformation[] find(Long warennummer) {
        try (PreparedStatement statement = this.prepareFindStatement(warennummer)) {
            ResultSet resultSet = statement.executeQuery();
            List<Produktinformation> produktinformationsListe = new ArrayList<Produktinformation>();
            while (resultSet.next()) {
                Serializable information = null;
                Blob blob = resultSet.getBlob(INFORMATION_COLUMN_NAME);
                
                if (blob != null) {
                    try (ObjectInputStream stream = new ObjectInputStream(blob.getBinaryStream())) {
                        information = (Serializable) stream.readObject();
                    } catch (IOException | ClassNotFoundException exception) {
                        information = null;
                    }
                }
                Produktinformation produktinformation = new Produktinformation(
                        resultSet.getString(BEZEICHNUNG_COLUMN_NAME), information);
                produktinformationsListe.add(produktinformation);
            }
            connection.commit();
            return produktinformationsListe.toArray(new Produktinformation[0]);
        } catch (SQLException exception) {
            System.out.println(FIND_BY_WARENNUMMER_STRING);
            System.err.println(exception.getMessage());
            return null;
        }
    }

    private PreparedStatement prepareFindStatement(Long warennummer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_WARENNUMMER_STRING);
        statement.setLong(1, warennummer);
        return statement;
    }

}
