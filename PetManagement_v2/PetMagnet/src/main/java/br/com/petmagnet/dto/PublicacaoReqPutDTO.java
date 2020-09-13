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

	public PublicacaoReqPutDTO(Long idEstabelecimento, Long idColaborador, Long idAnuncio, Date dtPublicacao,
			Date dtEncerramento) {
		super();
		this.idEstabelecimento = idEstabelecimento;
		this.idColaborador = idColaborador;
		this.idAnuncio = idAnuncio;
		this.dtPublicacao = dtPublicacao;
		this.dtEncerramento = dtEncerramento;
	}

	public PublicacaoReqPutDTO() {
		super();
	}

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}	
}