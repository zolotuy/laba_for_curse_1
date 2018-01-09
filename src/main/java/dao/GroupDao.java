package dao;

import model.Group;
import model.Student;
import model.Subject;

import java.sql.*;

/**
 * Created by zolotuy on 02.01.2018.
 */
public class GroupDao extends Dao {
    private static final String avgMarkOfGroup = "SELECT AVG(mark) AS avg_mark_group FROM student_subject inner join student on student.group_id = ?";
    private static final String insert = "INSERT INTO groups(name,number,number_of_students,avarage_of_mark) VALUES (?,?,?,?)";
    private static final String delete = "DELETE FROM groups WHERE name=?";
    private static final String update = "UPDATE groups SET name=?,number=?,number_of_students=?,avarage_of_mark=? WHERE number=?";
    private static final String selectGroup = "SELECT * FROM groups WHERE id=?";

    public Group addGroup(Group group) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, group.getName());
        preparedStatement.setString(2, group.getNumber());
        preparedStatement.setLong(3,group.numberOfStudents());
        preparedStatement.setDouble(4,group.avarageOfGroup());
        preparedStatement.executeUpdate();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                group.setId(generatedKeys.getInt("id"));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        preparedStatement.close();
        return group;
    }

    public Integer deleteGroup(Group group) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
        preparedStatement.setString(1, group.getName());
        return preparedStatement.executeUpdate();
    }

    public Integer updateGroup(Group group) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(update);
        preparedStatement.setString(1, group.getName());
        preparedStatement.setString(2, group.getNumber());
        preparedStatement.setLong(3,group.numberOfStudents());
        preparedStatement.setDouble(4,group.avarageOfGroup());
        preparedStatement.setString(5, group.getNumber());
        return preparedStatement.executeUpdate();
    }

    public Double getAverageMaarkOfGroup(Group group) throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement(avgMarkOfGroup);
        preparedStatement.setInt(1, group.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double avg_mark_group = resultSet.getDouble("avg_mark_group");
        return avg_mark_group;
    }

    public Group getGroup(int id) throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectGroup);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Group group = new Group(resultSet.getString("number"), resultSet.getString("name"));
        group.setId(id);
        return group;
    }

    public void insertStudentAndGroupAndSubject(Group group) throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, group.getName());
        preparedStatement.setString(2, group.getNumber());
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            group.setId(generatedKeys.getInt("id"));
        }
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        for (Student student : group.getStudents()) {
            student.setGroup(group);
            studentDao.addStudent(student);
        }
        for (Subject subject : group.getSubjects()) {
            subjectDao.addSubject(subject);
        }
    }

}
