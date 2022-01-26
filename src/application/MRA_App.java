package application;

import dbadapter.UD_Adapter;

import java.util.Date;
import java.util.List;

import dbadapter.MD_Adapter;
import dbadapter.Movie;
import interfaces.RUCmds;
import interfaces.UCmds;


public class MRA_App implements RUCmds, UCmds{
	
	private static MRA_App instance;
	public static MRA_App getInstance() {
		if (instance == null) {
			instance = new MRA_App();
		}

		return instance;
	}	

	@Override
	public boolean requestRegistration(String name, int age, String email) {
		
		boolean success=true; 
		
		String nameSQL=name;
		int ageSQL=age;
		String emailSQL=email;
		 
		if(UD_Adapter.getInstance().addUser(nameSQL,ageSQL,emailSQL))
		{return success;}
		else {
		 
		return false;}
		 
	}
	
	 
	
	@Override
	public boolean addRating(int rating, String uid, String mid, String comment) {
		boolean success=true; 
		
		int ratingSQL = rating;
		String uidSQL = uid;
		String midSQL = mid;
		String commentSQL = comment;
		
		if(MD_Adapter.getInstance().rate(ratingSQL, uidSQL, midSQL, commentSQL)) {
			return success;
		} else {
			return false; }
		
	}

	@Override
	public boolean forwardAddNewMovie(String title, String director, String actors, Date publishingDate, int mid) {
		String titleSQL = title;
		String directorSQL = director;
		String actorsSQL = actors;
		Date publishingDateSQL = publishingDate;
		int midSQL = mid;
		
		if (MD_Adapter.getInstance().addMovie(titleSQL, directorSQL, actorsSQL, publishingDateSQL, midSQL)) {
			return true;
		} else {
			return false;
		}
			
	}
	

	@Override
	public List<Movie> getMovies() {
		return MD_Adapter.getInstance().get_movies();
	}
		
		
	}



