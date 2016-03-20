package br.com.akula.impl.auth;

import org.springframework.security.core.GrantedAuthority;

public class PermissaoConcedida implements GrantedAuthority {

	private static final long serialVersionUID = -5935552058923083419L;

	private String permissao;
	
	public PermissaoConcedida(String permissao) {
		this.permissao = permissao;
	}
	
	public String getAuthority() {
		return this.permissao;
	}

	@Override
	public String toString() {
		return "PermissaoConcedida [permissao=" + permissao + "]";
	}
}