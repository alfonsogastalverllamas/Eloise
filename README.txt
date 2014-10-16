README

PROYECTO REALIZADO POR ALFONSO GASTALVER LLAMAS.

Para hacer funcionar este proyecto debemos descomprimir los archivos de tal manera que quede de la misma forma en la que está es decir siendo imprescindible que el archivo Eloise.jar esté en la misma ruta que las carpetas: "archivos" y "Eloise_lib".

Lo único que hemos de hacer es ejecutar "Eloise.jar".

La Ejecución genera unas gráficas y un archivo de texto con las estadísticas que se puede comprobar en la carpeta: archivos/imagenes.

Los archivos que se pueden usar en la ejecucion son los .txt que hay dentro de la carpeta archivos. Para añadir nuevos problemas hemos de añadir un .txt con formato PROBEN1 en dicha carpeta y éste lo reconocerá.

Para crear redes ocultas se deberá poner el número de neuronas por capa que desee en el campo de texto habilitado para ello en el formato siguiente: "NúmeroNeuronasCapa1,NúmeroNeuronasCapa2,..."
Ejemplo: 3,2,1 --> 3 Neuronas en la capa oculta 1ª, dos en la 2ª y uno en la ultima.

Las gráficas dependen del número de ejemplos, por ello pueden modificarse los saltos de gráfica para que la gráfica se ajuste a dicho número. Para ello están habilitado 5 campos, uno por gráfica.

IMPORTANTE.
Factor de Aprendizaje y Momentum deben ir en formato Double, que se hará notar que es 0,algo y no 0.algo como estamos normalmente acostumbrados. Por defecto 0,1 / 0,9.

FIN. Los botones de las gráficas deben cerrarse en la x de la esquina.

COMPROBAR AL CERRAR LA APLICACION QUE JAVA NO SE SIGUE EJECUTANDO.