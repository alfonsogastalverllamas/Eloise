package utilidad;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

import domain.Arco;
import domain.NeuronaEntrada;
import domain.NeuronaOculta;
import domain.NeuronaSalida;

// ESTA CLASE REPRESENTA AL ALGORITMO DE PROPAGACION HACIA ADELANTE, LA CREO ASI PUES ME ES UTIL
// QUE POSEA LOS ATRIBUTOS QUE ESTAN PUESTOS.
public class PropagacionAdelante {
	
	/* ATRIBUTOS */
	private RedNeuronal redNeuronal;
	private List<Double> entradas;
	private List<Double> salidas;

	/* CONSTRUCTORES */
	public PropagacionAdelante(RedNeuronal redNeuronal,
			 List<Double> entradas
			) {
		super();
		this.redNeuronal = redNeuronal;
		this.entradas = entradas;
		this.salidas = new ArrayList<Double>();
	}

	public PropagacionAdelante() {
		super();
	}

	/* METODOS BASICOS */
	
	public RedNeuronal getRedNeuronal() {
		return redNeuronal;
	}

	public void setRedNeuronal(RedNeuronal redNeuronal) {
		this.redNeuronal = redNeuronal;
	}


	public List<Double> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Double> entradas) {
		this.entradas = entradas;
	}

	public List<Double> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Double> salidas) {
		this.salidas = salidas;
	}

	/* OTROS METODOS */ 
	
	/** PROPAGACION HACIA ADELANTE **/
	public RedNeuronal propagacionAdelante() {
		
		Integer s = entradas.size();
		List<NeuronaEntrada> r = redNeuronal.getCapaEntrada();
		
		/** comprueba que lista de entradas = neuronas en la capa de entrada **/
		if (s != r.size()){
			System.out
					.println("Error: distinto número de entradas que de neuronas de la capa de entrada.");
		}	
		
		/** ejecuta el algoritmo **/
		else {
			// CAPA ENTRADA
			for (int i = 0; i < s; i++) {
				NeuronaEntrada n = r.get(i); 
				Double in = entradas.get(i);
				n.setEntrada(in);
				n.setSalida(in); //Introduce las entradas en las neuronas de Entrada.
			}
			
			// CAPAS OCULTAS
			for (int j = 0; j < redNeuronal.getCapasOcultas().size(); j++) {
				for (NeuronaOculta no : redNeuronal.getCapasOcultas().get(j)) { //PARA CADA CAPA
					Double salida = 0.0;
					for (Arco e : no.getEntradas()) {
						salida += e.getPeso() * e.getEntrada().getSalida();
					}
					no.setSalida(funcionActivacion(salida
							+ (no.getPesoUmbral() * (-1)))); //sigmoide(in)
				}
			}
			
			// CAPA SALIDA
			for (NeuronaSalida ns : redNeuronal.getCapaSalida()) {
				Double salida = 0.0;
				for (Arco e : ns.getEntradas()) {
					salida += e.getPeso() * e.getEntrada().getSalida();
				}
				ns.setSalida(funcionActivacion(salida
						+ (ns.getPesoUmbral() * (-1))));
				this.getSalidas().add(ns.getSalida()); //Añade las salidas a la red.
			}
		}
		return redNeuronal; //Devuelve la red neuronal.
	}
	
	//FUNCION DE ACTIVACION SIGMOIDE. NO HEMOS PUESTO OTRA PERO PODRIAMOS IMPLEMENTAR CUALQUIER OTRA, O VARIAS.
		public Double funcionActivacion(Double valor) {
			return 1 / (1 + (Math.exp(-valor)));
		}

	
}
