/*
 * Copyright (c) 2017-2018 Antoine "Idden" ROCHAS.
 * This work is under Creative Commons (CC) BY-NC-SA 2.0 License.
 * https://creativecommons.org/licenses/by-nc-sa/2.0/
 */

package io.idden.nickreloaded.version;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.idden.nickreloaded.version.impl.*;
import io.idden.nickreloaded.version.wrapper.VersionWrapper;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Define the NMS Wrapper to use.
 *
 * @author Wesley Smith (modified for NickReloaded purposes by Antoine "Idden" ROCHAS)
 * @since 2.0-rc1
 */
public enum NMSVersion
{
    ONE_EIGHT_R1("1_8_R1", Wrapper1_8_R1.class),

    ONE_EIGHT_R2("1_8_R2", Wrapper1_8_R2.class),

    ONE_EIGHT_R3("1_8_R3", Wrapper1_8_R3.class),

    ONE_NINE_R1("1_9_R1", Wrapper1_9_R1.class),

    ONE_NINE_R2("1_9_R2", Wrapper1_9_R2.class),

    //ONE_TEN_R1("1_10_R1", Wrapper1_10_R1.class), todo: do this one

    ONE_ELEVEN_R1("1_11_R1", Wrapper1_11_R1.class),

    ONE_TWELVE_R1("1_12_R1", Wrapper1_12_R1.class);

    private static final LoadingCache<Class<? extends VersionWrapper>, VersionWrapper> WRAPPER_CACHE = CacheBuilder.newBuilder().maximumSize(values().length).expireAfterWrite(3, TimeUnit.MINUTES).build(new CacheLoader<Class<? extends VersionWrapper>, VersionWrapper>()
    {
        @Override
        public VersionWrapper load(Class<? extends VersionWrapper> aClass)
                throws Exception
        {
            return aClass.newInstance();
        }
    });

    private final String                          pkg;
    private final Class<? extends VersionWrapper> wrapper;

    /**
     * Creates a new value Version value
     *
     * @param pkg     The package value of this NMS version
     * @param wrapper The {@link VersionWrapper} class for this NMS version
     */
    NMSVersion(String pkg, Class<? extends VersionWrapper> wrapper)
    {
        this.pkg = pkg;
        this.wrapper = wrapper;
    }

    /**
     * Finds the {@link net.wesjd.anvilgui.version.Version} from the NMS package value
     *
     * @param pkg The NMS package value
     *
     * @return The {@link net.wesjd.anvilgui.version.Version}, or null if no version is found
     */
    public static NMSVersion of(final String pkg)
    {
        return Arrays.stream(values()).filter(ver -> pkg.equals("v" + ver.getPackage())).findFirst().orElse(null);
    }

    /**
     * Gets the package value of this NMS version
     *
     * @return The package value
     */
    public String getPackage()
    {
        return pkg;
    }

    /**
     * Gets the {@link VersionWrapper} for this NMS version
     *
     * @return The {@link VersionWrapper} for this NMS version
     */
    public VersionWrapper getWrapper()
    {
        try
        {
            return WRAPPER_CACHE.get(wrapper);
        }
        catch (ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }
}
