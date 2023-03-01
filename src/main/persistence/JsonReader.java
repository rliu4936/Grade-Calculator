package persistence;

import model.Student;
import model.Course;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Student read(Student student) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudent(jsonObject, student);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject, Student s) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfCourses");
        for (Object json : jsonArray) {
            addCourse(s, (JSONObject) json);
        }
        return s;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCourse(Student s, JSONObject jsonObject) {
        Course c = new Course(jsonObject.getString("courseName"));
        for (Object json : jsonObject.getJSONArray("gradingGroups")) {
            JSONObject gg = (JSONObject) json;
            c.addGradingGroup(gg.getString("groupName"), gg.getInt("weight"), gg.getInt("grade"));
        }
        s.addCourse(c);
    }
}
