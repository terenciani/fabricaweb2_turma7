package fabricaweb2;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestUsuarioDAO {

	EntityManager em;
	ClassPathXmlApplicationContext ctx;
	UsuarioDAO usuarioDAO;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		usuarioDAO = new UsuarioDAO(em);
	}
	
	@After
	public void finaliza() {
		ctx.close();
	}

	@Test
	public void testSalvar() {
		Usuario usu = new Usuario();
		usu.setLogin("terenciani");
		usu.setNome("Marcelo");
		usu.setSenha("152535");

		Usuario usuPersistido = usuarioDAO.salvar(usu);
				
		assertNotNull(usuPersistido);
	}

	@Test
	public void testBuscarPorId() {
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setLogin("teste");
		usuarioTeste.setNome("Teste");
		usuarioTeste.setSenha("teste");
		
		Usuario usuarioPersistido = usuarioDAO.salvar(usuarioTeste);
		Integer idSalvo = usuarioPersistido.getId();
		
		Usuario usuarioBuscado = usuarioDAO.buscarPorId(idSalvo);
		
		assertEquals(usuarioPersistido, usuarioBuscado);;
		
	}
}
