package utilidad;
/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS CON AYUDA DE ANTONIO PARRA RUIZ */
// ESTA CLASE ES UTILIZADA PARA GENERAR IDS DE LAS NEURONAS

public class Generator {
private static Integer id = 0;
public static Integer getId(){
	return id++;
}
public static void resetId(){
	id=0;
}
}
