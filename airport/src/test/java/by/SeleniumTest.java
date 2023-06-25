package by;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import by.app.config.DBConfig;
import by.app.config.RootConfig;
import by.app.config.WebMvcConfig;

@SpringJUnitWebConfig(classes = {WebMvcConfig.class, DBConfig.class, RootConfig.class})
public class SeleniumTest {
	private WebDriver driver;
	JavascriptExecutor js;

	@BeforeEach
	void setUp() throws Exception {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testLangAdding() {
		for (int i = 0; i < 2; i++) {
			addNewLang();
		}
		
	}
	
	private void addNewLang() {
	    driver.get("http://localhost:8080/airport/");
	    driver.manage().window().setSize(new Dimension(947, 776));
		try {
			driver.findElement(By.linkText("Панель оператора")).click();
		} catch (Exception e) {
			driver.findElement(By.linkText("Operator bar")).click();
		}
	    driver.findElement(By.linkText("НАСТРОЙКА ЯЗЫКОВ ВОСПРОИЗВЕДЕНИЯ")).click();
	    driver.findElement(By.name("add")).click();
	    driver.findElement(By.name("SAVE")).click();
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).sendKeys("italiano-итальянский");
	    driver.findElement(By.name("SAVE")).click();
	    driver.findElement(By.id("tag")).click();
	    driver.findElement(By.id("tag")).sendKeys("it");
	    driver.findElement(By.name("SAVE")).click();
	}

}
