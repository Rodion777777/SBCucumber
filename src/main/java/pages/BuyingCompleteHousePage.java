package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;

public class BuyingCompleteHousePage extends BasePage {
    @FindBy(xpath = "//input[@id='estateCost']")
    private
    WebElement estateCost;


    @FindBy(xpath = "//input[@id='initialFee']")
    private
    WebElement initialFee;

    @FindBy(xpath = "//input[@id='creditTerm']")
    private
    WebElement creditItem;



    @FindBy(xpath = "//span[@data-test-id='monthlyPayment']")
    private WebElement monthlyPayment;

    public String getMonthlyPayment() {
        return monthlyPayment.getText();
    }

    @FindBy (xpath = "//div[contains(@class, 'discounts')]//label[contains(@class, 'switch_checked')]")
    private
    WebElement salaryCardSwitch;

    @FindBy(xpath = "//div[@class='dcCalc_frame__discounts']/div[5]//span[@class='dcCalc_switch__control']")
    private
    WebElement family;

    @FindBy(xpath = "//div[@class='dcCalc_frame__discounts']/div[3]//span[@class='dcCalc_switch__control']")
    private
    WebElement incomeStatement;

    @FindBy(xpath = "//span[@data-test-id='rate']")
    private WebElement rate;

    public String getRate() {
        return rate.getText();
    }

    public String getAmountOfCredit() {
        return amountOfCredit.getText();
    }

    @FindBy(xpath = "//span[@data-test-id='amountOfCredit']")
    private WebElement amountOfCredit;

    @FindBy(xpath = "//span[@data-test-id='requiredIncome']")
    private WebElement requiredIncome;

    public String getRequiredIncome() {
        return requiredIncome.getText();
    }

    @Step("ввод стоимости недвижимости {estateCostValue}")
    public void setEstateCost(String estateCostValue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)");
        WebDriverWait wait = new WebDriverWait(driver, 4, 2000);
        driver.switchTo().frame("iFrameResizer0");
        estateCost.clear();
        estateCost.sendKeys(estateCostValue);
        Function<? super WebDriver, Object> checkEstateCostElement = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String expectedPrice = "47 315 ₽";
                String actualPrice = monthlyPayment.getText();
                if ((!expectedPrice.equals(actualPrice))) {
                    estateCost.clear();
                    estateCost.sendKeys(estateCostValue);
                }
                return (expectedPrice.equals(actualPrice));

            }
        };


        wait.until(checkEstateCostElement);
    }

    @Step("ввод первоначального взноса {initialFeeValue}")
    public void setInitialFee(String initialFeeValue) {
        WebDriverWait wait = new WebDriverWait(driver, 4, 2000);
        initialFee.clear();
        initialFee.sendKeys(initialFeeValue);

        Function<? super WebDriver, Object> checkEstateCostElement1 = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String expectedPrice = "22 545 ₽";
                String actualPrice = monthlyPayment.getText();
                if ((!expectedPrice.equals(actualPrice))) {
                    initialFee.clear();
                    initialFee.sendKeys(initialFeeValue);
                }
                return (expectedPrice.equals(actualPrice));
            }
        };

        wait.until(checkEstateCostElement1);
    }

    @Step("ввод срока кредита {creditItemValue}")
    public void setCreditItem(String creditItemValue){
        WebDriverWait wait = new WebDriverWait(driver, 4, 2000);
        creditItem.clear();
        creditItem.sendKeys(creditItemValue);
        Function<? super WebDriver, Object> checkEstateCostElement2 = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String expectedPrice = "18 310 ₽";
                String actualPrice = monthlyPayment.getText();
                if((!expectedPrice.equals(actualPrice))){
                    creditItem.clear();
                    creditItem.sendKeys(creditItemValue);
                }
                return (expectedPrice.equals(actualPrice));
            }
        };
        wait.until(checkEstateCostElement2);
    }

    @Step("клик по зарплатной карте")
    public void clickSalaryCard() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollBy(0,350)");
        driver.switchTo().frame("iFrameResizer0");
        salaryCardSwitch.click();
    }

    @Step("клик по справке о доходах")
    public void clickIncomeStatement(){
        incomeStatement.click();
        driver.switchTo().defaultContent();
    }

    @Step("клик по молодой семье")
    public void clickFamily() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,150)");
        driver.switchTo().frame("iFrameResizer0");
        family.click();
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollBy(0,-500)");
        driver.switchTo().frame("iFrameResizer0");
    }

    @Step("сравнение стоимости недвижимости")
    public void assertAmountOfCredit(String amountOfCreditValue){
        assertEquals("сумма кредита не совпадает", amountOfCreditValue, getAmountOfCredit());
    }

    @Step("сравнение ежемесячного платежа")
    public void assertMonthlyPayment(String monthlyPaymentValue){
        assertEquals("ежемесячный платеж не совпадает", monthlyPaymentValue, getMonthlyPayment());
    }

    @Step("сравнение необходимого дохода")
    public void assertRequiredIncome(String requiredIncomePaymentValue){
        assertEquals("необходимый доход не совпадает", requiredIncomePaymentValue, getRequiredIncome());
    }

    @Step("сравнение процентной ставки")
    public void assertRate(String rateValue){
        assertEquals("процентная ставка не совпадает", rateValue, getRate());
    }

}
