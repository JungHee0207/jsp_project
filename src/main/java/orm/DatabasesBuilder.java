package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabasesBuilder {
	
//	String resource = "org/mybatis/example/mybatis-config.xml";
//	InputStream inputStream = Resources.getResourceAsStream(resource);
//	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	
	private static SqlSessionFactory factory;
	private static final String CONGIF = "orm/mybatisConfig.xml";
	
	static {
		//초기화
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsReader(CONGIF));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}

}
