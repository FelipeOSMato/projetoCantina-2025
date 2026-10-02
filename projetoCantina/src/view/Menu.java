package view;

import javax.swing.*;
import javax.swing.event.*;

import view.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class Menu extends JFrame {
	
	private JLabel txIndex;
	private JButton btComprar;
	private JLabel imagemLogo;
	
	ImageIcon imgLogo = new ImageIcon("assets/logoEscolar.png");
	
	public Menu() {
		setSize(800, 600);
		setTitle("Cantina");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		JMenu cadastrar = new JMenu("Cadastro");
		JMenu sobre = new JMenu("Sobre");
		JMenu sair = new JMenu("Sair");

		JMenuItem sairOp = new JMenuItem("Sair do Sistema");

		JMenuItem cadFunc = new JMenuItem("Cadastrar Funcionário");
		JMenuItem cadProd = new JMenuItem("Cadastrar Produto");
		JMenuItem cadCli = new JMenuItem("Cadastrar Cliente");
		JMenuItem dev = new JMenuItem("Nossos Desenvolvedores");
		JMenuItem funcio = new JMenuItem("Nossos Funcionários");
		JMenuItem sobNos = new JMenuItem("Sobre Nós");
		JMenuItem sobCli = new JMenuItem("Nossos Clientes");
		JMenuItem histCompra = new JMenuItem("Histórico de Compras");
		
		sair.add(sairOp);
		cadastrar.add(cadFunc);
		cadastrar.add(cadProd);
		cadastrar.add(cadCli);
		sobre.add(dev);
		sobre.add(funcio);
		sobre.add(sobCli);
		sobre.add(sobNos);
		sobre.add(histCompra);

		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(cadastrar);
		bar.add(sobre);
		bar.add(sair);
		
		//Perguntar se quer mesmo sair
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				int pergSair = JOptionPane.showConfirmDialog(null, "Deseja Realmente Sair?"
						,"Escolha entre sim ou não" ,JOptionPane.YES_NO_OPTION);
				if(pergSair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		sairOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int pergSair = JOptionPane.showConfirmDialog(null, "Deseja Realmente Sair?"
						,"Escolha entre sim ou não" ,JOptionPane.YES_NO_OPTION);
				if(pergSair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		});
		
		//
		cadFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastroFunc cadFuncio = new CadastroFunc();
				cadFuncio.setVisible(true);
			}
		});
		
		histCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				HistoricoCompra histComp = new HistoricoCompra();
				histComp.setVisible(true);
			}
		});
		
		cadProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastroProd cadProdu = new CadastroProd();
				cadProdu.setVisible(true);
			}
		});
		
		cadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastroCli cadCli = new CadastroCli();
				cadCli.setVisible(true);
			}
		});
		
		funcio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FuncionarioSobre funcionario = new FuncionarioSobre();
				funcionario.setVisible(true);
			}
		});
		
		sobCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ClientesSobre cliente = new ClientesSobre();
				cliente.setVisible(true);
			}
		});
		
		sobNos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Sobre sob = new Sobre();
				sob.setVisible(true);
			}
		});
		dev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Dev desenvolv = new Dev();
				desenvolv.setVisible(true);
			}
		});
		
		imagemLogo = new JLabel(imgLogo);
		imagemLogo.setBounds(300, 20, 200, 200);
		add(imagemLogo);
		
		txIndex = new JLabel();
		txIndex.setText("<html><body><center><h2>Bem Vindo à Cantina!</h2>"
				+ "<h3>Aqui você pode comprar diversas coisas, desde <br>"
				+ "alimentos e bebidas, até materiais escolares!</h3></center></body></html>");
		txIndex.setBounds(245, 220, 300, 150);
		txIndex.setForeground(new Color(255,255,255));
		add(txIndex);
		
		btComprar = new JButton();
		btComprar= new JButton();
		btComprar.setText("Comprar");
		btComprar.setBackground(new Color(30, 124, 180));
		btComprar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btComprar.setForeground(new Color(255,255,255));
		btComprar.setFont(new Font("Arial", 1,15));
		btComprar.setBounds(345, 380, 100, 50);
		btComprar.setFocusPainted(false);
		add(btComprar);
		
		btComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Comprar compras = new Comprar();
				compras.setVisible(true);
			}
		});
		
		setVisible(true);
		}
}
