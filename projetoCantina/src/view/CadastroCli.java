package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import control.ClienteDAO;
import model.ModelCliente;

public class CadastroCli extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lbNome, lbEmail, lbSenha, lbCPF, lbFone, lbCep, tituloCad ;
	
	private JTextField txNome, txEmail,txCpf, txFone, txCep, txSenha;
	
	private JButton voltar, enviar;
	
	public static boolean cadastrado = false;
	
	public CadastroCli() {
		setSize(700, 700);
		setTitle("Cadastro de Funcionários");
		setResizable(false);
		setModal(true);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloCad = new JLabel();
		tituloCad.setText("Cadastro de Clientes");
		tituloCad.setBounds(215, 50, 400, 40);
		tituloCad.setForeground(new Color(255,255,255));
		tituloCad.setFont(new Font("Arial", 1, 20));
		add(tituloCad);
		
		lbNome = new JLabel();
		lbNome.setText("Nome: ");
		lbNome.setBounds(200, 150, 300, 30);
		lbNome.setForeground(new Color(255,255,255));
		lbNome.setFont(new Font("Arial",1,18));
		add(lbNome);
		
		lbEmail = new JLabel();
		lbEmail.setText("Email: ");
		lbEmail.setBounds(200, 190, 300, 30);
		lbEmail.setForeground(new Color(255,255,255));
		lbEmail.setFont(new Font("Arial",1,18));
		add(lbEmail);
		
		
		lbSenha = new JLabel();
		lbSenha.setText("Senha: ");
		lbSenha.setBounds(180, 230, 300, 30);
		lbSenha.setForeground(new Color(255,255,255));
		lbSenha.setFont(new Font("Arial",1,18));
		add(lbSenha);
		
		lbCPF = new JLabel();
		lbCPF.setText("Cpf: ");
		lbCPF.setBounds(200, 270, 300, 30);
		lbCPF.setForeground(new Color(255,255,255));
		lbCPF.setFont(new Font("Arial",1,18));
		add(lbCPF);
		
		lbFone = new JLabel();
		lbFone.setText("Telefone: ");
		lbFone.setBounds(160, 310, 300, 30);
		lbFone.setForeground(new Color(255,255,255));
		lbFone.setFont(new Font("Arial",1,18));
		add(lbFone);
		
		lbCep = new JLabel();
		lbCep.setText("Cep: ");
		lbCep.setBounds(200, 350, 300, 30);
		lbCep.setForeground(new Color(255,255,255));
		lbCep.setFont(new Font("Arial",1,18));
		add(lbCep);
		
		txNome = new JTextField();
		txNome.setBounds(260, 150,200, 30);
		add(txNome);
	
		txEmail = new JTextField();
		txEmail.setBounds(260, 190,200, 30);
		add(txEmail);
		
		txSenha = new JTextField();
		txSenha.setBounds(260, 230,200,30);
		add(txSenha);
		
		txCpf = new JTextField();
		txCpf.setBounds(260, 270, 200, 30);
		add(txCpf);
		
		txFone = new JTextField();
		txFone.setBounds(260,310,200,30);
		add(txFone);
		
		txCep = new JTextField();
		txCep.setBounds(260, 350, 200, 30);
		add(txCep);
		
		enviar = new JButton();
		enviar= new JButton();
		enviar.setText("Enviar");
		enviar.setBackground(new Color(30, 124, 180));
		enviar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		enviar.setForeground(new Color(255,255,255));
		enviar.setFont(new Font("Arial", 1,15));
		enviar.setBounds(280, 500, 100, 50);
		enviar.setFocusPainted(false);
		add(enviar);
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(txNome.getText().trim().isEmpty() || txEmail.getText().trim().isEmpty() || txCpf.getText().trim().isEmpty() || 
					txFone.getText().trim().isEmpty() || txCep.getText().trim().isEmpty() || txSenha.getText().trim().isEmpty() ) {
				    JOptionPane.showMessageDialog(CadastroCli.this, "Preencha todos os campos!");
				    return;
				}else {
					ModelCliente model = new ModelCliente();
					
					
					
					model.setNomeUsuario(txNome.getText());
					model.setCpfUsuario(txCpf.getText());
					model.setEmailUsuario(txEmail.getText());
					model.setCepUsuario(txCep.getText());
					model.setSenhaUsuario(txSenha.getText());
					model.setFoneUsuario(txFone.getText());
					model.setStatusCliente("Ativo");
					
					ClienteDAO inserir = new ClienteDAO();
					
					try {
						if(inserir.verificarUser(model)) {
							JOptionPane.showMessageDialog(CadastroCli.this, "Erro: Usuário já Existe no sistema");
						}else {
							inserir.inserirCli(model);
							JOptionPane.showMessageDialog(CadastroCli.this, "Usuário Cadastrado com Sucesso!");
						}
					}catch(SQLException e){
						JOptionPane.showMessageDialog(CadastroCli.this, "Erro ao cadastrar: "+ e);
					}
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
}