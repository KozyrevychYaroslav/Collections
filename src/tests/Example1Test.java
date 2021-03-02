package tests;

import ai181.kozyrevych.example1.Group;
import ai181.kozyrevych.example1.GroupNumberController;
import ai181.kozyrevych.example1.Student;
import ai181.kozyrevych.example1.Subject;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Example1Test {
    Student student1;
    Student student2;
    Student student3;
    Student student4;
    Student student5;
    Student student6;

    @BeforeEach
    public void config() {
        student1 = new Student("Иван", "Иванов", "Иванович");
        student2 = new Student("Петр", "Петров", "Петрович");
        student3 = new Student("Ярослав", "Ярославов", "Ярославович");
        student4 = new Student("Игорь", "Игорев", "Игоревич");
        student5 = new Student("Максим", "Максимов", "Максимович");
        student6 = new Student("Алексей", "Алексеев", "Алексеевич");
    }

    @Test
    @DisplayName("Testing Student")
    public void studentTest() {
        Student student = new Student("Иван", "Иванов", "Иванович");

        assertEquals("Иван", student.getName());
        assertEquals("Иванов", student.getSurname());
        assertEquals("Иванович", student.getPatronymic());
        assertEquals(student.getSubjectsMarks().toString(), "{BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}");
        assertEquals("Иван Иванов Иванович\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n", student.toString());

        student.doHomework(Subject.BIOLOGY);
        student.doHomework(Subject.ENGLISH);
        student.doHomework(Subject.INFORMATICS);

        assertEquals("[BIOLOGY, ENGLISH, INFORMATICS]", student.getDoneHomework().toString());
        assertEquals("[ENGLISH, INFORMATICS]", student.passHomework().toString());

        student.doHomework(Subject.BIOLOGY);

        assertEquals("[INFORMATICS, BIOLOGY]", student.passHomework().toString());

        LinkedHashMap<Subject, Integer> map = new LinkedHashMap<>();

        map.put(Subject.INFORMATICS, 10);
        map.put(Subject.BIOLOGY, 8);
        map.put(Subject.MATH, 9);
        map.put(Subject.ENGLISH, 11);
        student.setSubjectsMarks(map);

        assertEquals(student.getSubjectsMarks().toString(), "{INFORMATICS=10, BIOLOGY=8, MATH=9, ENGLISH=11}");
    }

    @Test
    @Order(2)
    @DisplayName("Testing Group + groupNumberController")
    public void testGroup() {
        Group group1 = new Group("B10");
        Group group2 = new Group("C10");
        Group group3 = new Group("A10");

        assertEquals("Группа № B10. Количество студентов = 0\n", group1.toString());

        group1.addStudent(student1);
        group1.addStudent(student2);
        group2.addStudent(student3);
        group2.addStudent(student4);
        group3.addStudent(student5);
        group3.addStudent(student6);

        assertAll("Group toString() error",
                () -> assertEquals("Группа № B10. Количество студентов = 2\n", group1.toString()),
                () -> assertEquals("Группа № C10. Количество студентов = 2\n", group2.toString()),
                () -> assertEquals("Группа № A10. Количество студентов = 2\n", group3.toString()));

        assertEquals("Группа № A10. Количество студентов = 2\n" +
                "Группа № B10. Количество студентов = 2\n" +
                "Группа № C10. Количество студентов = 2\n", GroupNumberController.getSortedGroups());

        assertThrows(IllegalArgumentException.class, () -> new Group("C10"));

        assertFalse(group1.addStudent(student1));

        Student copyStudent = new Student("Иван", "Иванов", "Иванович");

        //без переопределенного equals было бы assertTrue
        assertFalse(group1.addStudent(copyStudent));

        assertFalse(student1 == copyStudent);

        assertTrue(student1.hashCode() == copyStudent.hashCode());

        assertTrue(student1.equals(copyStudent));
    }

    @Test
    @Order(3)
    @DisplayName("Checking comparator")
    public void testComparator() {
        Group group4 = new Group("A11");

        group4.addStudent(student1);
        group4.addStudent(student2);
        group4.addStudent(student3);
        group4.addStudent(student4);
        group4.addStudent(student5);
        group4.addStudent(student6);
        List<Student> students = group4.getStudents();

        students.sort(Comparator.comparing(Student::getName).
                thenComparing(Student::getSurname).
                thenComparing(Student::getPatronymic));

        assertEquals("[Алексей Алексеев Алексеевич\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                ", Иван Иванов Иванович\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                ", Игорь Игорев Игоревич\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                ", Максим Максимов Максимович\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                ", Петр Петров Петрович\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                ", Ярослав Ярославов Ярославович\n" +
                "Marks: {BIOLOGY=0, MATH=0, INFORMATICS=0, ENGLISH=0}\n" +
                "]", students.toString());
    }

}
