package ui;

import model.Course;
import model.GradingGroup;

import javax.swing.*;
import java.awt.*;
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
    JLabel lowestGrade;
    JLabel highestGrade;

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
        weightedGrade = new JLabel("N/A");
        lowestGrade = new JLabel("N/A");
        highestGrade = new JLabel("N/A");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(courseName);

        JPanel row = new JPanel();
        row.add(moreGradingGroupButton);
        row.add(updateCourseButton);
        add(row);
        add(weightedGrade, BorderLayout.WEST);
        add(lowestGrade, BorderLayout.WEST);
        add(highestGrade, FlowLayout.LEADING);

        setVisible(true);
        MainGUI.student.addCourse(course);

    }

    public void moreGradingGroup() {
        JPanel row = new JPanel();
        JTextField weightValue = new JTextField("Weight");
        JTextField gradeValue = new JTextField("Grade");
        JTextField gradingGroupName = new JTextField("Grading Group Name");
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

    public void setMoreGradingGroup(String inputGroupName, String weight, String grade) {
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

    public void setCourseName(String str) {
        courseName.setText(str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moreGradingGroupButton) {
            moreGradingGroup();
            MainGUI.getFrame().invalidate();
            MainGUI.getFrame().validate();
            MainGUI.getFrame().repaint();
        }
        if (e.getSource() == updateCourseButton) {
            updateCourse();
        }
    }
}
