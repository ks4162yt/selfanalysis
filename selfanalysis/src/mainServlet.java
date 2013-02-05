

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * Servlet implementation class mainServlet
 */
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String yourname = request.getParameter("yourname");
		
		response.setContentType("text/html; charset=utf-8");
		
		/* investigate profile date from database */
		profileDAO dao = new profileDAO();
		ArrayList<profileTransferObject> profileList;
		profileList = dao.getYourProfile(yourname);
		
		for(int i=0;i<profileList.size();i++){
			System.out.println(profileList.get(i).getName());
		}
		
		//
        // Create a new instance of Gson
        //
        Gson gson = new Gson();
        
        //convert Array to json
        String j_profile = gson.toJson(profileList);
        System.out.println(j_profile);
        
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				response.getOutputStream(), "utf-8"));
		// XMLOutputter outputter = new XMLOutputter();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("utf-8");

		out.write(j_profile);
		out.close();
		
		/*
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println(yourname);
		out.println("</BODY>");
		out.println("</HTML>");
		*/
	}

}
