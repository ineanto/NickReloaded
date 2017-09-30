package fr.idden.nickreloaded.api.nms.throwable;

import fr.idden.nickreloaded.api.manager.PayloadManager;

public class PayloadUnsupportedVersionException
        extends Throwable
{
    public PayloadUnsupportedVersionException(PayloadManager.NMSModule module) { super("Unsupported version for " + module.name() + " module."); }
}
