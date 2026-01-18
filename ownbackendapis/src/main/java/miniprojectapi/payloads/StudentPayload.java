package miniprojectapi.payloads;

import org.json.JSONObject;

public class StudentPayload {
    
public static String createStudentPayload(
            String firstName,
            String lastName,
            String email,
            String mobile,
            String dateOfBirth,
            String gender,
            String country
    ) {
        JSONObject student = new JSONObject();
        student.put("firstName", firstName);
        student.put("lastName", lastName);                          
        student.put("email", email);
        student.put("mobile", mobile);
        student.put("dateOfBirth", dateOfBirth);
        student.put("gender", gender);
        student.put("country", country);        
        return student.toString();
    }
}
