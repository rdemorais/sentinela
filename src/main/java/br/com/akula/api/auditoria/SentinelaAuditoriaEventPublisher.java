package br.com.akula.api.auditoria;

import java.io.Serializable;

import org.springframework.context.ApplicationEventPublisherAware;

import br.com.akula.api.model.Auditavel;
import br.com.akula.api.model.EventoAuditoria;

public interface SentinelaAuditoriaEventPublisher extends ApplicationEventPublisherAware{
	public void notificar(Serializable user, Auditavel source, EventoAuditoria ev);
}