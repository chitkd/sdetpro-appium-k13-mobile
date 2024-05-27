package test_data.models;

public class LoginCred {
    private String username;
    private String password;

    public LoginCred() {
    }

    public LoginCred(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
