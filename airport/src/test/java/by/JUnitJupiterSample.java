package by;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import by.app.config.DBConfig;
import by.app.config.RootConfig;
import by.app.config.WebMvcConfig;
import by.services.LanguageService;

//@SpringJUnitWebConfig(classes = {WebMvcConfig.class, DBConfig.class, RootConfig.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class, DBConfig.class, RootConfig.class})
class JUnitJupiterSample {
	
//	@Autowired
//	private ApplicationContext appContext;

	@Autowired
	private LanguageService ls;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test01() {
		assertTrue(true);
	}
	
	
	@Test
	void test02() {
		assertTrue(ls.getDefaultLang().getTag().equals("ru"));
	}
	
	

}
