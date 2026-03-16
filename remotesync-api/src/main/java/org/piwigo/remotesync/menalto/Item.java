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
@Table(name = "items")
@NamedQueries({
        @NamedQuery(name = Item.FIND_BY_PATH, query = "select i from Item i where i.myPath =:path"),
})
public class Item
{
    public static final String FIND_BY_PATH = "Item.findByPath";
    public static final String PATH = "path";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer myId;

    @Column(name = "relative_path_cache", nullable = false, length = 255)
    private String myPath;

    @Column(name = "relative_url_cache", nullable = false, length = 255)
    private String myUrl;

    public Integer getId()
    {
        return myId;
    }

    public Item setId(Integer id)
    {
        myId = id;
        return this;
    }

    public String getPath()
    {
        return myPath;
    }

    public Item setPath(String path)
    {
        myPath = path;
        return this;
    }

    public String getUrl()
    {
        return myUrl;
    }

    public Item setUrl(String url)
    {
        myUrl = url;
        return this;
    }
}
