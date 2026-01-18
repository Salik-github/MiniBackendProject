package miniprojectapi.students;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import miniprojectapi.base.BaseTest;
import miniprojectapi.config.Endpoints;
import static miniprojectapi.students.StudentBase.createStudentAndReturnResponse;
import static miniprojectapi.students.StudentBase.generateStudentInvalidPayload;
import static miniprojectapi.students.StudentBase.generateStudentValidPayload;

public class CreateStudentTest extends BaseTest {

    @Description("Create student API test")
    @Test
    public void createStudentTest() {

        Response response = createStudentAndReturnResponse();
        System.out.println("Base Path: " + response.asString());
        verifyResponseCode(response, 201);
        StudentBase.verifyResponseBody(response);
    }

    @Test
    public void createStudentWithoutRequiredData() {

        String studentPayload = generateStudentInvalidPayload();
        System.out.println("Base URI: " + io.restassured.RestAssured.baseURI);
        System.out.println("Port: " + io.restassured.RestAssured.port);
        System.out.println("Base Path: " + io.restassured.RestAssured.basePath);
        System.out.println("Base Path: " + studentPayload);
        Response response = APIRequest.post(Endpoints.STUDENTS, studentPayload);
        System.out.println("Base Path: " + response.asString());

        verifyResponseCode(response, 400);
    }

    @Test
    public void verifyduplicateEmailNotAllowed() {

        String studentPayload = generateStudentValidPayload();
        Response response1 = APIRequest.post(Endpoints.STUDENTS, studentPayload);
        verifyResponseCode(response1, 201);
        StudentBase.verifyResponseBody(response1);

        Response response2 = APIRequest.post(Endpoints.STUDENTS, studentPayload);
        System.out.println("Base Path: " + response2.asString());

        verifyResponseCode(response2, 201);

    }
}
