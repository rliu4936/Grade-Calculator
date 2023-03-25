package ui;

import model.Course;
import model.GradingGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CourseGUI extends JPanel implements ActionListener {
    Course course;
    JTextField courseName;
    JButton updateCourseButton;
    JButton moreGradingGroupButton;
    List<JTextField> weightValues;
    List<JTextField> gradeValues;
    List<JTextField> gradingGroupNames;
    JLabel weightedGrade;

    public CourseGUI(boolean add) {
        courseName = new JTextField("Course Name");
        course = new Course("");
        weightValues = new ArrayList<JTextField>();
        gradeValues = new ArrayList<JTextField>();
        gradingGroupNames = new ArrayList<JTextField>();
        moreGradingGroupButton = new JButton("Add Grading Group");
        moreGradingGroupButton.addActionListener(this);
        updateCourseButton = new JButton("Update");
        updateCourseButton.addActionListener(this);
        weightedGrade = new JLabel("N/A");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(courseName);

        JPanel row = new JPanel();

        row.add(moreGradingGroupButton);
        row.add(updateCourseButton);
        row.add(weightedGrade);

        add(row);
        setVisible(true);
        setVisible(true);
        if (add) {
            MainGUI.student.addCourse(course);
        }
    }

    public void moreGradingGroup() {
        JPanel row = new JPanel();
        JTextField weightValue = new JTextField("Weight (int 0-100)");
        JTextField gradeValue = new JTextField("Grade (int 0-100)");
        JTextField gradingGroupName = new JTextField("Grading Group Name");

        weightValues.add(weightValue);
        gradeValues.add(gradeValue);
        gradingGroupNames.add(gradingGroupName);

        row.add(gradingGroupName);
        row.add(gradeValue);
        row.add(weightValue);
        this.add(row);
        row.setVisible(true);
    }

    public void setMoreGradingGroup(String inputGroupName, String weight, String grade) {
        JPanel row = new JPanel();
        JTextField weightValue = new JTextField(weight);
        JTextField gradeValue = new JTextField(grade);
        JTextField gradingGroupName = new JTextField(inputGroupName);

        weightValues.add(weightValue);
        gradeValues.add(gradeValue);
        gradingGroupNames.add(gradingGroupName);

        row.add(gradingGroupName);
        row.add(gradeValue);
        row.add(weightValue);
        this.add(row);
        row.setVisible(true);
    }

    public void updateCourse() {
        Course c = new Course(courseName.getText());
        for (int i = 0; i < weightValues.size(); i++) {
            c.addGradingGroup(gradingGroupNames.get(i).getText(),
                    Integer.parseInt(weightValues.get(i).getText()),
                    Integer.parseInt(gradeValues.get(i).getText()));
        }
        weightedGrade.setText("" + c.getGrade());
        course.setGradingGroups(c.getGradingGroups());
        course.setCourseName(courseName.getText());
    }

    public void setCourseName(String str) {
        courseName.setText(str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moreGradingGroupButton) {
            moreGradingGroup();
            MainGUI.getFrame().pack();
        }
        if (e.getSource() == updateCourseButton) {
            updateCourse();
        }
    }
}
