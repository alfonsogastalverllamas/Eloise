package utilidad;

/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS */

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import java.io.File;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;

import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import domain.Ejemplo;

public class Metodos {
	
	/* ATRIBUTOS */
	public static VentanaEloise ventana;
	
	/* METODOS ESTATICOS */
	
	/** Algoritmo RetroPropagacion **/
	public static RedNeuronal retroPropagacion(RedNeuronal red,
			List<Double> entradas, List<Double> salidasEsperadas,
			Double factorAprendizaje, Double momentum) {
		RedNeuronal redN = red;
		PropagacionAdelante p1 = new PropagacionAdelante(redN, entradas);

		/** propagar hacia adelante **/
		redN = p1.propagacionAdelante();

		PropagacionHaciaAtras p2 = new PropagacionHaciaAtras(redN,
				salidasEsperadas, factorAprendizaje, momentum);
		/** propagar hacia atrás **/
		redN = p2.propagacionHaciaAtras();
		return redN;

	}
	
	/** ALGORITMO VALIDACION CRUZADA **/
	public static List<Double> validacionCruzada(String fichero,
			List<Integer> ocultas, Double factorAprendizaje, Double momentum,
			Integer ntraining, Integer nvalidation, Integer ntest,
			Integer ntestC, Integer ngL) {

		ventana.appendResult("Iniciando validacion cruzada..."); 
		
		//Atributos que necesitaremos.
		List<Ejemplo> ejemplos = new ArrayList<Ejemplo>(); //CONJUNTO DE EJEMPLOS
		Integer ent = 0; //Numero de entradas
		Integer sal = 0; //Numero de salidas
		Integer training = 0; //Numero de ejemplos de entrenamiento
		Integer validation = 0; //Numero de ejemplos de validacion
		Integer test = 0; //Numero de ejemplos de validacion

		List<Double> erroresEpocas = new ArrayList<Double>();
		Double errorEpocasMinimo = 10000.0;

		Double gL = 0.0;
		Double errorValidacion = 10000.0;

		Double pK = 0.0;

		// Para mostrar resultados:
		List<Double> overfit = new ArrayList<Double>();
		List<Double> erroresEntrenamiento = new ArrayList<Double>();
		List<Double> erroresValidacion = new ArrayList<Double>();
		List<Double> erroresTest = new ArrayList<Double>();
		List<Double> erroresTestClasification = new ArrayList<Double>();

		List<Double> epocas = new ArrayList<Double>();
		List<Double> epocasRelevantes = new ArrayList<Double>();
		Integer nEpocas = 0;

		Metodos.ventana.appendResult("Leyendo archivo de entrada...");
		//LECTURA DE ARCHIVO
		try {
			BufferedReader in = new BufferedReader(new FileReader("archivos/"+fichero));
			String s;
			List<String> l = new ArrayList<String>();

			while ((s = in.readLine()) != null) {
				l.add(s);
			}
			in.close();

			for (int ins = 0; ins < 7; ins++) {
				String[] var = l.get(ins).split("=");
				if (ins == 0 || ins == 1)
					ent += new Integer(var[1]);
				if (ins == 2 || ins == 3)
					sal += new Integer(var[1]);
				if (ins == 4)
					training = new Integer(var[1]);
				if (ins == 5)
					validation = new Integer(var[1]);
				if (ins == 6)
					test = new Integer(var[1]);
			}
			for (int i = 7; i < l.size(); i++) {
				String str = l.get(i).replaceAll("  ", " ");
				String[] var2 = str.split(" ");
				List<Double> entradas = new ArrayList<Double>();
				List<Double> salidasEsperadas = new ArrayList<Double>();
				for (int j = 0; j < ent; j++) {
					entradas.add(new Double(var2[j]));
				}
				for (int k = ent; k < var2.length; k++) {
					salidasEsperadas.add(new Double(var2[k]));
				}
				ejemplos.add(new Ejemplo(entradas, salidasEsperadas));
			}

		} catch (Exception e) {
			Metodos.ventana.appendResult("Fichero erróneo.");
			e.printStackTrace();
		}
		
		//CREAR RED NEURONAL
		Metodos.ventana.appendResult("Creando red neuronal...");
		RedNeuronal red = new RedNeuronal(ent, ocultas, sal);
		Metodos.ventana.appendResult("La Red Neuronal Queda Definida Por: \n"+red.toString() + "\n\n");

		Boolean criterioParada = false; //3000 Iteraciones / PkMin / GL>5
		Integer nIteraciones = 0; //Contador

		//INICIO DEL ALGORITMO DE ENTRENAMIENTO (EPOCAS)
		Metodos.ventana.appendResult("Iniciando fase de entrenamiento...");
		while (!criterioParada) {
			Double errorPkMin = 10000.0; //Inicializamos errorMinimo a un valor alto.
			Double errorPk = 0.0;
			
			// EPOCAS k=5
			for (int epo = 0; epo < 5; epo++) {

				List<Double> erroresTrainings = new ArrayList<Double>();
				Double salidaMax = 0.0;
				Double salidaMin = 10000.0;
				
				// EJEMPLOS DEL CONJUNTO DE ENTRENAMIENTO
				for (int i = 0; i < training; i++) {
					red = retroPropagacion(red, ejemplos.get(i).entradas,
							ejemplos.get(i).salidasEsperadas,
							factorAprendizaje, momentum);
					//Errores minimos cuadráticos
					List<Double> erroresSalidas = new ArrayList<Double>();
					List<Double> sali = ejemplos.get(i).salidasEsperadas;
					for (int j = 0; j < sali.size(); j++) {
						Double error;
						Double salida = red.getCapaSalida().get(j).getSalida();
						error = Math.pow(sali.get(j) - salida, 2);
						erroresSalidas.add(error);
						if (salidaMax < salida)
							salidaMax = salida;
						if (salidaMin > salida)
							salidaMin = salida;
					}
					Double errorSalida = 0.0;
					for (Double err : erroresSalidas) {
						errorSalida += err;
					}
					erroresTrainings.add(errorSalida);
				}
				
				//Errores del conjunto de entrenamiento.
				Double errorEv = 0.0;
				for (Double err : erroresTrainings)
					errorEv += err;
				Double errorEpoca = 100
						* ((salidaMax - salidaMin) / (sal * training))
						* errorEv;
				erroresEpocas.add(errorEpoca);
				errorPk += errorEpoca;
				nEpocas++;
				epocas.add(new Double(nEpocas));

				if (errorEpocasMinimo > errorEpoca) {
					errorEpocasMinimo = errorEpoca;
					erroresEntrenamiento.add(errorEpoca);
				}
				if (errorPkMin > errorEpoca)
					errorPkMin = errorEpoca;

			}
			
			//CALCULO DE Pk
			pK = 1000 * ((errorPk / (5 * errorPkMin)) - 1);
			errorPk = 0.0;
			errorPkMin = 0.0;
			System.out.println("El valor de pk es: " + pK);
			
			
			// VALIDACION
			List<Double> erroresValidations = new ArrayList<Double>();
			Double salidaVMax = 0.0;
			Double salidaVMin = 10000.0;
			
			//Ejemplos de Validacion
			for (int j = training; j < training + validation; j++) {
				PropagacionAdelante p1 = new PropagacionAdelante(red,
						ejemplos.get(j).entradas);
				red = p1.propagacionAdelante();

				List<Double> erroresSalidasV = new ArrayList<Double>();
				List<Double> saliV = ejemplos.get(j).salidasEsperadas;
				
				//Errores minimos cuadraticos de validacion
				for (int i = 0; i < saliV.size(); i++) {
					Double errorV;
					Double salidaV = red.getCapaSalida().get(i).getSalida();
					errorV = Math.pow(saliV.get(i) - salidaV, 2);

					erroresSalidasV.add(errorV);
					if (salidaVMax < salidaV)
						salidaVMax = salidaV;

					if (salidaVMin > salidaV)
						salidaVMin = salidaV;

				}
				Double errorSalidaV = 0.0;
				for (Double err : erroresSalidasV)
					errorSalidaV += err;
				erroresValidations.add(errorSalidaV);

			}
			Double errV = 0.0;
			for (Double errc : erroresValidations)
				errV += errc;

			Double errorValidacionActual = 100 * ((salidaVMax - salidaVMin)
					* errV / (sal * validation));

			if (errorValidacion > errorValidacionActual) {
				errorValidacion = errorValidacionActual;
				epocasRelevantes.add(new Double(nEpocas));
				erroresValidacion.add(errorValidacionActual);

			}
			
			
			//Calculo de GL
			gL = 100 * ((errorValidacionActual / errorValidacion) - 1);

			overfit.add(gL);

			nIteraciones++;
			System.out.println("GL: " + gL);
			
			//Comprobacion de Criterio Parada
			criterioParada = (nIteraciones >= 3000 || gL > 5.0 || pK < 0.1);

		}
		Metodos.ventana.appendResult("CRITERIO OK");
		Metodos.ventana.appendResult("Fase entrenamiento terminada:");
		Metodos.ventana.appendResult("Iteraciones: " + nIteraciones);
		Metodos.ventana.appendResult("Valor GL: " + gL);
		Metodos.ventana.appendResult("Epocas: " + nEpocas);
		Metodos.ventana.appendResult("Epocas Relevantes: "
				+ epocasRelevantes.size());

		System.out.println("Iteraciones: " + nIteraciones);
		System.out.println("Epocas: " + nEpocas);
		System.out.println("EpocasRelevantes: " + epocasRelevantes.size());
		
		
		// TEST
		System.out.println("CRITERIO OK");

		Metodos.ventana.appendResult("Iniciando fase testeo...");
		//Ejemplos de Test
		for (int k = training + validation; k < training + validation + test; k++) {
			PropagacionAdelante p1 = new PropagacionAdelante(red,
					ejemplos.get(k).entradas);
			red = p1.propagacionAdelante();

			Double salidaTMax = 0.0;
			Double salidaTMin = 10000.0;
			List<Double> erroresSalidasT = new ArrayList<Double>();
			List<Double> saliT = ejemplos.get(k).salidasEsperadas;
			
			//Errores de Test y de Clasificacion de Test
			for (int i = 0; i < saliT.size(); i++) {
				Double errorT;
				Double salidaT = red.getCapaSalida().get(i).getSalida();
				Double errorTestClasification = Math
						.abs(saliT.get(i) - salidaT);
				erroresTestClasification.add(new Double(Math
						.round(errorTestClasification) * 100));
				errorT = Math.pow(errorTestClasification, 2);

				erroresSalidasT.add(errorT);
				if (salidaTMax < salidaT)
					salidaTMax = salidaT;

				if (salidaTMin > salidaT)
					salidaTMin = salidaT;

			}

			Double errorSalidaT = 0.0;
			for (Double err : erroresSalidasT)
				errorSalidaT += err;
			Double errorTest = 100 * (((salidaTMax - salidaTMin) * errorSalidaT) / errorValidacion);
			erroresTest.add(errorTest);

		}
		
		//Finalizado el Algoritmo
		Metodos.ventana.appendResult("Finalizada la fase de testeo.");

		Metodos.ventana
				.appendResult("FIN DEL ALGORITMO DE VALIDACION CRUZADA.\n\n\n");
		System.out.println("FIN");

		//Metodo para crear graficas, mostrar resultados y crear archivo de texto con ellos.
		mostrarResultados(erroresEntrenamiento, erroresValidacion, erroresTest,
				erroresTestClasification, overfit, epocas, epocasRelevantes,
				nIteraciones, ntraining, nvalidation, ntest, ntestC, ngL);

		return red.getSalidas();

	}

	//Funcion Media Aritmetica
	public static Double media(List<Double> l) {
		Double res = 0.0;
		for (Double d : l)
			res += d;
		return res / l.size();
	}

	//Funcion Desviacion Tipica
	public static Double desviacionTipica(List<Double> l, Double media) {
		Double res = 0.0;
		for (Double d : l) {
			res += Math.pow(d - media, 2);
		}
		return Math.sqrt(res / (l.size() - 1));
	}

	//Metodo para mostrar resultados
	public static void mostrarResultados(List<Double> erroresTrainings,
			List<Double> erroresValidation, List<Double> erroresTest,
			List<Double> erroresTestClasification, List<Double> overfit,
			List<Double> epocas, List<Double> epocasRelevantes,
			Integer nIteraciones, Integer training, Integer validation,
			Integer test, Integer testC, Integer gL) {
		//Calculos de datos
		Double mediaT = media(erroresTrainings);
		Double desviacionT = desviacionTipica(erroresTrainings, mediaT);

		Double mediaV = media(erroresValidation);
		Double desviacionV = desviacionTipica(erroresValidation, mediaV);

		Double mediaTe = media(erroresTest);
		Double desviacionTe = desviacionTipica(erroresTest, mediaTe);

		Double p = mediaTe / mediaV;

		Double mediaTeC = media(erroresTestClasification);
		Double desviacionTeC = desviacionTipica(erroresTestClasification,
				mediaTeC);

		Double mediaO = media(overfit);
		Double desviacionO = desviacionTipica(overfit, mediaO);

		Double mediaE = media(epocas);
		Double desviacionE = desviacionTipica(epocas, mediaE);

		Double mediaER = media(epocasRelevantes);
		Double desviacionER = desviacionTipica(epocasRelevantes, mediaER);

		//Mostrar resultados en el programa
		Metodos.ventana
		.appendResult("------------------------------------");
		Metodos.ventana
		.appendResult("RESULTADOS DEL ALGORITMO:");
		Metodos.ventana
		.appendResult("------------------------------------ \n");
		Metodos.ventana
				.appendResult("La media de errores de Entrenamiento es: "
						+ mediaT);
		Metodos.ventana
				.appendResult("La desviacion tipica de errores de Entrenamiento es: "
						+ desviacionT);
		Metodos.ventana.appendResult("\nLa media de errores de Validacion es: "
				+ mediaV);
		Metodos.ventana
				.appendResult("La desviacion tipica de errores de Validacion es: "
						+ desviacionV);
		Metodos.ventana.appendResult("\nLa media de errores de Testeo es: "
				+ mediaTe);
		Metodos.ventana
				.appendResult("La desviacion tipica de errores de Testeo es: "
						+ desviacionTe);
		Metodos.ventana
				.appendResult("\nLa correlacion entre Validación y Testeo es: "
						+ p);
		Metodos.ventana
				.appendResult("\nLa media de errores de Testeo de Clasificacion es: "
						+ mediaTeC);
		Metodos.ventana
				.appendResult("La desviacion tipica de errores de Testeo de Clasificacion es: "
						+ desviacionTeC);
		Metodos.ventana.appendResult("\nLa media del Overfit es: " + mediaO);
		Metodos.ventana.appendResult("La desviacion tipica del Overfit es: "
				+ desviacionO);
		Metodos.ventana.appendResult("\nLa media de Epocas es: " + mediaE);
		Metodos.ventana.appendResult("La desviacion tipica de Epocas es: "
				+ desviacionE);
		Metodos.ventana.appendResult("\nLa media de Epocas Relevantes es: "
				+ mediaER);
		Metodos.ventana
				.appendResult("La desviacion tipica de Epocas Relevantes es: "
						+ desviacionER);
		
		Metodos.ventana
		.appendResult("------------------------------------");
		//MOSTRARLO POR PANTALLA DE JAVA
		System.out
				.println("La media de errores de Entrenamiento es: " + mediaT);
		System.out
				.println("La desviacion tipica de errores de Entrenamiento es: "
						+ desviacionT);
		System.out.println("La media de errores de Validacion es: " + mediaV);
		System.out.println("La desviacion tipica de errores de Validacion es: "
				+ desviacionV);
		System.out.println("La media de errores de Testeo es: " + mediaTe);
		System.out.println("La desviacion tipica de errores de Testeo es: "
				+ desviacionTe);
		System.out.println("La correlacion entre Validación y Testeo es: " + p);
		System.out
				.println("La media de errores de Testeo de Clasificacion es: "
						+ mediaTeC);
		System.out
				.println("La desviacion tipica de errores de Testeo de Clasificacion es: "
						+ desviacionTeC);
		System.out.println("La media del Overfit es: " + mediaO);
		System.out.println("La desviacion tipica del Overfit es: "
				+ desviacionO);
		System.out.println("La media de Epocas es: " + mediaE);
		System.out.println("La desviacion tipica de Epocas es: " + desviacionE);
		System.out.println("La media de Epocas Relevantes es: " + mediaER);
		System.out.println("La desviacion tipica de Epocas Relevantes es: "
				+ desviacionER);
		
		//CREACION DE ARCHIVO DE TEXTO SE GUARDA EN IMAGENES
		File f;
		f = new File("archivos/imagenes/Estadisticas.txt");
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write("La media de errores de Entrenamiento es: "
					+ mediaT
					+ ".\nLa desviacion tipica de errores de Entrenamiento es: "
					+ desviacionT);
			wr.append("\n\nLa media de errores de Validacion es: " + mediaV
					+ ".\nLa desviacion tipica de errores de Validacion es: "
					+ desviacionV);
			wr.append("\n\nLa media de errores de Testeo es: " + mediaTe
					+ ".\nLa desviacion tipica de errores de Testeo es: "
					+ desviacionTe);
			wr.append("\n\nLa correlacion entre Validación y Testeo es: " + p);
			wr.append("\n\nLa media de errores de Testeo Clasificiacion es: "
					+ mediaTeC
					+ ".\nLa desviacion tipica de errores de Validacion es: "
					+ desviacionTeC);
			wr.append("\n\nLa media de Overfit es: " + mediaO
					+ ".\nLa desviacion tipica de Overfit es: " + desviacionO);
			wr.append("\n\nLa media de Epocas es: " + mediaE
					+ ".\nLa desviacion tipica de Epocas es: " + desviacionE);
			wr.append("\n\nLa media de EpocasRelevantes es: " + mediaER
					+ ".\nLa desviacion tipica de EpocasRelevantes es: "
					+ desviacionER);
			
			wr.close();
			bw.close();

		} catch (IOException e) {
		}
		;

		// CREACION DE GRAFICAS. No voy a detenerme a explicar esto. Pues he usado la libreria JChart.
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < erroresTrainings.size(); i += training) {
			Integer e = (int) Math.round(epocas.get(i));
			dataset.setValue(erroresTrainings.get(i), "Epocas", e.toString());
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Errores De Entrenamiento", "Número de Epocas",
				"Porcentaje de Error", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		chart.setBackgroundPaint(Color.white);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
				0.0f, new Color(0, 64, 64));

		renderer.setSeriesPaint(0, gp0);

		try {
			ChartUtilities.saveChartAsJPEG(new File(
					"archivos/imagenes/erroresEntrenamiento.jpg"), chart, 500, 300);
		} catch (IOException e) {
			System.err.println("Error creando grafico.");
		}


		DefaultCategoryDataset datasetValidacion = new DefaultCategoryDataset();
		for (int i = 0; i < erroresValidation.size(); i += validation) {
			datasetValidacion.setValue(erroresValidation.get(i),
					"Número de Ejemplo", new Integer(i + 1).toString());
		}

		JFreeChart chartValidacion = ChartFactory.createBarChart(
				"Errores De Validacion", "Número de Ejemplos",
				"Porcentaje de Error", datasetValidacion,
				PlotOrientation.VERTICAL, false, true, false);
		chartValidacion.setBackgroundPaint(Color.white);

		CategoryPlot plotValidacion = (CategoryPlot) chartValidacion.getPlot();


		BarRenderer rendererValidacion = (BarRenderer) plotValidacion
				.getRenderer();
		rendererValidacion.setDrawBarOutline(false);

		
		GradientPaint gp0Validacion = new GradientPaint(0.0f, 0.0f,
				Color.yellow, 0.0f, 0.0f, new Color(0, 64, 64));

		rendererValidacion.setSeriesPaint(0, gp0Validacion);

		try {
			ChartUtilities.saveChartAsJPEG(new File(
					"archivos/imagenes/erroresValidacion.jpg"), chartValidacion, 500,
					300);
		} catch (IOException e) {
			System.err.println("Error creando grafico.");
		}

		

		DefaultCategoryDataset datasetTest = new DefaultCategoryDataset();
		for (int i = 0; i < erroresTest.size(); i += test) {
			datasetTest.setValue(erroresTest.get(i), "Número de Ejemplo",
					new Integer(i + 1).toString());
		}

		JFreeChart chartTest = ChartFactory.createBarChart("Errores De Testeo",
				"Número de Ejemplos", "Porcentaje de Error", datasetTest,
				PlotOrientation.VERTICAL, false, true, false);
		chartTest.setBackgroundPaint(Color.white);

		CategoryPlot plotTest = (CategoryPlot) chartTest.getPlot();

		
		BarRenderer rendererTest = (BarRenderer) plotTest.getRenderer();
		rendererTest.setDrawBarOutline(false);

		
		GradientPaint gp0Test = new GradientPaint(0.0f, 0.0f, Color.green,
				0.0f, 0.0f, new Color(0, 64, 64));

		rendererTest.setSeriesPaint(0, gp0Test);

		try {
			ChartUtilities.saveChartAsJPEG(new File(
					"archivos/imagenes/erroresTesteo.jpg"), chartTest, 500, 300);
		} catch (IOException e) {
			System.err.println("Error creando grafico.");
		}

		
		DefaultCategoryDataset datasetTestC = new DefaultCategoryDataset();
		for (int i = 0; i < erroresTestClasification.size(); i += testC) {
			datasetTestC.setValue(erroresTestClasification.get(i),
					"Número de Ejemplo", new Integer(i + 1).toString());
		}

		JFreeChart chartTestC = ChartFactory.createBarChart(
				"Errores De Testeo de Clasificacion", "Número de Ejemplos",
				"Error", datasetTestC, PlotOrientation.VERTICAL, false, true,
				false);
		chartTestC.setBackgroundPaint(Color.white);

		CategoryPlot plotTestC = (CategoryPlot) chartTestC.getPlot();

		
		BarRenderer rendererTestC = (BarRenderer) plotTestC.getRenderer();
		rendererTestC.setDrawBarOutline(false);

		
		GradientPaint gp0TestC = new GradientPaint(0.0f, 0.0f, Color.blue,
				0.0f, 0.0f, new Color(0, 64, 64));

		rendererTest.setSeriesPaint(0, gp0TestC);

		try {
			ChartUtilities.saveChartAsJPEG(new File(
					"archivos/imagenes/erroresTesteoClasificacion.jpg"), chartTestC,
					500, 300);
		} catch (IOException e) {
			System.err.println("Error creando grafico.");
		}

		
		XYSeries series = new XYSeries("Crecimiento GL");
		for (int i = 0; i < overfit.size(); i += gL) {
			series.add(i + 1, overfit.get(i));
		}
		
		XYSeriesCollection datasetGL = new XYSeriesCollection();
		datasetGL.addSeries(series);
		
		
		JFreeChart chartGL = ChartFactory.createXYAreaChart("Crecimiento GL", 
				"Iteracion", 
				"Valor de GL", 
				datasetGL, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				false 
				);
		try {
			ChartUtilities.saveChartAsJPEG(new File(
					"archivos/imagenes/CrecimientoGL.jpg"), chartGL, 500, 300);
		} catch (IOException e) {
			System.err.println("Error creando grafico.");
		}
		
		Generator.resetId();
	}

	//DISTINTAS LLAMADAS AL ALGORITMO GENERAL RPROP QUE MUESTRA EL TIEMPO TOTAL DE EJECUCION
	public static List<Double> rPROP(String fichero) {
		long tiempoInicio = System.currentTimeMillis();
		List<Double> res = validacionCruzada(fichero, new ArrayList<Integer>(),
				0.1, 0.9, 1, 1, 1, 1, 1);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		Metodos.ventana
		.appendResult("\n El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		System.out.println("El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		return res;
	}

	public static List<Double> rPROP(String fichero, List<Integer> ocultas) {
		long tiempoInicio = System.currentTimeMillis();
		List<Double> res = validacionCruzada(fichero, ocultas, 0.1, 0.9, 40, 5,
				60, 60, 1);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		Metodos.ventana
		.appendResult("\n El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		return res;
	}

	public static List<Double> rPROP(String fichero, List<Integer> ocultas,
			Double factorAprendizaje, Double momentum, Integer training,
			Integer validation, Integer test, Integer testC, Integer gL) {
		long tiempoInicio = System.currentTimeMillis();
		List<Double> res = validacionCruzada(fichero, ocultas,
				factorAprendizaje, momentum, training, validation, test, testC,
				gL);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		Metodos.ventana
		.appendResult("\n El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		return res;
	}

	public static List<Double> rPROP(String fichero, List<Integer> ocultas,
			Double factorAprendizaje) {
		long tiempoInicio = System.currentTimeMillis();
		List<Double> res = validacionCruzada(fichero, ocultas,
				factorAprendizaje, 0.9, 40, 5, 50, 60, 1);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		Metodos.ventana
		.appendResult("\n El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		return res;
	}

	public static List<Double> rPROP(String fichero, List<Integer> ocultas,
			Double factorAprendizaje, Double momentum) {
		long tiempoInicio = System.currentTimeMillis();
		List<Double> res = validacionCruzada(fichero, ocultas,
				factorAprendizaje, momentum, 40, 5, 50, 60, 1);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		Metodos.ventana
		.appendResult("\n El tiempo total de la ejecución es: " + totalTiempo
				+ " miliseg");
		return res;
	}

}
