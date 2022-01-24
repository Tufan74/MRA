package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRA_App;


public class RUserGUIrateMovie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
				
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		 
		request.setAttribute("navtype", "ruser");
		request.setAttribute("pagetitle", "rate");

		// Dispatch request to template engine
		try {
			request.getRequestDispatcher("/templates/defaultWebpageRUser.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		// Set attribute for navigation type
		request.setAttribute("navtype", "ruser");

		// Check whether the call is insertOffer or not
		if (request.getParameter("action").equals("rate")) {

			// Append parameter of request
			String ratingstr = (String) request.getParameter("rating");
			int rating = Integer.parseInt(ratingstr);
			
			String uid = (String) request.getParameter("uid");
			
			String mid = (String) request.getParameter("mid");
			
			String comment = (String) request.getParameter("comment");
			
			
			
			if(new MRA_App().addRating(rating, uid, mid, comment)==true) {
 
		   try {
	 		    request.setAttribute("pagetitle", "rate");
			    request.setAttribute("message", "Rate OK");
				request.getRequestDispatcher("/templates/okRepresentation.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}}
		    
			
			 if(new MRA_App().addRating(rating, uid, mid, comment)==false) {
				 
				   try {
			 		    request.setAttribute("pagetitle", "rate");
					    request.setAttribute("message", "Rate failure:Yor already rated this movie!");
						request.getRequestDispatcher("/templates/failInfoRepresentation.ftl").forward(request, response);

					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}}
			
					
			// Call doGet if request is not equal to insertOffer
		} else 
			doGet(request, response);

	}
	
	
	
}
