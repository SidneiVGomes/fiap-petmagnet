package br.com.petmagnet.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.maps.errors.ApiException;

import br.com.petmagnet.config.GeoLocationProperties;
import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.model.Estabelecimento;
import br.com.petmagnet.repository.EnderecoRepository;
import br.com.petmagnet.repository.EstabelecimentoRepository;
import br.com.petmagnet.service.EstabelecimentoService;
import br.com.petmagnet.util.AppImageCompress;
import br.com.petmagnet.util.AppLocalizacao;
import br.com.petmagnet.util.AppLocalizacaoModel;
import groovy.util.logging.Slf4j;

@Slf4j
@Service
@Transactional
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private GeoLocationProperties geoLocationProperties;

	@Override
	public Estabelecimento gravar(Estabelecimento estb) {
		Endereco endereco = this.enderecoRepository
				.findByLogradouroAndNumeroAndBairroAndCidadeAndUF(estb.getEndereco().getLogradouro(),
						estb.getEndereco().getNumero(), estb.getEndereco().getBairro(), estb.getEndereco().getCidade(),
						estb.getEndereco().getUF())
				.orElseGet(() -> gravarNovoEndereco(estb.getEndereco()));

		if (endereco == null) {
			return null;
		}

		estb.setEndereco(endereco);

		if (this.estabelecimentoRepository.findByCnpj(estb.getCnpj()).isPresent()) {
			throw new AppBeanNotFoundException("Estabelecimento já está cadastrado");
		}

		return this.estabelecimentoRepository.save(estb);
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
		Endereco novoEndereco = this.enderecoRepository
				.findByLogradouroAndNumeroAndBairroAndCidadeAndUF(novoEstabel.getEndereco().getLogradouro(),
						novoEstabel.getEndereco().getNumero(), novoEstabel.getEndereco().getBairro(),
						novoEstabel.getEndereco().getCidade(), novoEstabel.getEndereco().getUF())
				.map(endereco -> {
					endereco.setCep(novoEstabel.getEndereco().getCep());
					endereco.setUF(novoEstabel.getEndereco().getUF());
					endereco.setPais(novoEstabel.getEndereco().getPais());
					endereco.setLatitude(novoEstabel.getEndereco().getLatitude());
					endereco.setLongitude(novoEstabel.getEndereco().getLongitude());

					return this.enderecoRepository.save(endereco);
				}).orElseGet(() -> {
					return this.enderecoRepository.save(novoEstabel.getEndereco());
				});

		return this.estabelecimentoRepository.findById(id).map(estabalecimento -> {
			estabalecimento.setNome(novoEstabel.getNome());
			estabalecimento.setComplEndereco(novoEstabel.getComplEndereco());
			estabalecimento.setEndereco(novoEndereco);

			return this.estabelecimentoRepository.save(estabalecimento);
		}).orElseThrow(() -> new AppBeanNotFoundException("Estabelecimento não cadastrado"));
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

	private Endereco gravarNovoEndereco(Endereco e) {
		AppLocalizacao gl = new AppLocalizacao(geoLocationProperties.getApiKey());
		AppLocalizacaoModel lcl = new AppLocalizacaoModel();

		try {
			lcl = gl.getGeoLocalizacao(e.getCep());

			e.setLatitude(lcl.getLatitude());
			e.setLongitude(lcl.getLongitude());

			this.enderecoRepository.save(e);

		} catch (ApiException e1) {

		} catch (InterruptedException e1) {

		} catch (IOException e1) {

		} catch (ParseException e1) {

		} catch (org.json.simple.parser.ParseException e1) {

		}

		return e;
	}
}
