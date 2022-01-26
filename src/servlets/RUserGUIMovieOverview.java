package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRA_App;
import datatypes.TimeData;
import dbadapter.Movie;

public class RUserGUIMovieOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		 
		request.setAttribute("pagetitle", "Movie Overview");

		// Dispatch request to template engine
		
		try {
			List<Movie> movies = MRA_App.getInstance().getMovies();
			request.setAttribute("movies", movies);
			request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
		}
		catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);

	}
