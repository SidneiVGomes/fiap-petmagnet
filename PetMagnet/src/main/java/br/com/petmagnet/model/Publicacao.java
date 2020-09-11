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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
	@JsonIgnore
	private Estabelecimento estabelecimento;	
	
	@ManyToOne()
	@JoinColumn(name = "id_colaborador")
	@JsonIgnore
	private Colaborador colaborador;
	
	@ManyToMany()
	@JoinTable(name = "tb_publicacao_anuncio", 
			   joinColumns = @JoinColumn(name = "id_publicacao"), 
			   inverseJoinColumns = @JoinColumn(name = "id_anuncio"))
	@JsonIgnore
	private List<Anuncio> anuncios;	
	
	@Column(name = "cancelado")
	private Boolean cancelado;
}
