import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler.java
 * Handles all persistent storage operations (reading/writing student records
 * to a text file) so the data survives after the program closes.
 */
public class FileHandler {

    private static final String FILE_NAME = "students.txt";

    /**
     * Loads all students from the file into a List.
     * If the file doesn't exist yet (first run), returns an empty list.
     */
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students; // no data yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromCSV(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return students;
    }

    /**
     * Writes the current list of students back to the file,
     * overwriting the previous contents (called after every add/update/delete).
     */
    public void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
