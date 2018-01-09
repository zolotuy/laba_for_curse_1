package dao;

import model.Student;
import model.Subject;
import serialization.JSONSerializable;

import java.util.List;

/**
 * Created by zolotuy on 03.01.2018.
 */
public class StudentRepository {
    public static void fromJSONFileToDataBase(String filename) throws Exception {
        JSONSerializable<Student> jsonSerializable = new JSONSerializable<>();
        List<Student> list = jsonSerializable.listFromFile(filename,Student.class);
        StudentDao studentDao = new StudentDao();
        for (Student student:list) {
               studentDao.insertStudentAndMark(student);
               studentDao.getAverageMarkOfStudent(student);
        }
    }
}
