import cmdline.CmdArgs;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.httpclient.okhttp.OkHttpHttpClientConfig;
import com.github.scribejava.httpclient.okhttp.OkHttpProvider;
import oauth.SapHanaOnDemandApi20;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        CmdArgs cmdArgs = getCmdArgs(args);
        if(cmdArgs == null) {
            System.exit(1);
        }

        SapHanaOnDemandApi20 sampleApi20 = new SapHanaOnDemandApi20();
        OAuth20Service oAuth20Service = new ServiceBuilder(cmdArgs.getClientId())
                .apiSecret(cmdArgs.getClientSecret())
                .httpClient(new OkHttpProvider().createClient(OkHttpHttpClientConfig.defaultConfig()))
                .build(sampleApi20);
        try {
            OAuth2AccessToken accessToken = oAuth20Service.getAccessTokenPasswordGrant(cmdArgs.getUsername(), cmdArgs.getPassword());
            OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, SapHanaOnDemandApi20.PRODUCTS_RESOURCE_ENDPOINT);
            oAuth20Service.signRequest(accessToken.getAccessToken(), oAuthRequest);
            Response response = oAuth20Service.executeAsync(oAuthRequest).get();
            System.out.println(response.getBody());
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Nullable
    private static CmdArgs getCmdArgs(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Main <credentials_properties_file_path>");
            System.exit(1);
        }
        String propertiesFilePath = args[0];
        InputStream inputStream = null;
        CmdArgs cmdArgs = null;
        try {
            inputStream = new FileInputStream(propertiesFilePath);
            cmdArgs = CmdArgs.parse(inputStream);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Invalid file path");
        } finally {
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Do nothing
                }
            }
        }
        return cmdArgs;
    }
}
