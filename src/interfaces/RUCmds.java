package interfaces;

import java.util.List;

import datatypes.TimeData;
import dbadapter.Movie;

public interface RUCmds {
	
	public boolean forwardAddNewMovie(String title, String director, String actors, TimeData publishingDate);
	
	public boolean addRating(int rating, String uid, String mid, String comment);
	
	public List<Movie> getMovies();
	
}
