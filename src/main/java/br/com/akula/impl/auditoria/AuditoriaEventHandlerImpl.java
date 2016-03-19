package br.com.akula.impl.auditoria;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.akula.api.auditoria.AuditoriaEvent;
import br.com.akula.api.auditoria.AuditoriaEventHandler;
import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.Auditavel;
import br.com.akula.api.model.EventoAuditoria;
import br.com.akula.api.model.Usuario;

public class AuditoriaEventHandlerImpl implements AuditoriaEventHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(AuditoriaEventHandlerImpl.class);

	@Autowired
	private SentinelaDao sentinelaDao;
	
	@Override
	public void onApplicationEvent(AuditoriaEvent event) {
		try {
			EventoAuditoria evA = event.getEvA();
			Auditavel audi = (Auditavel) event.getSource();
			Usuario usuarioDb = sentinelaDao.find(Usuario.class, event.getUser());
			switch (evA) {
			case INSERT:
				audi.setUsuarioInsert(usuarioDb);
				audi.setDataHoraInsert(new Date());
				break;
			case UPDATE:
				audi.setUsuarioUpdate(usuarioDb);
				audi.setDataHoraUpdate(new Date());
				break;
			case DELETE:
				audi.setUsuarioDelete(usuarioDb);
				audi.setDataHoraDelete(new Date());
				break;
			default:
				break;
			}
			sentinelaDao.merge(audi);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}