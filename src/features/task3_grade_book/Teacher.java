package features.task3_grade_book;

import java.util.Random;

public class Teacher implements Runnable {
    private final GradeBook gradeBook;
    private final String group;
    private final Random random;

    private final int STUDENTS_IN_GROUP = 40;

    public Teacher(GradeBook gradeBook, String group) {
        this.gradeBook = gradeBook;
        this.group = group;
        this.random = new Random();
    }

    @Override
    public void run() {

        int numberOfStudentsToGrade = random.nextInt(STUDENTS_IN_GROUP / 4) + 5;

        for (int i = 0; i < numberOfStudentsToGrade; i++) {

            String studentId = "Student" + (random.nextInt(STUDENTS_IN_GROUP) + 1);
            int grade = random.nextInt(100) + 1;
            boolean success = gradeBook.addGrade(group, studentId, grade);
            if (!success) {
                i--;
            }

        }
    }
}