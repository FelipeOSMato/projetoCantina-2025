package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import control.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

import model.ModelProdu;

public class CadastroProd extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lbNome, lbPreco, lbQuantia, tituloCad, lbDesc, lbTipo;
	private JTextField txNome, txDesc, txPreco, txQuantia ;
	
	private JButton voltar, enviar;
	
	private JComboBox cbTipoProdu;
	
	public static boolean cadastradoProdu = false;

	public CadastroProd() {
		setSize(700, 600);
		setTitle("Cadastro de Produto");
		setResizable(false);
		setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloCad = new JLabel();
		tituloCad.setText("Cadastro de Produtos");
		tituloCad.setBounds(215, 50, 400, 40);
		tituloCad.setForeground(new Color(255,255,255));
		tituloCad.setFont(new Font("Arial", 1, 20));
		add(tituloCad);
		
		lbNome = new JLabel();
		lbNome.setText("Nome do Produto: ");
		lbNome.setBounds(100, 100, 300, 30);
		lbNome.setForeground(new Color(255,255,255));
		lbNome.setFont(new Font("Arial",1,18));
		add(lbNome);
		
		txNome = new JTextField();
		txNome.setBounds(290, 100,200, 30);
		add(txNome);
		
		lbDesc = new JLabel();
		lbDesc.setText("Descrição do Produto: ");
		lbDesc.setBounds(90, 140, 300, 30);
		lbDesc.setForeground(new Color(255,255,255));
		lbDesc.setFont(new Font("Arial",1,18));
		add(lbDesc);
		
		txDesc = new JTextField();
		txDesc.setBounds(290, 140,200, 30);
		add(txDesc);
		
		lbPreco = new JLabel();
		lbPreco.setText("Preço do Produto: R$");
		lbPreco.setBounds(100, 180, 300, 30);
		lbPreco.setForeground(new Color(255,255,255));
		lbPreco.setFont(new Font("Arial",1,18));
		add(lbPreco);
		
		txPreco = new JTextField();
		txPreco.setBounds(290, 180, 200, 30);
		add(txPreco);
		
		lbQuantia = new JLabel();
		lbQuantia.setText("Quantia do Produto: ");
		lbQuantia.setBounds(100, 260, 300, 30);
		lbQuantia.setForeground(new Color(255,255,255));
		lbQuantia.setFont(new Font("Arial",1,18));
		add(lbQuantia);
		
		txQuantia = new JTextField();
		txQuantia.setBounds(290, 260, 200, 30);
		add(txQuantia);
		
		
		String[] inicio = {"Selecionar..."};
		
		lbTipo = new JLabel();
		lbTipo.setText("Tipo de Produto: ");
		lbTipo.setBounds(100, 220, 300, 30);
		lbTipo.setForeground(new Color(255,255,255));
		lbTipo.setFont(new Font("Arial",1,18));
		add(lbTipo);
		
		cbTipoProdu = new JComboBox<>(inicio);
		cbTipoProdu.setBounds(290, 220, 200, 30);
		cbTipoProdu.setFont(new Font("Arial", Font.BOLD, 16));
		cbTipoProdu.setForeground(Color.WHITE);
		cbTipoProdu.setBackground(new Color(30, 124, 180));
		cbTipoProdu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		add(cbTipoProdu);
		
		try {
			ProdutoDAO produDao = new ProdutoDAO();
			List<ModelProdu> listarCombo = produDao.listarTipoProdu();
			for(ModelProdu tipProd:listarCombo) {
				cbTipoProdu.addItem(tipProd.getTituloTipoProdu());
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar o tipo do produto: " + e);
		}
		
		enviar = new JButton();
		enviar= new JButton();
		enviar.setText("Enviar");
		enviar.setBackground(new Color(30, 124, 180));
		enviar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		enviar.setForeground(new Color(255,255,255));
		enviar.setFont(new Font("Arial", 1,15));
		enviar.setBounds(280, 380, 100, 50);
		enviar.setFocusPainted(false);
		add(enviar);
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(txNome.getText().trim().isEmpty() || txPreco.getText().trim().isEmpty() || txDesc.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"Preencha o formulário antes de enviar!");
				}else if(!verificarNum(txPreco.getText())) {
					JOptionPane.showMessageDialog(CadastroProd.this, "Preço inválido: ");
				}else if(!verificarNum(txQuantia.getText())) {
					JOptionPane.showMessageDialog(CadastroProd.this, "Quantia inválido: ");
				}
				else  if(Double.parseDouble(txPreco.getText())>0 && Integer.parseInt(txQuantia.getText())>0 && !cbTipoProdu.getSelectedItem().equals("Selecionar...")) {
					ModelProdu model = new ModelProdu();
					
					model.setTituloTipoProdu(cbTipoProdu.getSelectedItem().toString()); 
					model.setNomeProdu(txNome.getText());
					model.setDescProdu(txDesc.getText());
					model.setValorProdu(Double.parseDouble(txPreco.getText()));
					model.setQuantiaEstoque(Integer.parseInt(txQuantia.getText()));
					
					ProdutoDAO inserir = new ProdutoDAO();
					
					try {
						inserir.inserirProdu(model);
						JOptionPane.showMessageDialog(CadastroProd.this, "Produto Cadastrado com sucesso!");
					}catch (SQLException e) {
						JOptionPane.showMessageDialog(CadastroProd.this, "Erro ao cadastrar: " + e);
					}
					
					

				}else if (Double.parseDouble(txPreco.getText())<=0) {
					JOptionPane.showMessageDialog(null, "Não esqueça de colocar um preço maior que 0!");
				}else {
					JOptionPane.showMessageDialog(null, "Não esqueça de selecionar um tipo!");
				}
			}
		});
		
		voltar = new JButton();
		voltar.setText("Voltar");
		voltar.setBackground(new Color(255, 255, 255));
		voltar.setForeground(new Color(30, 124, 180));
		voltar.setBounds(30, 30, 100, 30);
		voltar.setFocusPainted(false);
		add(voltar);
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}
	public boolean verificarNum(String txt) {
		try {
			 Double.parseDouble(txt);
			 return true;
		}catch(NumberFormatException e) {
			 return false;
		}
	}
}