package features.task3_grade_book;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainGradeBook {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();

        ExecutorService executor = Executors.newFixedThreadPool(4); // 1 викладач + 3 асистенти
        executor.execute(new Teacher(gradeBook, "Group1"));
        executor.execute(new Teacher(gradeBook, "Group2"));
        executor.execute(new Teacher(gradeBook, "Group3"));

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Grades Group 1: " + gradeBook.getGradesForGroup("Group1"));
        System.out.println("Grades Group 2: " + gradeBook.getGradesForGroup("Group2"));
        System.out.println("Grades Group 3: " + gradeBook.getGradesForGroup("Group3"));
    }
}