package model;

// Many Grading Groups make up a course. Each Grading Group has a name, weight, and grade
public class GradingGroup {
    private String groupName;
    private Integer weight;
    private Integer grade;

    GradingGroup(String groupName, Integer weight, Integer grade) {
        this.groupName = groupName;
        this.weight = weight;
        this.grade = grade;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getGroupName() {
        return groupName;
    }
}
