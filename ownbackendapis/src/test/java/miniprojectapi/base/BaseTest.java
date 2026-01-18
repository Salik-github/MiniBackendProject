package miniprojectapi.base;

import org.testng.annotations.BeforeSuite;

import io.restassured.response.Response;
import miniprojectapi.config.BaseURI;

public class BaseTest {
    
    @BeforeSuite
    public void setup()
    {
        BaseURI.setup();
    }

     public static void verifyResponseCode(Response response, int expectedCode) {
        int actualCode = response.getStatusCode();
        if (expectedCode != actualCode) {
            throw new AssertionError(
                    "Expected response code: " + expectedCode + ", but got: " + actualCode);
        }
    }
}
