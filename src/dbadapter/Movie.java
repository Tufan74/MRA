package dbadapter;

import java.util.Date;

public class Movie {
    private String title;
    private String director;
    private Date publishingDate;
    private int mid;
	private String actors;
    
    public Movie(String title, String director, String actors, Date publishingDate, int mid) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.publishingDate = publishingDate;
        this.mid = mid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getActors() {
        return actors;
    }
    
    public void setActors(String actors) {
        this.actors = actors;
    }
    
    public Date setPublishingDate() {
        return publishingDate;
    }
    
    public void getPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }
    
    public int getMid() {
        return mid;
    }
    
    public void setMid(int mid) {
        this.mid = mid;
    }
}