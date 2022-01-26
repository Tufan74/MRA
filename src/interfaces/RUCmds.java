package interfaces;

import java.util.Date;
import java.util.List;

import dbadapter.Movie;

public interface RUCmds {
	
	public boolean forwardAddNewMovie(String title, String director, String actors, Date publishingDate, int mid);
	
	public boolean addRating(int rating, String uid, String mid, String comment);
	
	public List<Movie> getMovies();
	
}
