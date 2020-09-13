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

public class PublicacaoResDTO extends RepresentationModel<PublicacaoResDTO> {
	private Long idPublicacao;
	private Date dtPublicacao;
	private Date dtEncerramento;
	private EstabelecimentoResDTO estabelecimento;	
	private List<AnuncioResDTO> anuncios;	

	@JsonIgnore
	private List<Publicacao> publicacao;	
		
	public PublicacaoResDTO(Publicacao obj) {
		this.setAnuncios(new AnuncioResDTO(obj.getAnuncios()).toList());
		this.setDtEncerramento(obj.getDtEncerramento());
		this.setDtPublicacao(obj.getDtPublicacao());
		this.setEstabelecimento(new EstabelecimentoResDTO(obj.getEstabelecimento()));
		this.setIdPublicacao(obj.getId());
	}

	public PublicacaoResDTO(List<Publicacao> obj) {
		this.setPublicacao(obj);
	}

	public List<PublicacaoResDTO> toList() {
		List<PublicacaoResDTO> listDTO = new ArrayList<PublicacaoResDTO>();
		
		for (Publicacao e : this.publicacao) {
			listDTO.add(new PublicacaoResDTO(e));
		}
		
		return listDTO;
	}

	public PublicacaoResDTO(Long idPublicacao, Date dtPublicacao, Date dtEncerramento,
			Estabelecimento estabelecimento, List<Anuncio> anuncios, List<Publicacao> publicacao) {
		super();
		this.idPublicacao = idPublicacao;
		this.dtPublicacao = dtPublicacao;
		this.dtEncerramento = dtEncerramento;
		
		this.estabelecimento = new EstabelecimentoResDTO(estabelecimento);
		this.anuncios = new AnuncioResDTO(anuncios).toList();
		this.publicacao = publicacao;
	}

	public PublicacaoResDTO() {
		super();
	}

	public PublicacaoResDTO(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public PublicacaoResDTO(Link initialLink) {
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

	public List<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(List<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}

	public EstabelecimentoResDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoResDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public List<AnuncioResDTO> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<AnuncioResDTO> anuncios) {
		this.anuncios = anuncios;
	}
}
