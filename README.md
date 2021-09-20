## OAuth client API implementation

This makes use of the [ScribeJava](https://github.com/scribejava/scribejava) library for the OAuth Client implementation. ScribeJava supports Synchronous and Asynchronous API calls.\
[OkHttp](https://github.com/square/okhttp) is used as the Http client library.

To build the project, run the following gradle
```bash
./gradlew build
```

To run the application, download the .jar from [release](https://github.com/GBavithira/oath_implementation/releases/tag/v1.0).
```bash
java -jar <jar_file> <credentials_path_file>
```

Credentials path file (credentials_path_file) should contain properties of the following format.
```bash
client_id=dummy_client_id
client_secret=dummy_client_secret
username=dummy_user
password=dummh_password
```

To run the test case
```bash
./gradlew test
```