package poesia.inscripcion.dto;

public class CarnetDTO {

	private Boolean valido;
	private String razon;
	
	public CarnetDTO() {}
	
	public CarnetDTO(Boolean valido, String razon) {
		this.valido = valido;
		this.razon = razon;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	
}
