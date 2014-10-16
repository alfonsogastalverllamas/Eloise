package domain;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

//ESTA CLASE REPRESENTA UNA UNIDAD DE LAS CAPAS OCULTAS

public class NeuronaOculta extends Neurona {
	
	/* ATRIBUTO */
	private List<Arco> entradas;
	private List<Arco> salidas;
	
	/* CONSTRUCTORES */
	public NeuronaOculta() {
		super();
		entradas = new ArrayList<Arco>();
		salidas = new ArrayList<Arco>();
	}
	public NeuronaOculta(List<Arco> entradas, List<Arco> salidas) {
		super();
		this.entradas = entradas;
		this.salidas = salidas;
	}
	
	/* METODOS BASICOS */
	public List<Arco> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<Arco> entradas) {
		this.entradas = entradas;
	}
	public List<Arco> getSalidas() {
		return salidas;
	}
	public void setSalidas(List<Arco> salidas) {
		this.salidas = salidas;
	}

	public String toString() {
		return "Neurona: id = " + getId().toString() + " Peso Umbral = " + getPesoUmbral() + " ( " + getSalidas().toString() + " ) "  + "\n";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entradas == null) ? 0 : entradas.hashCode());
		result = prime * result + ((salidas == null) ? 0 : salidas.hashCode());
		return result*(super.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeuronaOculta other = (NeuronaOculta) obj;
		if (entradas == null) {
			if (other.entradas != null)
				return false;
		} else if (!entradas.equals(other.entradas))
			return false;
		if (salidas == null) {
			if (other.salidas != null)
				return false;
		} else if (!salidas.equals(other.salidas))
			return false;
		return super.equals(obj);
	}
	
	
}
