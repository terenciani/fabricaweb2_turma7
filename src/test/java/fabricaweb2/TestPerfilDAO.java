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
import br.com.fabricadeprogramador.dao.PerfilDAO;
import br.com.fabricadeprogramador.entidade.Perfil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestPerfilDAO {

	@Autowired
	@Qualifier ("perfilDAOJPA")
	PerfilDAO perfilDAO;

	@Test
	@Transactional
	public void testSalvar() {
		Perfil per = new Perfil();
		per.setDescricao("ADMIN");

		Perfil perPersistido = perfilDAO.salvar(per);

		assertNotNull(perPersistido);
	}

	@Test
	@Transactional
	public void testBuscarPorId() {
		Perfil perfilTeste = new Perfil();
		perfilTeste.setDescricao("VISITANTE");

		Perfil perfilPersistido = perfilDAO.salvar(perfilTeste);
		Integer idSalvo = perfilPersistido.getId();

		Perfil perfilBuscado = perfilDAO.buscarPorId(idSalvo);

		assertEquals(perfilPersistido, perfilBuscado);

	}

	
	@Test
	@Transactional
	public void testBuscarTodos() {
		// Cria um novo perfil
		Perfil perfilTeste = new Perfil();
		perfilTeste.setDescricao("TESTE");;

		// Salva o Perfil do teste
		perfilDAO.salvar(perfilTeste);

		List<Perfil> perfilsLista = perfilDAO.buscarTodos();

		assertTrue(perfilsLista.size() > 0);
	}
	
	@Test
	@Transactional
	public void testExcluir() throws DAOException {
		// Criar um novo perfil
		Perfil per = new Perfil();
		per.setDescricao("TESTE_EXCLUSAO");

		// Salvar Perfil de teste
		Perfil perSalvo = perfilDAO.salvar(per);

		// Excluir Perfil
		perfilDAO.excluir(perSalvo);
		// Busca por Id
		Perfil perExcluido = perfilDAO.buscarPorId(perSalvo.getId());

		// Define correto se o objeto não for encontrado
		assertEquals(null, perExcluido);
	}

	
}
