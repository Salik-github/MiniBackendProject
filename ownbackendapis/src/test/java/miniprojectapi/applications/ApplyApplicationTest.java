package miniprojectapi.applications;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static miniprojectapi.applications.ApplicationBase.applyApplication;
import static miniprojectapi.applications.ApplicationBase.applyApplicationReturn;
import miniprojectapi.base.BaseTest;

public class ApplyApplicationTest extends BaseTest {

    @Test
    public void applyApplicationWithValidStudentIdAndCourseId() {

        applyApplication();

    }

    @Test
    public void applyApplicationWithInvalidStudentId() {
        String invalidStudentId = "invalid-student-id";
        String courseId = ApplicationBase.createCourse();
        Response response = applyApplicationReturn(invalidStudentId, courseId);
        verifyResponseCode(response, 404);
    }

    @Test
    public void applyApplicationWithInvalidCourseId() {
        String studentId = ApplicationBase.createStudent();
        String invalidCourseId = "invalid-course-id";
        Response response = applyApplicationReturn(studentId, invalidCourseId);
        verifyResponseCode(response, 404);

    }

    @Test
    public void applyApplicationWithEmptyPayload() {
        String studentId = "";
        String invalidCourseId = "";
        Response response = applyApplicationReturn(studentId, invalidCourseId);
        verifyResponseCode(response, 400);
    }

    @Test
    public void applyApplyDuplicateApplication() {
        String studentId = ApplicationBase.createStudent();
        String courseId = ApplicationBase.createCourse();
        Response firstResponse = applyApplicationReturn(studentId, courseId);
        verifyResponseCode(firstResponse, 201);
        Response secondResponse = applyApplicationReturn(studentId, courseId);
        verifyResponseCode(secondResponse, 409);
    }

}
