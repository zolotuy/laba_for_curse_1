package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.HashMap;

/**
 * Created by zolotuy on 26.12.2017.
 */
public class Student implements Comparable<Student> {
    private String name;
    private int gradebook;
    private int id;
    private LocalDate dataOfBirth;
    @JsonIgnore
    private Group group;
    private HashMap<Subject, Integer> subjectsAndMarks = new HashMap<>();

    public Student(String name, int gradebook) {
        this.name = name;
        this.gradebook = gradebook;
    }

    @Override
    public String toString() {
        return "model.Student{" +
                "name='" + name + '\'' +
                ", gradebook=" + gradebook +
                ",dataOfBirth="+dataOfBirth+
                ",averageOfMarks="+averageOfMarks()+
                ", subjectAndMarks:" + subjectsAndMarks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @JsonIgnore
    public int getAge() {
        LocalDate now = LocalDate.now();
        if (Period.between(dataOfBirth, now).getYears()<101)
            return Period.between(dataOfBirth, now).getYears();
        else throw new IllegalArgumentException();
    }

    public void clearAllSubjectAndMark() {
        subjectsAndMarks.clear();
    }

    public Integer editMark(Subject subject, int mark) {
        if (isContains(subject) && (subject != null))
            return subjectsAndMarks.replace(subject, mark);
        else return null;
    }

    public Integer addSubjectAndMark(Subject subject, int mark) {
        if (isContains(subject) && (subject != null)) {
            return subjectsAndMarks.put(subject, mark);
        } else return null;
    }

    public boolean isContains(Subject subject) {
        if (subject != null)
            return (group.getSubjects().contains(subject));
        else return false;
    }

    public Double averageOfMarks() {
        return subjectsAndMarks.values().stream().mapToDouble(el -> el).average().orElse(0);
    }
    @JsonProperty
    public void setGroup(Group group) {
        this.group = group;
    }
    @JsonIgnore
    public Group getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGradebook() {
        return gradebook;
    }

    public void setGradebook(int gradebook) {
        this.gradebook = gradebook;
    }

    public HashMap<Subject, Integer> getSubjectsAndMarks() {
        return subjectsAndMarks;
    }

    public void setSubjectsAndMarks(HashMap<Subject, Integer> subjectsAndMarks) {
        this.subjectsAndMarks = subjectsAndMarks;
    }

    public LocalDate getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(LocalDate dataOfBirth) {
        if (dataOfBirth.isBefore(LocalDate.now()))
        this.dataOfBirth = dataOfBirth;
        else throw new IllegalArgumentException();
    }

    public Student(){}

}
