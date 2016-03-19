package br.com.akula.api.dao;

import java.io.Serializable;

import br.com.akula.api.model.Permissao;

public interface SentinelaDao {
	public Object merge(Object o) throws RuntimeException;
	public <T> T find(Class<T> c, Serializable pk) throws RuntimeException;
	public Permissao findPermissao(String perm) throws RuntimeException;
}