package domain;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

//ESTA CLASE REPRESENTA UNA UNIDAD DE LA CAPA DE SALIDA

public class NeuronaSalida extends Neurona {
	
	/* ATRIBUTOS */
	private List<Arco> entradas;
	
	/* CONSTRUCTORES */
	public NeuronaSalida() {
		super();
		entradas = new ArrayList<Arco>();
	}
	public NeuronaSalida(List<Arco> entradas) {
		super();
		this.entradas =entradas;
	}

	/* METODOS BASICOS */
	public String toString() {
		return "Neurona: id = " + getId().toString() + " Peso Umbral = " + getPesoUmbral() + "\n";}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entradas == null) ? 0 : entradas.hashCode());
		return result*(super.hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeuronaSalida other = (NeuronaSalida) obj;
		if (entradas == null) {
			if (other.entradas != null)
				return false;
		} else if (!entradas.equals(other.entradas))
			return false;
		return super.equals(obj);
	}

	
	public List<Arco> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<Arco> entradas) {
		this.entradas = entradas;
	}
	
	
	

}
