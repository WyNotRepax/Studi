package de.hsos.swa.ssa.suchen.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.DBProperties;
import de.hsos.swa.ssa.utils.Geld;

public class WareDAOImpl implements WareDAO {

    private Connection connection;

    private static final String TABLE_NAME = "ware";
    private static final String WARENNUMER_COLUMN_NAME = "warennummer";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String PREIS_COLUMN_NAME = "preis";
    private static final String BESCHREIBUNG_COLUMN_NAME = "beschreibung";

    private static final String FIND_BY_ID_STRING = String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s.%s=?",
            WARENNUMER_COLUMN_NAME, NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME, TABLE_NAME,
            TABLE_NAME, WARENNUMER_COLUMN_NAME);

    private static final String FIND_BY_NAME_STRING = String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s.%s=?",
            WARENNUMER_COLUMN_NAME, NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME, TABLE_NAME,
            TABLE_NAME, NAME_COLUMN_NAME);

    private static final String FIND_BY_NAME_LIKE_STRING = String.format(
            "SELECT %s, %s, %s, %s FROM %s WHERE LOWER(%s.%s) LIKE LOWER(?) ESCAPE '!'", WARENNUMER_COLUMN_NAME,
            NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME, TABLE_NAME, TABLE_NAME, NAME_COLUMN_NAME);

    WareDAOImpl() {
        try {
            this.connection = DriverManager.getConnection(DBProperties.getURL());
            this.connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Ware find(long warennummer) {
        try (PreparedStatement statement = this.prepareFindStatement(warennummer)) {
            ResultSet resultSet = statement.executeQuery();
            Ware ware = null;
            if (resultSet.next()) {
                ware = new Ware(resultSet.getLong(WARENNUMER_COLUMN_NAME), resultSet.getString(NAME_COLUMN_NAME),
                        new Geld(resultSet.getDouble(PREIS_COLUMN_NAME)),
                        resultSet.getString(BESCHREIBUNG_COLUMN_NAME));
                System.out.println(ware.toString());
            }
            connection.commit();
            return ware;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    private PreparedStatement prepareFindStatement(long warennummer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_STRING);
        statement.setLong(1, warennummer);
        return statement;
    }

    @Override
    public Ware[] find(String warenname) {

        try (PreparedStatement statement = this.prepareFindStatement(warenname)) {
            ResultSet resultSet = statement.executeQuery();
            List<Ware> warenListe = new ArrayList<Ware>();
            while (resultSet.next()) {
                Ware ware = new Ware(resultSet.getLong(WARENNUMER_COLUMN_NAME), resultSet.getString(NAME_COLUMN_NAME),
                        new Geld(resultSet.getDouble(PREIS_COLUMN_NAME)),
                        resultSet.getString(BESCHREIBUNG_COLUMN_NAME));
                warenListe.add(ware);
            }
            connection.commit();
            return warenListe.toArray(new Ware[0]);
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    private PreparedStatement prepareFindStatement(String warenname) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_STRING);
        statement.setString(1, warenname);
        return statement;

    }

    @Override
    public Ware[] findLike(String warenname) {
        try (PreparedStatement statement = this.prepareFindLikeStatement(warenname)) {
            ResultSet resultSet = statement.executeQuery();
            List<Ware> warenListe = new ArrayList<Ware>();
            while (resultSet.next()) {
                Ware ware = new Ware(resultSet.getLong(WARENNUMER_COLUMN_NAME), resultSet.getString(NAME_COLUMN_NAME),
                        new Geld(resultSet.getDouble(PREIS_COLUMN_NAME)),
                        resultSet.getString(BESCHREIBUNG_COLUMN_NAME));
                warenListe.add(ware);
            }
            connection.commit();
            return warenListe.toArray(new Ware[0]);
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    private PreparedStatement prepareFindLikeStatement(String warenname) throws SQLException {
        // Escape wildcards
        warenname = "%" + warenname.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![") + "%";
        PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_LIKE_STRING);
        statement.setString(1, warenname);
        return statement;

    }
}
