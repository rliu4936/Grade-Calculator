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

    public Integer getWeight() {
        return weight;
    }

    public Integer getGrade() {
        return grade;
    }

    public void printGradingGroup() {
        System.out.println("\t" + groupName + ": grade: " + grade + "% | " + "weight: " + weight + "%");
    }
}
