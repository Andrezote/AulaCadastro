package br.univel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends TelaBasePrincipal{
	
	private ContatoModelo model;
	private Contato contatoSelecionado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	
	public TelaPrincipal() {
		super();
		configuraTabela();
		configuraBotoes();
	}
	
	private void configuraBotoes() {
		btnNovo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicou em novo");
				Novo();
			}});
		
		btnSalvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("clicou em salvar");
				Salvar();
			}
		});
		
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("clicou em excluir");
			}
		});
	}
	
	
	protected void Salvar() {
		Contato c = new Contato();
		int id = Integer.parseInt(txtId.getText());
		String nome = txtNome.getText();
		String telefone = txtTelefone.getText();
		
		c.setId(id);
		c.setNome(nome);
		c.setTelefone(telefone);
		
		model.adiconar(c);
		
	}
	protected void Novo() {
		contatoSelecionado = null;
		LimparCampos();
	}
	
	private void LimparCampos() {
		txtId.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
	}
	
	private void configuraTabela() {
		this.model = new ContatoModelo(null);
		table.setModel(model);
	}

}
