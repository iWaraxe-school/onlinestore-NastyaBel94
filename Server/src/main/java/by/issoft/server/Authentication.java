package by.issoft.server;


import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.nio.charset.Charset;

public class Authentication extends BasicAuthenticator {

    private static final String USER = "nastya1";
    private static final String PASSWORD = "1234N";

    public Authentication(String realm, Charset charset) {
        super(realm, charset);
    }

    @Override
    public boolean checkCredentials(String user, String password) {
        return user.equals(USER) && password.equals(PASSWORD);
    }

}

