package fr.antoinerochas.nickreloaded.core.storage.core;

import fr.antoinerochas.nickreloaded.core.storage.mysql.Table;

import java.sql.Connection;

public interface DatabaseImpl
{
    void connect();

    void close();

    Connection getConnection();

    void setConnection(Connection connection);

    boolean isConnected();

    Table getTable(String name);
}
