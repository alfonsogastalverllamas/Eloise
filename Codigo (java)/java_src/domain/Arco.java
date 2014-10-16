package domain;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
// ESTA CLASE DESCRIBE UNA UNION SINAPTICA ENTRE DOS NEURONAS

public class Arco {
	
	/* ATRIBUTOS */ 
	
	private Neurona entrada;
	private Neurona salida;
	private Double peso;

	/* CONSTRUCTORES */
	public Arco() {
		super();
	}

	public Arco(Neurona entrada, Neurona salida, Double peso) {
		super();
		this.entrada = entrada;
		this.salida = salida;
		this.peso = peso;
	}
	
	/* METODOS BASICOS */

	public Neurona getEntrada() {
		return entrada;
	}

	public void setEntrada(Neurona entrada) {
		this.entrada = entrada;
	}

	public Neurona getSalida() {
		return salida;
	}

	public void setSalida(Neurona salida) {
		this.salida = salida;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String toString() {
		return "Arista: Entrada = " + entrada.getId() + ", Salida = "
				+ salida.getId() + ", Peso = " + getPeso();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((salida == null) ? 0 : salida.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (salida == null) {
			if (other.salida != null)
				return false;
		} else if (!salida.equals(other.salida))
			return false;
		return true;
	}

}
