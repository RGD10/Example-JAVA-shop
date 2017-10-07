package Tienda;

import java.sql.Date;
/**
*
* @author Ruben
*/
	public class Compra {
		private String id,login;
		private Date fecha;

		public Compra(){
			id="";
			login="";
			fecha= new Date(System.currentTimeMillis());
		}

		public Compra(String idcompra, Date date, String login) {
			// TODO Auto-generated constructor stub
			id=idcompra;
			fecha=date;
			this.login=login;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
	}
