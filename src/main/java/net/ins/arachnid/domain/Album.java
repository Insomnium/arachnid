package net.ins.arachnid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.Date;

/**
 * Created by ins on 5/24/15.
 */
@Document
public class Album {

    @Id
    private long id;
    private String title;
    @Field("released")
    private Date releaseDate;
    private Collection<String> genres;

    public Album() {
    }

    public Album(String title, Date releaseDate, Collection<String> genres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
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

    public Collection<String> getGenres() {
        return genres;
    }

    public void setGenres(Collection<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(album.releaseDate) : album.releaseDate != null) return false;
        return !(genres != null ? !genres.equals(album.genres) : album.genres != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                '}';
    }
}
