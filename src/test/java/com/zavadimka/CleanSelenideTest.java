package com.zavadimka;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты с чистым Selenide")
public class CleanSelenideTest {

    String url = "https://github.com/";
    String repo = "eroshenkoam/allure-example";
    int issueNumber = 80;

    @Test
    @Feature("Issue в репозитории GitHub")
    @Story("Поиск Issue")
    @Owner("zavadimka")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Issue", url = "https://github.com/")
    @DisplayName("Поиск Issue с заданным номером")
    public void issueSearchTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open(url);

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $(".QueryBuilder-InputWrapper").submit();

        $(linkText(repo)).click();
        $("#issues-tab").click();
        $(withText("#" + issueNumber)).shouldBe(exist);

        takeScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
