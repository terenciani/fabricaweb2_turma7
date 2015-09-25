package fabricaweb2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestUsuarioDAO {

	EntityManager em;
	ClassPathXmlApplicationContext ctx;
	UsuarioDAO usuarioDAO;

	@Before
	public void init() {
		
		ctx = new  ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		usuarioDAO = new UsuarioDAOJPA(em);
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

		assertEquals(usuarioPersistido, usuarioBuscado);
		

	}

	@Test
	public void testExcluir() throws DAOException {
		// Cria um novo usuario
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setLogin("teste");
		usuarioTeste.setNome("Teste");
		usuarioTeste.setSenha("teste");

		// Salva o Usuario do teste
		Usuario usuarioPersistido = usuarioDAO.salvar(usuarioTeste);

		// Exclui usuario
		usuarioDAO.excluir(usuarioPersistido);

		// Busca por id
		Usuario usuarioExcluido = usuarioDAO.buscarPorId(usuarioPersistido.getId());

		// Assert se for igual a null
		assertEquals(null, usuarioExcluido);
	}

	@Test
	public void testBuscarTodos() {
		// Cria um novo usuario
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setLogin("teste");
		usuarioTeste.setNome("Teste");
		usuarioTeste.setSenha("teste");

		// Salva o Usuario do teste
		usuarioDAO.salvar(usuarioTeste);
		
		List<Usuario> usuariosLista = usuarioDAO.buscarTodos();
		
		assertTrue(usuariosLista.size()>0);
	}
}
