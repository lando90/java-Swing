package ventanaEmpleados;

//crear javaBean Empleado con todos los parametros
//los objetos de empleado se referencian fuera del bucle pero se inicializan dentro
//generar un vector como tuplas haya
//generar tantos addElement como columnas haya
//hacer un Jtable, jPanel, menu(jFrame)
//Jtable default table

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

public class EmpleadoController extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame jpFrame;
	public JScrollPane jpScroll;
	public JTable jpTable;
	public DefaultTableModel dt;
	public JMenuBar menuBar;
	public JMenuItem mI1,mI2,mI3,mI4,mI5;
	public JMenu menu, submenu;
	public JTextField nombr2,appe2,depar2,email2,sal2,feci2,fecf2;
	public JLabel nombr,appe,depar,email,sal,feci,fecf;
	public JCheckBox chBox;
	public JComboBox  list;
	public JButton mod, ins, ver, del;
	public JDialog jPass;
	public static JFrame frame, f8;
	public int idSelected;
	public JPasswordField pass;
	
	
public EmpleadoController() throws SQLException, ClassNotFoundException{
		
		setLayout(new BorderLayout());
		add(Menu(),BorderLayout.NORTH);
		add(tabla(),BorderLayout.WEST);
		add(panelEste(),BorderLayout.EAST);
		
		//Add the scroll pane to this panel.
	
	}
private Component Menu() {
		
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Filtro");

		//a group of JMenuItems
		mI1 = new JMenuItem("Buscar por E-mail", KeyEvent.VK_T);
		mI2 = new JMenuItem("Buscar por nombre", KeyEvent.VK_T);
		mI3 = new JMenuItem("Buscar por departamento", KeyEvent.VK_T);
		
		menu.add(mI1);
		menu.add(mI2);
		menu.add(mI3);
		menuBar.add(menu);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Imprimir");
		menu.setMnemonic(KeyEvent.VK_N);

		menuBar.add(menu);
		
		mI1.addActionListener(new GestorBusEmail());
		mI2.addActionListener(new GestorBusDepar());
		mI3.addActionListener(new GestorBusNombre());
		
		return menuBar;
	}
	public JPanel panelEste(){
		JPanel jpPrincipal = new JPanel(new GridLayout(3, 1));
		jpPrincipal.add(panelEsteArriba(),BorderLayout.NORTH);
		jpPrincipal.add(panelEsteAbajo(),BorderLayout.SOUTH);
		jpPrincipal.setPreferredSize(new Dimension(200,200));
		return jpPrincipal;

	}
	public JPanel panelEsteArriba(){
		String data[] = { "IT", "RRHH", "Seleccion" };
		JPanel jpPrincipal = new JPanel(new GridBagLayout());
		nombr = new JLabel("Nombre");
		nombr2 = new JTextField("");
		appe = new JLabel("Apellidos");
		appe2 = new JTextField("");
		depar = new JLabel("Departamento");
		
		list = new JComboBox(data); //data has type Object[]
		list.setSelectedIndex(0);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(20, 1));
		
		email = new JLabel("Email");
		email2 = new JTextField("");
		sal = new JLabel("Salario");
		sal2 = new JTextField("");
		feci = new JLabel("Fecha Inicio");
		feci2 = new JTextField("");
		fecf = new JLabel("Fecha Fin");
		fecf2 = new JTextField("");
		//ver.addActionListener(new GestorVer());
		
		nombr.setMaximumSize(new Dimension(200,200));
		nombr.setAlignmentX(Component.LEFT_ALIGNMENT);;
		nombr2.setMaximumSize(new Dimension(200,200));
		nombr2.setAlignmentX(Component.LEFT_ALIGNMENT);
		nombr2.setEnabled(false);
		
		appe.setMaximumSize(new Dimension(200,200));
		appe.setAlignmentX(Component.LEFT_ALIGNMENT);
		appe2.setMaximumSize(new Dimension(200,200));
		appe2.setAlignmentX(Component.LEFT_ALIGNMENT);
		appe2.setEnabled(false);
		
		depar.setMaximumSize(new Dimension(200,200));
		depar.setAlignmentX(Component.LEFT_ALIGNMENT);
		list.setMaximumSize(new Dimension(200,200));
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		list.setEnabled(false);
		
		email.setMaximumSize(new Dimension(200,200));
		email.setAlignmentX(Component.LEFT_ALIGNMENT);
		email2.setMaximumSize(new Dimension(200,200));
		email2.setAlignmentX(Component.LEFT_ALIGNMENT);
		email2.setEnabled(false);
		
		sal.setMaximumSize(new Dimension(200,200));
		sal.setAlignmentX(Component.LEFT_ALIGNMENT);
		sal2.setMaximumSize(new Dimension(200,200));
		sal2.setAlignmentX(Component.LEFT_ALIGNMENT);
		sal2.setEnabled(false);
		
		feci.setMaximumSize(new Dimension(200,200));
		feci.setAlignmentX(Component.LEFT_ALIGNMENT);
		feci2.setMaximumSize(new Dimension(200,200));
		feci2.setAlignmentX(Component.LEFT_ALIGNMENT);
		feci2.setEnabled(false);
		
		fecf.setMaximumSize(new Dimension(200,200));
		fecf.setAlignmentX(Component.LEFT_ALIGNMENT);
		fecf2.setMaximumSize(new Dimension(200,200));
		fecf2.setAlignmentX(Component.LEFT_ALIGNMENT);
		fecf2.setEnabled(false);
		
		jpPrincipal.setLayout(new BoxLayout(jpPrincipal, BoxLayout.Y_AXIS));
		
		jpPrincipal.add(nombr);
		jpPrincipal.add(nombr2);
		jpPrincipal.add(appe);
		jpPrincipal.add(appe2);
		jpPrincipal.add(depar);
		jpPrincipal.add(listScroller);
		jpPrincipal.add(email);
		jpPrincipal.add(email2);
		jpPrincipal.add(sal);
		jpPrincipal.add(sal2);
		jpPrincipal.add(feci);
		jpPrincipal.add(feci2);
		jpPrincipal.add(fecf);
		jpPrincipal.add(fecf2);
		return jpPrincipal;
	}
	public JPanel panelEsteAbajo(){
		JPanel jpPrincipal = new JPanel(new GridLayout(16, 1));
		jpPrincipal.setLayout(new BoxLayout(jpPrincipal, BoxLayout.Y_AXIS));
		ver = new JButton("Ver");
		ver.addActionListener(new GestorVer());
		jpPrincipal.add(ver);
		
		mod= new JButton("Modificar");
		mod.addActionListener(new GestorMod());
		mod.setEnabled(false);
		jpPrincipal.add(mod);
		
		ins = new JButton("Insertar");
		ins.addActionListener(new GestorVer());
		ins.setEnabled(false);
		jpPrincipal.add(ins);
		
		del = new JButton("Delete");
		del.addActionListener(new GestorRemove());
		del.setEnabled(false);
		jpPrincipal.add(del);
		
		chBox = new JCheckBox("Editar/Insertar");
		chBox.addActionListener(new GestorEdit());
		jpPrincipal.add(chBox);
		
		 return jpPrincipal;
	}
	
	public JPanel tabla(){
		dt= new DefaultTableModel();
		dt.addColumn("Id");
		dt.addColumn("Nombre");
		dt.addColumn("Apellidos");
		dt.addColumn("Email");
		dt.addColumn("Departamento");
		dt.addColumn("Salario");
		dt.addColumn("Fecha Inicio");
		dt.addColumn("Fecha Fin");
		
		jpTable = new JTable(dt);
		jpTable.addMouseListener(new GestorTabla());
		jpScroll = new JScrollPane(jpTable);
		JPanel jpPrincipal = new JPanel();
		jpPrincipal.add(jpScroll);
		// jpScroll.setPreferredSize(new Dimension(1000,750));
		jpTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		jpTable.setFillsViewportHeight(true);
		//Create the scroll pane and add the table to it.
		
		 return jpPrincipal;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		EmpleadoController empCon = new EmpleadoController();
		//Create and set up the window.
		frame = new JFrame("TablaEmpleados");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
     
		empCon.setOpaque(true); //content panes must be opaque
     	frame.setContentPane(empCon);
     	frame.setSize(dim.width,dim.height);

     //Display the window.
     //frame.pack();
     frame.setVisible(true);
		
		
		
		
	}
	
	public class GestorTabla extends MouseAdapter{
		public void mouseReleased(MouseEvent e){
			try {
			ConexionBBDD conDB = new ConexionBBDD();
			Empleado emp = new Empleado();
			idSelected = (int) jpTable.getValueAt(jpTable.getSelectedRow(),0);
			
			emp = conDB.selectDetalle(idSelected);
			
			
			nombr2.setText(""+emp.getNombre());
			appe2.setText(""+emp.getApellido());
			email2.setText(""+emp.getEmail());
			switch(emp.getDepartamento()){
				case("Seleccion"):
					list.setSelectedIndex(2);
					break;
				
				case ("rrhh") :
					list.setSelectedIndex(1);
					break;
				
				case("IT"):
					list.setSelectedIndex(0);
					break;
				
			}
			list.setSelectedItem(emp);
			sal2.setText(""+emp.getSalario());
			feci2.setText(""+emp.getFechaIni());
			fecf2.setText(""+emp.getFechaFin());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public class GestorEdit implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(chBox.isSelected()){
				JTextField userfield = new JTextField();
				JPasswordField passfield = new JPasswordField();
				
				userfield.setPreferredSize(new Dimension(150,20));
				passfield.setPreferredSize(new Dimension(150,20));
				
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("User:"));
				myPanel.add(userfield);
				myPanel.add(new JLabel("Pass:"));
				myPanel.add(passfield);
				
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Conexion", JOptionPane.OK_CANCEL_OPTION);
				
				if(result == JOptionPane.OK_OPTION){
					if ("root".equals(userfield.getText()) && "root".equals(new String(passfield.getPassword()))) {
						JOptionPane.showMessageDialog(null, "login ok");
						nombr2.setEditable(true);
						nombr2.setEnabled(true);
						appe2.setEditable(true);
						appe2.setEnabled(true);
						list.setEnabled(true);
						email2.setEditable(true);
						email2.setEnabled(true);
						sal2.setEditable(true);
						sal2.setEnabled(true);
						feci2.setEditable(true);
						feci2.setEnabled(true);
						fecf2.setEditable(true);
						fecf2.setEnabled(true);
						mod.setEnabled(true);
						ins.setEnabled(true);
						del.setEnabled(true);
					}else {
						chBox.setSelected(false);
						JOptionPane.showMessageDialog(null, "login failed");
					}
				}
			}else{
				nombr2.setEditable(false);
				nombr2.setEnabled(false);
				appe2.setEditable(false);
				appe2.setEnabled(false);
				list.setEnabled(false);
				email2.setEditable(false);
				email2.setEnabled(false);
				sal2.setEditable(false);
				sal2.setEnabled(false);
				feci2.setEditable(false);
				feci2.setEnabled(false);
				fecf2.setEditable(false);
				fecf2.setEnabled(false);
				mod.setEnabled(false);
				ins.setEnabled(false);
				del.setEnabled(false);
			}
			
		}
		
	}
	public class GestorRemove implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				ConexionBBDD conDB = new ConexionBBDD();
				if(jpTable.getValueAt(jpTable.getSelectedRow(),0)!=null){
						conDB.delete(idSelected);
						dt.removeRow(jpTable.getSelectedRow());
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	public class GestorMod implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				ConexionBBDD conDB = new ConexionBBDD();
				Empleado emp = new Empleado();
				if(jpTable.getValueAt(jpTable.getSelectedRow(),0)!=null){
					emp.setId(idSelected);
					emp.setNombre(nombr2.getText());
					emp.setApellido(appe2.getText());
					emp.setDepartamento((String) list.getSelectedItem());
					emp.setEmail(email2.getText());
					emp.setSalario(Float.parseFloat(sal2.getText()));
//					emp.setFechaIni(Timestamp.valueOf(feci2.getText()));
//					emp.setFechaFin(Timestamp.valueOf(fecf2.getText()));
						
					conDB.update(emp);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	public class GestorVer implements ActionListener{
		private Object fila[] = new Object[8];

		public void actionPerformed(ActionEvent e){
			try{
				List<Empleado> listaEmp= new ArrayList<>();
				ConexionBBDD conDB = new ConexionBBDD();
					
				listaEmp = conDB.selectFiltrado("",null);
				while(dt.getRowCount()>0){
						dt.removeRow(0);
				}
				for(Empleado em:listaEmp){
					fila[0] = em.getID();
					fila[1] = em.getNombre();
					fila[2] = em.getApellido();
					fila[3] = em.getEmail();
					fila[4] = em.getDepartamento();
					fila[5] = em.getSalario();
					fila[6] = em.getFechaIni();
					fila[7] = em.getFechaFin();
					dt.addRow(fila);
				}
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
	}
	public class GestorIns implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				ConexionBBDD conDB = new ConexionBBDD();
				Empleado emp = new Empleado();
				if(nombr2.getText()!=null && appe2.getText()!= null){
					emp.setNombre(nombr2.getText());
					emp.setApellido(appe2.getText());
					emp.setDepartamento(list.getName());
					emp.setEmail(email2.getText());
					emp.setSalario(Float.parseFloat(sal2.getText()));
//						emp.setFechaIni(Timestamp.valueOf(feci2.getText()));
//						emp.setFechaFin(Timestamp.valueOf(fecf2.getText()));
					conDB.insert(emp);
					nombr2.setToolTipText("");
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
	}
	public class GestorBusEmail implements ActionListener{
		private Object fila[] = new Object[8];
		public void actionPerformed(ActionEvent e){
			try {
				List<Empleado> listaEmp= new ArrayList<>();
				ConexionBBDD conDB = new ConexionBBDD();
				JTextField emailfield = new JTextField();
				
				emailfield.setPreferredSize(new Dimension(150,20));
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Email:"));
				myPanel.add(emailfield);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "filtro", JOptionPane.OK_CANCEL_OPTION);
				
//				String email = JOptionPane.showInputDialog(null, "E-Mail");
				
				
				if(result == JOptionPane.OK_OPTION){
					listaEmp = conDB.selectFiltrado("email",emailfield.getText());
					while(dt.getRowCount()>0){
						dt.removeRow(0);
					}
					if(listaEmp == null){
						JOptionPane.showMessageDialog(null, "No se han encontrado registros para esta busqueda");
					}else{
						for(Empleado em:listaEmp){
							fila[0] = em.getID();
							fila[1] = em.getNombre();
							fila[2] = em.getApellido();
							fila[3] = em.getEmail();
							fila[4] = em.getDepartamento();
							fila[5] = em.getSalario();
							fila[6] = em.getFechaIni();
							fila[7] = em.getFechaFin();
							dt.addRow(fila);
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	public class GestorBusDepar implements ActionListener{
		private Object fila[] = new Object[8];
		public void actionPerformed(ActionEvent e){
			try {
				List<Empleado> listaEmp= new ArrayList<>();
				ConexionBBDD conDB = new ConexionBBDD();
				JTextField depfield = new JTextField();
				
				depfield.setPreferredSize(new Dimension(150,20));
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Departamento:"));
				myPanel.add(depfield);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "filtro", JOptionPane.OK_CANCEL_OPTION);

				if(result == JOptionPane.OK_OPTION){
					listaEmp = conDB.selectFiltrado("departamento",depfield.getText());
					while(dt.getRowCount()>0){
						dt.removeRow(0);
					}
					if(listaEmp == null){
						JOptionPane.showMessageDialog(null, "No se han encontrado registros para esta busqueda");
					}else{
						for(Empleado em:listaEmp){
							fila[0] = em.getID();
							fila[1] = em.getNombre();
							fila[2] = em.getApellido();
							fila[3] = em.getEmail();
							fila[4] = em.getDepartamento();
							fila[5] = em.getSalario();
							fila[6] = em.getFechaIni();
							fila[7] = em.getFechaFin();
							dt.addRow(fila);
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	public class GestorBusNombre implements ActionListener{
		private Object fila[] = new Object[8];
		public void actionPerformed(ActionEvent e){
			try {
				List<Empleado> listaEmp= new ArrayList<>();
				ConexionBBDD conDB = new ConexionBBDD();
				JTextField nombrefield = new JTextField();
				
				nombrefield.setPreferredSize(new Dimension(150,20));
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Nombre:"));
				myPanel.add(nombrefield);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "filtro", JOptionPane.OK_CANCEL_OPTION);

				if(result == JOptionPane.OK_OPTION){
					listaEmp = conDB.selectFiltrado("departamento",nombrefield.getText());
					while(dt.getRowCount()>0){
						dt.removeRow(0);
					}
					if(listaEmp == null){
						JOptionPane.showMessageDialog(null, "No se han encontrado registros para esta busqueda");
					}else{
						for(Empleado em:listaEmp){
							fila[0] = em.getID();
							fila[1] = em.getNombre();
							fila[2] = em.getApellido();
							fila[3] = em.getEmail();
							fila[4] = em.getDepartamento();
							fila[5] = em.getSalario();
							fila[6] = em.getFechaIni();
							fila[7] = em.getFechaFin();
							dt.addRow(fila);
						}
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
}
