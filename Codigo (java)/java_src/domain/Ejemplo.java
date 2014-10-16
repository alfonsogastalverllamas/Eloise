package domain;

/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.List;

//ESTA CLASE REPRESENTA LOS EJEMPLOS DE LOS ARCHIVOS DE TEXTO.

public class Ejemplo {
	
	/* ATRIBUTOS */
	public List<Double> entradas;
	public List<Double> salidasEsperadas;
	
	/* CONSTRUCTORES */
	public Ejemplo() {
		super();
	}
	public Ejemplo(List<Double> entradas, List<Double> salidasEsperadas) {
		super();
		this.entradas = entradas;
		this.salidasEsperadas = salidasEsperadas;
	}
	
	/* METODOS BASICOS */
	public List<Double> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<Double> entradas) {
		this.entradas = entradas;
	}
	public List<Double> getSalidasEsperadas() {
		return salidasEsperadas;
	}
	public void setSalidasEsperadas(List<Double> salidasEsperadas) {
		this.salidasEsperadas = salidasEsperadas;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entradas == null) ? 0 : entradas.hashCode());
		result = prime
				* result
				+ ((salidasEsperadas == null) ? 0 : salidasEsperadas.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplo other = (Ejemplo) obj;
		if (entradas == null) {
			if (other.entradas != null)
				return false;
		} else if (!entradas.equals(other.entradas))
			return false;
		if (salidasEsperadas == null) {
			if (other.salidasEsperadas != null)
				return false;
		} else if (!salidasEsperadas.equals(other.salidasEsperadas))
			return false;
		return true;
	}
	public String toString() {
		return "Ejemplo [entradas=" + entradas + ", salidasEsperadas="
				+ salidasEsperadas + "]";
	}
	
}
