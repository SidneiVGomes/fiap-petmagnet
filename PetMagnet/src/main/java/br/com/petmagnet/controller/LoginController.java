package br.com.petmagnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("conectar")
public class LoginController {

//	@Autowired
//	EstabelecimentoServiceImpl estabelecimentoService;
//	
//	@Autowired
//	ColaboradorServiceImpl ColaboradorService;
//	
//	@GetMapping("")
//	public String abrirFormLogin(Login l, Model m) {
//		return "login/entrar";
//	}
//	
//	@PostMapping("")
//	public String validarAcesso(@Valid Login login, BindingResult r, RedirectAttributes ra) {
//		if (r.hasErrors()) {
//			return "login/entrar";
//		}
//		
////		Colaborador colaborador = login.validarAcesso(ColaboradorService);
////		
////		if (colaborador == null) {
////			ra.addFlashAttribute("msgerro", "Ops! Usuário ou Senha não Cadastrados.");
////			return "redirect:conectar";
////		}
////		
//		return "redirect:estabelecimento";
//	}
}
