package net.ins.arachnid.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * Created by ins on 5/24/15.
 */
@Entity
@Table(name = "albums")
public class Album {
    @Id
    private long id;
    private String title;
    private Date releaseDate;
    private Collection<String> relatedGenres;

    public Album() {
    }

    public Album(String title, Date releaseDate, Collection<String> relatedGenres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.relatedGenres = relatedGenres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Collection<String> getRelatedGenres() {
        return relatedGenres;
    }

    public void setRelatedGenres(Collection<String> relatedGenres) {
        this.relatedGenres = relatedGenres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(album.releaseDate) : album.releaseDate != null) return false;
        return !(relatedGenres != null ? !relatedGenres.equals(album.relatedGenres) : album.relatedGenres != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (relatedGenres != null ? relatedGenres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", relatedGenres=" + relatedGenres +
                '}';
    }
}
