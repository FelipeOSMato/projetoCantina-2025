package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;

import model.ModelFuncionario;
import dao.ConexaoBD;

public class FuncionarioDAO {
	private Connection connection;
	
	public FuncionarioDAO() {
		this.connection = new ConexaoBD().getConnection();	 
	}
	
	public boolean verificarFunc(ModelFuncionario func) throws SQLException{
		String sql = "Select cpfFuncionario FROM tbFuncionario where cpfFuncionario = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, func.getCpfFuncionario());
		
		ResultSet rs = stmt.executeQuery();
		boolean cpfFunc = false;
		
		if(rs.next()) {
			cpfFunc = true;
		}
		rs.close();
		stmt.close();
		
		return cpfFunc;
	}
	
	public int getterDoId(ModelFuncionario func)throws SQLException{
		String sql = "Select idHierarquia from tbHierarquia Where tituloHierarquia = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, func.getCargoFuncionario());
		
		ResultSet rs = stmt.executeQuery();
		int idHierarquia = 0;
		
		if(rs.next()) {
			idHierarquia = rs.getInt("idHierarquia");
		}
		rs.close();
		stmt.close();
		
		return idHierarquia;
	}
	
	public void inserirFunc(ModelFuncionario func)throws SQLException{
		
		int idHierarquia = getterDoId(func);
		
		try {
			String sql = "insert into tbfuncionario(nomeFuncionario, dataNascFuncionario, cpfFuncionario,"
					+ " statusFuncionario, emailFuncionario,foneFunc, idHierarquia, generoFunc) values(?,?,?,?,?,?,?,?)";	
			
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setString(1, func.getNomeFuncionario());
			
			java.util.Date dataJava = func.getDataNascFuncionario();
			java.sql.Date dataSql = new java.sql.Date(dataJava.getTime());
			stmt.setDate(2, dataSql);
			
			stmt.setString(3, func.getCpfFuncionario());
			stmt.setString(4, func.getStatusFuncionario());
			stmt.setString(5, func.getEmailFuncionario());
			stmt.setString(6, func.getFoneFuncionario());
			stmt.setInt(7, idHierarquia);
			stmt.setString(8, func.getGeneroFuncionario());
			
			stmt.execute();
			stmt.close();
			
			System.out.println("Funcionário Cadastrado com Sucesso!");
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
	public List<ModelFuncionario> listarFunc() throws SQLException{
		List<ModelFuncionario> funcionarios = new ArrayList<ModelFuncionario>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select idFuncionario, nomeFuncionario, emailFuncionario,"
				+ " cpfFuncionario, foneFunc, dataNascFuncionario,"
				+ " generoFunc, statusFuncionario, tituloHierarquia,salarioHierarquia"
				+ " From tbFuncionario "
				+ "Inner Join tbHierarquia ON tbHierarquia.idHierarquia = tbFuncionario.idHierarquia");
			
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			ModelFuncionario func = new ModelFuncionario();
			func.setIdFuncionario(rs.getInt(1));
			func.setNomeFuncionario(rs.getString(2));
			func.setEmailFuncionario(rs.getString(3));
			func.setCpfFuncionario(rs.getString(4));
			func.setFoneFuncionario(rs.getString(5));
			func.setDataNascFuncionario(rs.getDate(6));
			func.setGeneroFuncionario(rs.getString(7));
			func.setStatusFuncionario(rs.getString(8));
			func.setCargoFuncionario(rs.getString(9));
			func.setSalarioFunc(rs.getDouble(10));
				
			funcionarios.add(func);
			}
			
		rs.close();
		stmt.close();			
			
		return funcionarios;
	}
	public void excluirFunc(ModelFuncionario funcionario) throws SQLException{
		try {
			String sql = "DELETE from tbFuncionario where idFuncionario = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, funcionario.getIdFuncionario());
			stmt.execute();
			stmt.close();
			System.out.println("Dados exluídos com sucesso!");
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
}
