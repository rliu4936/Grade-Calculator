package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// CourseGUI represents a single Course in the GUI
public class CourseGUI extends JPanel implements ActionListener {
    Course course;
    JTextField courseName;
    JButton updateCourseButton;
    JButton moreGradingGroupButton;
    JButton deleteCourseButton;
    List<JTextField> weightValues;
    List<JTextField> gradeValues;
    List<JTextField> gradingGroupNames;
    JLabel weightedGrade;
    JLabel lowestGrade;
    JLabel highestGrade;

    // MODIFIES: this
    // EFFECTS: CourseGUI constructor that initializes all fields
    public CourseGUI() {
        courseName = new JTextField("Course Name");
        course = new Course("");

        weightValues = new ArrayList<>();
        gradeValues = new ArrayList<>();
        gradingGroupNames = new ArrayList<>();

        moreGradingGroupButton = new JButton("Add Grading Group");
        moreGradingGroupButton.addActionListener(this);

        updateCourseButton = new JButton("Update");
        updateCourseButton.addActionListener(this);

        deleteCourseButton = new JButton("Delete");
        deleteCourseButton.addActionListener(this);

        weightedGrade = new JLabel("Grade: N/A");
        lowestGrade = new JLabel("Lowest: N/A");
        highestGrade = new JLabel("Highest: N/A");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setupLabels();

        setVisible(true);
        MainGUI.student.addCourse(course);
    }

    // MODIFIES: this
    // EFFECTS: adds all labels to the GUI
    public void setupLabels() {
        add(courseName);
        JPanel row = new JPanel();
        row.add(moreGradingGroupButton);
        row.add(updateCourseButton);
        row.add(deleteCourseButton);
        add(row);
        add(weightedGrade);
        add(lowestGrade);
        add(highestGrade);
    }

    // MODIFIES: this
    // EFFECTS: adds a Grading Group to the Course
    public void moreGradingGroup(String inputGroupName, String weight, String grade) {
        JPanel row = new JPanel();
        JTextField weightValue = new JTextField(weight);
        JTextField gradeValue = new JTextField(grade);
        JTextField gradingGroupName = new JTextField(inputGroupName);

        weightValue.setPreferredSize(new Dimension(50, 20));
        gradeValue.setPreferredSize(new Dimension(50, 20));
        gradingGroupName.setPreferredSize(new Dimension(100, 20));

        weightValues.add(weightValue);
        gradeValues.add(gradeValue);
        gradingGroupNames.add(gradingGroupName);

        row.add(gradingGroupName);
        row.add(gradeValue);
        row.add(weightValue);
        this.add(row);
        row.setVisible(true);
    }

    // EFFECTS: updates the calculations for the course
    public void updateCourse() {
        Course c = new Course(courseName.getText());
        for (int i = 0; i < weightValues.size(); i++) {
            c.addGradingGroup(gradingGroupNames.get(i).getText(),
                    Integer.parseInt(weightValues.get(i).getText()),
                    Integer.parseInt(gradeValues.get(i).getText()));
        }
        weightedGrade.setText("Grade:" + Math.round(c.getGrade() * 100) / 100.0);
        highestGrade.setText("Highest:" + c.getHighestGrade());
        lowestGrade.setText("Lowest:" + c.getLowestGrade());
        course.setGradingGroups(c.getGradingGroups());
        course.setCourseName(courseName.getText());
    }

    // MODIFIES: this
    // EFFECTS: updates the course name
    public void setCourseName(String str) {
        courseName.setText(str);
    }

    // MODIFIES: this
    // EFFECTS: delete this from the student
    public void deleteCourse() {
        MainGUI.student.deleteCourse(course);
        setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: Processes button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moreGradingGroupButton) {
            moreGradingGroup("Name", "Weight", "Grade");
            MainGUI.refresh();
        }
        if (e.getSource() == updateCourseButton) {
            updateCourse();
        }
        if (e.getSource() == deleteCourseButton) {
            deleteCourse();
        }
    }
}
