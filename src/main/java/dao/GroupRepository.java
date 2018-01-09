package dao;

import model.Group;
import model.Student;
import model.Subject;
import serialization.JSONSerializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.Dao.getConnection;

/**
 * Created by zolotuy on 04.01.2018.
 */
public class GroupRepository {
    public static void fromJSONFileToDataBase(String filename) throws Exception {
        JSONSerializable<Group> jsonSerialization = new JSONSerializable<>();
        List<Group> groupList = jsonSerialization.listFromFile(filename, Group.class);
        GroupDao groupDao = new GroupDao();
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        for (Group group : groupList) {
            groupDao.addGroup(group);
            for (Student student : group.getStudents()) {
                student.setGroup(group);
                studentDao.addStudent(student);
            }
            for (Subject subject : group.getSubjects()) {
                subjectDao.addSubject(subject);
            }
            for (Student student : group.getStudents()) {
                for (Subject subject : group.getSubjects()) {
                    if (student.getSubjectsAndMarks().containsKey(subject)) {
                        studentDao.addMark(student, subject, student.getSubjectsAndMarks().get(subject));
                    }
                }
            }
        }
    }
}

