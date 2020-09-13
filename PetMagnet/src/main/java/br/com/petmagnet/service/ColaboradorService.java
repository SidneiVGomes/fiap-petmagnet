package br.com.petmagnet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petmagnet.dto.ColaboradorReqDTO;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;

@Service
public interface ColaboradorService {
	public Colaborador gravar(Colaborador obj);
	public Colaborador alterar(Long id, ColaboradorReqDTO obj);
	public Colaborador excluir(Long id);
	public Optional<Colaborador> consultarPorId(Long id);
	public Optional<Colaborador> consultarPorColaborador(Estabelecimento estabelecimento, Long idColaborador);
	public List<Colaborador> consultarTodos();
}
