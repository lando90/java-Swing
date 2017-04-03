package ventanaEmpleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class ConexionBBDD{
	private  Connection conexion;
	private  Statement st;
	private  ResultSet resultado;
	
	public Connection getConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaDB","root","root");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conexion;
	}
	public void cierraConexion(Connection cn){
		try{
			if(cn!=null && !cn.isClosed()){
				cn.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public  Empleado selectDetalle(int idDetalle) throws SQLException{
		Connection dt= getConexion();
		Empleado objEmpl = null;
		st=conexion.createStatement();
		resultado=st.executeQuery("SELECT * FROM table1 where id="+idDetalle);
		while(resultado.next()){
			objEmpl = new Empleado();
			objEmpl.setId(resultado.getInt("id"));
			objEmpl.setNombre(resultado.getString("nombre"));
			objEmpl.setApellido(resultado.getString("apellido"));
			objEmpl.setDepartamento(resultado.getString("departamento"));
			objEmpl.setEmail(resultado.getString("email"));
			objEmpl.setSalario(resultado.getFloat("salario"));
			objEmpl.setFechaIni(resultado.getTimestamp("fechaIni"));
			// objEmpl.setFechaFin()

		}
		st.close();
		cierraConexion(dt);
		return objEmpl;
	}
	
	public  void insert(Empleado emp) throws SQLException{
		Connection dt= getConexion();
		st=conexion.createStatement();
		String query = ("INSERT INTO table1 (nombre, apellido, email, departamento, salario, fechaIni) values(\"");
		query += emp.getNombre()+"\",\""+emp.getApellido()+"\",\""+emp.getEmail()+"\",\""+emp.getDepartamento()+"\","+emp.getSalario()+",\"2001-11-11 00:00:00\")";
		st.execute(query);
		st.close();
		cierraConexion(dt);
		
	}
	public void delete(int idSelected)throws SQLException{
		Connection dt= getConexion();
		st=conexion.createStatement();
		String query = ("delete from table1 WHERE id="+idSelected);
		st.execute(query);
		st.close();
		cierraConexion(dt);
		
	}
	public void update(Empleado emp) throws SQLException {
		Connection dt= getConexion();
		st=conexion.createStatement();
		String query = ("UPDATE table1 set nombre=\""+emp.getNombre()+"\", apellido=\""+emp.getApellido()+	"\", email=\""+emp.getEmail()+"\", departamento=\""+emp.getDepartamento()+"\", salario="+emp.getSalario()+", fechaIni=\"2001-11-11 00:00:00\" WHERE ID="+emp.getID());
		st.execute(query);
		st.close();
		cierraConexion(dt);
		
	}
	public List<Empleado> selectFiltrado(String tipo, String filtro) throws SQLException {
		Connection dt= getConexion();
		st=conexion.createStatement();
		List<Empleado> empList = new ArrayList<>();
		Empleado objEmpl;
		
		String query = ("SELECT * FROM table1 ");
		
		switch(tipo){
		case("email"):
			query += ("WHERE email LIKE \"%"+filtro+"%\"");
			break;
		case("departamento"):
			query += ("WHERE departamento LIKE \"%"+filtro+"%\"");
			break;
		case("nombre"):
			query += ("WHERE nombre LIKE= \"%"+filtro+"%\"");
			break;
		default:
			break;
		}
		resultado=st.executeQuery(query);
		while(resultado.next()){
			objEmpl = new Empleado();
			objEmpl.setId(resultado.getInt("id"));
			objEmpl.setNombre(resultado.getString("nombre"));
			objEmpl.setApellido(resultado.getString("apellido"));
			objEmpl.setDepartamento(resultado.getString("departamento"));
			objEmpl.setEmail(resultado.getString("email"));
			objEmpl.setSalario(resultado.getFloat("salario"));
			objEmpl.setFechaIni(resultado.getTimestamp("fechaIni"));
			// objEmpl.setFechaFin()
			
			empList.add(objEmpl);

		}
		st.close();
		cierraConexion(dt);
		return empList;
	}
	
	
}