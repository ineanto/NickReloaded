package fr.idden.nickreloaded.api.storage.mysql;


import fr.idden.nickreloaded.api.storage.impl.DatabaseImpl;

import java.sql.*;

public class MySQLDatabase
        implements DatabaseImpl
{
    private Connection connection;
    private String host;
    private String user;
    private String password;

    private boolean connected;

    public MySQLDatabase(final String host, final int port, final String database, final String user, final String password)
    {
        this.host = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.user = user;
        this.password = password;
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
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            this.connection = DriverManager.getConnection(host,
                                                          user,
                                                          password);
            this.connected = true;
        }
        catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e)
        {
            e.printStackTrace();
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

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
                return exists;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                close();
            }
        }

        return false;
    }
}
