package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConteMainPage {
   // page_url = https://conteshop.by/

       public ConteMainPage(WebDriver driver) {
           this.driver = driver;
           PageFactory.initElements(driver, this);
       }

        WebDriver driver;

        @FindBy(xpath= "/html/body/div[4]/div[2]/div[1]/div/div[2]/div[3]/noindex/form/input[1]")
        WebElement SearchField;

        @FindBy(xpath="/html/body/div[4]/div[2]/div[1]/div/div[2]/div[3]/noindex/form/input[2]")
        WebElement SearchButton;

        @FindBy(xpath="/html/body/div[5]/div[6]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]")
        WebElement FilterButton;

        @FindBy(xpath="/html/body/div[5]/div[6]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/ul/li[4]")
        WebElement FilterByDescendingOrder; // фильтр по убыванию цены


        public ConteMainPage OpenPage()
        {
            driver.get("https://conteshop.by/");
            return this;
        }

        public ConteMainPage OpenPageFilter()
        {
            driver.get("https://conteshop.by/catalogsearch/result/?q=%D0%AE%D0%B1%D0%BA%D0%B0");
            return this;
        }


        public ConteMainPage SearchFor(String searchQuery) {
            Actions builder=new Actions(driver);    // создаем объект Actions
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(SearchField));
            builder.moveToElement(SearchField).click().sendKeys(searchQuery).build().perform();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(SearchButton));
            builder.moveToElement(SearchButton).click().build().perform();
            return this;
        }

    public ConteMainPage SortItems()
    {
        Actions builder = new Actions(driver);
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(FilterButton));
        builder.moveToElement(FilterButton).click().build().perform();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(FilterByDescendingOrder));
        FilterByDescendingOrder.click();
        return this;
    }
}
