package br.univel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

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
		configuraMouse();
		LimparCampos();
	}
	
	private void configuraMouse() {
		this.table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					preencheCampos(idx);
				}
			}
		});
		
	}
	protected void preencheCampos(int idx) {
		contatoSelecionado = model.getContato(idx);
		
		super.txtId.setText(String.valueOf(contatoSelecionado.getId()));
		super.txtNome.setText(contatoSelecionado.getNome());
		super.txtTelefone.setText(contatoSelecionado.getTelefone());
		super.lblprontoParaSer.setText(PRONTO_PARA_SER_ATUALIZADO);
		btnExcluir.setEnabled(true);
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
				try {
					Salvar();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
				}
				
			}
		});
		
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("clicou em excluir");
				excluir();
			}
		});
	}
	
	
	protected void excluir() {
		model.exclui(contatoSelecionado);
		Novo();
	}
	protected void Salvar() {
		if(contatoSelecionado == null){
			Contato c = new Contato();
			c = salvaContato(c);
			model.adiconar(c);
		}else{
			salvaContato(contatoSelecionado);
			model.fireTableDataChanged();
		}
	}
	private Contato salvaContato(Contato c) {
		c.setId(Integer.parseInt(txtId.getText()));
		c.setNome(txtNome.getText());
		c.setTelefone(txtTelefone.getText());
		
		Novo();
		return c;
	}
	protected void Novo() {
		contatoSelecionado = null;
		LimparCampos();
	}
	
	private void LimparCampos() {
		txtId.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		btnExcluir.setEnabled(false);
		super.lblprontoParaSer.setText("");
	}
	
	private void configuraTabela() {
		this.model = new ContatoModelo();
		table.setModel(model);
	}

}
