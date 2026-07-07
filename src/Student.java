/**
 * Student.java
 * Model class representing a single student record.
 * Demonstrates OOP principles: encapsulation via private fields + getters/setters.
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private String department;
    private String email;
    private String phone;
    private double marks;

    public Student(int id, String name, int age, String department, String email, String phone, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
    }

    // ---------- Getters ----------
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public double getMarks() { return marks; }

    // ---------- Setters (used for Update operation) ----------
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setMarks(double marks) { this.marks = marks; }

    /**
     * Converts this student object into a single CSV line for file storage.
     */
    public String toCSV() {
        return id + "," + name + "," + age + "," + department + "," + email + "," + phone + "," + marks;
    }

    /**
     * Rebuilds a Student object from a CSV line read from the file.
     */
    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        String department = parts[3].trim();
        String email = parts[4].trim();
        String phone = parts[5].trim();
        double marks = Double.parseDouble(parts[6].trim());
        return new Student(id, name, age, department, email, phone, marks);
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d Name: %-15s Age: %-3d Dept: %-10s Email: %-20s Phone: %-12s Marks: %.2f",
                id, name, age, department, email, phone, marks);
    }
}
