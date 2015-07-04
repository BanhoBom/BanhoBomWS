package banhobom.ws.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EstacaoLista {

	private List<Estacao> estacoes = new ArrayList<Estacao>();
	
	public EstacaoLista(){
	}

	public List<Estacao> getEstacoes() {
		return estacoes;
	}

	public void setEstacoes(List<Estacao> estacoes) {
		this.estacoes = estacoes;
	}
	
	
}
