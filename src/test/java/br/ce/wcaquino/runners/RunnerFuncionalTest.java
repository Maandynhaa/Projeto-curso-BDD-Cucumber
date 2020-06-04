package br.ce.wcaquino.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features/", 
glue = {"br.ce.wcaquino.runners.steps","br.ce.wcaquino.config"},
tags = {"@funcionais"},
plugin = {"pretty", "html:target/report-html", "json:target/report.json" },
monochrome = true,
snippets = SnippetType.CAMELCASE,
dryRun = false,
strict = false
)

public class RunnerFuncionalTest {
@BeforeClass
//@Ignore
public static void reset() {
	System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver4.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("https://srbarriga.herokuapp.com/");
	driver.findElement(By.id("email")).sendKeys("amanda.amy12@hotmail.com");
	driver.findElement(By.id("senha")).sendKeys("teste12");
	driver.findElement(By.tagName("button")).click();
	driver.findElement(By.linkText("reset")).click();
	driver.quit();
	}
}
