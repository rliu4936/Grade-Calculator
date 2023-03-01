package persistence;

import model.Course;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a writer that writes JSON representation of workroom to file
public class JsonProcessor {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String source;

    // EFFECTS: constructs writer to write to destination file
    public JsonProcessor(String source) {
        this.source = source;
    }

    // EFFECTS: write a json file from a student
    public void save(Student s) throws FileNotFoundException {
        writer = new PrintWriter(new File(source));
        writer.print(s.toJson().toString(TAB));
        writer.close();
    }

    // EFFECTS: load a student from json file
    public void read(Student student) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseStudent(jsonObject, student);
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
    // EFFECTS: parses workroom from JSON object and returns it
    private void parseStudent(JSONObject jsonObject, Student s) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfCourses");
        for (Object json : jsonArray) {
            addCourse(s, (JSONObject) json);
        }
    }

    // MODIFIES: s
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
