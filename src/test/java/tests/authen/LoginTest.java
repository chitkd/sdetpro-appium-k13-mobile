package tests.authen;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import test_flows.authencation.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

//        List<LoginCredData> loginCredDataList = new ArrayList<>();
//        loginCredDataList.add(new LoginCredData("cindy@", "87654321"));
//        loginCredDataList.add(new LoginCredData("cindy@sth.com.vn", "1234567"));
//        loginCredDataList.add(new LoginCredData("cindy@sth.com.vn", "123456789"));
//
//        for (LoginCredData loginCred : loginCredDataList) {
//            try{
//                LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getUsername(), loginCred.getPassword()
//                );
//                loginFlow.gotoLoginScreen();
//                loginFlow.login();
//                loginFlow.verifyLogin();
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        

        appiumDriver.quit();
    }

    // Data Model Class
    public static class LoginCredData{
        private String username;
        private String password;

        public LoginCredData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
