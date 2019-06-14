package cob.michealcob.rappiat.model;

public class Movie {
    Integer id;
    String title;
    String rating;
    String overview;
    String posterpath;
    String backdrop;
    String movieid;

    public Integer getId() {
        return id;
    }

    public String getMovieid() {
        return movieid;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public String getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
