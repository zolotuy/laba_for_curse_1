package dao;

import model.Group;
import model.Student;
import model.Subject;

import java.sql.*;

/**
 * Created by zolotuy on 29.12.2017.
 */
public class StudentDao extends Dao {
    private static final String insertMark = "INSERT INTO student_subject(mark, subject_id, student_id) VALUES (?,?,?)";
    private static final String deleteMark = "DELETE FROM student_subject WHERE (student_id=?)AND(subject_id = ?)";
    private static final String updateMark = "UPDATE student_subject SET mark=?,subject_id=?,student_id=? WHERE (subject_id = ?)AND(student_id=?) ";
    private static final String avgMarkOfStudent = "SELECT AVG(mark)AS avg_mark FROM student_subject where student_id =?";
    private static final String insert = "INSERT INTO student(name,gradebook,group_id,avarage_of_mark,birth_date,age) VALUES (?,?,?,?,?,?)";
    private static final String delete = "DELETE FROM student WHERE name==?";
    private static final String update = "UPDATE student SET student.name=?,student.gradebook=?,student.group_id=? WHERE student.name==?";

    public Student addStudent(Student student) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        if (student.getGroup() == null) {
            preparedStatement.setNull(3, Types.INTEGER);
        } else {
            preparedStatement.setInt(3, student.getGroup().getId());
        }
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getGradebook());
        preparedStatement.setDouble(4,student.averageOfMarks());
        preparedStatement.setDate(5,Date.valueOf(student.getDataOfBirth()));
        preparedStatement.setInt(6,student.getAge());
         preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            student.setId(generatedKeys.getInt("id"));
        }
        return student;
    }

    public Integer deleteStudent(Student student) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
        preparedStatement.setString(1, student.getName());
        return preparedStatement.executeUpdate();
    }

    public Integer updateStudent(Student student) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(update);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getGradebook());
        preparedStatement.setInt(3, student.getGroup().getId());
        preparedStatement.setString(4, student.getName());
        return preparedStatement.executeUpdate();
    }
    public Integer addMark(Student student, Subject subject, int mark) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(insertMark);
        preparedStatement.setInt(1, mark);
        preparedStatement.setInt(2, subject.getId());
        preparedStatement.setInt(3, student.getId());
        return preparedStatement.executeUpdate();
    }

    public Integer deleteMark(Student student, Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteMark);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setInt(2, subject.getId());
        return preparedStatement.executeUpdate();
    }

    public Integer updateMark(Student student, Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(updateMark);
        preparedStatement.setInt(1, student.getSubjectsAndMarks().get(subject));
        preparedStatement.setInt(2, subject.getId());
        preparedStatement.setInt(3, student.getId());
        preparedStatement.setInt(4, subject.getId());
        preparedStatement.setInt(5, student.getId());
        return preparedStatement.executeUpdate();
    }

    public Double getAverageMarkOfStudent(Student student) throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement(avgMarkOfStudent);
        preparedStatement.setInt(1, student.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double avg_mark = resultSet.getDouble("avg_mark");
        return avg_mark;
    }
    private static final String selectStudent="SELECT * FROM student LEFT join groups on student.group_id = groups.id WHERE student.id=?";
    public Student getStudent(int id)throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectStudent);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Student student = new Student(resultSet.getString("student.name"),resultSet.getInt("student.gradebook"));
        student.setId(id);
        Group group = new Group(resultSet.getString("groups.number"),resultSet.getString("groups.name"));
        group.setId(resultSet.getInt("groups.id"));
        student.setGroup(group);
        return student;
    }
    public Integer insertStudentAndMark(Student student) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getGradebook());
        preparedStatement.setInt(3, student.getGroup().getId());
        preparedStatement.executeUpdate();
        
        SubjectDao subjectDao = new SubjectDao();
        preparedStatement = getConnection().prepareStatement(insertMark);
        for (Subject subject : student.getSubjectsAndMarks().keySet()) {
            subjectDao.addSubject(subject);
            addMark(student,subject,student.getSubjectsAndMarks().get(subject));
        }
        return preparedStatement.executeUpdate();
    }
}
