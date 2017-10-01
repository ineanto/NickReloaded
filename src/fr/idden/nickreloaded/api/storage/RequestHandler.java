package fr.idden.nickreloaded.api.storage;

import fr.idden.nickreloaded.api.storage.impl.DatabaseImpl;
import fr.idden.nickreloaded.api.storage.mysql.Field;
import fr.idden.nickreloaded.api.storage.mysql.Method;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler
{

    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseImpl database;

    public RequestHandler(DatabaseImpl database)
    {
        this.database = database;
    }

    public void executeUpdate(String query)
    {
        if(database.isConnected())
        {
            try
            {
                PreparedStatement sts = database.getConnection().prepareStatement(query);
                sts.executeUpdate();
                sts.close();
            }
            catch (SQLException e)
            {
                logger.log(Level.SEVERE,
                           e.getMessage(),
                           e);
            }
        }
    }

    /**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The object to getInstance.
     * @return The find object.
     */
    public Object select(String query, String get)
    {
        if(database.isConnected())
        {
            Object request = null;

            try
            {
                PreparedStatement sts = database.getConnection().prepareStatement(query);
                ResultSet result = sts.executeQuery();
                while (result.next())
                {
                    request = result.getObject(get);
                }

                sts.close();
            }
            catch (SQLException e)
            {
                logger.log(Level.SEVERE,
                           e.getMessage(),
                           e);
            }

            return request;
        }

        return null;
    }

    /**
     * Read a list of values with MySQL query.
     *
     * @param query The MySQL written query.
     * @param get   The field to read.
     * @return Returns found List.
     */
    public List<Object> selectList(String query, String get)
    {
        if(database.isConnected())
        {
            List<Object> request = new ArrayList();

            try
            {
                PreparedStatement sts = database.getConnection().prepareStatement(query);
                ResultSet result = sts.executeQuery();
                while (result.next())
                    request.add(result.getObject(get));

                sts.close();
            }
            catch (SQLException e)
            {
                logger.log(Level.SEVERE,
                           e.getMessage(),
                           e);
            }
            return request;
        }

        return null;
    }

    /**
     * Retrieves the string parsed from the list of where values.
     *
     * @param where The list of where values.
     * @return The parsed string.
     */
    public String getListOfWhereValues(List<Field> where)
    {
        StringBuilder whereFields = new StringBuilder();
        where.forEach(whereField -> whereFields.append(whereField.getParsedNameAndValue()).append(Method.AND));

        return whereFields.toString().substring(0,
                                                whereFields.length() - 4);
    }

}
