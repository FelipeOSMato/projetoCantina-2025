package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Sobre extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel titQuem, titHist, txQuem, txHist;
	private JButton voltar;
	
	public Sobre() {
		setSize(700, 600);
		setTitle("Sobre Nós");
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		titQuem = new JLabel();
		titQuem.setBounds(280, 50, 200, 40);
		titQuem.setText("<html><body><h2>Quem Somos</h2></body></html>");
		titQuem.setForeground(new Color(255,255,255));
		add(titQuem);
		
		txQuem = new JLabel();
		txQuem.setBounds(140, 100, 400, 200);
		txQuem.setText("<html><body><center>Somos mais que uma cantina<br>"
				+ " somos um ponto de encontro, onde sabores se misturam com histórias.<br>"
				+ " Aqui, cada prato conta um pedaço de quem somos:<br>"
				+ " pessoas que se dedicam a servir com carinho,<br>"
				+ " criar momentos de alegria e transformar cada visita em uma experiência acolhedora.<br>"
				+ " Nossa essência está na amizade, no sabor e na energia que compartilhamos a cada refeição.</center></body></html>");
		txQuem.setForeground(new Color (255,255,255));
		add(txQuem);
		
		titHist = new JLabel();
		titHist.setBounds(280, 300, 200, 40);
		titHist.setText("<html><body><h2>Nossa História</h2></body></html>");
		titHist.setForeground(new Color(255,255,255));
		add(titHist);
		
		txHist = new JLabel();
		txHist.setBounds(140, 350, 400, 200);
		txHist.setText("<html><body><center>Nossa história começou com um sonho simples:<br>"
				+ "criar um espaço onde todos se sentissem em casa.<br>"
				+ " Ao longo dos anos, a cantina se tornou um ponto de encontro da comunidade,<br>"
				+ " marcada por risos, conversas e memórias compartilhadas.<br>"
				+ " Cada prato servido carrega um pedacinho dessa trajetória,<br>"
				+ " feita de dedicação, sabor e carinho, transformando momentos comuns em lembranças especiais<br></center></body></html>");
		txHist.setForeground(new Color (255,255,255));
		add(txHist);
		
		voltar = new JButton();
		voltar= new JButton();
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