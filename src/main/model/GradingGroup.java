package model;

public class GradingGroup {
    private String groupName;
    private Integer weight;
    private Integer grade;

    GradingGroup(String groupName, Integer weight, Integer grade) {
        this.groupName = groupName;
        this.weight = weight;
        this.grade = grade;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void printGradingGroup() {
        System.out.println("\t" + groupName + ": grade: " + grade + "% | " + "weight: " + weight + "%");
    }
}
