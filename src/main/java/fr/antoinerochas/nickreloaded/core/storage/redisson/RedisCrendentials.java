package fr.antoinerochas.nickreloaded.core.storage.redisson;

public class RedisCrendentials
{
    private String host, client, password;
    private int port;

    public RedisCrendentials(String host, String password, int port)
    {
        this.host = host;
        this.password = password;
        this.port = port;
    }

    public String toURI()
    {
        return "redis://" + host + ":" + port;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public String getClientName()
    {
        return client;
    }

    public String getPassword()
    {
        return password;
    }
}
