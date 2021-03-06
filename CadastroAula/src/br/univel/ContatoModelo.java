package br.univel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContatoModelo extends AbstractTableModel{
	
	List<Contato> lista;
	
	public ContatoModelo() {
		this((List<Contato>)null);
	}
	
	public ContatoModelo(List<Contato> list) {
		if(list  == null){
			this.lista = new ArrayList<>();
			for(int i = 0; i< 10; i++){
				Contato c = new Contato();
				c.setId(i);
				c.setNome("Nome " + i);
				c.setTelefone("Telefone " + i);
				lista.add(c);
			}
		}else{
			this.lista = list;
		}
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0:
			return "Id";
		case 1: 
			return "Nome";
		case 2:
			return "Telefone";
		}
		
		return super.getColumnName(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Contato c = lista.get(row);
		switch(column){
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getTelefone();
		}
		return new Exception("coluna nao implementada");
	}

	public void adiconar(Contato c) {
		this.lista.add(c);
		fireTableDataChanged();
	}

	public Contato getContato(int idx) {
		return lista.get(idx);
	}

	public void exclui(Contato contatoSelecionado) {
		this.lista.remove(contatoSelecionado);
		this.fireTableDataChanged();
	}

}
