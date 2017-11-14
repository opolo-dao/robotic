package lv.challenge.domain.photo_video;

import com.google.gson.annotations.Expose;

public class Photo {
    @Expose
    private String title;
    @Expose
    private String comment;
    @Expose
    private String filename;

    public Photo() {
    }

    public Photo(String title, String comment, String filename) {
        this.title = title;
        this.comment = comment;
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return filename != null ? filename.equals(photo.filename) : photo.filename == null;
    }

    @Override
    public int hashCode() {
        return filename != null ? filename.hashCode() : 0;
    }
}
