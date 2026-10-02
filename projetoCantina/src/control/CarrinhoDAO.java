package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ModelCarrinho;
import model.ModelFuncionario;
import model.ModelProdu;
import dao.ConexaoBD;

public class CarrinhoDAO {
	private Connection connection;
	
	public CarrinhoDAO() {
		this.connection = new ConexaoBD().getConnection();	 
	}
	
	public int getterDoId(ModelCarrinho car)throws SQLException{
		String sql = "Select idProduto from tbProduto Where nomeProduto = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, car.getNomeProduto());
		
		ResultSet rs = stmt.executeQuery();
		int idProd = 0;
		
		if(rs.next()) {
			idProd = rs.getInt("idProduto");
		}
		rs.close();
		stmt.close();
		
		return idProd;
	}
	
	public int getterDoCliente(ModelCarrinho car)throws SQLException{
		String sql = "Select idUsuario from tbUsuario Where nomeUsuario = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, car.getNomeCliente());
		
		ResultSet rs = stmt.executeQuery();
		int idUser = 0;
		
		if(rs.next()) {
			idUser = rs.getInt("idUsuario");
		}
		rs.close();
		stmt.close();
		
		return idUser;
	}
	public int getterDoPagamento(ModelCarrinho car)throws SQLException{
		String sql = "Select idTipoPagamento from tbTipoPagamento Where tipoPagamento = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, car.getTipoPagamento());
		
		ResultSet rs = stmt.executeQuery();
		int idPag = 0;
		
		if(rs.next()) {
			idPag = rs.getInt("idTipoPagamento");
		}
		rs.close();
		stmt.close();
		
		return idPag;
	}
	
	public void inserirCarrinho(ModelCarrinho car)throws SQLException{
		
		int idProd = getterDoId(car);
		int idUser = getterDoCliente(car);
		int idPag = getterDoPagamento(car);
		
		try {
			String sql = "insert into tbPagamentoProduto(valorPagamento, quantidadeItensVenda, idProduto, idTipoPagamento, idUsuario) values(?, ?, ?, ?, ?)";	
			
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setDouble(1, car.getValorPagamento());
			stmt.setInt(2, car.getQuantidadeItens());
			stmt.setDouble(3, idProd);
			stmt.setInt(4, idPag);
			stmt.setInt(5, idUser);

			
			stmt.execute();
			stmt.close();
			
			System.out.println("Carrinho criado com Sucesso!");
			
		}catch(SQLException e) {
			System.out.println("Erro: "+e);
		}finally {
			connection.close();
		}
	}
	public List<ModelCarrinho> listarCarrinho() throws SQLException{

		List<ModelCarrinho> carrinho = new ArrayList<ModelCarrinho>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select idPagamentoProduto, nomeProduto, quantidadeItensVenda, valorPagamento,"
				+ " tipoPagamento, nomeUsuario FROM tbPagamentoProduto INNER JOIN"
				+ " tbProduto on tbProduto.idProduto = tbPagamentoProduto.idProduto"
				+ " INNER JOIN tbTipoPagamento on tbTipoPagamento.idTipoPagamento = tbPagamentoProduto.idTipoPagamento"
				+ " INNER JOIN tbUsuario on tbUsuario.idUsuario = tbPagamentoProduto.idUsuario");
			
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ModelCarrinho car = new ModelCarrinho();
			car.setIdCarrinho(rs.getInt(1));
			car.setNomeProduto(rs.getString(2));
			car.setQuantidadeItens(rs.getInt(3));
			car.setValorPagamento(rs.getDouble(4));
			car.setTipoPagamento(rs.getString(5));
			car.setNomeCliente(rs.getString(6));
				
			carrinho.add(car);
			}
			
		rs.close();
		stmt.close();			
			
		return carrinho;
	}
	
	public List<ModelCarrinho> listarClienteCarro() throws SQLException{
		List<ModelCarrinho> cliente = new ArrayList<ModelCarrinho>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select nomeUsuario From tbUsuario WHERE statusUsuario = 'Ativo'");
			
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			ModelCarrinho tipCar = new ModelCarrinho();
			tipCar.setNomeCliente(rs.getString(1));
				
			cliente.add(tipCar);
			}
			
		rs.close();
		stmt.close();			
			
		return cliente;
	}
	public List<ModelCarrinho> listarTipoPagamento() throws SQLException{
		List<ModelCarrinho> tipoPag = new ArrayList<ModelCarrinho>();
		PreparedStatement stmt = this.connection.prepareStatement(""
				+ "	Select tipoPagamento From tbTipoPagamento");
			
		ResultSet rs = stmt.executeQuery();
			
		while(rs.next()) {
			ModelCarrinho tipCar = new ModelCarrinho();
			tipCar.setTipoPagamento(rs.getString(1));
				
			tipoPag.add(tipCar);
			}
			
		rs.close();
		stmt.close();			
			
		return tipoPag;
	}
	public void excluirCar(ModelCarrinho carro) throws SQLException{
		try {
			String sql = "DELETE from tbPagamentoProduto where idPagamentoProduto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, carro.getIdCarrinho());
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
