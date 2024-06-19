package models.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import models.components.login.LoginDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private final static By usernameSel = AppiumBy.accessibilityId("input-email");
    private final static By passwordSel = AppiumBy.accessibilityId("input-password");
    private final static By loginBtnSel = AppiumBy.accessibilityId("button-LOGIN");
    private final static By invalidEmailTxtSelAndroid = AppiumBy.xpath("//*[contains(@text, 'valid email')]");
    private final static By invalidEmailTxtSeliOS = AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Please enter a valid email address\"]");
    private final static By invalidPasswordTxtSel = AppiumBy.xpath("//*[contains(@text, 'at least 8 characters')]");

    public LoginPage(AppiumDriver appiumDriver, String platformName) {
        super(appiumDriver, platformName);
    }

    public WebElement username(){
        return component.findElement(usernameSel);
    }

    @Step("Input username as {username}")
    public void inputUsername(String username){
        username().sendKeys(username);
    }


    public WebElement password(){
        return component.findElement(passwordSel);
    }
    @Step("Input password as {password}")
    public void inputPassword(String password){
        username().sendKeys(password);
    }
    public WebElement loginBtn(){
        return component.findElement(loginBtnSel);
    }

    @Step("Click on Login button")
    public void clickOnLoginButton(){
        loginBtn().click();
    }
    public String getInvalidEmailStr(){
        if (Platform.valueOf(platformName).equals(Platform.ANDROID)){
            return component.findElement(invalidEmailTxtSelAndroid).getText();
        }
        else {
            return component.findElement(invalidEmailTxtSeliOS).getText();
        }
    }

    public String getInvalidPasswordStr(){
        return component.findElement(invalidPasswordTxtSel).getText();
    }

    public LoginDialog loginDialog(){
        return new LoginDialog(appiumDriver, platformName);
    }
}

