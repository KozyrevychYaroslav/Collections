package ai181.kozyrevych.example1;

import java.util.*;

public class Student {
    private Map<Subject, Integer> subjectsMarks = new LinkedHashMap<>();
    private Set<Subject> doneHomework = new LinkedHashSet<>();
    private String name;
    private String surname;
    private String patronymic;

    {
        subjectsMarks.put(Subject.BIOLOGY, 0);
        subjectsMarks.put(Subject.MATH, 0);
        subjectsMarks.put(Subject.INFORMATICS, 0);
        subjectsMarks.put(Subject.ENGLISH, 0);
    }

    public Student(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public Map<Subject, Integer> getSubjectsMarks() {
        return subjectsMarks;
    }

    public void setSubjectsMarks(Map<Subject, Integer> subjectsMarks) {
        this.subjectsMarks = subjectsMarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void doHomework(Subject subject) {
        doneHomework.add(subject);
    }

    public Set<Subject> passHomework() {
        Iterator<Subject> iterator = doneHomework.iterator();
        // в первую очередь студент сдает свое первое выполненное дз
        // следовательно оно исчезает из требуемых для сдачи дз
        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        return doneHomework;
    }

    public Set<Subject> getDoneHomework() {
        return doneHomework;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic + "\nMarks: " + subjectsMarks + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return subjectsMarks.equals(student.subjectsMarks) &&
                doneHomework.equals(student.doneHomework) &&
                name.equals(student.name) &&
                surname.equals(student.surname) &&
                patronymic.equals(student.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectsMarks, doneHomework, name, surname, patronymic);
    }
}
