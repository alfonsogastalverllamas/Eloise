package utilidad;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

import domain.Arco;
import domain.NeuronaEntrada;
import domain.NeuronaOculta;
import domain.NeuronaSalida;

// ESTA CLASE REPRESENTA UNA RED NEURONAL

public class RedNeuronal {

	/* ATRIBUTOS */
	List<NeuronaEntrada> capaEntrada;
	List<List<NeuronaOculta>> capasOcultas;
	List<NeuronaSalida> capaSalida;

	/* CONSTRUCTORES */
	public RedNeuronal() {
		super();
		capaEntrada = new ArrayList<NeuronaEntrada>();
		capasOcultas = new ArrayList<List<NeuronaOculta>>();
		capaSalida = new ArrayList<NeuronaSalida>();
	}

	public RedNeuronal(List<NeuronaEntrada> capaEntrada,
			List<List<NeuronaOculta>> capasOcultas,
			List<NeuronaSalida> capaSalida) {
		super();
		this.capaEntrada = capaEntrada;
		this.capasOcultas = capasOcultas;
		this.capaSalida = capaSalida;
	}

	//CONSTRUCTOR POR INTEGERS
	public RedNeuronal(Integer ent, List<Integer> ocu, Integer sal) {

		capaEntrada = new ArrayList<NeuronaEntrada>();
		capasOcultas = new ArrayList<List<NeuronaOculta>>();
		capaSalida = new ArrayList<NeuronaSalida>();
		System.out.println("Creando Capa De Entrada...");
		// CAPA ENTRADAS
		for (int i = 0; i < ent; i++) {
			List<Arco> aristasEntrada = new ArrayList<Arco>();
			NeuronaEntrada ne = new NeuronaEntrada();
			ne.setId(Generator.getId());
			ne.setPesoUmbral(1.0);
			if (ocu.size() != 0) {
				for (int j = 0; j < ocu.get(0); j++) {
					Arco es = new Arco();
					es.setPeso((Math.random() * 2 - 1));
					es.setEntrada(ne);
					// es.setSalida(null);
					aristasEntrada.add(es);
				}

			} else {
				for (int j = 0; j < sal; j++) {
					Arco es = new Arco();
					es.setPeso((Math.random() * 2 - 1));
					es.setEntrada(ne);
					// es.setSalida(null);
					aristasEntrada.add(es);
				}

			}
			ne.setSalidas(aristasEntrada);
			capaEntrada.add(ne);
		}

		// CAPAS OCULTAS
		if (ocu.size() != 0) {
			System.out.println("Hay Capas Ocultas! En concreto: " + ocu.size()
					+ ".");
			System.out.println("Creando Capas Ocultas...");
		} else {
			System.out.println("No Hay Capas Ocultas!");
		}
		for (int o = 0; o < ocu.size(); o++) {
			List<NeuronaOculta> lno = new ArrayList<NeuronaOculta>();
			for (int c = 0; c < ocu.get(o); c++) {
				List<Arco> aristasOcuSalida = new ArrayList<Arco>();
				List<Arco> aristasOcuEntrada = new ArrayList<Arco>();
				NeuronaOculta no = new NeuronaOculta();
				no.setPesoUmbral((Math.random() * 2 - 1));
				no.setId(Generator.getId());

				// Aristas Salidas
				if (o != ocu.size() - 1) {
					for (int u = 0; u < ocu.get(o + 1); u++) {
						Arco os = new Arco();
						os.setPeso((Math.random() * 2 - 1));
						os.setEntrada(no);
						// os.setSalida(null);
						aristasOcuSalida.add(os);
					}
				} else {
					for (int t = 0; t < sal; t++) {
						Arco os = new Arco();
						os.setPeso((Math.random() * 2 - 1));
						os.setEntrada(no);
						// os.setSalida(null);
						aristasOcuSalida.add(os);
					}
				}

				// Aristas Entradas
				if (o == 0) {
					for (NeuronaEntrada n : capaEntrada) {
						n.getSalidas().get(c).setSalida(no);
						aristasOcuEntrada.add(n.getSalidas().get(c));
					}

				} else {
					for (NeuronaOculta n : capasOcultas.get(o - 1)) {
						n.getSalidas().get(c).setSalida(no);
						aristasOcuEntrada.add(n.getSalidas().get(c));
					}

				}

				no.setEntradas(aristasOcuEntrada);
				no.setSalidas(aristasOcuSalida);
				lno.add(no);
			}
			capasOcultas.add(lno);
		}
		System.out.println("Creando Capa De Salida...");
		// CAPA SALIDA
		for (int s = 0; s < sal; s++) {
			List<Arco> aristasSalida = new ArrayList<Arco>();
			NeuronaSalida ns = new NeuronaSalida();
			ns.setId(Generator.getId());
			ns.setPesoUmbral((Math.random() * 2 - 1));

			// Arcos
			if (ocu.size() == 0) {
				for (NeuronaEntrada n : capaEntrada) {
					n.getSalidas().get(s).setSalida(ns);
					aristasSalida.add(n.getSalidas().get(s));
				}
			} else {
				for (NeuronaOculta n : capasOcultas
						.get(capasOcultas.size() - 1)) {
					n.getSalidas().get(s).setSalida(ns);
					aristasSalida.add(n.getSalidas().get(s));
				}
			}
			ns.setEntradas(aristasSalida);
			capaSalida.add(ns);

		}
		System.out.println("Red Neuronal creada correctamente.");

	}
	
	/* METODOS BASICOS */

	public List<NeuronaEntrada> getCapaEntrada() {
		return capaEntrada;
	}

	public void setCapaEntrada(List<NeuronaEntrada> capaEntrada) {
		this.capaEntrada = capaEntrada;
	}

	public List<List<NeuronaOculta>> getCapasOcultas() {
		return capasOcultas;
	}

	public void setCapasOcultas(List<List<NeuronaOculta>> capasOcultas) {
		this.capasOcultas = capasOcultas;
	}

	public List<NeuronaSalida> getCapaSalida() {
		return capaSalida;
	}

	public void setCapaSalida(List<NeuronaSalida> capaSalida) {
		this.capaSalida = capaSalida;
	}


	public String toString() {
		return "RedNeuronal: \n Capa Entrada: \n" + getCapaEntrada().toString()
				+ "\n Capas Ocultas: \n" + getCapasOcultas().toString()
				+ "\n Capa Salida: \n" + getCapaSalida().toString() ;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((capaEntrada == null) ? 0 : capaEntrada.hashCode());
		result = prime * result
				+ ((capaSalida == null) ? 0 : capaSalida.hashCode());
		result = prime * result
				+ ((capasOcultas == null) ? 0 : capasOcultas.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedNeuronal other = (RedNeuronal) obj;
		if (capaEntrada == null) {
			if (other.capaEntrada != null)
				return false;
		} else if (!capaEntrada.equals(other.capaEntrada))
			return false;
		if (capaSalida == null) {
			if (other.capaSalida != null)
				return false;
		} else if (!capaSalida.equals(other.capaSalida))
			return false;
		if (capasOcultas == null) {
			if (other.capasOcultas != null)
				return false;
		} else if (!capasOcultas.equals(other.capasOcultas))
			return false;
		return true;
	}
	
	/* OTROS METODOS */
	
	public Integer getNumeroTotalNeurona(){
		Integer res = 0;
		res+=this.getCapaEntrada().size();
		for(List<NeuronaOculta> l: this.getCapasOcultas())
			res+=l.size();
		res+=this.getCapaSalida().size();
		return res;
	}
	
	public List<Double> getSalidas(){
		List<Double> res = new ArrayList<Double>();
		for(NeuronaSalida ns : getCapaSalida())
			res.add(ns.getSalida());
		return res;
	}
}
