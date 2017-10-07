package Tienda;
/**
*
* @author Ruben
*/
public class Usuario {
		private String login,pswd;
		private float saldo;


		public Usuario(){
			login="";
			pswd="";
			saldo=0;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPswd() {
			return pswd;
		}

		public void setPswd(String pswd) {
			this.pswd = pswd;
		}

		public float getSaldo() {
			return saldo;
		}

		public void setSaldo(float saldo) {
			this.saldo = saldo;
		}
}
