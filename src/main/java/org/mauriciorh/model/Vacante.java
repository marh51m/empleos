package org.mauriciorh.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="vacantes")

public class Vacante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Atributos iniciales
	private Integer id;
	private String nombre; //nombre de la oferta de trabajo 
	private String descripcion; //descripcion de la oferta de trabajo
	private LocalDate fecha; //Fecha de publicacion de la oferta de trabajo 
	private Double salario; //Salario aproximado que se ofrece
	private Integer destacado; // 1= destacado -- 0= no destaca
	
	private String imagen ="logo.png";
	private String estatus;
	private String detalles;
	
	//@Transient  //Para descartar este atributo, no hay la relación 
	@OneToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Integer getDestacado() {
		return destacado;
	}
	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	

	public String getDetalles() {
		return detalles;
	}
	
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	
	
	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen + ", estatus=" + estatus
				+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
	}
	
}
