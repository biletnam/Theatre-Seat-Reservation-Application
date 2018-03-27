package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;

/**
 * Servlet implementation class AddShowtime
 */
public class AddShowtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowtime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int showroomId = (int) session.getAttribute("showroomIdDB");
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String movieName = request.getParameter("MovieName");
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date startTimeDate = null;
		try {
			startTimeDate = (Date) formatter.parseObject(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date endTimeDate = null;
		try {
			endTimeDate = (Date) formatter.parseObject(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date startTimeDateList = null;
		Date endTimeDateList = null;
		
		ArrayList<MovieShowing> ListOfMovieShowing = new ArrayList<MovieShowing>();
		boolean check = false;
		
		MovieShowingDB aDB = new MovieShowingDB();
		//Get all of the MovieShowing times to see if there is conflict with new showing time
		ListOfMovieShowing = aDB.getMovieShowingList(showroomId);
		
		for (int i = 0 ; i < ListOfMovieShowing.size() ; i ++ ){
			try {
				startTimeDateList = (Date) formatter.parseObject(ListOfMovieShowing.get(i).getStartTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				endTimeDateList=(Date) formatter.parseObject(ListOfMovieShowing.get(i).getEndTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//To ensure movie times does not conflict
			if (!((startTimeDate.after(endTimeDateList)) || endTimeDate.before(startTimeDateList))){
				check = true; 
			}
		}
		
		if (check == true ){
			response.sendRedirect("ConflictTime.jsp");
		}else{
			aDB.addShowtime(showroomId, startTime, endTime, movieName);
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("AddShowtimeConfirmation.jsp");
			    dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
