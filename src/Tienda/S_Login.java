package Tienda;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
*
* @author Ruben
*/
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class S_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DAOUsuario userdao;
		
		userdao = new DAOUsuario();
		String id;
		String contra;
		
		
		id=request.getParameter("nombre");
		contra=request.getParameter("contra");
		Vector<LineaCompra>cesta;
		ServletOutputStream out;
		out=response.getOutputStream();
		response.setContentType("text/html");
		Usuario user=userdao.findById(id);
		HttpSession sesion=request.getSession(true);
		cesta=new Vector<LineaCompra>();
		
		if(user!=null && user.getPswd().equals(contra)){
			sesion.setAttribute("usuario", user);
			sesion.setAttribute("cesta", cesta	);
			out.println("<html>");
			out.println("<body>");
			out.println("usuario validado correctamente");
			out.println("</body>");
			out.println("</html>");
			RequestDispatcher dispatcher;
			dispatcher=request.getRequestDispatcher("MenuCompra");
			dispatcher.forward(request, response);
			
		}else{
			out.println("<html>");
			out.println("<body>");
			out.println("usuario o contraseña incorrectos");
			out.println("</body>");
			out.println("</html>");
			RequestDispatcher dispatcher;
			dispatcher=request.getRequestDispatcher("Login.html");
			dispatcher.include(request, response);
			
		}
	}

}
