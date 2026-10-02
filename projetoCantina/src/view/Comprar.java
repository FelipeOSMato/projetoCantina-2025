package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.CarrinhoDAO;
import control.ProdutoDAO;
import model.ModelCarrinho;
import model.ModelProdu;

public class Comprar extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel tituloComprar, txTuto, lbCategoria, lbCliente, lbForma, lbQuantia, quantiaReal, lbValor, lbHeader;
	
	private int tanto = 0, estoque = 0;
	private double valor = 0.0;
	private String exibirValor = "0,00";
	
	private JButton adi, tirar, voltar, enviar, comprar;
	
	private JComboBox cbTipoProdu, cbCliente, cbTipoPagamento;
	
	private JScrollPane scroll;

	
	public Comprar() {
		setSize(1000, 750);
		setTitle("Loja");
		setResizable(false);
		setLayout(null);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(30, 124, 180));
		
		tituloComprar = new JLabel();
		tituloComprar.setText("Nossa Loja");
		tituloComprar.setBounds(450, 50, 400, 40);
		tituloComprar.setForeground(new Color(255,255,255));
		tituloComprar.setFont(new Font("Arial", 1, 25));
		add(tituloComprar);
		
		txTuto = new JLabel();
		txTuto.setBounds(340, 30, 400, 200);
		txTuto.setText("<html><body><center>Para comprar selecione <br/>"
				+ "A categoria do produto, o produto desejado <br/>"
				+ "O cliente que deseja comprar <br/>"
				+ "E o método de pagamento!</center></body></html>");
		txTuto.setForeground(new Color (255,255,255));
		txTuto.setFont(new Font("Arial", 1, 15));
		add(txTuto);
		
		String[] inicio = {"Selecionar..."};
		
		lbCategoria = new JLabel();
		lbCategoria.setText("Categoria");
		lbCategoria.setBounds(200, 210, 300, 30);
		lbCategoria.setForeground(new Color(255,255,255));
		lbCategoria.setFont(new Font("Arial",1,18));
		add(lbCategoria);
		
		cbTipoProdu = new JComboBox<>(inicio);
		cbTipoProdu.setBounds(150, 180, 200, 30);
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
		
		String[] inicioCar = {"Selecionar..."};
		
		cbCliente = new JComboBox<>(inicioCar);
		cbCliente.setBounds(400, 180, 200, 30);
		cbCliente.setFont(new Font("Arial", Font.BOLD, 16));
		cbCliente.setForeground(Color.WHITE);
		cbCliente.setBackground(new Color(30, 124, 180));
		cbCliente.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		add(cbCliente);
		
		lbCliente = new JLabel();
		lbCliente.setText("Cliente");
		lbCliente.setBounds(465, 210, 300, 30);
		lbCliente.setForeground(new Color(255,255,255));
		lbCliente.setFont(new Font("Arial",1,18));
		add(lbCliente);
		
		try {
			CarrinhoDAO carDao = new CarrinhoDAO();
			List<ModelCarrinho> listarCar = carDao.listarClienteCarro();
			for(ModelCarrinho tipCar:listarCar) {
				cbCliente.addItem(tipCar.getNomeCliente());
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar o tipo do produto: " + e);
		}
		
		cbTipoPagamento = new JComboBox<>(inicioCar);
		cbTipoPagamento.setBounds(650, 180, 200, 30);
		cbTipoPagamento.setFont(new Font("Arial", Font.BOLD, 16));
		cbTipoPagamento.setForeground(Color.WHITE);
		cbTipoPagamento.setBackground(new Color(30, 124, 180));
		cbTipoPagamento.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		add(cbTipoPagamento);
		
		lbForma = new JLabel();
		lbForma.setText("Forma de Pagamento");
		lbForma.setBounds(660, 210, 300, 30);
		lbForma.setForeground(new Color(255,255,255));
		lbForma.setFont(new Font("Arial",1,18));
		add(lbForma);
		
		try {
			CarrinhoDAO carDao = new CarrinhoDAO();
			List<ModelCarrinho> listarCar = carDao.listarTipoPagamento();
			for(ModelCarrinho tipCar:listarCar) {
				cbTipoPagamento.addItem(tipCar.getTipoPagamento());
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar o tipo do produto: " + e);
		}
		
		lbHeader = new JLabel();
		lbHeader.setText("Produto  |  Descrição  |  Valor  |  Em Estoque");
		lbHeader.setBounds(300, 150, 800, 200);
		lbHeader.setForeground(new Color(255,255,255));
		lbHeader.setFont(new Font("Arial",1,18));
		add(lbHeader);
		
		DefaultListModel<ModelProdu> modeloLista = new DefaultListModel<>();
		JList<ModelProdu> listaProd = new JList(modeloLista);
		listaProd.setBackground(new Color(30, 124, 180)); // fundo
		listaProd.setForeground(Color.WHITE);              // cor do texto
		listaProd.setFont(new Font("Arial", Font.BOLD, 16));;
		listaProd.setSelectionBackground(Color.WHITE); // fundo seleção
		listaProd.setSelectionForeground(new Color(30, 124, 180));
		listaProd.setFixedCellHeight(30);
		listaProd.setBorder(new LineBorder(Color.WHITE, 1));
		
        JScrollPane scroll = new JScrollPane(listaProd);
        scroll.setBounds(120, 260, 750, 200);
        add(scroll);
		
		ModelProdu modelProd = new ModelProdu();
		
		cbTipoProdu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(cbTipoProdu.getSelectedItem().equals("Selecionar...")) {
					JOptionPane.showMessageDialog(null, "Não esqueça de selecionar um tipo!");
					modeloLista.clear();
				}else {
					try {
						ProdutoDAO prod = new ProdutoDAO();
						List<ModelProdu> listaProdSelec = prod.listarProdTipo(cbTipoProdu.getSelectedItem().toString());
						
						modeloLista.clear();
						
						for(ModelProdu produto:listaProdSelec) {
							modeloLista.addElement(produto);
						}
					}catch(SQLException e) {
						JOptionPane.showMessageDialog(Comprar.this, "Erro ao listar: " + e);
					}
				}
			}
		});
		lbQuantia = new JLabel();
		lbQuantia.setText("Quantidade: ");
		lbQuantia.setBounds(440, 470, 300, 30);
		lbQuantia.setForeground(new Color(255,255,255));
		lbQuantia.setFont(new Font("Arial",1,18));
		add(lbQuantia);
		
		adi = new JButton();
		adi.setText("+");
		adi.setBackground(new Color(255, 255, 255));
		adi.setForeground(new Color(30, 124, 180));
		adi.setBounds(520, 500, 70, 30);
		adi.setFocusPainted(false);
		add(adi);
		
		quantiaReal = new JLabel();
		quantiaReal.setText(""+tanto);
		quantiaReal.setBounds(490, 500, 300, 30);
		quantiaReal.setForeground(new Color(255,255,255));
		quantiaReal.setFont(new Font("Arial",1,20));
		add(quantiaReal);
		
		tirar = new JButton();
		tirar.setText("-");
		tirar.setBackground(new Color(255, 255, 255));
		tirar.setForeground(new Color(30, 124, 180));
		tirar.setBounds(400, 500, 70, 30);
		tirar.setFocusPainted(false);
		add(tirar);
		
		lbValor = new JLabel();
		lbValor.setText("Valor Total: R$"+exibirValor);
		lbValor.setBounds(410, 540, 300, 30);
		lbValor.setForeground(new Color(255,255,255));
		lbValor.setFont(new Font("Arial",1,20));
		add(lbValor);
		
		adi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(tanto>=0 && tanto<listaProd.getSelectedValue().getQuantiaEstoque() && listaProd.getSelectedIndex() != -1) {
		    		tanto ++;
			        
			        estoque = listaProd.getSelectedValue().getQuantiaEstoque()-(tanto);
		    		valor = tanto*listaProd.getSelectedValue().getValorProdu();
		    		exibirValor = String.format("%.2f", valor);
			        
		    		lbValor.setText("Valor Total: R$"+exibirValor);
			        quantiaReal.setText("" + tanto);
		        }else if(listaProd.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog(null, "Não esqueça de selecionar um item!");
		        }
		    }
		});

		tirar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (tanto > 0 && tanto<=listaProd.getSelectedValue().getQuantiaEstoque() && listaProd.getSelectedIndex() != -1) {
		            tanto--;
		            
			        estoque = listaProd.getSelectedValue().getQuantiaEstoque()-(tanto);
		    		valor = tanto*listaProd.getSelectedValue().getValorProdu();
		    		exibirValor = String.format("%.2f", valor);
		    		
		    		lbValor.setText("Valor Total: R$"+exibirValor);
		            quantiaReal.setText("" + tanto);
		        }else if(listaProd.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog(null, "Não esqueça de selecionar um item!");
		        }
		    }
		});
		
		listaProd.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && listaProd.getSelectedIndex() != -1) {
			        tanto = 0;
			        estoque = listaProd.getSelectedValue().getQuantiaEstoque()-(tanto);
			        
		    		valor = tanto*listaProd.getSelectedValue().getValorProdu();
			        exibirValor = String.format("%.2f", valor);
			        
			        lbValor.setText("Valor Total: R$"+exibirValor);
			        quantiaReal.setText("" + tanto);
				}
			}
		});
		
		comprar = new JButton();
		comprar.setText("Finalizar");
		comprar.setBackground(new Color(255, 255, 255));
		comprar.setForeground(new Color(30, 124, 180));
		comprar.setBounds(450, 600, 100, 50);
		comprar.setFont(new Font("Arial",1,16));
		comprar.setFocusPainted(false);
		add(comprar);
		
		comprar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(cbTipoProdu.getSelectedItem().equals("Selecionar...") || cbCliente.getSelectedItem().equals("Selecionar...") 
				||cbTipoPagamento.getSelectedItem().equals("Selecionar...") || tanto == 0) {
					JOptionPane.showMessageDialog(Comprar.this,"Não deixe nenhum campo vazio !");
				}else {
					ModelCarrinho modelCarro = new ModelCarrinho();
					ModelProdu prod = new ModelProdu();
					
					prod.setQuantiaEstoque(estoque);
					prod.setIdProdu(listaProd.getSelectedValue().getIdProdu());
					
					modelCarro.setNomeCliente(cbCliente.getSelectedItem().toString());
					modelCarro.setNomeProduto(listaProd.getSelectedValue().getNomeProdu());
					modelCarro.setQuantidadeItens(tanto);
					modelCarro.setTipoPagamento(cbTipoPagamento.getSelectedItem().toString());
					modelCarro.setValorPagamento(valor);
					
					CarrinhoDAO inserir = new CarrinhoDAO();
					ProdutoDAO prodDao = new ProdutoDAO();
					
					try {
						int pergCompra = JOptionPane.showConfirmDialog(null, "Deseja Finalizar Compra?"
								,"Escolha entre sim ou não" ,JOptionPane.YES_NO_OPTION);
						if(pergCompra == JOptionPane.YES_OPTION) {
							inserir.inserirCarrinho(modelCarro);
							prodDao.alterarEstoque(prod);
							JOptionPane.showMessageDialog(Comprar.this, "Compra Finalizada com sucesso!");
							dispose();
						}
					}catch(SQLException e) {
						JOptionPane.showMessageDialog(Comprar.this, "Erro ao cadastrar: " + e);
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