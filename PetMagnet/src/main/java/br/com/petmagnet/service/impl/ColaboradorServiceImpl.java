package br.com.petmagnet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.petmagnet.exception.BeanNotFoundException;
import br.com.petmagnet.model.Colaborador;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.repository.ColaboradorRepository;
import br.com.petmagnet.repository.EstabelecimentoRepository;
import br.com.petmagnet.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ColaboradorServiceImpl implements ColaboradorService {
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Override
	public Colaborador cadastrar(Colaborador novoColaborador) {
		Estabelecimento estabelecimento = this.estabelecimentoRepository
				.findById(novoColaborador.getEstabelecimento().getId())
				.orElseThrow(() -> new BeanNotFoundException("Estabelecimento não cadastrado"));

		Colaborador colaborador = this.colaboradorRepository.
				findByNomeAndSenha(novoColaborador.getNome(), novoColaborador.getSenha())
				.orElseGet(
						() -> this.colaboradorRepository.save(novoColaborador)
			    );

		return colaborador;
	}

	@Override
	public Colaborador alterar(Long Id, Colaborador obj) {
		return this.colaboradorRepository.findById(Id)
				.map(colaborador -> {
					colaborador.setSenha(obj.getSenha());
					
					return this.colaboradorRepository.save(colaborador);
				})
				.orElseThrow(
						() -> new BeanNotFoundException("Colaborador não cadastrado")
				);
	}

	@Override
	public Colaborador excluir(Long id) {
		return this.colaboradorRepository.findById(id)
				.map(colaborador -> {
					this.colaboradorRepository.deleteById(id);
					return colaborador;
				}).orElseThrow(() -> new BeanNotFoundException("Estabelecimento não cadastrado"));
	}

	@Override
	public List<Colaborador> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Colaborador> consultarPorId(Long id) {
		return this.colaboradorRepository.findById(id);
	}

	@Override
	public Optional<Colaborador> consultarPorColaborador(Estabelecimento estabelecimento, Long idColaborador) {
		return this.colaboradorRepository.findByEstabelecimentoAndId(estabelecimento, idColaborador);
	}
}
