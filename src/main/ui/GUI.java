package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    static Student student;
    private JPanel panel;
    private JFrame frame;
    private JButton jbAddCourse;
    private JLabel label;
    private JTextField courseName;
    private JTextField weightValue;
    private JTextField gradeValue;
    private JTextField gradingGroupName;

    public GUI() {
        student = new Student();
        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel("test");
        courseName = new JTextField();
        gradingGroupName = new JTextField();
        weightValue = new JTextField();
        gradeValue = new JTextField();


        jbAddCourse = new JButton("Add Course");
        jbAddCourse.addActionListener(this);
        panel.add(jbAddCourse);
        panel.add(label);
        panel.add(courseName);
        panel.add(gradeValue);
        panel.add(weightValue);
        panel.add(gradingGroupName);


        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Course c = new Course(courseName.getText());
        c.addGradingGroup(gradingGroupName.getText(), Integer.parseInt(weightValue.getText()),
                Integer.parseInt(gradeValue.getText()));
        student.addCourse(c);
        panel.add(new JLabel(courseName.getText()));

        label.setText(student.getListOfCourses().size() + "");
    }
}
