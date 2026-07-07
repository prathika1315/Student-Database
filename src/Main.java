import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Entry point - console menu that drives the whole Student Management System.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        HtmlReportGenerator reportGenerator = new HtmlReportGenerator();

        int choice;
        do {
            printMenu();
            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent(sc, manager);
                case 2 -> viewAllStudents(manager);
                case 3 -> searchStudent(sc, manager);
                case 4 -> updateStudent(sc, manager);
                case 5 -> deleteStudent(sc, manager);
                case 6 -> reportGenerator.generateReport(manager.getAllStudents());
                case 7 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        } while (choice != 7);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("========= STUDENT MANAGEMENT SYSTEM =========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student (by ID or Name)");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Generate HTML Report");
        System.out.println("7. Exit");
        System.out.println("==============================================");
    }

    private static void addStudent(Scanner sc, StudentManager manager) {
        int id = readInt(sc, "Enter ID: ");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        int age = readInt(sc, "Enter Age: ");
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        double marks = readDouble(sc, "Enter Marks: ");

        Student student = new Student(id, name, age, dept, email, phone, marks);
        boolean added = manager.addStudent(student);
        System.out.println(added ? "Student added successfully!" : "A student with this ID already exists!");
    }

    private static void viewAllStudents(StudentManager manager) {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("--- All Students (" + students.size() + ") ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent(Scanner sc, StudentManager manager) {
        System.out.println("Search by: 1. ID   2. Name");
        int option = readInt(sc, "Choose option: ");

        if (option == 1) {
            int id = readInt(sc, "Enter ID: ");
            Student s = manager.searchById(id);
            System.out.println(s != null ? s : "No student found with ID " + id);
        } else if (option == 2) {
            System.out.print("Enter name (or part of it): ");
            String name = sc.nextLine();
            List<Student> results = manager.searchByName(name);
            if (results.isEmpty()) {
                System.out.println("No matching students found.");
            } else {
                results.forEach(System.out::println);
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void updateStudent(Scanner sc, StudentManager manager) {
        int id = readInt(sc, "Enter ID of student to update: ");
        Student existing = manager.searchById(id);
        if (existing == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.println("Leave a field blank to keep the current value.");

        System.out.print("New Name [" + existing.getName() + "]: ");
        String name = sc.nextLine();
        if (name.isBlank()) name = existing.getName();

        System.out.print("New Age [" + existing.getAge() + "]: ");
        String ageStr = sc.nextLine();
        int age = ageStr.isBlank() ? existing.getAge() : Integer.parseInt(ageStr);

        System.out.print("New Department [" + existing.getDepartment() + "]: ");
        String dept = sc.nextLine();
        if (dept.isBlank()) dept = existing.getDepartment();

        System.out.print("New Email [" + existing.getEmail() + "]: ");
        String email = sc.nextLine();
        if (email.isBlank()) email = existing.getEmail();

        System.out.print("New Phone [" + existing.getPhone() + "]: ");
        String phone = sc.nextLine();
        if (phone.isBlank()) phone = existing.getPhone();

        System.out.print("New Marks [" + existing.getMarks() + "]: ");
        String marksStr = sc.nextLine();
        double marks = marksStr.isBlank() ? existing.getMarks() : Double.parseDouble(marksStr);

        boolean updated = manager.updateStudent(id, name, age, dept, email, phone, marks);
        System.out.println(updated ? "Student updated successfully!" : "Update failed.");
    }

    private static void deleteStudent(Scanner sc, StudentManager manager) {
        int id = readInt(sc, "Enter ID of student to delete: ");
        boolean deleted = manager.deleteStudent(id);
        System.out.println(deleted ? "Student deleted successfully!" : "No student found with ID " + id);
    }

    // ---------- Input helpers with basic validation ----------
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
