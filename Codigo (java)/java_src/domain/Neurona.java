package domain;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
//ESTA ES UNA CLASE ES ABSTRACTA QUE REPRESENTA LAS NEURONAS.

public abstract class Neurona {
	
	/* ATRIBUTO */ 
	
	private Integer id;
	private Double pesoUmbral;
	private Double salida;

	
	/* CONSTRUCTORES */
	
	public Neurona(){
		super();

	}

	/* METODOS BASICOS */
	
	public String toString() {
		return "Neurona [pesoUmbral=" + pesoUmbral + ", salida=" + salida
				+ ", pesosEntrada=" + "]";
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Neurona other = (Neurona) obj;
		if (pesoUmbral == null) {
			if (other.pesoUmbral != null)
				return false;
		} else if (!pesoUmbral.equals(other.pesoUmbral))
			return false;
		if (salida == null) {
			if (other.salida != null)
				return false;
		} else if (!salida.equals(other.salida))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPesoUmbral() {
		return pesoUmbral;
	}

	public void setPesoUmbral(Double pesoUmbral) {
		this.pesoUmbral = pesoUmbral;
	}

	public Double getSalida() {
		return salida;
	}

	public void setSalida(Double salida) {
		this.salida = salida;
	}

}
