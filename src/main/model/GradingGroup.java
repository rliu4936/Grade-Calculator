package model;

import org.json.JSONObject;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GradingGroup gg = (GradingGroup) o;
        return groupName.equals(gg.getGroupName())
                && weight == gg.getWeight()
                && grade == gg.getGrade();
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
