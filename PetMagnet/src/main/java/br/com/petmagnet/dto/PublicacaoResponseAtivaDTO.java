package br.com.petmagnet.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import br.com.petmagnet.model.Anuncio;
import br.com.petmagnet.model.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PublicacaoResponseAtivaDTO extends RepresentationModel<PublicacaoResponseAtivaDTO> {
	private Long id;
	private Date dtPublicacao;
	private Date dtEncerramento;
	private Estabelecimento estabelecimento;	
	private List<Anuncio> anuncios;	
}
