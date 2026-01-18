package miniprojectapi.payloads;

import org.json.JSONObject;

public class CoursePayload {
    public static String createCourse(
            String courseName,
            String courseCode,
            String level,
            String duration,
            String intake,
            int tuitionFee,
            String currency,
            String university
    ){
        JSONObject course = new JSONObject()
                .put("courseName", courseName)
                .put("courseCode", courseCode)
                .put("level", level)
                .put("duration", duration)
                .put("intake", intake)
                .put("tuitionFee", tuitionFee)
                .put("currency", currency)
                .put("university", university);
        return course.toString();
    }
}
