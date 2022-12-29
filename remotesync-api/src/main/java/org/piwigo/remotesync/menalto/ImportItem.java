package org.piwigo.remotesync.menalto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "import_item")
@NamedQueries({
        @NamedQuery(name = "ImportItem.findAll", query = "select t from ImportItem t")
})
public class ImportItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer myId;

    @Column(name = "path", nullable = false, length = 255)
    private String myPath;

    @Column(name = "album_id", nullable = false)
    private Integer myAlbumId;

    @Column(name = "image_id", nullable = true)
    private Integer myImageId;

    public Integer getId()
    {
        return myId;
    }

    public void setId(Integer id)
    {
        myId = id;
    }

    public String getPath()
    {
        return myPath;
    }

    public ImportItem setPath(String path)
    {
        myPath = path;
        return this;
    }

    public Integer getAlbumId()
    {
        return myAlbumId;
    }

    public ImportItem setAlbumId(Integer albumId)
    {
        myAlbumId = albumId;
        return this;
    }

    public Integer getImageId()
    {
        return myImageId;
    }

    public ImportItem setImageId(Integer imageId)
    {
        myImageId = imageId;
        return this;
    }
}
