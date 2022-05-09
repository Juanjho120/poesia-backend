package poesia.genero.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for Table "generos_poesia"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "generos_poesia")
public class GeneroPoesia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGeneroPoesia;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "estado")
	private Boolean estado;
	
	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;
	
	public GeneroPoesia() {}

	public GeneroPoesia(Integer idGeneroPoesia, String nombre) {
		this.idGeneroPoesia = idGeneroPoesia;
		this.nombre = nombre;
		this.estado = true;
		this.fechaModificacion = LocalDateTime.now();
	}

	/**
	 * @return the idGeneroPoesia
	 */
	public Integer getIdGeneroPoesia() {
		return idGeneroPoesia;
	}

	/**
	 * @param idGeneroPoesia the idGeneroPoesia to set
	 */
	public void setIdGeneroPoesia(Integer idGeneroPoesia) {
		this.idGeneroPoesia = idGeneroPoesia;
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
