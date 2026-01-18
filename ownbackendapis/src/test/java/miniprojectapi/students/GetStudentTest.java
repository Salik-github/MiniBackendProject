package miniprojectapi.students;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import miniprojectapi.base.BaseTest;
import miniprojectapi.config.Endpoints;
import static miniprojectapi.students.StudentBase.createStudentAndReturnResponse;
import static miniprojectapi.students.StudentBase.returnStudentId;

public class GetStudentTest extends BaseTest {

    @Test
    public void getAllStudents() {
        // Implementation for getting all students
        Response response = APIRequest.get(Endpoints.STUDENTS);
        System.out.println("Response: " + response.asString());
        verifyResponseCode(response, 200);
        StudentBase.verifyResponseBody(response);
    }

    @Test
    public void getStudentById() {
        // Implementation for getting a student by ID
        Response response = createStudentAndReturnResponse();
        verifyResponseCode(response, 201);

        String studentId = returnStudentId(response);
        Response response1 = APIRequest.get(Endpoints.STUDENTS + "/" + studentId);
        System.out.println("Response: " + response1.asString());
        verifyResponseCode(response1, 200);
        response1.then().body("students_id", notNullValue());
        response1.then().body("students_id", equalTo(Integer.parseInt(studentId)));
    }

    @Test
    public void getStudentByInvalidId() {
        // Implementation for getting a student by invalid ID
        String studentId = "99e99"; // Non-existing student ID
        Response response = APIRequest.get(Endpoints.STUDENTS + "/" + studentId);
        System.out.println("Response: " + response.asString());
        verifyResponseCode(response, 404);
    }

    @Test
    public void verifyStudentApplicationsInitialZero() {
        // Implementation for verifying that a new student has zero applications
        Response createResponse = createStudentAndReturnResponse();
        verifyResponseCode(createResponse, 201);
        StudentBase.verifyResponseBody(createResponse);
        String studentId = returnStudentId(createResponse);

        Response getResponse = APIRequest.get(Endpoints.STUDENTS + "/" + studentId);
        verifyResponseCode(getResponse, 200);
        getResponse.then().body("applicationCount", equalTo(0));
        getResponse.then().body("applications", empty());
    }

    @Test
    public void verifyStudentResponseStructureIsCorrectWhenNoneExist() {

        Response createResponse = createStudentAndReturnResponse();
        verifyResponseCode(createResponse, 201);
        StudentBase.verifyResponseBody(createResponse);

      String studentId = returnStudentId(createResponse);
        Response getResponse = APIRequest.get(Endpoints.STUDENTS + "/" + studentId);
        verifyResponseCode(getResponse, 200);
        System.out.println("Response: " + getResponse.asString());
        StudentBase.verifyResponseBodyStructure(getResponse, studentId);

    }
}
