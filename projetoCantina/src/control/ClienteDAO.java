package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;

import model.ModelCliente;
import model.ModelFuncionario;
import dao.ConexaoBD;

public class ClienteDAO {
	private Connection connection;
	
	public ClienteDAO() {
		this.connection = new ConexaoBD().getConnection();	 
	}
	
	public boolean verificarUser(ModelCliente cli) throws SQLException{
		String sql = "Select cpfUsuario FROM tbUsuario where cpfUsuario = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, cli.getCpfUsuario());
		
		ResultSet rs = stmt.executeQuery();
		boolean cpfUsuario = false;
		
		if(rs.next()) {
			cpfUsuario = true;
		}
		rs.close();
		stmt.close();
		
		return cpfUsuario;
	}
	
	
	public void inserirCli(ModelCliente cli)throws SQLException{
		
		try {
			String sql = "insert into tbusuario(nomeUsuario, senhaUsuario, emailUsuario,"
					+ " foneUsuario, cpfUsuario, cepUsuario, statusUsuario) values(?,?,?,?,?,?,?)";	
			
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setString(1, cli.getNomeUsuario());
			stmt.setString(2, cli.getSenhaUsuario());
			stmt.setString(3, cli.getEmailUsuario());
			stmt.setString(4, cli.getFoneUsuario());
			stmt.setString(5, cli.getCpfUsuario());
			stmt.setString(6, cli.getCepUsuario());
			stmt.setString(7, cli.getStatusCliente());
			
			stmt.execute();
			stmt.close();
			
			System.out.println("Cliente Cadastrado com Sucesso!");
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
	public List<ModelCliente> listarCli() throws SQLException{
		List<ModelCliente> clientes = new ArrayList<ModelCliente>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select idUsuario, nomeUsuario, emailUsuario,"
				+ " cpfUsuario, foneUsuario, cepUsuario"
				+ " From tbUsuario WHERE statusUsuario = 'Ativo'");
		
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			ModelCliente cli = new ModelCliente();
			cli.setIdUsuario(rs.getInt(1));
			cli.setNomeUsuario(rs.getString(2));
			cli.setEmailUsuario(rs.getString(3));
			cli.setCpfUsuario(rs.getString(4));
			cli.setFoneUsuario(rs.getString(5));
			cli.setCepUsuario(rs.getString(6));
				
			clientes.add(cli);
			}
			
		rs.close();
		stmt.close();			
			
		return clientes;
	}
	public void excluirCli(ModelCliente cli) throws SQLException{
		try {
			String sql = "Update tbUsuario set statusUsuario = ? where idUsuario = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, "Inativo");
			stmt.setInt(2, cli.getIdUsuario());
			
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
