package model;

import dao.GroupRepository;
import serialization.JSONSerializable;
import serialization.XMLSerialization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zolotuy on 26.12.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Subject subject = new Subject("Math_1");
        //subject.setId(1);da
        Subject subject1 = new Subject("English" + "_" + "2");
        // subject1.setId(2);
        Group group = new Group("243", "pz");
        group.addSubject(subject);
        group.addSubject(subject1);
        Student student = new Student("sanya", 34567);
        group.addStudent(student);
        Student student1 = new Student("roma", 25671);
        group.addStudent(student1);
        student.setGroup(group);
        student1.setGroup(group);
        student.setDataOfBirth(LocalDate.of(1999, 2, 9));
        student1.setDataOfBirth(LocalDate.of(1995, 4, 8));
        student.addSubjectAndMark(subject, 5);
        student1.addSubjectAndMark(subject1, 5);
        student.addSubjectAndMark(subject1, 4);
        student1.addSubjectAndMark(subject, 3);

        ArrayList<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        ArrayList<Group> groupArrayList = new ArrayList<>();
        groupArrayList.add(group);
//       Statement statement = Dao.getConnection().createStatement();
//       ResultSet resultSet = statement.executeQuery("SELECT version()");
//       resultSet.next();
//       System.out.println(resultSet.getString(1));

//        JSONSerializable<Group> jsonObject = new JSONSerializable<>();
//        jsonObject.listToFile(groupArrayList, "subject.json");
       List<Group> groupArrayList1 = new ArrayList<>();
//        groupArrayList1 = jsonObject.listFromFile("subject.json", Group.class);

        XMLSerialization<Group> xmlSerialization = new XMLSerialization<>();
        xmlSerialization.listToFile(groupArrayList,"my.xml");
        System.out.println(xmlSerialization.listFromFile("my.xml", Group.class));

        //GroupRepository.fromJSONFileToDataBase("subject.json");
        //SubjectRepository.fromJSONFileToDataBase("subject.json");

        //  System.out.println(jsonObject.fromFile("subject.json",Group.class));
        //jsonObject.toFile(subject,"subject.json");
        //System.out.println(jsonObject.fromFile("subject.json",Subject.class));
        // SubjectDao subjectDao = new SubjectDao();
        // subjectDao.addSubject(subject);
//     Group group = new Group( "243","pz");
//     group.addSubject(subject);
//      group.setId(1);
//      Student student = new Student("sanya",34567);
//      student.setDataOfBirth(LocalDate.of(1999,7,3));
//        System.out.println(student.getAge());
//     student.setGroup(group);
//    student.setId(1);
//       student.addSubjectAndMark(subject,5);
//        GroupDao groupDao = new GroupDao();
//        groupDao.addGroup(group);
//        StudentDao studentDao = new StudentDao();
//        studentDao.addStudent(student);
//        System.out.println(studentDao.getAverageMarkOfStudent(student));
//        System.out.println(groupDao.getAverageMaarkOfGroup(group));

//        student.setGroup(group);
//        student.addSubjectAndMark(new Subject("Math"), 34);
//        student.addSubjectAndMark(new Subject("English"), 80);
//        Student student1 = new Student("roma", 52345);
//        student1.setGroup(group);
//        student1.addSubjectAndMark(new Subject("Math"), 20);
//        group.addStudent(student);
//        group.addStudent(student1);
//        //group.sortOnAverage();
        // System.out.println(group.toString());
        //System.out.println(group.avarageOfGroup());
//        String url = "jdbc:postgresql://localhost:5432/Chair";
//        String userName = "postgres";
//        String password = "street777";
//        Class.forName("org.postgresql.Driver");
//        try(Connection connection = DriverManager.getConnection(url, userName, password);
//            Statement statement = connection.createStatement()) {
        //statement.executeUpdate(" DATABASE Chair");
        // statement.executeUpdate("INSERT INTO groups(name, number, id) VALUES ('pz','243',1)");
//            statement.executeUpdate("INSERT INTO Subject(name,id) VALUES ('Math',1)");
//            statement.executeUpdate("INSERT INTO Student(name,id,gradebook,group_id) VALUES ('Sasha',1,2345,1)");
//            statement.executeUpdate("INSERT INTO student_subject(student_id, subject_id, mark) VALUES (1,1,5)");
//            statement.executeUpdate("SELECT number FROM groups");
    }

}

