package tests.authen;

import org.testng.Assert;
import org.testng.annotations.Test;
import test_flows.authencation.LoginFlow;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationTest extends BaseTest {

    @Test
    public void loginWithCreds(){
        List<LoginCredData> loginCredData = new ArrayList<>();
        loginCredData.add(new LoginCredData("cindy@", "87654321"));
        loginCredData.add(new LoginCredData("cindy@sth.com.vn", "1234567"));
        loginCredData.add(new LoginCredData("cindy@sth.com.vn", "123456789"));

        for (LoginCredData loginCred : loginCredData) {
            LoginFlow loginFlow = new LoginFlow(
                    appiumDriver, loginCred.getUsername(), loginCred.getPassword()
            );
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        }

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
