package br.com.petmagnet.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class LogRegistro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@CreatedDate
	@Column(name = "dt_criacao", nullable = false)
	@JsonIgnore
	private Instant dtCriacao = Instant.now();
	
	@LastModifiedDate
	@Column(name = "dt_alteracao", nullable = false)
	@JsonIgnore
	private Instant dtUltimaAlteracao = Instant.now();

	public LogRegistro(Instant dtCriacao, Instant dtUltimaAlteracao) {
		super();
		this.dtCriacao = dtCriacao;
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public LogRegistro() {
		super();
	}

	public Instant getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Instant dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Instant getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Instant dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
