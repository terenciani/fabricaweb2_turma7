package fabricaweb2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestUsuarioService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	@Test
	@Transactional
	public void testSalvar() throws ServiceException {
		// Criar um novo usuario
		Usuario usu = new Usuario();
		usu.setNome("testService2");
		usu.setLogin("testService2");
		usu.setSenha("testService2");

		Usuario usuarioSalvo =	usuarioService.salvar(usu);
		
		Assert.assertNotNull(usuarioSalvo);
		
	}

	@Test (expected = ServiceException.class)
	@Transactional
	public void testNaoSalvar() throws ServiceException {
		Usuario usu = new Usuario();
		usu.setNome("testService");
		usu.setLogin("testService");
		usu.setSenha("testService");

		usuarioDAO.salvar(usu);

		usuarioService.salvar(usu);
	}
}
