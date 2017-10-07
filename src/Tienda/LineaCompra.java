package Tienda;
/**
*
* @author Ruben
*/
public class LineaCompra {
	private String id,idcompra;
	private int cantidad;
	private float subtotal;
	
	
	public LineaCompra(String idcompra2, String id2, int cantidad2, float subtotal2) {
		// TODO Auto-generated constructor stub
		idcompra=idcompra2;
		id=id2;
		cantidad=cantidad2;
		subtotal=subtotal2;
	}
	public LineaCompra() {
		idcompra="";
		id="";
		cantidad=0;
		subtotal=0;
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcompra() {
		return idcompra;
	}
	public void setIdcompra(String idcompra) {
		this.idcompra = idcompra;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
