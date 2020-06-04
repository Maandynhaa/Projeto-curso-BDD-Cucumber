package br.ce.wcaquino.runners.steps;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class InserirContasSteps {
	WebDriver driver;

	@Dado("^que desejo adicionar a conta$")
	public void queDesejoAdicionarAConta() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://srbarriga.herokuapp.com/");
		driver.findElement(By.id("email")).sendKeys("amanda.amy12@hotmail.com");
		driver.findElement(By.id("senha")).sendKeys("teste12");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("^adiciono a conta \"([^\"]*)\"$")
	public void adicionoAConta(String arg1) throws Throwable {
		driver.findElement(By.id("nome")).sendKeys(arg1);
		driver.findElement(By.tagName("button")).click();
	}

	@Então("^recebo a mensagem \"([^\"]*)\"$")
	public void receboAMensagem(String msg) throws Throwable {
//		String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		String texto = driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-')]")).getText();
		assertEquals(msg, texto);
	}

	@After(order = 1, value = "@funcionais")
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("target/screenshot/" + cenario.getId() + ".jpg"));
			driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After(order = 0, value = "@funcionais")
	public void fecharBrownser() {
		driver.quit();
	}

//	@Dado("^que estou acessando a aplicação$")
//	public void queEstouAcessandoAAplicação() throws Throwable {
//		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get("https://srbarriga.herokuapp.com/");
//	}
//
//	@Quando("^informo o usuário \"([^\"]*)\"$")
//	public void informoOUsuário(String usuario) throws Throwable {
//		driver.findElement(By.id("email")).sendKeys(usuario);
//	}
//
//	@Quando("^a senha \"([^\"]*)\"$")
//	public void aSenha(String senha) throws Throwable {
//		driver.findElement(By.id("senha")).sendKeys(senha);
//	}
//
//	@Quando("^seleciono entrar$")
//	public void selecionoEntrar() throws Throwable {
//		driver.findElement(By.tagName("button")).click();
//	}
//
//	@Então("^visualizo a página inicial$")
//	public void visualizoAPáginaInicial() throws Throwable {
//		/// html/body/div[1]
//		String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
//		assertEquals("Bem vindo, Amanda!", texto);
//	}
//
//	@Quando("^seleciono Contas$")
//	public void selecionoContas() throws Throwable {
//		driver.findElement(By.linkText("Contas")).click();
//	}
//
//	@Quando("^seleciono Adicionar$")
//	public void selecionoAdicionar() throws Throwable {
//		driver.findElement(By.linkText("Adicionar")).click();
//	}
//
//	@Quando("^informo a conta \"([^\"]*)\"$")
//	public void informoAConta(String nome) throws Throwable {
//		driver.findElement(By.id("nome")).sendKeys(nome);
//	}
//
//	@Quando("^seleciono Salvar$")
//	public void selecionoSalvar() throws Throwable {
//		driver.findElement(By.tagName("button")).click();
//	}

}
