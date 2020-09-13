package br.com.petmagnet.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
 
@Entity
@Table(name = "tb_estabelecimento")
public class Estabelecimento extends LogRegistro {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_estabelecimento")
	@Column(name = "id_estabelecimento")
	private Long id;	
	
	@Column(name = "nr_cnpj", length = 20)
	@CNPJ
	@NotEmpty
	private String cnpj;

	@Column(name = "nm_estabel", length = 60)
	@NotEmpty
	@Length(min = 5, max = 60, message = "O nome deve ter entre {min} a {max} caracteres")
	private String nome;
	
	@Column(name = "ds_compl_end", length = 20)
	private String complEndereco;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Endereco endereco;

	public Estabelecimento(Long id, @CNPJ @NotEmpty String cnpj,
			@NotEmpty @Length(min = 5, max = 60, message = "O nome deve ter entre {min} a {max} caracteres") String nome,
			String complEndereco, Endereco endereco) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.complEndereco = complEndereco;
		this.endereco = endereco;
	}

	public Estabelecimento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplEndereco() {
		return complEndereco;
	}

	public void setComplEndereco(String complEndereco) {
		this.complEndereco = complEndereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
