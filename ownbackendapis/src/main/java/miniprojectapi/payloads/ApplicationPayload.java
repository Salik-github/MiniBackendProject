package miniprojectapi.payloads;

import org.json.JSONObject;

public class ApplicationPayload {

    public static String applicationPayload(String studentId, String courseId) {
        JSONObject applicationPayload = new JSONObject();
        applicationPayload.put("studentId", studentId);
        applicationPayload.put("courseId", courseId);
        return applicationPayload.toString();
    }

}
