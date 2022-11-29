package Test;

import Page.ConteMainPage;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConteTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @Test
    public void TestSearch() {
        var conteMainPage = new ConteMainPage(driver);
        conteMainPage.OpenPage();
        conteMainPage.SearchFor("Юбка");
        List<WebElement> searchResults = driver
                .findElements(By.xpath("/html/body/div[5]/div[6]/div/div[2]/div/div[2]/div[3]/div[2]/div/div"));
        assertEquals(true, searchResults.size() > 0);
    }

    @Test
    public void TestSort() {
        var conteMainPage = new ConteMainPage(driver);
        conteMainPage.OpenPageFilter();
        conteMainPage.SortItems();
        List<WebElement> searchResults = driver
                .findElements(By.xpath("/html/body/div[5]/div[6]/div/div[2]/div/div[2]/div[3]/div[2]/div/div"));
        if (searchResults.size() > 0) {
            String firstItemPrice = searchResults.get(0).findElement(By.xpath("//*[@id=\"blockPage1\"]/div/div[1]/div/div[2]/div[2]/div/div/div")).getText();   //получаем цену первого элемента
            String lastItemPrice = searchResults.get(searchResults.size() - 1).findElement(By.xpath("/html/body/div[5]/div[6]/div/div[2]/div/div[2]/div[3]/div[2]/div/div/div[17]/div/div[2]/div[2]/div/div/div")).getText();   //получаем цену последнего элемента
            assertEquals(true, firstItemPrice.compareTo(lastItemPrice) > 0);
            // сравниваем цены первого и последнего элементов, если первый элемент дороже, то сортировка по убыванию работает

            System.out.println("Цена первого элемента: " + firstItemPrice);
            System.out.println("Цена последнего элемента: " + lastItemPrice);
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }
}
