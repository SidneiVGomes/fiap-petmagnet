package br.com.petmagnet.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.dto.interfac.RequestDTO;
import br.com.petmagnet.model.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicacaoReqPutDTO implements RequestDTO{
	@JsonIgnore
	private Long idEstabelecimento;
	private Long idColaborador;
	private Long idAnuncio;
	private Date dtPublicacao;
	private Date dtEncerramento;
	
	@Override
	public Object toEntity() {
		return new ModelMapper().map(this, Publicacao.class);
	}	
}
