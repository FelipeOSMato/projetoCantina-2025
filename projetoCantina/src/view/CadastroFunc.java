package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import control.FuncionarioDAO;
import model.ModelFuncionario;

public class CadastroFunc extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lbNome, lbEmail, lbGenero, lbIdade, lbCPF, lbFone, lbCargo, tituloCad ;
	private JTextField txNome, txEmail,txCpf, txFone;
	
	private JSpinner dataNasc;
	
	private JRadioButton rbMasc, rbFem, rbOutro, rbGerente, rbEstagiario, rbAtendente, rbCozinheiro;
	
	private ButtonGroup grupoDoRadioGen, grupoDeRadioCarg;
	
	private JButton voltar, enviar;
	
	public static boolean cadastrado = false;
	
	public CadastroFunc() {
		setSize(700, 700);
		setTitle("Cadastro de Funcionários");
		setResizable(false);
		setModal(true);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloCad = new JLabel();
		tituloCad.setText("Cadastro de Funcionários");
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
		
		lbGenero = new JLabel();
		lbGenero.setText("Genero");
		lbGenero.setBounds(300, 350, 300, 30);
		lbGenero.setForeground(new Color(255,255,255));
		lbGenero.setFont(new Font("Arial",1,18));
		add(lbGenero);
		
		lbCargo = new JLabel();
		lbCargo.setText("Cargo");
		lbCargo.setBounds(300, 420, 300, 20);
		lbCargo.setForeground(new Color(255,255,255));
		lbCargo.setFont(new Font("Arial",1,18));
		add(lbCargo);
		
		lbIdade = new JLabel();
		lbIdade.setText("Data de Nascimento: ");
		lbIdade.setBounds(170, 230, 300, 30);
		lbIdade.setForeground(new Color(255,255,255));
		lbIdade.setFont(new Font("Arial",1,18));
		add(lbIdade);
		
		lbCPF = new JLabel();
		lbCPF.setText("CPF: ");
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
		
		txNome = new JTextField();
		txNome.setBounds(260, 150,200, 30);
		add(txNome);
	
		txEmail = new JTextField();
		txEmail.setBounds(260, 190,200, 30);
		add(txEmail);
		
		txCpf = new JTextField();
		txCpf.setBounds(260, 270,200,30);
		add(txCpf);
		
		txFone = new JTextField();
		txFone.setBounds(260, 310, 200, 30);
		add(txFone);
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(2000,Calendar.JANUARY, 1);
		Date dataInic = calendario.getTime();
		
		SpinnerDateModel modeloData = new SpinnerDateModel(dataInic, null, new Date(), Calendar.DAY_OF_MONTH);
		dataNasc = new JSpinner(modeloData);
		JSpinner.DateEditor editorData = new JSpinner.DateEditor(dataNasc, "dd/MM/yyyy");
		dataNasc.setEditor(editorData);
		dataNasc.setBounds(360,230,100,30);
		add(dataNasc);
		
		
		rbMasc = new JRadioButton();
		rbMasc.setText("Masculino");
		rbMasc.setFont(new Font("Arial",1,15));
		rbMasc.setForeground(new Color(255,255,255));
		rbMasc.setBackground(new Color(30,124,180));
		rbMasc.setBounds(200,380,110,30);
		add(rbMasc);
		
		rbFem = new JRadioButton();
		rbFem.setText("Feminino");
		rbFem.setFont(new Font("Arial",1,15));
		rbFem.setBackground(new Color(30,124,180));
		rbFem.setForeground(new Color(255,255,255));
		rbFem.setBounds(310,380,100,30);
		add(rbFem);
		
		rbOutro = new JRadioButton();
		rbOutro.setText("Outro");
		rbOutro.setFont(new Font("Arial",1,15));
		rbOutro.setForeground(new Color(255,255,255));
		rbOutro.setBackground(new Color(30,124,180));
		rbOutro.setBounds(410,380,100,30);
		add(rbOutro);
		
		grupoDoRadioGen = new ButtonGroup();
		grupoDoRadioGen.add(rbMasc);
		grupoDoRadioGen.add(rbFem);
		grupoDoRadioGen.add(rbOutro);
		
		rbGerente = new JRadioButton();
		rbGerente.setText("Gerente");
		rbGerente.setFont(new Font("Arial",1,15));
		rbGerente.setForeground(new Color(255,255,255));
		rbGerente.setBackground(new Color(30,124,180));
		rbGerente.setBounds(120,450,110,30);
		add(rbGerente);
		
		rbEstagiario = new JRadioButton();
		rbEstagiario.setText("Estagiario");
		rbEstagiario.setFont(new Font("Arial",1,15));
		rbEstagiario.setForeground(new Color(255,255,255));
		rbEstagiario.setBackground(new Color(30,124,180));
		rbEstagiario.setBounds(230,450,110,30);
		add(rbEstagiario);
		
		rbAtendente = new JRadioButton();
		rbAtendente.setText("Atendente");
		rbAtendente.setFont(new Font("Arial",1,15));
		rbAtendente.setForeground(new Color(255,255,255));
		rbAtendente.setBackground(new Color(30,124,180));
		rbAtendente.setBounds(340,450,110,30);
		add(rbAtendente);
		
		rbCozinheiro = new JRadioButton();
		rbCozinheiro.setText("Cozinheiro");
		rbCozinheiro.setFont(new Font("Arial",1,15));
		rbCozinheiro.setForeground(new Color(255,255,255));
		rbCozinheiro.setBackground(new Color(30,124,180));
		rbCozinheiro.setBounds(450,450,110,30);
		add(rbCozinheiro);
		
		grupoDeRadioCarg = new ButtonGroup();
		grupoDeRadioCarg.add(rbGerente);
		grupoDeRadioCarg.add(rbEstagiario);
		grupoDeRadioCarg.add(rbAtendente);
		grupoDeRadioCarg.add(rbCozinheiro);
		
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
					txFone.getText().trim().isEmpty()) {
				    JOptionPane.showMessageDialog(CadastroFunc.this, "Preencha todos os campos!");
				    return;
				}else {
					ModelFuncionario model = new ModelFuncionario();
					
					if(rbGerente.isSelected()) {
						model.setCargoFuncionario("Gerente");
					}else if(rbEstagiario.isSelected()) {
						model.setCargoFuncionario("Estagiário");
					}else if(rbAtendente.isSelected()) {
						model.setCargoFuncionario("Atendente");
					}else if(rbCozinheiro.isSelected()) {
						model.setCargoFuncionario("Cozinheiro");
					}else {
						JOptionPane.showMessageDialog(CadastroFunc.this, "Selecione um Cargo!");
						return;
					}
					
					if(rbMasc.isSelected()) {
						model.setGeneroFuncionario("Masculino");
					}else if(rbFem.isSelected()) {
						model.setGeneroFuncionario("Feminino");
					}else if(rbOutro.isSelected()) {
						model.setGeneroFuncionario("Outro");
					}else {
						JOptionPane.showMessageDialog(CadastroFunc.this, "Selecione um Gênero!");
						return;
					}
					
					model.setNomeFuncionario(txNome.getText());
					model.setCpfFuncionario(txCpf.getText());
					model.setEmailFuncionario(txEmail.getText());
					model.setFoneFuncionario(txFone.getText());
					model.setStatusFuncionario("Ativo");
					
					Date dataNascimento = (Date) dataNasc.getValue();
					
					model.setDataNascFuncionario(dataNascimento);
					
					FuncionarioDAO inserir = new FuncionarioDAO();
					
					try {
						
						if(inserir.verificarFunc(model)) {
							JOptionPane.showMessageDialog(CadastroFunc.this, "O Funcionário Cadastrado já Existe!");
						}else {
							inserir.inserirFunc(model);
							JOptionPane.showMessageDialog(CadastroFunc.this, "Funcionário Cadastrado com Sucesso!");
						}
					}catch(SQLException e){
						JOptionPane.showMessageDialog(CadastroFunc.this, "Erro ao cadastrar: "+ e);
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