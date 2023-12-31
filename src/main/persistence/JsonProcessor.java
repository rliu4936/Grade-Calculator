package persistence;

import model.Course;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reads from Json stored in file to Student and Writes Student to a Json file
public class JsonProcessor {
    private static final int TAB = 4;
    private String source;

    // EFFECTS: constructs processor to read and write to the file source
    public JsonProcessor(String source) {
        this.source = source;
    }

    // EFFECTS: write a json file from a student.
    // throws FileNotFoundException if file is not found
    public void save(Student s) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(source));
        writer.print(s.toJson().toString(TAB));
        writer.close();
    }

    // MODIFIES: student
    // EFFECTS: load a student from json file
    // throws IOException if an error occurs reading data from file
    public Student read() throws IOException {
        Student emptyStudent = new Student();
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseStudent(jsonObject, emptyStudent);
        return emptyStudent;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // MODIFIES: s
    // EFFECTS: parses student from JSON object and adds it to student s
    private void parseStudent(JSONObject jsonObject, Student s) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfCourses");
        for (Object json : jsonArray) {
            addCourse(s, (JSONObject) json);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses course and adds it to the student s
    private void addCourse(Student s, JSONObject jsonObject) {
        Course c = new Course(jsonObject.getString("courseName"));
        for (Object json : jsonObject.getJSONArray("gradingGroups")) {
            JSONObject gg = (JSONObject) json;
            c.addGradingGroup(gg.getString("groupName"), gg.getInt("weight"), gg.getInt("grade"));
        }
        s.addCourse(c);
    }
}
