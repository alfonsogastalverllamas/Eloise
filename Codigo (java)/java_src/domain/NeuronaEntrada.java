package domain;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

//ESTA ES LA CLASE QUE REPRESENTA A LAS UNIDADES DE ENTRADA. EL PESO UMBRAL ESTA GUARDADO COMO 1.0 PARA NO MODIFICAR NADA.

public class NeuronaEntrada extends Neurona {
	
	/*  ATRIBUTOS */
	private Double entrada;
	private List<Arco> salidas;
	
	/* CONSTRUCTORES */
	public NeuronaEntrada() {
		super();
		salidas = new ArrayList<Arco>();
	}
	public NeuronaEntrada(Double entrada, List<Arco> salidas) {
		super();
		this.entrada = entrada;
		this.salidas = salidas;
	}
	
	/* METODOS BASICOS */
	public String toString() {
		return "Neurona: id = " + getId().toString() + " ( " + getSalidas().toString() + " ) "  + "\n";
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + ((salidas == null) ? 0 : salidas.hashCode());
		return result*(super.hashCode());
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeuronaEntrada other = (NeuronaEntrada) obj;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (salidas == null) {
			if (other.salidas != null)
				return false;
		} else if (!salidas.equals(other.salidas))
			return false;
		return super.equals(obj);
	}
	public Double getEntrada() {
		return entrada;
	}
	public void setEntrada(Double entrada) {
		this.entrada = entrada;
	}
	public List<Arco> getSalidas() {
		return salidas;
	}
	public void setSalidas(List<Arco> salidas) {
		this.salidas = salidas;
	}
	
	
	

}
