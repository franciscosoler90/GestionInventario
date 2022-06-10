package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

import info.Info;
import utilidades.GestionCeldas;
import utilidades.GestionEncabezadoTabla;
import utilidades.Lectura;
import utilidades.ModeloTabla;
import utilidades.TablaInfo;
import utilidades.Utilidades;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaTabla extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneTabla;
	private JTable tabla;
	
	TablaInfo tablaInfo = new TablaInfo();

	ModeloTabla modelo;

	/**
	 * Create the frame.
	 */
	public VentanaTabla() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTabla.class.getResource("/recursos/iconos/base-de-datos.png")));
		setTitle("Gestión Inventario");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 460);
		
		iniciarComponentes();
		setLocationRelativeTo(null);
		
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTabla = new JScrollPane();
		contentPane.add(scrollPaneTabla);
		
		tabla = new JTable();
		tabla.setBackground(Color.WHITE);
		tabla.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabla.addMouseListener(this);
		tabla.setOpaque(false);
		scrollPaneTabla.setViewportView(tabla);
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Inicio");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo archivo...");
		
		
		mnNewMenu.add(mntmNewMenuItem);
		this.setVisible(true);
		
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Lanza la ventana FileChooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				//Filtro para archivos de texto
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
				fileChooser.addChoosableFileFilter(filter);
				
				
				int seleccion = fileChooser.showOpenDialog(null);
				
				if (seleccion == JFileChooser.APPROVE_OPTION){
					
				   File fichero = fileChooser.getSelectedFile();
	
					try {
						
						tablaInfo = Lectura.leerArchivo(fichero);
						
						   if(tablaInfo!=null && tablaInfo.getIsValid() ) {
							   
							   //Construir tabla
							   construirTabla(tablaInfo);
							   
						   }else {
							   mostrarMensaje("Error", "Archivo no válido", 0);
						   }					
	
					} catch (Exception e1) {
						mostrarMensaje("Error", "Archivo no válido", 0);
					}
				   
				}
				
			}
		});
	}

	private void construirTabla(TablaInfo tablaInfo) {
		
		ArrayList<String> cabecera = tablaInfo.getCabecera();
		
		//Campos extra
		cabecera.add(" ");
		cabecera.add(" ");
		
		ArrayList<Info> listaInfo = tablaInfo.getListaInfo();
		
		String titulos[] = new String[cabecera.size()];
		for (int i = 0; i < titulos.length; i++) {
			titulos[i]=cabecera.get(i);
		}
		Object[][] data = obtenerMatrizDatos(cabecera, listaInfo);
		construirTabla(titulos,data);
		
	}

	private Object[][] obtenerMatrizDatos(ArrayList<String> cabecera, ArrayList<Info> listaInfo) {

		//Array de dos dimensiones, filas x columnas
		String informacion[][] = new String[listaInfo.size()][cabecera.size()];

		//Rellena la información de las celdas
		for (int x = 0; x < informacion.length; x++) {
			informacion[x][Utilidades.EMPRESA] = listaInfo.get(x).getEmpresa()+ "";
			informacion[x][Utilidades.ALMACEN] = listaInfo.get(x).getAlmacen()+ "";
			informacion[x][Utilidades.COD_ARTICULO] = listaInfo.get(x).getCodArticulo()+ "";
			informacion[x][Utilidades.DESC_ARTICULO] = listaInfo.get(x).getDesArticulo()+ "";
			informacion[x][Utilidades.UBICACION] = listaInfo.get(x).getUbicacion()+ "";
			informacion[x][Utilidades.EXISTENCIAS] = listaInfo.get(x).getExistencias()+ "";
			informacion[x][Utilidades.FECHA_HORA] = listaInfo.get(x).getFechaHora()+ "";
			
			
			//Campos extra
			informacion[x][Utilidades.PERFIL] = "PERFIL";
			informacion[x][Utilidades.EVENTO] = "EVENTO"; 
			
		}

		return informacion;
	}
	

	private void construirTabla(String[] titulos, Object[][] data) {
		

		modelo=new ModeloTabla(data, titulos);
		//se asigna el modelo a la tabla
		tabla.setModel(modelo);

		//se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
		tabla.getColumnModel().getColumn(Utilidades.EMPRESA).setCellRenderer(new GestionCeldas("numerico"));
		tabla.getColumnModel().getColumn(Utilidades.ALMACEN).setCellRenderer(new GestionCeldas("numerico"));
		tabla.getColumnModel().getColumn(Utilidades.COD_ARTICULO).setCellRenderer(new GestionCeldas("texto"));
		tabla.getColumnModel().getColumn(Utilidades.DESC_ARTICULO).setCellRenderer(new GestionCeldas("texto"));
		tabla.getColumnModel().getColumn(Utilidades.UBICACION).setCellRenderer(new GestionCeldas("texto"));
		tabla.getColumnModel().getColumn(Utilidades.EXISTENCIAS).setCellRenderer(new GestionCeldas("numerico"));
		tabla.getColumnModel().getColumn(Utilidades.FECHA_HORA).setCellRenderer(new GestionCeldas("texto"));
		
		//Campos extra
		tabla.getColumnModel().getColumn(Utilidades.PERFIL).setCellRenderer(new GestionCeldas("icono"));
		tabla.getColumnModel().getColumn(Utilidades.EVENTO).setCellRenderer(new GestionCeldas("icono"));		
		
		//Permitir el ordenamiento manual de las celdas
		tabla.getTableHeader().setReorderingAllowed(true);
		
		//tamaño de las celdas
		tabla.setRowHeight(25);
		
		//Color del grid
		tabla.setGridColor(new java.awt.Color(0, 0, 0)); 
		
		
		//Se define el tamaño de largo para cada columna y su contenido
		tabla.getColumnModel().getColumn(Utilidades.EMPRESA).setPreferredWidth(10);
		tabla.getColumnModel().getColumn(Utilidades.ALMACEN).setPreferredWidth(10);
		tabla.getColumnModel().getColumn(Utilidades.COD_ARTICULO).setPreferredWidth(30);
		tabla.getColumnModel().getColumn(Utilidades.DESC_ARTICULO).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(Utilidades.UBICACION).setPreferredWidth(80);
		tabla.getColumnModel().getColumn(Utilidades.EXISTENCIAS).setPreferredWidth(60);
		tabla.getColumnModel().getColumn(Utilidades.FECHA_HORA).setPreferredWidth(80);
		
		//Campos extra
		tabla.getColumnModel().getColumn(Utilidades.PERFIL).setPreferredWidth(10);
		tabla.getColumnModel().getColumn(Utilidades.EVENTO).setPreferredWidth(10);

		//personaliza el encabezado
		JTableHeader jtableHeader = tabla.getTableHeader();
	    jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
	    tabla.setTableHeader(jtableHeader);
	    
	    //se asigna la tabla al scrollPane
	    scrollPaneTabla.setViewportView(tabla);
	}
	

	//Método para mostrar un mensaje personalizado
	private static void mostrarMensaje(String titulo, String mensaje, int icono) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, icono);
	}

	private void validarSeleccionMouse(int fila) {
		
		Utilidades.filaSeleccionada = fila;
		
		String mensaje = "INFORMACIÓN\n" + tablaInfo.getListaInfo().get(fila).toString();

		mostrarMensaje("Información", mensaje,1);
		
	}
	
	//Eventos del ratón
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int fila = tabla.rowAtPoint(e.getPoint());
		int columna = tabla.columnAtPoint(e.getPoint());
		
		
		if(columna==Utilidades.PERFIL) {
			
			validarSeleccionMouse(fila);
			
		}else if(columna==Utilidades.EVENTO) {
			
			//Evento
			mostrarMensaje("Evento","Evento",1);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	


	
}
