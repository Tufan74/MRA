package interfaces;

import datatypes.TimeData;

public interface IMovieDatabase {
	
	public boolean rate(int rating, String uid, String mid, String comment);
	public boolean check_rate(int rating, int uid, int mid, String comment);
	
	public boolean addMovie(String title, String director, String actors, TimeData publishingDate);
	public boolean checkMovie(String title, TimeData publishingDate);
	
	
}
