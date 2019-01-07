package raidercalendar.android;

public class TokenHolder {
    static TokenHolder instance;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token = "";

    static {
        instance = new TokenHolder();

    }

    private TokenHolder() {
    }

    public static TokenHolder getInstance() {
        return TokenHolder.instance;
    }


}
