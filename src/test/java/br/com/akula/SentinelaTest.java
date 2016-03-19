package br.com.akula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.akula.api.model.EscopoPermissao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.service.SentinelaService;

@ContextConfiguration("/META-INF/app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SentinelaTest {

	@Autowired
	private SentinelaService sentinelaService;
	
	private static final String PERMISSAO = "ROLE_VISUALIZAR_LISTA_REUNIAO";
	private static final String GRUPO = "GRUPO_1";
	
	@Test
	@Transactional
	public void testSentinelaCrud() {
		sentinelaService.createGrupo("Grupo 2", "GRUPO_2", true);
		
		Grupo g = sentinelaService.findGrupo("GRUPO_2");
		
		assertEquals("Erro ao criar o grupo", "GRUPO_2", g.getIdentificadorUnico());
		
		g.setIdentificadorUnico("GRUPO_2_ALTERADO");
		
		sentinelaService.updateGrupo(g);
		
		Grupo gAlt = sentinelaService.findGrupo("GRUPO_2_ALTERADO");
		
		assertEquals("Erro ao alterar o grupo", "GRUPO_2_ALTERADO", gAlt.getIdentificadorUnico());
		
		
		sentinelaService.deleteGrupo(gAlt);
		
		Grupo gDel = sentinelaService.findGrupo("GRUPO_2_ALTERADO");
		
		assertNull(gDel);
	}
	
	
	@Test
	public void testSentinelaAll() throws InterruptedException {
		
		sentinelaService.createPermissao(PERMISSAO, EscopoPermissao.PAGINA);
		
		sentinelaService.createGrupo("Grupo 1", GRUPO, true);
		
		Grupo g = sentinelaService.findGrupo(GRUPO);
		
		sentinelaService.addPermissao(PERMISSAO, g);
		
		//DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:dataSource", "--user", "sa", "--password", "" });

		//Thread.sleep(10000);
	}
}