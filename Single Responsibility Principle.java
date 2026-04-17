// Class responsible only for handling student data
class Student {
    private String name;
    private int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getMarks() { return marks; }
}

// Class responsible only for calculating grades
class GradeCalculator {
    public String calculateGrade(Student student) {
        int marks = student.getMarks();
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 50) return "C";
        else return "D";
    }
}

// Class responsible only for displaying results
class ResultPrinter {
    public void printResult(Student student, String grade) {
        System.out.println("Student: " + student.getName() + ", Grade: " + grade);
    }
}

public class SRPDemo {
    public static void main(String[] args) {
        Student s = new Student("Harshan", 85);
        GradeCalculator gc = new GradeCalculator();
        ResultPrinter rp = new ResultPrinter();

        String grade = gc.calculateGrade(s);
        rp.printResult(s, grade);
    }
}
