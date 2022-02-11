package comworkmanager.enums;

public enum EnumTipoMessaggio {
	
	SUCCESS (0,"bg-success"),
	WARNING(1,"bg-warning"),
	ERROR(2,"bg-danger");
	
	private String tipoMessaggio;
	private int codice;
	
	EnumTipoMessaggio(int i, String string) {
		this.tipoMessaggio = string;
		this.codice = i;
		
	}
	
	public String getTipo() {
		return this.tipoMessaggio;
	}

	
}
