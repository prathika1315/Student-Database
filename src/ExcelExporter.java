import java.io.*;
import java.util.List;

/**
 * ExcelExporter.java
 * Automatically exports the current student list to a CSV file
 * (students_excel_report.csv) every time the data changes.
 *
 * CSV files open directly in Microsoft Excel as a normal spreadsheet
 * (columns, sorting, filtering all work) with no extra libraries needed.
 */
public class ExcelExporter {

    private static final String CSV_FILE = "students_excel_report.csv";

    public void export(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {

            // Header row
            writer.write("ID,Name,Age,Department,Email,Phone,Marks");
            writer.newLine();

            // Data rows
            for (Student s : students) {
                writer.write(
                        s.getId() + "," +
                        escapeComma(s.getName()) + "," +
                        s.getAge() + "," +
                        escapeComma(s.getDepartment()) + "," +
                        escapeComma(s.getEmail()) + "," +
                        escapeComma(s.getPhone()) + "," +
                        s.getMarks()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error exporting to CSV: " + e.getMessage());
        }
    }

    // If a name/department/etc. ever contains a comma, wrap it in quotes
    // so Excel doesn't misread it as extra columns.
    private String escapeComma(String value) {
        if (value != null && value.contains(",")) {
            return "\"" + value + "\"";
        }
        return value;
    }
}
