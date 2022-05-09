package poesia.genero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for Table "generos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "generos")
public class Genero {

	@Id
	private String idGenero;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public Genero() {}

	public Genero(String idGenero, String nombre) {
		this.idGenero = idGenero;
		this.nombre = nombre;
	}

	/**
	 * @return the idGenero
	 */
	public String getIdGenero() {
		return idGenero;
	}

	/**
	 * @param idGenero the idGenero to set
	 */
	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
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
	
}
