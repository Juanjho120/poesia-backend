package poesia.inscripcion.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import poesia.carrera.model.Carrera;
import poesia.genero.model.Genero;
import poesia.genero.model.GeneroPoesia;

/**
 * Model for Table "inscripciones"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "inscripciones")
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInscripcion;
	
	@Column(name = "carnet", nullable = false)
	private String carnet;
	
	@Column(name = "nombre_completo", nullable = false)
	private String nombreCompleto;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "id_genero", nullable = false, foreignKey = @ForeignKey(name = "fkInscripcionGenero"))
	private Genero genero;
	
	@Column(name = "telefono", nullable = false)
	private Integer telefono;
	
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@ManyToOne
	@JoinColumn(name = "id_carrera", nullable = false, foreignKey = @ForeignKey(name = "fkInscripcionCarrera"))
	private Carrera carrera;
	
	@ManyToOne
	@JoinColumn(name = "id_genero_poesia", nullable = false, foreignKey = @ForeignKey(name = "fkInscripcionGeneroPoesia"))
	private GeneroPoesia generoPoesia;
	
	@Column(name = "fecha_inscripcion", nullable = false)
	private LocalDate fechaInscripcion;
	
	@Column(name = "fecha_declamacion", nullable = false)
	private LocalDate fechaDeclamacion;
	
	@Column(name = "estado")
	private Boolean estado;
	
	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;
	
	public Inscripcion() {}

	public Inscripcion(Integer idInscripcion, String carnet, String nombreCompleto, String direccion, Genero genero,
			Integer telefono, LocalDate fechaNacimiento, Carrera carrera, GeneroPoesia generoPoesia, LocalDate fechaInscripcion) {
		this.idInscripcion = idInscripcion;
		this.carnet = carnet;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.genero = genero;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.carrera = carrera;
		this.generoPoesia = generoPoesia;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaModificacion = LocalDateTime.now();
		this.estado = true;
	}

	/**
	 * @return the idInscripcion
	 */
	public Integer getIdInscripcion() {
		return idInscripcion;
	}

	/**
	 * @param idInscripcion the idInscripcion to set
	 */
	public void setIdInscripcion(Integer idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	/**
	 * @return the carnet
	 */
	public String getCarnet() {
		return carnet;
	}

	/**
	 * @param carnet the carnet to set
	 */
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the telefono
	 */
	public Integer getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the carrera
	 */
	public Carrera getCarrera() {
		return carrera;
	}

	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	/**
	 * @return the generoPoesia
	 */
	public GeneroPoesia getGeneroPoesia() {
		return generoPoesia;
	}

	/**
	 * @param generoPoesia the generoPoesia to set
	 */
	public void setGeneroPoesia(GeneroPoesia generoPoesia) {
		this.generoPoesia = generoPoesia;
	}

	/**
	 * @return the fechaInscripcion
	 */
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	/**
	 * @param fechaInscripcion the fechaInscripcion to set
	 */
	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	/**
	 * @return the fechaDeclamacion
	 */
	public LocalDate getFechaDeclamacion() {
		return fechaDeclamacion;
	}

	/**
	 * @param fechaDeclamacion the fechaDeclamacion to set
	 */
	public void setFechaDeclamacion(LocalDate fechaDeclamacion) {
		this.fechaDeclamacion = fechaDeclamacion;
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
