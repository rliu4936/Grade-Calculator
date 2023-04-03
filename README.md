# Grades Calculator

## Description

### What will the application do?

The application will help users calculate their course averages, overall averages, as well as provide a range of possible marks they can receive.

### Who will use it?

Students who want to know how they are doing in a course / term **numerically**.

### Why is this project of interest to you?

This project is interesting to me, because I often find myself using an excel sheet to calculate my averages. I would
have to **manually** input the grades, weighting, and the math formulas for the calculation.

## User story

- As a user, I want to predict a range of possible marks that I can receive from a course
- As a user, I want to know my current weighted overall average 
- As a user, I want to know my current weighted course average
- As a user, I want to know my letter grade
- As a user, I want to add a course to a student
- As a user, I want to add grading groups to a course
- As a user, I want to assign weighting and a grade to a grading group
- As a user, I want to be able to save the courses and grades that I entered (if I so choose)
- As a user, I want to be able to be able to load my courses and grades from file (if I so choose)

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking "Add Course"
- You can generate the second required action related to adding Xs to a Y by clicking "Delete"
- You can locate my visual component by starting the application (splash screen)
- You can save the state of my application by clicking "Save"
- You can reload the state of my application by clicking "Load"

# Phase 4: Task 2
- Mon Apr 03 12:20:05 PDT 2023
- Course Name is added to student
- 
- Mon Apr 03 12:20:14 PDT 2023
- Course Name is renamed to CPSC 210
- 
- Mon Apr 03 12:20:15 PDT 2023
- Course Name is added to student
- 
- Mon Apr 03 12:20:21 PDT 2023
- Course Name is renamed to CPSC 121
- 
- Mon Apr 03 12:20:28 PDT 2023
- CPSC 210 is removed from student
-
- Mon Apr 03 12:20:28 PDT 2023
- CPSC 121 is removed from student

# Phase 4: Task 3

If I had more time to work on the project, I would make JsonProcessor a field of Student instead fields of the two 
user interfaces, because this would decrease coupling. Currently, MainGUI has code for handling 
the JFrame/GUI, JsonProcessor, and Student. I would try to split MainGUI into two classes. One for setting up the JFrame,
and one called StudentGUI representing a Student. Splitting MainGUI into two classes would increase cohesion. I would
also split CourseGUI, into CourseGUI and GradingGroupGUI. This would also increase cohesion. I would also look into
changing Student to implement interable<Course>, because Student is basically just a list of courses.
