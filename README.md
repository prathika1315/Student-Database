# Student Management System

A Java console application to manage student records with full CRUD functionality,
efficient search using Collections, persistent file storage, and an HTML/CSS report export.

## How this matches your resume bullets

| Resume claim | Where it's implemented |
|---|---|
| Full CRUD functionality | `StudentManager.java` — add, view, search, update, delete |
| Java Collections for search/retrieval | `ArrayList<Student>` for ordered storage + `HashMap<Integer, Student>` for O(1) lookup by ID (instead of looping the whole list) |
| File handling for persistent storage | `FileHandler.java` — reads/writes `students.txt` (CSV format) so data survives between runs |
| Modular, maintainable code | Separate classes for model (`Student`), storage (`FileHandler`), logic (`StudentManager`), UI (`Main`), and reporting (`HtmlReportGenerator`) |
| Java, HTML, CSS, OOP, File Handling | `HtmlReportGenerator.java` exports records into a styled `students_report.html` + `style.css` |

## Project structure

```
StudentManagementSystem/
└── src/
    ├── Student.java              (model class)
    ├── FileHandler.java           (file persistence)
    ├── StudentManager.java        (CRUD logic + Collections)
    ├── HtmlReportGenerator.java   (HTML/CSS report export)
    └── Main.java                  (console menu / entry point)
```

## How to run it in VS Code

1. Install the **Extension Pack for Java** in VS Code (if you haven't already).
2. Open the `StudentManagementSystem` folder in VS Code.
3. Open `src/Main.java`.
4. Click the **Run** button above `public static void main`, or press `Ctrl+F5`.
5. Use the numbered menu in the integrated terminal.

## How to run it from the command line

```bash
cd StudentManagementSystem/src
javac *.java
java Main
```

## What happens when you run it

- Choosing **6. Generate HTML Report** creates `students_report.html` and `style.css`
  in the same folder. Open `students_report.html` with VS Code's **Live Server**
  extension (right-click the file → "Open with Live Server") to view a styled table
  of all your student records in the browser.
- All add/update/delete operations are immediately saved to `students.txt`, so your
  data is still there the next time you run the program.

## Sample data to demo it quickly

When the menu appears, try:
1. **Add Student** → ID `101`, Name `Riya Sharma`, Age `20`, Dept `CSE`, Email, Phone, Marks `88.5`
2. **Add Student** → ID `102`, Name `Arjun Kumar`, Age `21`, Dept `ECE`, Email, Phone, Marks `79`
3. **View All Students** → see both listed
4. **Search Student** → by ID `101`
5. **Update Student** → change marks for `101`
6. **Generate HTML Report** → open `students_report.html`
7. **Delete Student** → remove `102`

## Talking points for interviews

- Why HashMap alongside ArrayList? ArrayList keeps natural insertion order for
  listing/reporting; HashMap gives constant-time lookup by ID instead of an O(n)
  scan — this is literally the "improving search and retrieval operations" line.
- Why CSV instead of serialization? Human-readable, easy to inspect/debug, and
  demonstrates real file I/O (BufferedReader/BufferedWriter) rather than a black-box
  binary format.
- Why generate HTML/CSS from Java? Shows the app isn't just a terminal tool — it can
  produce a real front-end artifact, and ties together your full listed tech stack.
