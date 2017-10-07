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

import com.sun.webkit.UIClient;

/**
*
* @author Ruben
*/

/**
 * Servlet implementation class Añadir
 */
@WebServlet("/Aniadir")
public class Anadir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Vector<LineaCompra>cesta;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Anadir() {
        super();
        // TODO Auto-generated constructor stub
   cesta=new Vector<LineaCompra>();
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
		DAOProducto daoproducto = new DAOProducto();
		DAOUsuario daousuario=new DAOUsuario();
		DAOLineaCompra daolineacompra= new DAOLineaCompra();
		Producto p ;
		LineaCompra lincompra = null;
		Usuario cli ;
		
		float subtotal=0;
		CompraHijo comprah= new CompraHijo();
		String idcompra=comprah.generarID();
		lincompra= new LineaCompra();
		
		ServletOutputStream out;
		out=response.getOutputStream();
		
		String id=request.getParameter("productos");
		String cantidad=request.getParameter("cantidad");
		
		HttpSession sesion= request.getSession(true);
		p=daoproducto.findById(id);
		cli=(Usuario)sesion.getAttribute("usuario");
		Usuario usuario2=daousuario.findById(cli.getLogin());
		
			lincompra.setIdcompra(idcompra);
			lincompra.setId(id);
			lincompra.setCantidad(Integer.parseInt(cantidad));
			subtotal+=comprah.subtotal(lincompra.getId())*lincompra.getCantidad();
			lincompra.setSubtotal(subtotal);
			
			Producto pro = daoproducto.findById(id);
			
			//comprueba si tiene saldo stock y actualizamos tanto producto como saldo de cliente
			if( p.getStock()>= lincompra.getCantidad()&& cli.getSaldo()>=lincompra.getSubtotal()){
				cesta.addElement(lincompra);
				usuario2.setSaldo(usuario2.getSaldo()-lincompra.getSubtotal());
				daousuario.update(usuario2);
				pro.setStock(pro.getStock()-lincompra.getCantidad());
				daoproducto.update(pro);
			}else{
				out.println("<html>");
				out.println("<body>");
				out.println("error al añadir");
				out.println("</body>");
				out.println("</html>");
			}
			
			for(LineaCompra lc:cesta){
				Producto pr = daoproducto.findById(id);
				out.println("Nombre del producto: "+pr.getNombre()+" Subtotal: "+lc.getSubtotal());
				out.println("<br>");
			}
			
			sesion.setAttribute("cesta", cesta);
			
			//seguir???
			RequestDispatcher dispatcher= request.getRequestDispatcher("seguir.html");
			dispatcher.include(request, response);
	}

	
	}

