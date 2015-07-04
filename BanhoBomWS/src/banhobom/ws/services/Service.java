package banhobom.ws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import banhobom.ws.entidades.Estacao;
import banhobom.ws.entidades.EstacaoLista;


@Path("/bb")
public class Service {
	
	@GET
	@Path("/listaEstacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public EstacaoLista retornarEstacaoLista() {
		EstacaoLista result = new EstacaoLista();
		result.setEstacoes(Estacao.retornarLista());
		return result;
	}
}
