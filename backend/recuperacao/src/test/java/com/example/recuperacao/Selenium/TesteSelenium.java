package com.example.recuperacao.Selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Disabled("pipeline execution")
public class TesteSelenium {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", getDriverLocation());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    private String getDriverLocation() {
        return requireNonNull(this.getClass().getClassLoader().getResource("selenium-drivers/chromedriver-96.exe")).getPath();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void AbrirSite() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contentEquals("PROVA DO FINAL DO SEMESTRE"), "Titulo Diferente");
    }

    @Test
    public void AbrirSiteEVerificarTitulo() throws InterruptedException {
        driver.get("http://localhost:3000/produto");
        Thread.sleep(3000);
        assertTrue(driver.findElement(By.className("titulo")).getText().contentEquals("Produtos"), "Titulo Diferente");
        Thread.sleep(3000);
        driver.get("http://localhost:3000/tipo");
        assertTrue(driver.findElement(By.className("titulo")).getText().contentEquals("Tipos de Produtos"), "Titulo Diferente");
    }


}
