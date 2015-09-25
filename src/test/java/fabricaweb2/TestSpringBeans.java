package fabricaweb2;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringBeans {

	@Test
	public void testContextSpring() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");

		BasicDataSource bds = (BasicDataSource) ctx.getBean("dataSource");
		System.out.println(bds.getPassword());
		
		ctx.close();
	}

}
