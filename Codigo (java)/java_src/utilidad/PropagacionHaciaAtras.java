package utilidad;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */
import java.util.ArrayList;
import java.util.List;

import domain.Arco;
import domain.NeuronaEntrada;
import domain.NeuronaOculta;
import domain.NeuronaSalida;

public class PropagacionHaciaAtras {

	/* ATRIBUTOS */
	private RedNeuronal redNeuronal;
	private List<Double> errores;
	private List<Double> salidasEsperadas;
	private Double factorAprendizaje;
	private Double momentum;
	List<List<Double>> erroresEnCadaIteracion;

	/* CONSTRUCTORES */

	public PropagacionHaciaAtras() {
		super();
		this.errores = new ArrayList<Double>();
		this.salidasEsperadas = new ArrayList<Double>();
		this.erroresEnCadaIteracion = new ArrayList<List<Double>>();
		erroresEnCadaIteracion.add(errores);
	}

	public PropagacionHaciaAtras(RedNeuronal redNeuronal, List<Double> errores,
			List<Double> salidasEsperadas, Double factorAprendizaje,
			Double momentum) {
		super();
		this.redNeuronal = redNeuronal;
		this.errores = errores;
		this.salidasEsperadas = salidasEsperadas;
		this.factorAprendizaje = factorAprendizaje;
		this.momentum = momentum;
		this.erroresEnCadaIteracion = new ArrayList<List<Double>>();
		erroresEnCadaIteracion.add(errores);
	}

	public PropagacionHaciaAtras(RedNeuronal redNeuronal,
			List<Double> salidasEsperadas, Double factorAprendizaje,
			Double momentum) {
		this.redNeuronal = redNeuronal;
		this.salidasEsperadas = salidasEsperadas;
		this.factorAprendizaje = factorAprendizaje;
		this.momentum = momentum;
		this.errores = new ArrayList<Double>();
		for (int i = 0; i < redNeuronal.getNumeroTotalNeurona(); i++)
			errores.add(0.0);
		this.erroresEnCadaIteracion = new ArrayList<List<Double>>();
		erroresEnCadaIteracion.add(errores);
		
	}

	/* METODOS BASICOS */

	public RedNeuronal getRedNeuronal() {
		return redNeuronal;
	}

	public void setRedNeuronal(RedNeuronal redNeuronal) {
		this.redNeuronal = redNeuronal;
	}

	public List<Double> getErrores() {
		return errores;
	}

	public void setErrores(List<Double> errores) {
		this.errores = errores;
	}

	public List<Double> getSalidasEsperadas() {
		return salidasEsperadas;
	}

	public void setSalidasEsperadas(List<Double> salidasEsperadas) {
		this.salidasEsperadas = salidasEsperadas;
	}

	public Double getFactorAprendizaje() {
		return factorAprendizaje;
	}

	public void setFactorAprendizaje(Double factorAprendizaje) {
		this.factorAprendizaje = factorAprendizaje;
	}

	public Double getMomentum() {
		return momentum;
	}

	public void setMomentum(Double momentum) {
		this.momentum = momentum;
	}

	public String toString() {
		return "PropagaciónHaciaAtrás [getRedNeuronal()="
				+ getRedNeuronal().toString() + ", getErrores()="
				+ getErrores().toString() + ", getSalidasEsperadas()="
				+ getSalidasEsperadas().toString()
				+ ", getFactorAprendizaje()="
				+ getFactorAprendizaje().toString() + ", getMomentum()="
				+ getMomentum().toString() + "]";
	}
	
	/* OTROS METODOS */
	
	/** ALGORTIMO PROPAGACION HACIA ATRAS **/
	public RedNeuronal propagacionHaciaAtras() {
		
		Integer s = salidasEsperadas.size();
		
		/** comprueba si hay mismo numero de salidas esperadas que de salidas de la capa **/
		if (s != redNeuronal.getCapaSalida().size()){
				System.out.println("Error: distinto número de salidas esperadas que de salidas de la red.");
		}else {
			// CAPA SALIDA
			for (int i = 0; i < redNeuronal.getCapaSalida().size(); i++) {
				NeuronaSalida ns = redNeuronal.getCapaSalida().get(i);
				Double ai = ns.getSalida();
				Double y = salidasEsperadas.get(i);
				Integer id = ns.getId();
				
				
				//Calculo del error
				Double error = ai * (1 - ai) * (y - ai);
				errores.set(id, error);
				

				//Actualización de pesos
				Double a0 = -1.0;
				Double w0i = ns.getPesoUmbral();
				Double sumatorioMomentum = factorAprendizaje*a0*errores.get(ns.getId());
				for(List<Double> l : erroresEnCadaIteracion){
					sumatorioMomentum += momentum*factorAprendizaje*a0*l.get(ns.getId()) ;
				}
				w0i += sumatorioMomentum;
				redNeuronal.getCapaSalida().get(i).setPesoUmbral(w0i);
			}

			
			// CAPAS OCULTAS
			
			/** comprobacion de que hay capas ocultas **/
			if (redNeuronal.getCapasOcultas().size() != 0) {
				for (int j = redNeuronal.getCapasOcultas().size() - 1; j == 0; j--) {
					for (NeuronaOculta no : redNeuronal.getCapasOcultas().get(j)) { // PARA CADA CAPA
						Double ai = no.getSalida();
						Double sumatorio = 0.0;
						
						// Actualización de pesos
						
						for (Arco e : no.getSalidas()) {
							Double wij = e.getPeso();
							Double errorOcu = errores
									.get(e.getSalida().getId());
							sumatorio += wij * errorOcu;
						
							
							
							Double sumatorioMomentum = factorAprendizaje*no.getSalida()*errores.get(e.getSalida().getId());
							for(List<Double> l : erroresEnCadaIteracion){
								sumatorioMomentum += momentum*factorAprendizaje*no.getSalida()*l.get(e.getSalida().getId());
							}
							wij += sumatorioMomentum;
							e.setPeso(wij);
							
							
						}

						// Calculo del error de la neurona

						Double error = ai * (1 - ai) * sumatorio;
						errores.set(no.getId(), error);


						// Actualizacion de pesos

						Double a0 = -1.0;
						Double w0i = no.getPesoUmbral();

						
						Double sumatorioMomentum = factorAprendizaje*a0*errores.get(no.getId());
						for(List<Double> l : erroresEnCadaIteracion){
							sumatorioMomentum += momentum*factorAprendizaje*a0*l.get(no.getId());
						}
						w0i += sumatorioMomentum;
						no.setPesoUmbral(w0i);
	

					}
				}
			} else {

			}
			// CAPA ENTRADA

			for (NeuronaEntrada ne : redNeuronal.getCapaEntrada()) {

				// Actualizacion de pesos
				for (Arco e : ne.getSalidas()) {
				
					Double wij = e.getPeso();
					
					Double sumatorioMomentum = factorAprendizaje*ne.getSalida()*errores.get(e.getSalida().getId());
					for(List<Double> l : erroresEnCadaIteracion){
						sumatorioMomentum += momentum*factorAprendizaje*ne.getSalida()*l.get(e.getSalida().getId());
					}
					wij += sumatorioMomentum;
					e.setPeso(wij);
					
				}
			}
		}
		this.erroresEnCadaIteracion.add(errores); // Para el Momentum
		
		return redNeuronal;
	}


}
