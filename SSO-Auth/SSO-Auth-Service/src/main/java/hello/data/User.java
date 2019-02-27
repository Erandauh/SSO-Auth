package hello.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hora on 2/25/19.
 */
public class User {

    @JsonProperty("Username")
    private String userName;
    @JsonProperty("Password")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
