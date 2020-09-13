package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.model.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PublicacaoAtivaResDTO extends RepresentationModel<PublicacaoAtivaResDTO> {
	private Long idPublicacao;
	private Date dtPublicacao;
	private Date dtEncerramento;
	private Estabelecimento estabelecimento;	
	private List<Anuncio> anuncios;	

	@JsonIgnore
	private List<Publicacao> publicacao;	
		
	public PublicacaoAtivaResDTO(Publicacao obj) {
		this.setAnuncios(obj.getAnuncios());
		this.setDtEncerramento(obj.getDtEncerramento());
		this.setDtPublicacao(obj.getDtPublicacao());
		this.setEstabelecimento(obj.getEstabelecimento());
		this.setIdPublicacao(obj.getId());
	}

	public PublicacaoAtivaResDTO(List<Publicacao> obj) {
		this.setPublicacao(obj);
	}

	public List<PublicacaoAtivaResDTO> toList() {
		List<PublicacaoAtivaResDTO> listDTO = new ArrayList<PublicacaoAtivaResDTO>();
		
		for (Publicacao e : this.publicacao) {
			listDTO.add(new PublicacaoAtivaResDTO(e));
		}
		
		return listDTO;
	}

	public PublicacaoAtivaResDTO(Long idPublicacao, Date dtPublicacao, Date dtEncerramento,
			Estabelecimento estabelecimento, List<Anuncio> anuncios, List<Publicacao> publicacao) {
		super();
		this.idPublicacao = idPublicacao;
		this.dtPublicacao = dtPublicacao;
		this.dtEncerramento = dtEncerramento;
		this.estabelecimento = estabelecimento;
		this.anuncios = anuncios;
		this.publicacao = publicacao;
	}

	public PublicacaoAtivaResDTO() {
		super();
	}

	public PublicacaoAtivaResDTO(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public PublicacaoAtivaResDTO(Link initialLink) {
		super(initialLink);
	}

	public Long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Long idPublicacao) {
		this.idPublicacao = idPublicacao;
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

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public List<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(List<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}	
}
