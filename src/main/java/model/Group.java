package model;

import java.util.*;

/**
 * Created by zolotuy on 26.12.2017.
 */
public class Group {
    private String number;
    private String name;
    private int id;
    private Set<Subject> subjects = new HashSet<>();
    private SortedSet<Student> students = new TreeSet<>();

    public Group(String numberOfGroup, String nameOfGroup) {
        this.number = numberOfGroup;
        this.name = nameOfGroup;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return number.equals(group.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "model.Group{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ",avarageOfGroup="+avarageOfGroup()+
                ",numberOfStudents="+numberOfStudents()+
                ",subjects:" + subjects+
                ", students:" + students +
                '}';
    }

    public boolean addSubject(Subject name) {
        if (!subjects.contains(name))
            return subjects.add(name);
        else return false;
    }

    public void clearStudents() {
        students.clear();
    }

    public Double avarageOfGroup() {
        return students.stream()
                .mapToDouble(el -> el.averageOfMarks())
                .average()
                .orElse(0);
    }

    public Long numberOfStudents() {
        return students.stream()
                .count();
    }

    public boolean addStudent(Student student) {
        if (student != null) {
            student.setGroup(this);
            return students.add(student);
        } else return false;
    }

    public boolean removeStudent(Student student) {
        if ((students != null) && (student != null))
            return students.remove(student);
        else return false;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Group(){}

    public ArrayList convertToArray(){
        ArrayList<Subject> list = new ArrayList<>();
        list.addAll(subjects);
        return list;
    }

    public SortedSet<Student> getStudents() {
        return students;
    }

    public void setStudents(SortedSet<Student> students) {
        this.students = students;
    }

}
