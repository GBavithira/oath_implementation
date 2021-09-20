package cmdline;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

/**
 * Class for parsing command line arguments.
 */
public class CmdArgs {
    private final String clientId;
    private final String clientSecret;
    private final String password;
    private final String username;

    private static final String ARG_CLIENT_ID = "client_id";
    private static final String ARG_CLIENT_SECRET = "client_secret";
    private static final String ARG_USERNAME = "username";
    private static final String ARG_PASSWORD = "password";

    public CmdArgs(@NotNull  String clientId, @NotNull String clientSecret, @NotNull String username, @NotNull String password) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public static CmdArgs parse(InputStream inputStream) throws IllegalArgumentException, IOException {
        Properties props = new Properties();
        props.load(inputStream);
        String clientId = props.getProperty(ARG_CLIENT_ID);
        String clientSecret = props.getProperty(ARG_CLIENT_SECRET);
        String username = props.getProperty(ARG_USERNAME);
        String password = props.getProperty(ARG_PASSWORD);
        if(clientId == null) {
            throw new IllegalArgumentException(ARG_CLIENT_ID + " is missing.");
        }
        if(clientSecret == null) {
            throw new IllegalArgumentException(ARG_CLIENT_SECRET +" is missing.");
        }
        if(username == null) {
            throw new IllegalArgumentException(ARG_USERNAME + " is missing.");
        }
        if(password == null) {
            throw new IllegalArgumentException(ARG_PASSWORD + " is missing.");
        }
        return new CmdArgs(clientId, clientSecret, username, password);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
