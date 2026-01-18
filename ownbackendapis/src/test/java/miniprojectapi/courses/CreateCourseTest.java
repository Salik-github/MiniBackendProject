package miniprojectapi.courses;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import miniprojectapi.base.BaseTest;
import static miniprojectapi.courses.CourseBase.generateCourseValidPayload;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourseTest extends BaseTest {
    
    @Test
    public void createCourseTest() {
                String coursePayload = generateCourseValidPayload();

        Response response = CourseBase.createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 201);
        CourseBase.verifyResponseBody(response);    
    }
    @Test
    public void createCourseWithoutRequiredData() {
        String coursePayload = "{}"; // Empty payload
        Response response = CourseBase.createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 400);
    }
    @Test
    public void verifyDuplicateCourseCodeNotAllowed() {
        String coursePayload = generateCourseValidPayload();
        Response response1 = CourseBase.createCourseAndReturnResponse(coursePayload);
        verifyResponseCode(response1, 201);         
        CourseBase.verifyResponseBody(response1);
        Response response2 = CourseBase.createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response2.asString());
        verifyResponseCode(response2, 409);
    }
    @Test
    public void verifyDefaultStatus() {
        String coursePayload = generateCourseValidPayload();
        Response response = CourseBase.createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 201);         
        CourseBase.verifyResponseBody(response);
        response.then().body("course.status", equalTo("Active"));
    }
}
