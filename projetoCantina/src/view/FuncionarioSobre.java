package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import control.FuncionarioDAO;
import model.ModelCliente;
import model.ModelFuncionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioSobre extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private int idadeFunc;
	private JLabel tituloFunc;
	private JButton voltar, btExcluir;
	private JTable tabelas;
	private DefaultTableModel modeloTabela;
	
	public FuncionarioSobre() {
		setSize(1200, 700);
		setTitle("Funcionários");
		setResizable(false);
		setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloFunc = new JLabel();
		tituloFunc.setText("Nossos Funcionários");
		tituloFunc.setBounds(400, 5, 400, 40);
		tituloFunc.setForeground(new Color(255,255,255));
		tituloFunc.setFont(new Font("Arial", 1, 20));
		add(tituloFunc);
		
		String[] colunasTabela= {"ID", "Nome", "Email", "CPF", "Telefone","Data de Nascimento", "Genero", "Status", "Cargo Atual", "Salario"};
		
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
        scroll.setBounds(20, 50, 1150, 500);
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
        			ModelFuncionario funcionario = new ModelFuncionario();
            		FuncionarioDAO funcDao = new FuncionarioDAO();
            		int linhaSelecao = -1;
            		linhaSelecao = tabelas.getSelectedRow();
            		if(linhaSelecao >=0) {
            			int pergDelete= JOptionPane.showConfirmDialog(null, "Deseja Mesmo Excluir?"
								,"Escolha entre sim ou não" ,JOptionPane.YES_NO_OPTION);
						if(pergDelete == JOptionPane.YES_OPTION) {
							int idFunc = Integer.parseInt(tabelas.getValueAt(linhaSelecao, 0).toString());
	            			funcionario.setIdFuncionario(idFunc);
	            			
	            			tabelas.getValueAt(linhaSelecao, 0);
	            			modeloTabela.removeRow(linhaSelecao);
	            			funcDao.excluirFunc(funcionario);
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
			FuncionarioDAO funcDAO = new FuncionarioDAO();
			List<ModelFuncionario> listaTable = funcDAO.listarFunc();
			
			for(ModelFuncionario func: listaTable) {
				Object[] funcionario = new Object[10];
				funcionario[0] = func.getIdFuncionario();
				funcionario[1] = func.getNomeFuncionario();
				funcionario[2] = func.getEmailFuncionario();
				funcionario[3] = func.getCpfFuncionario();
				funcionario[4] = func.getFoneFuncionario();
				funcionario[5] = func.getDataNascFuncionario();
				funcionario[6] = func.getGeneroFuncionario();
				funcionario[7] = func.getStatusFuncionario();
				funcionario[8] = func.getCargoFuncionario();
				funcionario[9] = func.getSalarioFunc();
				
				modeloTabela.addRow(funcionario);
			}
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(this, "Erro ao carregar funcionários: " + e);
		}
	}

}