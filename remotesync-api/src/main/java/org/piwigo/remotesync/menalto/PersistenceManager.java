package org.piwigo.remotesync.menalto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager
{
    public static final String PERSISTENCE_UNIT_NAME = "gallery3";

    private final EntityManagerFactory myFactory;
    private final EntityManager myEntityManager;

    public PersistenceManager()
    {
        try
        {
            myFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            myEntityManager = myFactory.createEntityManager();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void shutdown()
    {
        if (myEntityManager != null && myEntityManager.isOpen())
        {
            myEntityManager.close();
        }

        if (myFactory != null && myFactory.isOpen())
        {
            myFactory.close();
        }
    }

    public EntityManager getEntityManager()
    {
        if (myEntityManager != null && myEntityManager.isOpen())
        {
            return myEntityManager;
        }
        throw new RuntimeException("No database connection for persistence unit: " + PERSISTENCE_UNIT_NAME);
    }
}
