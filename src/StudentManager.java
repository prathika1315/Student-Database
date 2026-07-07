import java.util.*;

/**
 * StudentManager.java
 * Core business logic layer. Handles all CRUD operations.
 *
 * Keeps the same ArrayList + HashMap in-memory Collections used before
 * (for ordered listing + O(1) search by ID), but now the source of
 * truth is MySQL via DatabaseHandler instead of a text file.
 * The cache is refreshed from the database after every write.
 */
public class StudentManager {

    private List<Student> studentList;
    private Map<Integer, Student> studentMap;
    private DatabaseHandler dbHandler;
    private ExcelExporter excelExporter;

    public StudentManager() {
        dbHandler = new DatabaseHandler();
        excelExporter = new ExcelExporter();
        refreshCache();
    }

    private void refreshCache() {
        studentList = dbHandler.loadStudents();
        studentMap = new HashMap<>();
        for (Student s : studentList) {
            studentMap.put(s.getId(), s);
        }
        excelExporter.export(studentList); // keep the CSV report always up to date
    }

    // ---------------- CREATE ----------------
    public boolean addStudent(Student student) {
        if (dbHandler.existsById(student.getId())) {
            return false; // duplicate ID
        }
        boolean success = dbHandler.insertStudent(student);
        if (success) refreshCache();
        return success;
    }

    // ---------------- READ ----------------
    public List<Student> getAllStudents() {
        List<Student> sorted = new ArrayList<>(studentList);
        sorted.sort(Comparator.comparingInt(Student::getId));
        return sorted;
    }

    public Student searchById(int id) {
        return studentMap.get(id); // O(1) lookup thanks to HashMap
    }

    public List<Student> searchByName(String namePart) {
        List<Student> results = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(namePart.toLowerCase())) {
                results.add(s);
            }
        }
        return results;
    }

    // ---------------- UPDATE ----------------
    public boolean updateStudent(int id, String name, int age, String department,
                                  String email, String phone, double marks) {
        Student s = studentMap.get(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setAge(age);
        s.setDepartment(department);
        s.setEmail(email);
        s.setPhone(phone);
        s.setMarks(marks);

        boolean success = dbHandler.updateStudent(s);
        if (success) refreshCache();
        return success;
    }

    // ---------------- DELETE ----------------
    public boolean deleteStudent(int id) {
        boolean success = dbHandler.deleteStudent(id);
        if (success) refreshCache();
        return success;
    }

    public int totalStudents() {
        return studentList.size();
    }
}
