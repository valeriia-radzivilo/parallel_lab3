package features.task3_grade_book;


import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GradeBook {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> grades;
    private final Set<String> gradedStudents;

    public GradeBook() {
        grades = new ConcurrentHashMap<>();
        gradedStudents = Collections.newSetFromMap(new ConcurrentHashMap<>());
        grades.put("Group1", new ConcurrentHashMap<>());
        grades.put("Group2", new ConcurrentHashMap<>());
        grades.put("Group3", new ConcurrentHashMap<>());
    }

    public synchronized boolean addGrade(String group, String studentId, int grade) {
        if (!gradedStudents.contains(studentId)) {
            grades.get(group).put(studentId, grade);
            gradedStudents.add(studentId);
            return true;
        }
        return false;
    }

    public ConcurrentHashMap<String, Integer> getGradesForGroup(String group) {
        return grades.get(group);
    }
}