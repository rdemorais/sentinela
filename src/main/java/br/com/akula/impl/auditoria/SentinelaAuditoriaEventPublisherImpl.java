package br.com.akula.impl.auditoria;

import java.io.Serializable;

import org.springframework.context.ApplicationEventPublisher;

import br.com.akula.api.auditoria.AuditoriaEvent;
import br.com.akula.api.auditoria.SentinelaAuditoriaEventPublisher;
import br.com.akula.api.model.Auditavel;
import br.com.akula.api.model.EventoAuditoria;

public class SentinelaAuditoriaEventPublisherImpl implements SentinelaAuditoriaEventPublisher{

	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public void notificar(Serializable user, Auditavel source, EventoAuditoria ev) {
		AuditoriaEvent audEv = new AuditoriaEvent(user, source, ev);
		this.publisher.publishEvent(audEv);
	}
}