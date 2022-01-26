package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import application.MRA_App;
import java.util.Date;


public class RUserGUIaddMovie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("navtype", "registereduser");
		request.setAttribute("pagetitle", "addmovie");
		
		try {
			request.getRequestDispatcher("/templates/defaultWebpageRUAdd.ftl").forward(request, response);
			
		}
		catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("navtype", "registereduser");

	
	if (request.getParameter("action").equals("addmovie")) {
		String title = (String) request.getParameter("title");
		String director = (String) request.getParameter("director");
		String actors = (String) request.getParameter("actors");
		String midstr = (String) request.getParameter("mid");
		String publishingDatestr = (String) request.getParameter("publishingDate");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/ddd/yyyy");
		Date date1 = null;
		try {
			date1 = formatter.parse(publishingDatestr);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int mid =Integer.parseInt(midstr);
		
		if (new MRA_App().forwardAddNewMovie(title, director, actors, date1, mid) == true) {
			try {
				request.setAttribute("pagetitle", "addmovie");
			    request.setAttribute("message", "Add Movie OK");
				request.getRequestDispatcher("/templates/okRepresentation.ftl").forward(request, response);
			}
			catch (ServletException | IOException e) {
				e.printStackTrace();
				
			}
		}
		
		if (new MRA_App().forwardAddNewMovie(title, director, actors, date1, mid) == false) {
			try {
				request.setAttribute("pagetitle", "addmovie");
			    request.setAttribute("message", "Add Movie Failed");
				request.getRequestDispatcher("/templates/failInfoRepresentation.ftl").forward(request, response);
			}
			catch (ServletException | IOException e) {
				e.printStackTrace();
			}
				
			}
	
	} else {
			doGet(request, response);
		}
	}
}	