package org.piwigo.remotesync.menalto;

import jakarta.persistence.EntityManager;

public class Importer
{
    private final EntityManager myEntityManager;
    private final String myStartDirectory;

    public Importer(String directory)
    {
        PersistenceManager persistenceManager = new PersistenceManager();
        myEntityManager = persistenceManager.getEntityManager();
        myStartDirectory = directory;
    }

    public void addAlbum(String path, Integer albumId)
    {
        addImportItem(new ImportItem().setPath(getRelativePath(path)).setAlbumId(albumId));
    }

    public void addImage(String path, Integer albumId, Integer imageId)
    {
        addImportItem(new ImportItem().setPath(getRelativePath(path)).setAlbumId(albumId).setImageId(imageId));
    }

    private String getRelativePath(String path)
    {
        String relative = path.replace(myStartDirectory, "");
        return relative = relative.startsWith("/") ? relative.substring(1) : relative;
    }

    private void addImportItem(ImportItem importItem)
    {
        myEntityManager.getTransaction().begin();
        myEntityManager.persist(importItem);
        myEntityManager.getTransaction().commit();
    }
}
