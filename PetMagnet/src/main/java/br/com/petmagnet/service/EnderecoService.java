package br.com.petmagnet.service;
 
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petmagnet.model.Endereco;

@Service
public interface EnderecoService {
	public Endereco gravar(Endereco obj);
	public Endereco excluir(Long id);
	public Endereco consultar(Endereco obj);
	public List<Endereco> consultarTodos();
}
