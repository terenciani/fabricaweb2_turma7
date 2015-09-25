package fabricaweb2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestHibernate {

	public static void main(String[] args) {
//		// Fabrica de EntityManager
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fabricaweb2");
//
//		// Gerenciador de Entidades
//		EntityManager em = emf.createEntityManager();

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");

		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		
		EntityManager em = emf.createEntityManager();
		
		// Criando objeto a ser persistido
		Usuario usu = new Usuario();
		usu.setNome("Virmerson");
		usu.setLogin("vir");
		usu.setSenha("123456");

		UsuarioDAO usuarioDAO = new UsuarioDAOJPA(em);

		//usuarioDAO.salvar(usu);

		Usuario usuModificar = usuarioDAO.buscarPorId(4);

		usuModificar.setLogin("Bento");

		usuarioDAO.salvar(usuModificar);

//		usuarioDAO.excluir(usuModificar);

		// System.out.println(usuModificar);
	}
}
