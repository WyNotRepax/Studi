package de.hsos.swa.ssa.katalogVerwalten.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.DBProperties;
import de.hsos.swa.ssa.utils.Geld;

public class WareDAOImpl implements WareDAO {

    private Connection connection;

    private static final String TABLE_NAME = "ware";
    private static final String WARENNUMER_COLUMN_NAME = "warennummer";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String PREIS_COLUMN_NAME = "preis";
    private static final String BESCHREIBUNG_COLUMN_NAME = "beschreibung";

    private static final String CREATE_STRING = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES(?, ?, ?, ?)", TABLE_NAME,
            WARENNUMER_COLUMN_NAME, NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME);

    private static final String FIND_STRING = String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s.%s=?",
            WARENNUMER_COLUMN_NAME, NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME, TABLE_NAME,
            TABLE_NAME, WARENNUMER_COLUMN_NAME);

    private static final String UPDATE_STRING = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            TABLE_NAME, WARENNUMER_COLUMN_NAME, NAME_COLUMN_NAME, PREIS_COLUMN_NAME, BESCHREIBUNG_COLUMN_NAME,
            WARENNUMER_COLUMN_NAME);

    private static final String DELETE_STRING = String.format("DELETE FROM %s WHERE %s=?", TABLE_NAME,
            WARENNUMER_COLUMN_NAME);

    WareDAOImpl() {
        try {
            this.connection = DriverManager.getConnection(DBProperties.getURL());
            this.connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean create(Ware ware) {
        try (PreparedStatement statement = this.prepareCreateStatement(ware)) {
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException exception) {
            System.err.println(exception);
            System.err.println(exception.getSQLState());
            return false;
        }
    }

    private PreparedStatement prepareCreateStatement(Ware ware) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_STRING);
        statement.setLong(1, ware.getWarennummer());
        statement.setString(2, ware.getName());
        statement.setDouble(3, ware.getPreis().getValue());
        statement.setString(4, ware.getBeschreibung());
        return statement;
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
            }
            connection.commit();
            return ware;
        } catch (SQLException exception) {
            System.err.println(exception.getSQLState());
            return null;
        }
    }

    private PreparedStatement prepareFindStatement(long warennummer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_STRING);
        statement.setLong(1, warennummer);
        return statement;
    }


    @Override
    public boolean update(Ware oldWare, Ware newWare) {
        try (PreparedStatement statement = this.prepareUpdateStatement(oldWare,newWare)) {
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    private PreparedStatement prepareUpdateStatement(Ware oldWare,Ware newWare) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_STRING);
        statement.setLong(1, newWare.getWarennummer());
        statement.setString(2, newWare.getName());
        statement.setDouble(3, newWare.getPreis().getValue());
        statement.setString(4, newWare.getBeschreibung());
        statement.setLong(5, oldWare.getWarennummer());
        return statement;
    }

    @Override
    public boolean delete(Ware ware) {
        try (PreparedStatement statement = this.prepareDeleteStatement(ware)) {
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    private PreparedStatement prepareDeleteStatement(Ware ware) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_STRING);
        statement.setLong(1, ware.getWarennummer());
        return statement;
    }
}
