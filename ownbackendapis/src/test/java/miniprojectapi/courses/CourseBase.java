package miniprojectapi.courses;

import static org.hamcrest.Matchers.notNullValue;

import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import static miniprojectapi.base.BaseTest.verifyResponseCode;
import miniprojectapi.config.Endpoints;
import miniprojectapi.payloads.CoursePayload;
import miniprojectapi.utils.TestDataGenerator;

public class CourseBase {

    public static String generateCourseValidPayload() {
        return CoursePayload.createCourse(
                TestDataGenerator.randomCoursename(),
                TestDataGenerator.randomCourseCode(),
                TestDataGenerator.randomLevel(),
                TestDataGenerator.randomDuration(),
                TestDataGenerator.randomIntake(),
                TestDataGenerator.randomTuitionFee(),
                TestDataGenerator.randomCurrency(),
                TestDataGenerator.randomUniversity());
    }

    public static Response createCourseAndReturnResponse(String coursePayload) {
        return APIRequest.post(Endpoints.COURSES, coursePayload);
    }

    public static void verifyResponseBody(Response response) {
        // verifyResponseCode(response, 201);
        response.then().body("course.courseId", notNullValue());
    }

    public static String getCourseId(Response response) {
        return response.then().body("course.courseId", notNullValue()).extract().path("course.courseId");
    }

    public static Response getCourses() {
        return APIRequest.get(Endpoints.COURSES);

    }

    public static Response getCourses(String courseId) {
        return APIRequest.get(Endpoints.COURSES + "/course-id/" + courseId);

    }

    public static Response createCoursewithValidPayload()
    {
        String coursePayload = generateCourseValidPayload();
        Response response = createCourseAndReturnResponse(coursePayload);
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 201);
        verifyResponseBody(response);
        return response;
    }

}
