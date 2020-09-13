package br.com.petmagnet.service;

import org.springframework.stereotype.Service;
 
import br.com.petmagnet.model.Estabelecimento;

@Service
public interface LoginService {
	public Estabelecimento conectar(String email, String senha, EstabelecimentoService service);
	public void desconectar(Estabelecimento e);
}
