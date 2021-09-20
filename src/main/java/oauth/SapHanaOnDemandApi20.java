package oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;

/**
 * OAuth 2.0 API implementation for Sap Hana On Demand Service
 */
public class SapHanaOnDemandApi20 extends DefaultApi20 {

    private static final String ACCESS_TOKEN_ENDPOINT =
            "https://securitytraining.authentication.sap.hana.ondemand.com/oauth/token";
    public static final String PRODUCTS_RESOURCE_ENDPOINT =
            "https://product-list-dev.cfapps.sap.hana.ondemand.com/products";

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_ENDPOINT;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return null;
    }

    @Override
    public ClientAuthentication getClientAuthentication() {
        return new SapHanaOnDemandClientAuthentication();
    }
}
