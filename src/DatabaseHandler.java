import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHandler.java
 * Handles all persistent storage operations using MySQL via JDBC.
 * This replaces FileHandler.java — same job (save/load students),
 * but backed by a real database instead of a text file.
 */
public class DatabaseHandler {

    // ---------- UPDATE THESE TO MATCH YOUR MYSQL SETUP ----------
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Prathi@13."; // <-- change to YOUR MySQL root password
    // --------------------------------------------------------------

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Loads all students from the students table.
     */
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("marks")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    /**
     * Inserts a new student row.
     */
    public boolean insertStudent(Student s) {
        String query = "INSERT INTO students (id, name, age, department, email, phone, marks) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getName());
            stmt.setInt(3, s.getAge());
            stmt.setString(4, s.getDepartment());
            stmt.setString(5, s.getEmail());
            stmt.setString(6, s.getPhone());
            stmt.setDouble(7, s.getMarks());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates an existing student row by ID.
     */
    public boolean updateStudent(Student s) {
        String query = "UPDATE students SET name=?, age=?, department=?, email=?, phone=?, marks=? WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getAge());
            stmt.setString(3, s.getDepartment());
            stmt.setString(4, s.getEmail());
            stmt.setString(5, s.getPhone());
            stmt.setDouble(6, s.getMarks());
            stmt.setInt(7, s.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a student row by ID.
     */
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a student with the given ID already exists (used to prevent duplicates).
     */
    public boolean existsById(int id) {
        String query = "SELECT id FROM students WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error checking student: " + e.getMessage());
            return false;
        }
    }
}