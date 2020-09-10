package br.com.petmagnet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnuncioReqPutDTO implements RequestDTO{
	@JsonIgnore
	private Long idEstabelecimento;
	@JsonIgnore
	private Long idAnuncio;
	@JsonIgnore
	private Long idColaborador;
	private String titulo;
	private String descricao;
	
	@Override
	public Object toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		Colaborador colaborador = new Colaborador();
		Anuncio anuncio = new Anuncio();
		
		estabelecimento.setId(this.idEstabelecimento);
		colaborador.setId(this.idColaborador);
		
		anuncio.setColaborador(colaborador);
		anuncio.setEstabelecimento(estabelecimento);
		
		anuncio.setId(this.idAnuncio);
		anuncio.setTitulo(this.titulo);
		anuncio.setDescricao(this.descricao);
		
		return anuncio;
	}
	
	public Object toEntity(Long idEstabelecimento, Long idColaborador, Long idAnuncio) {
		this.setIdEstabelecimento(idEstabelecimento);
		this.setIdAnuncio(idAnuncio);
		this.setIdColaborador(idColaborador);
		
		return this.toEntity();
	}
}
