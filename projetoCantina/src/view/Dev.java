package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Dev extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel txDev, nmDev, imgVitor,imgFelipe;
	private JButton voltar;
	ImageIcon imagemFelps = new ImageIcon("assets/imgFelipe.png");
	ImageIcon imagemVitor = new ImageIcon("assets/imgvitor.jpg");
	
	public Dev() {
		setSize(620, 450);
		setTitle("Desenvolvedores");
		setResizable(false);
		setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		
		Container janelaDev = getContentPane();
		setLocationRelativeTo(janelaDev);
		janelaDev.setBackground(new Color(7, 99, 154));
		janelaDev.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(30, 124, 180));
		tabbedPane.setForeground(new Color(255,255,255));
		tabbedPane.setBounds(0,0,600,400);
			JPanel dev1 = new JPanel();
			dev1.setLayout(null);
			dev1.setBackground(new Color(30, 124, 180));
			
			imgVitor = new JLabel(imagemVitor);
			imgVitor.setBounds(30, 50, 200, 200);
			imgVitor.setBorder(new LineBorder(new Color(9, 91, 140), 3));
			dev1.add(imgVitor);
			
			nmDev = new JLabel();
			nmDev.setText("<html><body>Nome: Vitor Scarabelli Quadros<br>"
					+ "Email: scarabelli.vitinho@gmail.com</body></html>");
			nmDev.setBounds(240, 55, 300, 40);
			nmDev.setForeground(new Color(255,255,255));
			nmDev.setFont(new Font("Arial", 1, 15));
			dev1.add(nmDev);
			
			txDev = new JLabel();
			txDev.setText("'Bolo de morango'");
			txDev.setBounds(240, 85, 300, 30);
			txDev.setForeground(new Color(255,255,255));
			dev1.add(txDev);
			
			voltar = new JButton();
			voltar= new JButton();
			voltar.setText("Voltar");
			voltar.setBackground(new Color(255, 255, 255));
			voltar.setForeground(new Color(30, 124, 180));
			voltar.setBounds(10, 300, 100, 30);
			voltar.setFocusPainted(false);
			dev1.add(voltar);
			
			voltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});

			tabbedPane.addTab("Vitor", null, dev1, "Sobre o Vitor");
			
			JPanel dev2 = new JPanel();
			dev2.setLayout(null);
			dev2.setBackground(new Color(30, 124, 180));
			imgFelipe = new JLabel(imagemFelps);
			imgFelipe.setBounds(30, 50, 200, 200);
			imgFelipe.setBorder(new LineBorder(new Color(9, 91, 140), 3));
			dev2.add(imgFelipe);
			
			nmDev = new JLabel();
			nmDev.setText("<html><body>Nome: Felipe de Oliveira Silva<br>"
					+ "Email: felipeosilva0812@gmail.com</body></html>");
			nmDev.setBounds(240, 55, 300, 40);
			nmDev.setForeground(new Color(255,255,255));
			nmDev.setFont(new Font("Arial", 1, 15));
			dev2.add(nmDev);
			
			txDev = new JLabel();
			txDev.setText("'O Bolo é uma mentira!'");
			txDev.setBounds(240, 85, 300, 30);
			txDev.setForeground(new Color(255,255,255));
			dev2.add(txDev);
			
			voltar = new JButton();
			voltar= new JButton();
			voltar.setText("Voltar");
			voltar.setBackground(new Color(255, 255, 255));
			voltar.setForeground(new Color(30, 124, 180));
			voltar.setBounds(10, 300, 100, 30);
			voltar.setFocusPainted(false);
			dev2.add(voltar);
			
			voltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});
			
			tabbedPane.addTab("Felipe", null, dev2, "Sobre o Felipe");
			
		janelaDev.add(tabbedPane);
	}
}