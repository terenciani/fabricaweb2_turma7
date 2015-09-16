package fabricaweb2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.DAOException;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestUsuarioDAO2 {

	@Autowired
	@Qualifier ("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

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
	public void testBuscarTodos() {
		// Cria um novo usuario
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setLogin("teste");
		usuarioTeste.setNome("Teste");
		usuarioTeste.setSenha("teste");

		// Salva o Usuario do teste
		usuarioDAO.salvar(usuarioTeste);

		List<Usuario> usuariosLista = usuarioDAO.buscarTodos();

		assertTrue(usuariosLista.size() > 0);
	}
	
	@Test
	@Transactional
	public void testExcluir() throws DAOException {
		// Criar um novo usuario
		Usuario usu = new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");

		// Salvar Usuario de teste
		Usuario usuSalvo = usuarioDAO.salvar(usu);

		// Excluir Usuario
		usuarioDAO.excluir(usuSalvo);
		// Busca por Id
		Usuario usuExcluido = usuarioDAO.buscarPorId(usuSalvo.getId());

		// Define correto se o objeto não for encontrado
		assertEquals(null, usuExcluido);
	}

	@Test
	@Transactional
	public void testBuscarPorLogin() throws DAOException {
		// Criar um novo usuario
		Usuario usu = new Usuario();
		usu.setNome("test");
		usu.setLogin("test");
		usu.setSenha("test");

		// Salvar Usuario de teste
		Usuario usuSalvo = usuarioDAO.salvar(usu);

		// Busca por Id
		Usuario usuPersistido = usuarioDAO.buscarPorLogin(usu.getLogin());

		// Define correto se o objeto não for encontrado
		assertNotNull(usuPersistido);
	}
}
