package br.com.petmagnet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.petmagnet.model.PerfilTipo;
import br.com.petmagnet.service.impl.UsuarioServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private UsuarioServiceImpl service;
	
	private static final String[] ACESSO_PUBLICO = {
			
			"/usuarios/**",
			"/anuncios/**",
			"/estabelecimentos/**"							
	};
	
	
	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
//	private static final String USUARIO = PerfilTipo.USUARIO.getDesc();
//	private static final String COLABORADOR = PerfilTipo.COLABORADOR.getDesc();

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

				http.authorizeRequests()
				// acessos liberados para usuarios
				.antMatchers("/usuarios/**", "/estabelecimentos/**")
				.hasAuthority("USUARIO")
			
				.antMatchers(HttpMethod.GET, ACESSO_PUBLICO).permitAll()
				.anyRequest().authenticated()
				 
				.and()
				.formLogin()
			//	.loginPage("/conectar")
				
				
				.defaultSuccessUrl("/",true)
				//.failureUrl("/login-error")
				.permitAll()
			.and()	
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado");
	
				;	
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable();
			}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}



	
}

