package interfaces;

import java.util.Date;
import java.util.List;

import dbadapter.Movie;

public interface IMovieDatabase {
	
	public boolean rate(int rating, String uid, String mid, String comment);
	public boolean check_rate(int rating, String uid, String mid, String comment);
	
	public boolean addMovie(String title, String director, String actors, Date publishingDate, int mid);
	public boolean check_movie(String title, String director, String actors, Date publishingDate, int mid);
	
	public List<Movie> get_movie();
	
	
}
