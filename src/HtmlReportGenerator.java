import java.io.*;
import java.util.List;

/**
 * HtmlReportGenerator.java
 * Generates a styled HTML report (students_report.html) of all student
 * records, linked to an external style.css file.
 *
 * This is what brings "HTML, CSS" into the Java project: the Java program
 * writes out a real webpage that you (or anyone) can open in a browser,
 * or view live using VS Code's Live Server extension.
 */
public class HtmlReportGenerator {

    private static final String HTML_FILE = "students_report.html";
    private static final String CSS_FILE = "style.css";

    public void generateReport(List<Student> students) {
        writeCssFile();
        writeHtmlFile(students);
        System.out.println("Report generated: " + HTML_FILE + " (open it in a browser or Live Server)");
    }

    private void writeHtmlFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HTML_FILE))) {
            writer.write("<!DOCTYPE html>\n<html lang='en'>\n<head>\n");
            writer.write("<meta charset='UTF-8'>\n<title>Student Records Report</title>\n");
            writer.write("<link rel='stylesheet' href='" + CSS_FILE + "'>\n</head>\n<body>\n");
            writer.write("<div class='container'>\n");
            writer.write("<h1>Student Management System - Report</h1>\n");
            writer.write("<p class='subtitle'>Total Students: " + students.size() + "</p>\n");
            writer.write("<table>\n<tr>");
            writer.write("<th>ID</th><th>Name</th><th>Age</th><th>Department</th><th>Email</th><th>Phone</th><th>Marks</th>");
            writer.write("</tr>\n");

            for (Student s : students) {
                writer.write("<tr>");
                writer.write("<td>" + s.getId() + "</td>");
                writer.write("<td>" + s.getName() + "</td>");
                writer.write("<td>" + s.getAge() + "</td>");
                writer.write("<td>" + s.getDepartment() + "</td>");
                writer.write("<td>" + s.getEmail() + "</td>");
                writer.write("<td>" + s.getPhone() + "</td>");
                writer.write("<td>" + s.getMarks() + "</td>");
                writer.write("</tr>\n");
            }

            writer.write("</table>\n</div>\n</body>\n</html>");
        } catch (IOException e) {
            System.out.println("Error writing HTML report: " + e.getMessage());
        }
    }

    private void writeCssFile() {
        // Only write it once so re-running doesn't keep overwriting your edits
        File cssFile = new File(CSS_FILE);
        if (cssFile.exists()) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cssFile))) {
            writer.write(
                "body {\n" +
                "  font-family: 'Segoe UI', Arial, sans-serif;\n" +
                "  background: #f4f6f9;\n" +
                "  margin: 0;\n" +
                "  padding: 40px;\n" +
                "}\n\n" +
                ".container {\n" +
                "  max-width: 900px;\n" +
                "  margin: 0 auto;\n" +
                "  background: #ffffff;\n" +
                "  padding: 30px;\n" +
                "  border-radius: 10px;\n" +
                "  box-shadow: 0 4px 12px rgba(0,0,0,0.08);\n" +
                "}\n\n" +
                "h1 {\n" +
                "  color: #2c3e50;\n" +
                "  margin-bottom: 5px;\n" +
                "}\n\n" +
                ".subtitle {\n" +
                "  color: #7f8c8d;\n" +
                "  margin-bottom: 20px;\n" +
                "}\n\n" +
                "table {\n" +
                "  width: 100%;\n" +
                "  border-collapse: collapse;\n" +
                "}\n\n" +
                "th, td {\n" +
                "  padding: 10px 14px;\n" +
                "  text-align: left;\n" +
                "  border-bottom: 1px solid #e0e0e0;\n" +
                "}\n\n" +
                "th {\n" +
                "  background: #6c5ce7;\n" +
                "  color: #ffffff;\n" +
                "}\n\n" +
                "tr:hover {\n" +
                "  background: #f1f0fb;\n" +
                "}\n"
            );
        } catch (IOException e) {
            System.out.println("Error writing CSS file: " + e.getMessage());
        }
    }
}
