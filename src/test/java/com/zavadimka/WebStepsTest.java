package com.zavadimka;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты с использованием веб-степов")
public class WebStepsTest {

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
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openManePage(url);
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
        steps.takeScreenshot();
    }
}
