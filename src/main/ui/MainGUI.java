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
    static final String FILE = "./data/student.json";
    static final String SPLASH = "./data/splash.png";
    static Student student;
    private JPanel topPanel;
    private JPanel centerPanel;

    private static JFrame frame;
    private static JFrame splash;
    private JButton addCourseButton;
    private JButton saveButton;
    private JButton loadButton;

    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final Dimension WINDOW_SIZE = new Dimension(800, 800);
    static final Dimension TOP_BUTTON_SIZE = new Dimension(160, 90);

    static final Color GREY = new Color(66,69,73);
    static final Color DARK_GREY = new Color(40,43,48);

    JsonProcessor jp;

    public MainGUI() {
        jp = new JsonProcessor(FILE);
        student = new Student();
        frame = new JFrame();
        topPanel = new JPanel();

        frame.setMinimumSize(WINDOW_SIZE);
        frame.setPreferredSize(WINDOW_SIZE);

        topPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setBackground(DARK_GREY);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        centerPanel = new JPanel();
        centerPanel.setBackground(GREY);

        addButtons();

        centerPanel.setLayout(new FlowLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Grade Calculator");

        refresh();
        showSplash();
    }

    public void addButtons() {
        addCourseButton = new JButton("Add Course");
        addCourseButton.setPreferredSize(TOP_BUTTON_SIZE);
        addCourseButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.setPreferredSize(TOP_BUTTON_SIZE);
        saveButton.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.setPreferredSize(TOP_BUTTON_SIZE);
        loadButton.addActionListener(this);

        topPanel.add(addCourseButton);
        topPanel.add(saveButton);
        topPanel.add(loadButton);
    }

    public void showSplash() {
        splash = new JFrame();
        splash.setBackground(Color.BLUE);
        splash.repaint();
        splash.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        splash.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        splash.add(new JLabel(new ImageIcon(SPLASH)));

        frame.setVisible(false);
        splash.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        frame.setVisible(true);
        splash.setVisible(false);
    }

    public void addCourse() {
        CourseGUI c = new CourseGUI();
        centerPanel.add(c);
        refresh();
    }

    public void save() {
        try {
            jp.save(student);
        } catch (FileNotFoundException error) {
            System.out.println("Unable to write to file: " + "./data/student.json");
        }
    }

    public void load() {
        try {
            Student dummy = jp.read();
            student = new Student();
            centerPanel.removeAll();
            for (Course c : dummy.getListOfCourses()) {
                CourseGUI cgui = new CourseGUI();
                cgui.setCourseName(c.getCourseName());
                for (GradingGroup gg : c.getGradingGroups()) {
                    cgui.moreGradingGroup(gg.getGroupName(), gg.getWeight().toString(),
                            gg.getGrade().toString());
                }
                centerPanel.add(cgui);
                cgui.updateCourse();
            }
            refresh();
        } catch (IOException error) {
            System.out.println("Unable to read to file: " + "./data/student.json");
        }
    }

    public static void refresh() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            addCourse();
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == loadButton) {
            load();
        }
    }
}
