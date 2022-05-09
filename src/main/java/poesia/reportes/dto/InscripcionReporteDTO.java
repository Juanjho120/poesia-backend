package poesia.reportes.dto;

public class InscripcionReporteDTO {

	private String carnet;
	private String carrera;
	private String nombreCompleto;
	private String fechaInscripcion;
	private String generoPoesia;
	private String fechaDeclamacion;
	
	public InscripcionReporteDTO() {
		this.carnet = "";
		this.carrera = "";
		this.nombreCompleto = "";
		this.fechaInscripcion = "";
		this.fechaDeclamacion = "";
		this.generoPoesia = "";
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getGeneroPoesia() {
		return generoPoesia;
	}

	public void setGeneroPoesia(String generoPoesia) {
		this.generoPoesia = generoPoesia;
	}

	public String getFechaDeclamacion() {
		return fechaDeclamacion;
	}

	public void setFechaDeclamacion(String fechaDeclamacion) {
		this.fechaDeclamacion = fechaDeclamacion;
	}
	
}
