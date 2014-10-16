package utilidad;

/* CLASE CREADA POR ALFONSO GASTALVER LLAMAS CON AYUDA DE ANTONIO PARRA RUIZ. */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;

// ESTA CLASE ES PARA LA INTERFAZ. NO ME DETENDRE A EXPLICARLA.
@SuppressWarnings("serial")
public class VentanaEloise extends javax.swing.JFrame {
	private boolean entrenamientoAbierta, validacionAbierta, testAbierta, clasificacionAbierta, overfitAbierta;
	 
	
    public VentanaEloise() {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        initComponents();
        setVisible(true);
        
    }
   
    	
    
    public void appendResult(String result){
        jtaResultados.append(result+"\n");
    }
    
    private void ejecutar(){
        jbClasificacion.setEnabled(false);
        jbEjecutar.setEnabled(false);
        jbEntrenamiento.setEnabled(false);
        jbOverfit.setEnabled(false);
        jbTest.setEnabled(false);
        jbValidacion.setEnabled(false);
        jtfEjecutar.setEnabled(false);
        ///
        String capasOc = jtfCapasOcultas.getText();
        String[] ar1 = capasOc.split(",");
        List<Integer> capasOcultas = new LinkedList<Integer>();
        if(ar1.length > 0){
            for(String s : ar1){
            	try{
                capasOcultas.add(new Integer(s));
            	}catch(Exception e){}
            }
        }
        Integer entrenamiento = (Integer) jsEntrenamiento.getValue();
        Integer validacion = (Integer) jsValidacion.getValue();
        Integer test = (Integer) jsTest.getValue();
        Integer overfit = (Integer) jsOverfit.getValue();
        Integer clasificacion = (Integer) jsClasificacion.getValue();
        Double momentum = (Double) jsMomentum.getValue();
        Double factorAprendizaje = (Double) jsFactorAprendizaje.getValue();
        
        Metodos.rPROP("cancer1.txt",capasOcultas, factorAprendizaje, momentum, entrenamiento, validacion, test, overfit, clasificacion);
        ///
        jbClasificacion.setEnabled(true);
        jbEjecutar.setEnabled(true);
        jbEntrenamiento.setEnabled(true);
        jbOverfit.setEnabled(true);
        jbTest.setEnabled(true);
        jbValidacion.setEnabled(true);
        jtfEjecutar.setEnabled(true);        
    }
    
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfEjecutar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbEjecutar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaResultados = new javax.swing.JTextArea();
        jbEntrenamiento = new javax.swing.JButton();
        jbValidacion = new javax.swing.JButton();
        jbTest = new javax.swing.JButton();
        jbClasificacion = new javax.swing.JButton();
        jbOverfit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtfCapasOcultas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jsEntrenamiento = new javax.swing.JSpinner();
        jsValidacion = new javax.swing.JSpinner();
        jsTest = new javax.swing.JSpinner();
        jsClasificacion = new javax.swing.JSpinner();
        jsOverfit = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jsFactorAprendizaje = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jsMomentum = new javax.swing.JSpinner();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eloise");
        setLocationByPlatform(true);

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("ELOISE");

        jLabel2.setText("Entrenadora de Redes Neuronales y Algoritmos, especializada en PROBEN1.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Archivo a ejecutar:");

        jtfEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEjecutarActionPerformed(evt);
            }
        });

        jLabel4.setText(".txt");

        jbEjecutar.setText("Ejecutar");
        jbEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEjecutarActionPerformed(evt);
            }
        });

        jtaResultados.setEditable(false);
        jtaResultados.setColumns(20);
        jtaResultados.setRows(5);
        jtaResultados.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jtaResultados);

        jbEntrenamiento.setText("Entrenamiento");
        jbEntrenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEntrenamientoActionPerformed(evt);
            }
        });

        jbValidacion.setText("Validacion");
        jbValidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbValidacionActionPerformed(evt);
            }
        });

        jbTest.setText("Test");
        jbTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTestActionPerformed(evt);
            }
        });

        jbClasificacion.setText("Clasificacion");
        jbClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClasificacionActionPerformed(evt);
            }
        });

        jbOverfit.setText("Overfit");
        jbOverfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOverfitActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Capas ocultas:");

        jtfCapasOcultas.setText("3,5");
        

        jLabel6.setText("Con el ejemplo \"3,5\" tendremos dos capas ocultas con 3 y 5 neuronas respectivamente.");

        jLabel7.setText("Si queremos mas capas, seguir el patron. Dejar en blanco para cero capas ocultas.");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Valores para separacion de graficas:");

        jsEntrenamiento.setModel(new javax.swing.SpinnerNumberModel(20, 1, 1000, 5));

        jsValidacion.setModel(new javax.swing.SpinnerNumberModel(5, 1, 1000, 5));

        jsTest.setModel(new javax.swing.SpinnerNumberModel(20, 1, 1000, 5));

        jsClasificacion.setModel(new javax.swing.SpinnerNumberModel(40, 1, 1000, 5));

        jsOverfit.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        jLabel9.setText("Entrenamiento:");

        jLabel10.setText("Validacion:");

        jLabel11.setText("Test: ");

        jLabel12.setText("Clasificacion:");

        jLabel13.setText("Overfit:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Constantes:");

        jLabel15.setText("Factor de Aprendizaje:");

        jsFactorAprendizaje.setModel(new javax.swing.SpinnerNumberModel(0.1d, 0.01d, 20.0d, 0.1d));

        jLabel16.setText("Momentum:");

        jsMomentum.setModel(new javax.swing.SpinnerNumberModel(0.1d, 0.01d, 20.0d, 0.1d));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCapasOcultas))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsEntrenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsValidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(55, 55, 55)
                                .addComponent(jbEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbEntrenamiento)
                                .addGap(18, 18, 18)
                                .addComponent(jbValidacion)
                                .addGap(18, 18, 18)
                                .addComponent(jbTest)
                                .addGap(18, 18, 18)
                                .addComponent(jbClasificacion)
                                .addGap(18, 18, 18)
                                .addComponent(jbOverfit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsFactorAprendizaje, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsTest, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsOverfit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jbEjecutar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfCapasOcultas)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jsFactorAprendizaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jsMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jsEntrenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jsValidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jsOverfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jsTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbOverfit)
                    .addComponent(jbEntrenamiento)
                    .addComponent(jbValidacion)
                    .addComponent(jbTest)
                    .addComponent(jbClasificacion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEjecutarActionPerformed
        ejecutar();
    }//GEN-LAST:event_jtfEjecutarActionPerformed

    private void jbEjecutarActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutar();
    }


	private void jbEntrenamientoActionPerformed(java.awt.event.ActionEvent evt) {
    	JDialogImage e=null;
    	if(!entrenamientoAbierta){
        e = new JDialogImage("Errores de Entrenamiento","archivos/imagenes/erroresEntrenamiento.jpg");
       entrenamientoAbierta=true;
       
       e.addWindowListener(new WindowAdapter() {
           public void windowClosed(WindowEvent e) {
               entrenamientoAbierta = false;
           }

           public void windowClosing(WindowEvent e) {
        	   entrenamientoAbierta = false;
           }
       });
    	}
    
    }

 
	private void jbValidacionActionPerformed(java.awt.event.ActionEvent evt) {
    	JDialogImage e=null;
    	if(!validacionAbierta){
    	  e = new JDialogImage("Errores de Validacion","archivos/imagenes/erroresValidacion.jpg");
    	  validacionAbierta = true;
    	  e.addWindowListener(new WindowAdapter() {
              public void windowClosed(WindowEvent e) {
                  validacionAbierta = false;
              }

              public void windowClosing(WindowEvent e) {
           	   validacionAbierta = false;
              }
          });
       	}
    	
    	}
    	
    


	private void jbTestActionPerformed(java.awt.event.ActionEvent evt) {
    	JDialogImage e=null;
    	if(!testAbierta){
    		 e = new JDialogImage("Errores de Testeo","archivos/imagenes/erroresTesteo.jpg");
    	  testAbierta=true;
    	  e.addWindowListener(new WindowAdapter() {
              public void windowClosed(WindowEvent e) {
                  testAbierta = false;
              }

              public void windowClosing(WindowEvent e) {
           	   testAbierta = false;
              }
          });
       	}
    
    	}
    


	private void jbClasificacionActionPerformed(java.awt.event.ActionEvent evt) {
    	JDialogImage e=null;
    	if(!clasificacionAbierta){
    		 e = new JDialogImage("Errores de Clasificacion en el Testeo","archivos/imagenes/erroresTesteoClasificacion.jpg");
    	  clasificacionAbierta=true;
    	  e.addWindowListener(new WindowAdapter() {
              public void windowClosed(WindowEvent e) {
                  clasificacionAbierta = false;
              }

              public void windowClosing(WindowEvent e) {
           	   clasificacionAbierta = false;
              }
          });
       	}
    
    	}
    

	private void jbOverfitActionPerformed(java.awt.event.ActionEvent evt) {
    	JDialogImage e=null;
    	if(!overfitAbierta){
    		 e = new JDialogImage("Crecimiento del GL","archivos/imagenes/CrecimientoGL.jpg");
    	  overfitAbierta =true;
    	  e.addWindowListener(new WindowAdapter() {
              public void windowClosed(WindowEvent e) {
                  overfitAbierta = false;
              }

              public void windowClosing(WindowEvent e) {
           	   overfitAbierta = false;
              }
          });
       	}
    	
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbClasificacion;
    private javax.swing.JButton jbEjecutar;
    private javax.swing.JButton jbEntrenamiento;
    private javax.swing.JButton jbOverfit;
    private javax.swing.JButton jbTest;
    private javax.swing.JButton jbValidacion;
    private javax.swing.JSpinner jsClasificacion;
    private javax.swing.JSpinner jsEntrenamiento;
    private javax.swing.JSpinner jsFactorAprendizaje;
    private javax.swing.JSpinner jsMomentum;
    private javax.swing.JSpinner jsOverfit;
    private javax.swing.JSpinner jsTest;
    private javax.swing.JSpinner jsValidacion;
    private javax.swing.JTextArea jtaResultados;
    private javax.swing.JTextField jtfCapasOcultas;
    private javax.swing.JTextField jtfEjecutar;
    // End of variables declaration//GEN-END:variables


	public class JDialogImage extends JDialog{
        
        Image im;
        
        public JDialogImage(String title, String image){
            setResizable(false);
            setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
            setSize(500, 300);
            setTitle(title);
            setLocationByPlatform(true);
            try {
                im = ImageIO.read(new File(image));
            } catch (IOException ex) {
                System.out.println("Error cargando imagen: "+image);
            }
			JPanel jp = new JPanel(){
                @Override
                public void paint(Graphics g){
                    g.drawImage(im, 0, 0, null);
                }
            };
            setContentPane(jp);
            
            setVisible(true);
        }
    }

}
