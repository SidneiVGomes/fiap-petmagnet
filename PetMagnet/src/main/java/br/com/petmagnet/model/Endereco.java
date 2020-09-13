package br.com.petmagnet.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco extends LogRegistro {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_endereco")
	@Column(name = "id_endereco")
	private Long id;	
	
	@Column(name = "ds_logradouro", nullable = false, length = 150)
	private String logradouro;
	
	@Column(name = "nr_estabel", nullable = false, length = 5)
	private String numero;
	
	@Column(name = "ds_bairro", nullable = false, length = 40)
	private String bairro;
	
	@Column(name = "ds_cidade", nullable = false, length = 40)
	private String cidade;
	
	@Column(name = "cd_uf", nullable = false, length = 3)
	private String UF;
	
	@Column(name = "cd_pais", nullable = false, length = 02)
	private String pais;
	
	@Column(name = "nr_cep", nullable = false, length = 9)
	private String cep;
	
	@Column(name = "nr_latitude")
	private String latitude;
	
	@Column(name = "nr_longitude")
	private String longitude;

	public Endereco(Long id, String logradouro, String numero, String bairro, String cidade, String uF, String pais,
			String cep, String latitude, String longitude) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		UF = uF;
		this.pais = pais;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Endereco() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
//	@OneToMany(mappedBy = "endereco")
//	private Set<Estabelecimento> estabelecimento;
	
	
}
