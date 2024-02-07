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
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты с использованием лямба-степов")
public class LambdaStepsTest {

    String url = "https://github.com/";
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    @Feature("Issue в репозитории GitHub")
    @Story("Поиск Issue")
    @Owner("zavadimka")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Issue", url = "https://github.com/")
    @DisplayName("Поиск Issue с заданным номером")
    public void testLambdaStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open(url);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $(".QueryBuilder-InputWrapper").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).shouldBe(exist);
        });
        step("Делаем скриншот", () -> {
            takeScreenshot();
        });
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
