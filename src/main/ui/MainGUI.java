package ui;

import model.Course;
import model.GradingGroup;
import model.Student;
import persistence.JsonProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGUI implements ActionListener {
    static Student student;
    private JPanel topPanel;
    private JPanel centerPanel;

    private static JFrame frame;
    private JButton addCourseButton;
    private JButton saveButton;
    private JButton loadButton;

    JsonProcessor jp;

    public MainGUI() {
        jp = new JsonProcessor("./data/student.json");
        student = new Student();
        frame = new JFrame();
        topPanel = new JPanel();
        frame.setPreferredSize(new Dimension(800, 450));
        topPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setBackground(Color.red);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.green);

        addCourseButton = new JButton("Add Course");
        addCourseButton.setPreferredSize(new Dimension(160, 90));
        addCourseButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(160, 90));
        saveButton.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(160, 90));
        loadButton.addActionListener(this);

        topPanel.add(addCourseButton);
        topPanel.add(saveButton);
        topPanel.add(loadButton);

        centerPanel.setLayout(new FlowLayout());

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Grade Calculator");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            CourseGUI c = new CourseGUI(true);
            centerPanel.add(c);
            frame.pack();
        } else if (e.getSource() == saveButton) {
            System.out.println("save clicked");
            try {
                jp.save(student);
            } catch (FileNotFoundException error) {
                System.out.println("Unable to write to file: " + "./data/student.json");
            }
        } else if (e.getSource() == loadButton) {
            try {
                student = jp.read();
                centerPanel.removeAll();
                frame.revalidate();
                frame.repaint();
                for (Course c : student.getListOfCourses()) {
                    CourseGUI cgui = new CourseGUI(false);
                    cgui.setCourseName(c.getCourseName());
                    for (GradingGroup gg : c.getGradingGroups()) {
                        cgui.setMoreGradingGroup(gg.getGroupName(), gg.getWeight().toString(),
                                gg.getGrade().toString());
                    }
                    centerPanel.add(cgui);
                    frame.pack();
                }
            } catch (IOException error) {
                System.out.println("Unable to read to file: " + "./data/student.json");
            }
        }
    }

    public static JFrame getFrame() {
        return frame;
    }
}
