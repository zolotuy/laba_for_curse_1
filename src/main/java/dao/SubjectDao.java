package dao;

import model.Subject;

import java.sql.*;

/**
 * Created by zolotuy on 02.01.2018.
 */
public class SubjectDao extends Dao {
    private static final String insert = "INSERT INTO subject(name) VALUES (?)";
    private static final String delete = "DELETE FROM subject WHERE name=?";
    private static final String update = "UPDATE subject SET name=? WHERE name=?";

    public Subject addSubject(Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, subject.getName());
         preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            subject.setId(generatedKeys.getInt("id"));
        }
        return subject;
    }

    public Integer deleteSubject(Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
        preparedStatement.setString(1, subject.getName());
        return preparedStatement.executeUpdate();
    }

    public Integer updateSubject(Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(update);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setString(2, subject.getName());
        return preparedStatement.executeUpdate();
    }
    private static final String selectSubject="SELECT * FROM subject WHERE id=?";
    public Subject getSubject(int id)throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectSubject);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Subject subject = new Subject(resultSet.getString("name"));
        subject.setId(id);
        return subject;
    }

}
