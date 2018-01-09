package dao;

import model.Subject;
import serialization.JSONSerializable;

import java.io.File;
import java.util.List;

/**
 * Created by zolotuy on 03.01.2018.
 */
public class SubjectRepository {
    public static void fromJSONFileToDataBase(String filename) throws Exception {
        JSONSerializable<Subject> jsonSerializable = new JSONSerializable<>();
        List<Subject> list = jsonSerializable.listFromFile(filename,Subject.class);
        SubjectDao subjectDao = new SubjectDao();
        for (Subject subject:list) {
            subjectDao.addSubject(subject);
        }
    }
}
