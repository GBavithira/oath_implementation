package oauth;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;

/**
 * Customized Authentication class for SapHanaOnDemand Service
 */
public class SapHanaOnDemandClientAuthentication implements ClientAuthentication {

    private static final String PARAM_CLIENT_ID = "client_id";
    private static final String PARAM_CLIENT_SECRET = "client_secret";
    private static final String PARAM_LOGIN_HINT = "login_hint";

    @Override
    public void addClientAuthentication(OAuthRequest request, String apiKey, String apiSecret) {
        if (apiKey != null && apiSecret != null) {
            request.addParameter(PARAM_CLIENT_ID, apiKey);
            request.addParameter(PARAM_CLIENT_SECRET, apiSecret);
            request.addParameter(PARAM_LOGIN_HINT, "{\"origin\":\"ldap\"}");
        }
    }
}
