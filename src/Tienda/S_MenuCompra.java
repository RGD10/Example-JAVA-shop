package Tienda;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;

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
 * Servlet implementation class MenuCompra
 */
@WebServlet("/MenuCompra")
public class S_MenuCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_MenuCompra() {
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
		
		DAOProducto daoproducto = new DAOProducto();
		DAOLineaCompra daolineacompra=new DAOLineaCompra();
		Producto p ;
		LineaCompra lincompra = null;
		Usuario cli ;
		Vector<Producto>productos;
		Vector<LineaCompra>cesta;
		float subtotal=0;
		CompraHijo comprah= new CompraHijo();
		String idcompra=comprah.generarID();
		lincompra= new LineaCompra();
		
		ServletOutputStream out;
		productos=daoproducto.findAll();
		out=response.getOutputStream();
		cesta=daolineacompra.findAll();
		response.setContentType("text/html");
		
		out.println("<html>");
		out.println("<body>");
		out.println("<form action=\"Aniadir\">");
		out.println("<select name=\"productos\" >");
		
		for(Producto pr:productos){
			if(pr.getStock() > 0)
			out.println("<option value="+pr.getId()+">"+pr.getNombre()+pr.getPvp()+"</option>");
		}
		out.println("</select>");
		out.println("cantidad");
		out.println("<input name=\"cantidad\" type=\"text\" size=\"15\" >");
		out.println("<br>");
		out.println("<input value=\"añadir\" name=\"añadir\" type=\"submit\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		String id=request.getParameter("productos");
		String cantidad=request.getParameter("cantidad");
		HttpSession sesion= request.getSession();
		cesta=(Vector<LineaCompra>)sesion.getAttribute("cesta");
		cli=(Usuario)sesion.getAttribute("usuario");
		
		if(id!=null && cantidad!=null){
			lincompra.setIdcompra(idcompra);
			lincompra.setId(id);
			lincompra.setCantidad(Integer.parseInt(cantidad));
			subtotal=lincompra.getSubtotal();
			lincompra.setSubtotal(subtotal);
			cesta.addElement(lincompra);
			
		
		Producto pro = daoproducto.findById(id);
		if( pro.getStock()>= lincompra.getCantidad()&& cli.getSaldo()>=subtotal){
			lincompra.setIdcompra(idcompra);
			lincompra.setSubtotal(comprah.subtotal(lincompra.getId())*lincompra.getCantidad());
			
			//comprobar si tiene saldo el cliente
			if(cli.getSaldo()>=subtotal){
				cesta.addElement(lincompra);
			}else{
				System.out.println("no tienes suficiente saldo en la cuenta");
			}
		}
	cesta.clear(); //limpiamos la cesta para que no vuelva a salir la lista iniciado otra vez el programa
	}

	}
}
