package com.zavadimka;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу GitHub")
    public void openManePage(String url){
        open(url);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo){
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $(".QueryBuilder-InputWrapper").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo){
        $(linkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssueTab (){
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issueNumber}")
    public void shouldSeeIssueWithNumber (int issueNumber){
        $(withText("#" + issueNumber)).shouldBe(exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot (){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
