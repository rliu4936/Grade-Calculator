package persistence;

import model.Student;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void save(Student s) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
        writer.print(s.toJson().toString(TAB));
        writer.close();
    }

}
