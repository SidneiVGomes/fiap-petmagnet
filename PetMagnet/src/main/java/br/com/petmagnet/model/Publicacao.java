package br.com.petmagnet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_publicacao")
public class Publicacao extends LogRegistro {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_publicacao")
	@Column(name = "id_publicacao")
	private Long id;

	@Column(name = "dt_publicacao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtPublicacao;

	@Column(name = "dt_encerramento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtEncerramento;

	@ManyToOne()
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento estabelecimento;	
	
	@ManyToOne()
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;
	
	@JsonIgnore
	@ManyToMany()
	@JoinTable(name = "tb_publicacao_anuncio", 
			   joinColumns = @JoinColumn(name = "id_publicacao"), 
			   inverseJoinColumns = @JoinColumn(name = "id_anuncio"))
	private List<Anuncio> anuncios;	
	
	@Column(name = "cancelado")
	private Boolean cancelado;

	public Publicacao(Long id, Date dtPublicacao, Date dtEncerramento,
			Estabelecimento estabelecimento, Colaborador colaborador, List<Anuncio> anuncios, Boolean cancelado) {
		super();
		this.id = id;
		this.dtPublicacao = dtPublicacao;
		this.dtEncerramento = dtEncerramento;
		this.estabelecimento = estabelecimento;
		this.colaborador = colaborador;
		this.anuncios = anuncios;
		this.cancelado = cancelado;
	}

	public Publicacao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}
}
