package Tienda;

import java.io.IOException;
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
 * Servlet implementation class Cancelar
 */
@WebServlet("/Cancelar")
public class S_Cancelar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Cancelar() {
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
		
		HttpSession sesion = request.getSession();
		Vector<LineaCompra>lineas = (Vector<LineaCompra>) sesion.getAttribute("cesta");
		
		HttpSession user = request.getSession();
		Usuario cliente =  (Usuario) user.getAttribute("usuario");
		Usuario usuario2; 
		
		float subtotal = 0;
		DAOUsuario cliDao = new DAOUsuario();
		DAOProducto proDao = new DAOProducto();
		Producto p;
		usuario2 = cliDao.findById(cliente.getLogin());
		
		for(LineaCompra lc:lineas){
			p = proDao.findById(lc.getId());
			p.setStock(p.getStock()+lc.getCantidad());
			proDao.update(p);
			subtotal += lc.getSubtotal();
		}

		usuario2.setSaldo(usuario2.getSaldo()+subtotal);
		cliDao.update(usuario2);
		user.invalidate();
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("error.html");
		dispatcher.include(request, response);
	}

}
