package com.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.KlovReporter;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Stepdefs {

    private WebDriver driver;
    public static ExtentReports extentReports;

    @Before
    public void initWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/Users/willyaguirre/Documents/opensource/geckodriver");
        driver = new FirefoxDriver();

        KlovReporter klovReporter = new KlovReporter();

        klovReporter.initMongoDbConnection("localhost", 27017);

        klovReporter.setProjectName("SWTESTACADEMY");

        klovReporter.setReportName("1.4");

        klovReporter.setKlovUrl("http://localhost:9000");

        extentReports = new ExtentReports();
        extentReports.attachReporter(klovReporter);

    }

    @Given("^I am on the Google search page$")
    public void I_visit_google() {
        driver.get("https:\\www.google.com");
    }

    @When("^I search for \"(.*)\"$")
    public void search_for(String query) {
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys(query);
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
    }

    @Then("^ the page title should start with \"(.*)\"$")
    public void checkTitle(String titleStartsWith) {
        // Google's search is rendered dynamically with JavaScript
        // Wait for the page to load timeout after ten seconds
        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese");
                // Should see: "cheese! -Google Search"
            }
        });
    }

    @After()
    public void closeBrowser() {
        driver.quit();
        extentReports.flush();
    }

}