package fr.idden.nickreloaded.api.storage.sqlite;

import fr.idden.nickreloaded.NickReloaded;
import fr.idden.nickreloaded.api.storage.impl.DatabaseImpl;
import fr.idden.nickreloaded.api.storage.mysql.Table;

import java.sql.*;

public class SQLiteDatabase
        implements DatabaseImpl
{
    private Connection connection;
    private String host;

    private boolean connected;

    public SQLiteDatabase(final String database)
    {
        this.host = "jdbc:sqlite:./plugins/NickReloaded/" + database + ".db";
    }

    @Override
    public Table getTable(String name)
    {
        return new Table(this,
                         name);
    }

    @Override
    public void connect()
    {
        if (connected)
        {
            return;
        }

        try
        {
            Class.forName("org.sqlite.JDBC").newInstance();

            this.connection = DriverManager.getConnection(host);
            this.connected = true;
        }
        catch (SQLException | IllegalAccessException | InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            NickReloaded.disable("§cTried to load SQLite driver, but he hasn't been found. Disabling.");
        }
    }

    @Override
    public void close()
    {
        if (! connected)
        {
            return;
        }

        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection()
    {
        return connection;
    }

    @Override
    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public boolean isConnected()
    {
        return connected;
    }

    @Override
    public boolean tableExists(String name)
    {
        if(connected)
        {
            try
            {
                boolean exists;
                DatabaseMetaData meta = getConnection().getMetaData();
                ResultSet res = meta.getTables(null,
                                               null,
                                               name,
                                               new String[] {"TABLE"});
                exists = res.next();

                res.close();
                close();
                return exists;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }
}
