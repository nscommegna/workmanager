package comworkmanager.util;

public class Messaggio {
	String messaggio;
	String tipo;
	
	
	public Messaggio(String messaggio, String tipo) {
		super();
		this.messaggio = messaggio;
		this.tipo = tipo;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
