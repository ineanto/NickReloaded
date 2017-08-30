package fr.idden.nickreloaded.api.nms.throwable;

import fr.idden.nickreloaded.api.nms.PayloadManager;

public class PayloadModuleUnsupportedVersionException
        extends Throwable
{
    private static PayloadManager.NMSModule module;

    public PayloadModuleUnsupportedVersionException(String version, PayloadManager.NMSModule module) { super(version); this.module = module; }

    public PayloadManager.NMSModule getModule()
    {
        return module;
    }
}
