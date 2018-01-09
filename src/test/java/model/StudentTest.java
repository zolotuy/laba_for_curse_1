package model;


import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;

import static org.testng.Assert.*;

/**
 * Created by zolotuy on 27.12.2017.
 */
public class StudentTest {
    Group group;
    Student student;
    Student student1;
    Student student2;

    @Test
    public void testEquals() throws Exception {
        assertEquals(student, student2);
    }

    @Test
    public void testGetAge() throws Exception {
        student.setDataOfBirth(LocalDate.of(1999,7,3));
        assertEquals(18,student.getAge());
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testNotSetAge() throws Exception {
        student1.setDataOfBirth(LocalDate.of(2019,2,9));
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testNotGetAge() throws Exception {
        student1.setDataOfBirth(LocalDate.of(1594,2,9));
        assertEquals(423,student1.getAge());
    }

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
        student2 = new Student("sasha",38429);
        student.setGroup(group);
        student1.setGroup(group);
        student.addSubjectAndMark(subject, 5);
        student.addSubjectAndMark(subject1, 5);

    }

    @AfterClass
    public void tearDown() throws Exception {
        //student = null;
        //student1 = null;
        //group = null;
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(student.hashCode(), student.hashCode());

    }

    @org.testng.annotations.Test
    public void testIsNotContains() throws Exception {
        Subject subject = new Subject();
        subject.setName("History");
       // assertFalse(student1.isContains(subject));
    }

    @org.testng.annotations.Test
    public void testIsContains() throws Exception {
        Subject subject = new Subject();
        subject.setName("Math");
        assertTrue(student1.isContains(subject));
    }

    //    @org.testng.annotations.Test
//    public void testNotAddSubjectAndMark() throws Exception {
//        assertNull(student1.addSubjectAndMark(new Subject("History"),4));
//    }
//
//    @org.testng.annotations.Test(enabled = false)
//    public void testAddSubjectAndMark() throws Exception {
//        assertNotNull(student1.addSubjectAndMark(new Subject("Math"),1));
//    }
//    @org.testng.annotations.Test(dependsOnMethods = "testNotAddSubjectAndMark")
//    public void testNotEditMark() throws Exception {
//        assertNull(student1.editMark(new Subject("History"),2));
//    }
//    @org.testng.annotations.Test(dependsOnMethods = "testAddSubjectAndMark")
//    public void testEditMark() throws Exception {
//        assertNotNull(student1.editMark(new Subject("Math"),4));
//    }
    @DataProvider(name = "AverageOfMarks")
    public Object[][] simpleDataProvider() {
        return new Object[][]{
                {new Subject("Math_1"), 5, new Subject("English_4"), 3, 4d},
                {new Subject("Math_2"), 4, new Subject("English_5"), 2, 3d},
                {new Subject("Math_3"), 2, new Subject("English_6"), 0, 1d},
        };
    }

    @org.testng.annotations.Test(dataProvider = "AverageOfMarks")
    public void checkParametersAvarageOfMark(Subject subject, int mark, Subject subject1, int mark1, Double result)
            throws Exception {
        student.editMark(subject, mark);
        student.editMark(subject1, mark1);
        assertTrue(result.equals(student.averageOfMarks()));
    }

    @DataProvider(name = "Compare")
    Object[][] checkCompare(){
       return new Object[][]{
               {new Student("oleksander",3646),
                       new Student("Oleksander",3646),1},
               {new Student("Oleksander",3646),
                       new Student("Oleksander",3646),0},
               {new Student("Oleksander1",3646),
                       new Student("oleksander1",3646),-1}
       };
    }
    @org.testng.annotations.Test(dataProvider = "Compare")
    public void testCompareTo(Student student,Student student1,int result) throws Exception {
        int comparison=0;
        if (student.compareTo(student1)<0){
            comparison=-1;
        }
        else if (student.compareTo(student1)>0){
            comparison=1;
        }
        assertEquals(comparison,result);
    }

}