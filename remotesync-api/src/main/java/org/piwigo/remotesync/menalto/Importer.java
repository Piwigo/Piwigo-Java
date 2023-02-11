package org.piwigo.remotesync.menalto;

import jakarta.persistence.EntityManager;
import org.piwigo.remotesync.api.sync.SyncDirectoryWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Importer
{
    private static final Logger logger = LoggerFactory.getLogger(Importer.class);

    private static final String PREFIX = "albums";
    private final EntityManager myEntityManager;
    private final String myStartDirectory;

    public Importer(String directory)
    {
        PersistenceManager persistenceManager = new PersistenceManager();
        myEntityManager = persistenceManager.getEntityManager();
        myStartDirectory = directory + "/" + PREFIX;
    }

    public boolean shouldImport(String path)
    {
        return path.startsWith(myStartDirectory);
    }

    public void addAlbum(String path, Integer albumId)
    {
        addImportItem(path, albumId, null);
    }

    public void addImage(String path, Integer albumId, Integer imageId)
    {
        addImportItem(path, albumId, imageId);
    }

    private void addImportItem(String path, Integer albumId, Integer imageId)
    {
        String relativePath = getRelativePath(path);
        Item item = forPath(relativePath);
        ImportItem importItem = new ImportItem().setPath(relativePath).setAlbumId(albumId).setImageId(imageId).setUrl(item.getUrl());
        addImportItem(importItem);
    }

    private String getRelativePath(String path)
    {
        String relative = path.replace(myStartDirectory, "");
        return relative.startsWith("/") ? relative.substring(1) : relative;
    }

    private void addImportItem(ImportItem importItem)
    {
        myEntityManager.getTransaction().begin();
        myEntityManager.persist(importItem);
        myEntityManager.getTransaction().commit();
    }

    private Item forPath(String path)
    {
        try
        {
            return myEntityManager.createNamedQuery(Item.FIND_BY_PATH, Item.class)
                                  .setParameter(Item.PATH, path)
                                  .getSingleResult();
        } catch (Exception e)
        {
            logger.info("Error fetch info for entity with path:" + path);
            throw e;
        }
    }
}
