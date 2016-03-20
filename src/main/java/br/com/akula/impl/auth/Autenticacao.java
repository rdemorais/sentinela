package br.com.akula.impl.auth;

import java.util.ArrayList;
import java.util.Collection;

import javax.security.auth.Subject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class Autenticacao implements Authentication {

	private static final long serialVersionUID = -8881369559420747692L;
	
	private String nomeUsuario;
	private boolean isAuth;
	private Collection<GrantedAuthority> permissoes = new ArrayList<GrantedAuthority>();
	private Object details;

	public String getName() {
		return nomeUsuario;
	}
	
	public void setName(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public void addPermissao(PermissaoConcedida permissao) {
		this.permissoes.add(permissao);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	public Object getCredentials() {
		return new Object();
	}

	public Object getDetails() {
		return this.details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}
	public Object getPrincipal() {
		return nomeUsuario;
	}

	public boolean isAuthenticated() {
		return this.isAuth;
	}

	public void setAuthenticated(boolean isAuth) throws IllegalArgumentException {
		this.isAuth = isAuth;
	}

	@Override
	public boolean implies(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

}