package br.com.petmagnet.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.errors.ApiException;

import br.com.petmagnet.config.GeoLocationProperties;
import br.com.petmagnet.exception.AppBeanNotFoundException;
import br.com.petmagnet.model.Endereco;
import br.com.petmagnet.repository.EnderecoRepository;
import br.com.petmagnet.service.EnderecoService;
import br.com.petmagnet.util.AppLocalizacao;
import br.com.petmagnet.util.AppLocalizacaoModel;

@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private GeoLocationProperties geoLocationProperties;

	public Endereco gravar(Endereco obj) {
		Endereco endereco = this.consultarCEP(obj.getCep());

		if (endereco != null) {
			return endereco;
		}

		// Obtém a GeoLocalização
		AppLocalizacao gl = new AppLocalizacao(geoLocationProperties.getApiKey());
		AppLocalizacaoModel lcl = new AppLocalizacaoModel();

		try {
			lcl = gl.getGeoLocalizacao(obj.getCep());

			obj.setLatitude(lcl.getLatitude());
			obj.setLongitude(lcl.getLongitude());

			return this.enderecoRepository.save(obj);

		} catch (ApiException e1) {
			throw new AppBeanNotFoundException(e1.getMessage());
		} catch (InterruptedException e1) {
			throw new AppBeanNotFoundException(e1.getMessage());
		} catch (IOException e1) {
			throw new AppBeanNotFoundException(e1.getMessage());
		} catch (ParseException e1) {
			throw new AppBeanNotFoundException(e1.getMessage());
		} catch (org.json.simple.parser.ParseException e1) {
			throw new AppBeanNotFoundException(e1.getMessage());
		}
	}

	@Override
	public Endereco excluir(Long id) {
		Endereco endereco = this.enderecoRepository.findById(id).get();
		this.enderecoRepository.deleteById(id);
		return endereco;
	}

	@Override
	public Endereco consultar(Endereco obj) {
		try {
			if (obj.getLogradouro() == null) {
				return this.enderecoRepository.findByCep(obj.getCep()).get();
			} else {
				return this.enderecoRepository.findByLogradouroAndNumeroAndBairroAndCidadeAndUF(obj.getLogradouro(),
						obj.getNumero(), obj.getBairro(), obj.getCidade(), obj.getUF()).get();
			}

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Endereco> consultarTodos() {
		return this.enderecoRepository.findAll();
	}

	@Override
	public Endereco consultarCEP(String CEP) {
		try {
			return this.enderecoRepository.findByCep(CEP).get();
		} catch (Exception e) {
			return null;
		}
	}
}
