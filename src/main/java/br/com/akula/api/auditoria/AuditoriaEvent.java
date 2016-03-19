package br.com.akula.api.auditoria;

import java.io.Serializable;

import org.springframework.context.ApplicationEvent;

import br.com.akula.api.model.Auditavel;
import br.com.akula.api.model.EventoAuditoria;

public class AuditoriaEvent extends ApplicationEvent{

	private static final long serialVersionUID = -7527441145066979030L;
	
	private Serializable user;
	private EventoAuditoria evA;

	public AuditoriaEvent(Object source) {
		super(source);
	}
	
	public AuditoriaEvent(Serializable user, Auditavel source, EventoAuditoria ev) {
		super(source);
		this.evA = ev;
		this.user = user;
	}
	
	public EventoAuditoria getEvA() {
		return evA;
	}
	
	public Serializable getUser() {
		return user;
	}
}