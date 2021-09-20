import cmdline.CmdArgs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CmdArgsTest {

    @Test
    public void testMissingClientId() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test_credentials_miss_client_id.properties");
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> CmdArgs.parse(inputStream));
        try {
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            //Do nothing
        }
        assertEquals(thrownException.getMessage(), "client_id is missing.");
    }

    @Test
    public void testMissingClientSecret() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test_credentials_miss_client_secret.properties");
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> CmdArgs.parse(inputStream));
        try {
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            //Do nothing
        }
        assertEquals(thrownException.getMessage(), "client_secret is missing.");
    }

    @Test
    public void testMissingUsername() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test_credentials_miss_username.properties");
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> CmdArgs.parse(inputStream));
        try {
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            //Do nothing
        }
        assertEquals(thrownException.getMessage(), "username is missing.");
    }

    @Test
    public void testMissingPassword() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test_credentials_miss_password.properties");
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> CmdArgs.parse(inputStream));
        try {
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            //Do nothing
        }
        assertEquals(thrownException.getMessage(), "password is missing.");
    }

    @Test
    public void testInvalidCredentialsFileContent() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test_credentials_invalid.properties");
        try {
            if(inputStream!=null) {
                inputStream.close();
            }
        } catch (IOException e) {
            //Do nothing
        }
        assertThrows(IOException.class, () -> CmdArgs.parse(inputStream));
    }
}
