package br.com.petmagnet.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.repository.EstabelecimentoRepository;
import br.com.petmagnet.service.EstabelecimentoService;
import br.com.petmagnet.util.AppImageCompress;
import br.com.petmagnet.util.EnderecoTipo;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	private EnderecoServiceImpl enderecoService;
	
	@Override
	public Estabelecimento gravar(Estabelecimento obj) {
		obj.getEndereco().setTipoEndereco(EnderecoTipo.PESSOA_JURIDICA.ordinal());
		
		Endereco endereco =  this.enderecoService.consultar(obj.getEndereco());
				
		if (endereco == null) {
			endereco = this.enderecoService.gravar(obj.getEndereco());
		}
		
		obj.setEndereco(endereco);

		if (this.estabelecimentoRepository.findByCnpj(obj.getCnpj()).isPresent()) {
			throw new AppBeanNotFoundException("Estabelecimento já está cadastrado");
		}

		return this.estabelecimentoRepository.save(obj);
	}

	@Override
	public Estabelecimento excluir(Long id) {
		return this.estabelecimentoRepository.findById(id).map(estabelecimento -> {
			this.estabelecimentoRepository.deleteById(id);
			return estabelecimento;
		}).orElseThrow(() -> new AppBeanNotFoundException("Este estabelecimento não está cadastrado"));
	}

	@Override
	public List<Estabelecimento> consultarTodos() {
		return this.estabelecimentoRepository.findAll();
	}

	@Override
	public Estabelecimento consultarPorId(Long id) {
		return this.estabelecimentoRepository.findById(id)
				.orElseThrow(() -> new AppBeanNotFoundException("Estabelecimento não cadastrado"));
	}

	@Override
	public Estabelecimento alterar(Long id, Estabelecimento novoEstabel) {
		novoEstabel.getEndereco().setTipoEndereco(1);
		
		Endereco endereco = this.enderecoService.consultar(novoEstabel.getEndereco());
		
		if (endereco == null) {
			endereco = novoEstabel.getEndereco();
			
		} else {
			endereco.setTipoEndereco(novoEstabel.getEndereco().getTipoEndereco());
			endereco.setCep(novoEstabel.getEndereco().getCep());
			endereco.setUF(novoEstabel.getEndereco().getUF());
			endereco.setPais(novoEstabel.getEndereco().getPais());
			endereco.setLatitude(novoEstabel.getEndereco().getLatitude());
			endereco.setLongitude(novoEstabel.getEndereco().getLongitude());
		}

		endereco = this.enderecoService.gravar(endereco);
		
		Estabelecimento estabelecimento = this.estabelecimentoRepository.findById(id)
				.orElseThrow(() -> new AppBeanNotFoundException("Estabelecimento não cadastrado"));
		
		estabelecimento.setNome(novoEstabel.getNome());
		estabelecimento.setComplEndereco(novoEstabel.getComplEndereco());
		estabelecimento.setEndereco(endereco);

		return this.estabelecimentoRepository.save(estabelecimento);
	}

	public Boolean registrarLogo(Long idEstabelecimento, MultipartFile file) {
		Estabelecimento estabelecimento = this.estabelecimentoRepository.findById(idEstabelecimento)
			.orElseThrow(() -> new AppBeanNotFoundException("Estabelecimento não cadastrado"));

		try {
			estabelecimento.setLogo(AppImageCompress.compressBytes(file.getBytes()));
			
		} catch (IOException e) {
			throw new AppBeanNotFoundException("Estabelecimento não cadastrado");
		}
		
		this.estabelecimentoRepository.save(estabelecimento);
		
		return Boolean.TRUE;
	}
}
