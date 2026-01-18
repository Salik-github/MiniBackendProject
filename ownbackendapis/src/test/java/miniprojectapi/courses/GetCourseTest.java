package miniprojectapi.courses;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import miniprojectapi.base.BaseTest;
import static miniprojectapi.courses.CourseBase.generateCourseValidPayload;

public class GetCourseTest extends BaseTest {

    public void verifyGetCourseById() {
        String coursePayload = generateCourseValidPayload();
        Response response = CourseBase.createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 201);
        CourseBase.verifyResponseBody(response);
        // Implementation for verifying getting a course by ID
        String courseId = CourseBase.getCourseId(response);
        Response getResponse = CourseBase.getCourses(courseId);
        System.out.println("Course ID: " + courseId);
        verifyResponseCode(getResponse, 200);
        getResponse.then().body("courseId", equalTo(courseId));
    }

    @Test
    public void verifyGetAllCourses() {
        // Implementation for verifying getting all courses
        Response response = CourseBase.getCourses();
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 200);
        // Additional verifications can be added here
    }

    @Test
    public void verifyGetCourseByInvalidId() {
        // Implementation for verifying getting a course by invalid ID
        String invalidCourseId = "invalid-id";
        Response response = CourseBase.getCourses(invalidCourseId);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 404);
    }

}
