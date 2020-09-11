package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicacaoReqDTO implements RequestDTO{
	private Long idEstabelecimento;
	private Long idColaborador;
	private Long idAnuncio;
	private Date dtPublicacao;
	private Date dtEncerramento;
	
	@Override
	public Object toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(this.idEstabelecimento);
		
		Colaborador colaborador = new Colaborador();
		colaborador.setId(this.idColaborador);
		
		Anuncio anuncio = new Anuncio();
		anuncio.setId(this.idAnuncio);
		
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		anuncios.add(anuncio);
		
		Publicacao publicacao = new Publicacao();
		publicacao.setEstabelecimento(estabelecimento);
		publicacao.setColaborador(colaborador);
		publicacao.setAnuncios(anuncios);
		publicacao.setDtPublicacao(this.dtPublicacao);
		publicacao.setDtEncerramento(this.dtEncerramento);
		
		return publicacao;
	}	
}
