package Tienda;

import java.io.IOException;
import java.sql.Date;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
 * Servlet implementation class Compraa
 */
@WebServlet("/Compraa")
public class S_Compra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Compra() {
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
		HttpSession cliente = request.getSession();
		Usuario user = (Usuario) cliente.getAttribute("usuario");
		//guardas las lineas de compra en el vector
		HttpSession lineasCompra = request.getSession();
		Vector<LineaCompra>lineas = (Vector<LineaCompra>) lineasCompra.getAttribute("cesta");
		
		//añade la compra a la base de datos
		CompraHijo comprah=new CompraHijo();
		String idcompra= comprah.generarID();
		Compra c = new Compra(idcompra,new Date(System.currentTimeMillis()),user.getLogin());

		DAOCompra daocompra = new DAOCompra();
		daocompra.insert(c);
		
		//añade lineas de compra  a la base de datos
		DAOLineaCompra daolineacompra = new DAOLineaCompra();
		for(LineaCompra lcom:lineas){
			daolineacompra.insert(lcom);
		}
		
		//comprafinal
		RequestDispatcher dispatcher= request.getRequestDispatcher("confirmada.html");
		dispatcher.include(request, response);
				
	}

}
