package miniprojectapi.applications;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import static miniprojectapi.applications.ApplicationBase.applyApplicationReturn;
import miniprojectapi.base.BaseTest;
import miniprojectapi.config.Endpoints;

public class GetApplicationTest extends BaseTest {

    @Test
    public void getApplicationWithValidApplicationId() {
        // Test implementation goes here
        String studentId = ApplicationBase.createStudent();
        String courseId = ApplicationBase.createCourse();
        Response response = applyApplicationReturn(studentId, courseId);
        verifyResponseCode(response, 201);
        String applicationId = response.then().body("application.applicationId", notNullValue()).extract()
                .path("application.applicationId");
        Response response2 = APIRequest.get(Endpoints.APPLICATIONS + "/" + applicationId);
        verifyResponseCode(response2, 200);
        response2.then().body("applicationId", equalTo(applicationId));
    }
    @Test
    public void getApplicationWithInvalidApplicationId() {
        // Test implementation goes here
        String invalidApplicationId = "invalid-application-id";     
        Response response = APIRequest.get(Endpoints.APPLICATIONS + "/" + invalidApplicationId);
        verifyResponseCode(response, 404);          
    }
}
