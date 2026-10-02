package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;

import model.ModelCliente;
import model.ModelProdu;
import dao.ConexaoBD;

public class ProdutoDAO {
	private Connection connection;
	
	public ProdutoDAO() {
		this.connection = new ConexaoBD().getConnection();	 
	}
	
	public int getterDoId(ModelProdu prod)throws SQLException{
		String sql = "Select idTipoProduto from tbTipoProduto Where descTipoProduto = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, prod.getTituloTipoProdu());
		
		ResultSet rs = stmt.executeQuery();
		int idTipoProd = 0;
		
		if(rs.next()) {
			idTipoProd = rs.getInt("idTipoProduto");
		}
		rs.close();
		stmt.close();
		
		return idTipoProd;
	}
	
	public void inserirProdu(ModelProdu prod)throws SQLException{
		
		int idTipoProd = getterDoId(prod);
		
		try {
			String sql = "insert into tbProduto(nomeProduto, descProduto, valorProduto, quantidadeEstoque, idTipoProduto) values(?, ?, ?, ?, ?)";	
			
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setString(1, prod.getNomeProdu());
			stmt.setString(2, prod.getDescProdu());
			stmt.setDouble(3, prod.getValorProdu());
			stmt.setInt(4, prod.getQuantiaEstoque());
			stmt.setInt(5, idTipoProd);

			
			stmt.execute();
			stmt.close();
			
			System.out.println("Produto Cadastrado com Sucesso!");
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
	public List<ModelProdu> listarProdTipo(String tipo) throws SQLException{

		List<ModelProdu> produtos = new ArrayList<ModelProdu>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select idProduto, nomeProduto, descProduto,"
				+ " valorProduto, quantidadeEstoque FROM tbProduto INNER JOIN"
				+ " tbTipoProduto on tbTipoProduto.idTipoProduto = tbProduto.idTipoProduto"
				+ " WHERE descTipoProduto = ? and quantidadeEstoque > 0");
		stmt.setString(1, tipo);
			
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ModelProdu prod = new ModelProdu();
			prod.setIdProdu(rs.getInt(1));
			prod.setNomeProdu(rs.getString(2));
			prod.setDescProdu(rs.getString(3));
			prod.setValorProdu(rs.getDouble(4));
			prod.setQuantiaEstoque(rs.getInt(5));
				
			produtos.add(prod);
			}
			
		rs.close();
		stmt.close();			
			
		return produtos;
	}
	public List<ModelProdu> listarTipoProdu() throws SQLException{
		List<ModelProdu> tipoProdutos = new ArrayList<ModelProdu>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select descTipoProduto From tbTipoProduto");
			
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			ModelProdu tipProd = new ModelProdu();
			tipProd.setTituloTipoProdu(rs.getString(1));
				
			tipoProdutos.add(tipProd);
			}
			
		rs.close();
		stmt.close();			
			
		return tipoProdutos;
	}
	
	public void alterarEstoque(ModelProdu prod)throws SQLException{
		try {
			String sql = "Update tbProduto set quantidadeEstoque = ? WHERE idProduto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, prod.getQuantiaEstoque());
			stmt.setInt(2, prod.getIdProdu());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
}
