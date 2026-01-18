package miniprojectapi.applications;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.json.JSONObject;

import io.restassured.response.Response;
import miniprojectapi.api.APIRequest;
import miniprojectapi.base.BaseTest;
import miniprojectapi.config.Endpoints;
import miniprojectapi.courses.CourseBase;
import static miniprojectapi.payloads.ApplicationPayload.applicationPayload;
import static miniprojectapi.students.StudentBase.createStudentAndReturnResponse;
import static miniprojectapi.students.StudentBase.returnStudentId;

public class ApplicationBase extends BaseTest {
    public static String createStudent() {
        Response response = createStudentAndReturnResponse();
        verifyResponseCode(response, 201);
        return returnStudentId(response);
    }
    public static String createCourse() {
        Response courseResponse = CourseBase.createCoursewithValidPayload();
        return CourseBase.getCourseId(courseResponse);
    }
    public static Response applyApplicationReturn(String studentId, String courseId)
    {
        String applicationPayload = applicationPayload(studentId, courseId);
        Response response = APIRequest.post(Endpoints.APPLICATIONS, applicationPayload);
        return response;
    }
    public void createApplicationPayload(String studentId, String courseId) {

        JSONObject applicationPayload = new JSONObject();
        applicationPayload.put("studentId", studentId);
        applicationPayload.put("courseId", courseId);
    }

    public static void applyApplication() {
        String studentId = createStudent();
        String courseId = createCourse();
        Response response = applyApplicationReturn(studentId, courseId);
        verifyResponseCode(response, 201);
        response.then().body("application.applicationId", notNullValue());
        response.then().body("application.studentId", equalTo(studentId));
        response.then().body("application.courseId", equalTo(courseId));
    }
}
