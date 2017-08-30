package fr.idden.nickreloaded.api.storage.impl;

import java.util.UUID;

public interface StorageManagerImpl
{
    void load(UUID uuid);

    void save(UUID uuid);

    void createAccount(UUID uuid);

    boolean hasAccount(UUID uuid);

    void detectStorage();
}
