package fabricaweb2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fabricadeprogramador.entidade.Usuario;

public class TestHibernate {

	public static void main(String[] args) {
		// Fabrica de Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fabricaweb2");

		// Gerenciador de Entidades
		EntityManager em = emf.createEntityManager();

		Usuario usu = new Usuario();
		usu.setNome("Marcelo");
		usu.setLogin("terenciani");
		usu.setSenha("152535");

		// Iniciando transacao
		em.getTransaction().begin();
		// Prepara a Instrucao SQL
		em.persist(usu);
		// Confirmando transacao, Fazendo a pesistencia no banco
		em.getTransaction().commit();
	}
}
