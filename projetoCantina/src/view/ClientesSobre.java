package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import control.ClienteDAO;
import control.FuncionarioDAO;
import model.ModelCliente;
import model.ModelFuncionario;

import java.util.ArrayList;
import java.util.List;

public class ClientesSobre extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel tituloCli;
	private JButton voltar, btExcluir;
	private JTable tabelas;
	private DefaultTableModel modeloTabela;
	
	
	public ClientesSobre() {
		setSize(1000, 700);
		setTitle("Clientes");
		setResizable(false);
		setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloCli = new JLabel();
		tituloCli.setText("Nossos Clientes");
		tituloCli.setBounds(400, 5, 400, 40);
		tituloCli.setForeground(new Color(255,255,255));
		tituloCli.setFont(new Font("Arial", 1, 20));
		add(tituloCli);
		
		String[] colunasTabela= {"ID", "Nome", "Email", "CPF", "Telefone","Cep"};
		
		modeloTabela = new DefaultTableModel(colunasTabela, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tabelas = new JTable(modeloTabela);
		tabelas.setBackground(new Color(40, 144, 200));
		tabelas.setForeground(new Color(255,255,255));
		tabelas.setFont(new Font("Arial", 1, 14));
		tabelas.setFillsViewportHeight(true);
		tabelas.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		tabelas.setGridColor(new Color(255,255,255));
		
		tabelas.getTableHeader().setBackground(new Color(255, 255, 255));
		tabelas.getTableHeader().setForeground(new Color(40, 144, 200));
		tabelas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabelas.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(60, 164, 220)));
		
        JScrollPane scroll = new JScrollPane(tabelas);
        scroll.setBounds(20, 50, 950, 500);
        add(scroll);
        
		btExcluir = new JButton();
		btExcluir.setText("Excluir");
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setForeground(new Color(30, 124, 180));
		btExcluir.setBounds(30, 570, 100, 30);
		btExcluir.setFocusPainted(false);
		add(btExcluir);
		
        btExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		try {
        			ModelCliente cli = new ModelCliente();
            		ClienteDAO cliDao = new ClienteDAO();
            		int linhaSelecao = -1;
            		linhaSelecao = tabelas.getSelectedRow();
            		if(linhaSelecao >=0) {
            			
            			int pergDelete= JOptionPane.showConfirmDialog(null, "Deseja Mesmo Excluir?"
								,"Escolha entre sim ou não" ,JOptionPane.YES_NO_OPTION);
						if(pergDelete == JOptionPane.YES_OPTION) {
	            			int idCli = Integer.parseInt(tabelas.getValueAt(linhaSelecao, 0).toString());
	            			cli.setIdUsuario(idCli);
	            			
	            			tabelas.getValueAt(linhaSelecao, 0);
	            			modeloTabela.removeRow(linhaSelecao);
	            			cliDao.excluirCli(cli);
						}
            		}else {
        				JOptionPane.showMessageDialog(null, "Selecione uma linha!");
            		}
        		}catch(SQLException e) {
        			System.out.println("error: "+e);
        		}
        	}
       });
		
        CarregarDados();
        
		voltar = new JButton();
		voltar.setText("Voltar");
		voltar.setBackground(new Color(255, 255, 255));
		voltar.setForeground(new Color(30, 124, 180));
		voltar.setBounds(30, 10, 100, 30);
		voltar.setFocusPainted(false);
		add(voltar);
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}
	
	public void CarregarDados() {
		try {
			ClienteDAO cliDAO = new ClienteDAO();
			List<ModelCliente> listaTable = cliDAO.listarCli();
			
			for(ModelCliente cli: listaTable) {
				Object[] cliente = new Object[6];
				cliente[0] = cli.getIdUsuario();
				cliente[1] = cli.getNomeUsuario();
				cliente[2] = cli.getEmailUsuario();
				cliente[3] = cli.getCpfUsuario();
				cliente[4] = cli.getFoneUsuario();
				cliente[5] = cli.getCepUsuario();
			
				modeloTabela.addRow(cliente);
			}
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + e);
		}
	}

}