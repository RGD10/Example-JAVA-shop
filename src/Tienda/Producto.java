package Tienda;
/**
*
* @author Ruben
*/
public class Producto {
	private String id,nombre;
	private float pvp;
	private int stock;

	
	public Producto(){
		id="";
		nombre="";
		pvp=0;
		stock=0;
	}

	
	public float getPvp() {
		return pvp;
	}


	public void setPvp(float pvp) {
		this.pvp = pvp;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
