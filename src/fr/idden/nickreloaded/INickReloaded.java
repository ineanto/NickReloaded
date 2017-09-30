package fr.idden.nickreloaded;

import fr.idden.nickreloaded.api.manager.NickManager;
import fr.idden.nickreloaded.api.manager.PayloadManager;
import fr.idden.nickreloaded.api.manager.StorageManager;

public interface INickReloaded
{
    StorageManager getStorageManager();

    NickManager getNickManager();

    PayloadManager getPayloadManager();
}
