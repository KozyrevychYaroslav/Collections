package ai181.kozyrevych.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group implements Comparable<Group> {
    private List<Student> students = new ArrayList<>();
    private String groupNumber = "";

    public Group(String groupNumber) {
        GroupNumberController.addGroup(this, groupNumber);
        this.groupNumber = groupNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public boolean addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Группа № " + groupNumber + ". Количество студентов = " + students.size() + '\n';
    }

    @Override
    public int compareTo(Group o) {
        return groupNumber.compareTo(o.getGroupNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return students.equals(group.students) &&
                groupNumber.equals(group.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, groupNumber);
    }
}
