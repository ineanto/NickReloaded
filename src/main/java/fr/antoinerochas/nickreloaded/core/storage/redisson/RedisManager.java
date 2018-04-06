package fr.antoinerochas.nickreloaded.core.storage.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

public class RedisManager
{
    public static RedisManager INSTANCE;
    private final RedissonClient redissonClient;

    public RedisManager(RedisCrendentials crendentials)
    {
        INSTANCE = this;
        this.redissonClient = initRedisson(crendentials);
    }

    public void close()
    {
        RedisManager.INSTANCE.getRedissonClient().shutdown();
    }

    public RedissonClient initRedisson(RedisCrendentials crendentials)
    {
        final Config config = new Config();

        config.setCodec(new JsonJacksonCodec());
        //config.setUseLinuxNativeEpoll(true); ONLY IF LINUX
        config.setThreads(16); //CPU number * 2
        config.setNettyThreads(16);
        config.useSingleServer()
              .setAddress(crendentials.toURI())
              .setPassword(crendentials.getPassword())
              .setDatabase(0);

        return Redisson.create(config);
    }

    public RedissonClient getRedissonClient()
    {
        return redissonClient;
    }
}
