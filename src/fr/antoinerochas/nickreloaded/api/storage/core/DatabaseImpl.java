package fr.antoinerochas.nickreloaded.api.storage.core;

import fr.antoinerochas.nickreloaded.api.storage.mysql.Table;

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
