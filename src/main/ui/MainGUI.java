package ui;

import model.*;
import model.Event;
import persistence.JsonProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// MainGUI is the main GUI window
public class MainGUI implements ActionListener, WindowListener {
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

    // MODIFIES: this
    // EFFECTS: MainGUI constructor that initializes all fields
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
        frame.addWindowListener(this);

        refresh();
        showSplash();
    }

    // EFFECTS: Runs the GUI
    public static void main(String[] args) {
        new MainGUI();
    }

    // MODIFIES: this
    // EFFECTS: adds all the buttons to the GUI
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

    // EFFECTS: Shows the splash screen for 1 second at the beginning of the application
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

    // MODIFIES: this
    // EFFECTS: adds a course to the student
    public void addCourse() {
        CourseGUI c = new CourseGUI();
        centerPanel.add(c);
        refresh();
    }

    // EFFECTS: saves the student saved to file
    public void save() {
        try {
            jp.save(student);
        } catch (FileNotFoundException error) {
            System.out.println("Unable to write to file: " + "./data/student.json");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the student saved in file
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

    // EFFECTS: refreshes the GUI
    public static void refresh() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Processes button clicks
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

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
