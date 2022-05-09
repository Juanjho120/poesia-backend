package poesia.carrera.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for Table "carreras"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "carreras")
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCarrera;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "estado")
	private Boolean estado;
	
	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;
	
	public Carrera() {}
	
	public Carrera(Integer idCarrera, String nombre) {
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.estado = true;
		this.fechaModificacion = LocalDateTime.now();
	}

	/**
	 * @return the idCarrera
	 */
	public Integer getIdCarrera() {
		return idCarrera;
	}

	/**
	 * @param idCarrera the idCarrera to set
	 */
	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaModificacion
	 */
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}
