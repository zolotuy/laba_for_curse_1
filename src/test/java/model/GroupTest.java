package model;

import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Created by zolotuy on 28.12.2017.
 */
public class GroupTest {
    Group group;
    Student student;
    Student student1;

    @BeforeClass
    public void setUp() throws Exception {
        group = new Group("243", "pz");
        Subject subject = new Subject();
        subject.setName("Math");
        Subject subject1 = new Subject();
        subject1.setName("English");

        group.addSubject(subject);
        group.addSubject(subject1);
        student = new Student("sasha", 23451);
        student1 = new Student("roma", 32493);
        student.setGroup(group);
        student1.setGroup(group);
        student.addSubjectAndMark(subject, 5);
        student.addSubjectAndMark(subject1, 3);
        student1.addSubjectAndMark(subject1, 4);
        student1.addSubjectAndMark(subject, 2);

    }

    @AfterClass
    public void tearDown() throws Exception {
        group = null;
        student = null;
        student1 = null;
    }

    @Test
    public void testAddSubject() throws Exception {
        Subject subject = new Subject();
        subject.setName("History");
        assertTrue(group.addSubject(subject));
    }

    @Test
    public void testNotAddSubject() throws Exception {
        Subject subject = new Subject();
        subject.setName("Math");
        assertFalse(group.addSubject(subject));
    }

    @DataProvider(name = "AverageOfGroup")
    Object[][] simpleDataProvider() {
        return new Object[][]{
                {new Subject("Math_1"), 1,
                        new Subject("English_2"), 3,
                        new Subject("Math_3"), 4,
                        new Subject("English_4"), 4, 3d},
                {new Subject("Math_5"), 2,
                        new Subject("English_6"), 0,
                        new Subject("Math_7"), 3,
                        new Subject("English_8"), 1, 1.5}
        };
    }

    @Test(dataProvider = "AverageOfGroup")
    public void testAvarageOfGroup(Subject subject1for1, int mark1for1, Subject subject2for1, int mark2for1,
                                   Subject subject1for2, int mark1for2, Subject subject2for2, int mark2for2, double result) throws Exception {
        student.addSubjectAndMark(subject1for1, mark1for1);
        student.addSubjectAndMark(subject2for1, mark2for1);
        student1.addSubjectAndMark(subject1for2, mark1for2);
        student1.addSubjectAndMark(subject2for2, mark2for2);
        group.addStudent(student);
        group.addStudent(student1);
        assertEquals(group.avarageOfGroup(), result);
    }

    @Test
    public void testNumberOfStudents() throws Exception {
        group.addStudent(student);
        group.addStudent(student1);
        assertTrue(group.numberOfStudents().equals(2l));
    }

    @Test(groups = "addStudents")
    public void testAddStudent() throws Exception {
        assertTrue(group.addStudent(student));
    }

    @Test(groups = "addStudents")
    public void testNotAddStudent() throws Exception {
        assertFalse(group.addStudent(null));
    }

    @Test(dependsOnGroups = "addStudents")
    public void testRemoveStudent() throws Exception {
        group.addStudent(student1);
        assertTrue(group.removeStudent(student1));
    }

    @Test
    public void testNotRemoveStudent1() throws Exception {
        group.addStudent(student1);
        assertFalse(group.removeStudent(null));
    }

    @Test(dependsOnMethods = "testNotRemoveStudent1")
    public void testNotRemoveStudent2() throws Exception {
        group.clearStudents();
        assertFalse(group.removeStudent(student1));
    }

}