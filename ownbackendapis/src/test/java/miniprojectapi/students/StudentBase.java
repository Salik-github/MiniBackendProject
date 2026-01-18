package miniprojectapi.students;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import miniprojectapi.config.Endpoints;
import miniprojectapi.payloads.StudentPayload;
import miniprojectapi.utils.TestDataGenerator;

public class StudentBase {

    public static String generateStudentValidPayload() {
        return StudentPayload.createStudentPayload(
                TestDataGenerator.genFirstName(),
                TestDataGenerator.genLastName(),
                TestDataGenerator.genEmail(),
                TestDataGenerator.genMobile(),
                TestDataGenerator.genDateOfBirth(),
                TestDataGenerator.genGender(),
                TestDataGenerator.genCountry());
    }

    public static String generateStudentInvalidPayload() {
        return StudentPayload.createStudentPayload(
                "",
                "",
                "",
                "",
                TestDataGenerator.genDateOfBirth(),
                TestDataGenerator.genGender(),
                TestDataGenerator.genCountry());
    }

   

    public static void verifyResponseBody(Response response) {
        // verifyResponseCode(response, 201);
        response.then().body("student.students_id", notNullValue());
    }
    public static String returnStudentId(Response response) {
        return response.then().extract().jsonPath().getString("student.students_id");
    }

    public static void verifyResponseBodyStructure(Response response, String studentId) {
        // verifyResponseCode(response, 200);
        response.then().assertThat().body("students_id", equalTo(Integer.parseInt(studentId)));
        response.then().assertThat().body("firstName", notNullValue());
        response.then().assertThat().body("lastName", notNullValue());
        response.then().assertThat().body("email", notNullValue());
        response.then().assertThat().body("mobile", notNullValue());
        response.then().assertThat().body("dateOfBirth", notNullValue());
        response.then().assertThat().body("gender", notNullValue());
        response.then().assertThat().body("country", notNullValue());
    }

    public static Response createStudentAndReturnResponse() {
        String studentPayload = generateStudentValidPayload();
        return APIRequest.post(Endpoints.STUDENTS, studentPayload);
    }
}
