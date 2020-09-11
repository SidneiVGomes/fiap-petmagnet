package br.com.petmagnet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
