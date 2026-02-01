import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* ===================== COURSE CLASS ===================== */
class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;

    public Course(String code, String title, int credits, String instructor) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return code + " - " + title +
                " | Credits: " + credits +
                " | Instructor: " + instructor;
    }
}

/* ===================== STUDENT CLASS ===================== */
class Student {
    private String regNo;
    private String name;
    private String email;
    private String phone;
    private List<Course> registeredCourses = new ArrayList<>();

    public Student(String regNo, String name, String email, String phone) {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<Course> getRegisteredCourses() { return registeredCourses; }

    /* -------- Database Connection -------- */
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC",
                "root",
                "password"
        );
    }

    /* -------- Save Student -------- */
    public boolean saveStudent() {
        String sql = "INSERT INTO students (regNo, name, email, phone) VALUES (?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regNo);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* -------- Load Student -------- */
    public static Student loadStudent(String regNo) {
        String sql = "SELECT * FROM students WHERE regNo=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("regNo"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* -------- Course Operations -------- */
    public void registerCourse(Course c) {
        registeredCourses.add(c);
    }

    public boolean dropCourse(String code) {
        return registeredCourses.removeIf(c -> c.getCode().equals(code));
    }
}

/* ===================== MAIN GUI CLASS ===================== */
public class StudentManagementSystem extends JFrame {

    JTextField regNo, name, email, phone;
    JTextArea output;
    Student currentStudent;

    public StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /* ---- Input Panel ---- */
        JPanel input = new JPanel(new GridLayout(4, 2, 5, 5));
        input.setBorder(BorderFactory.createTitledBorder("Student Details"));

        regNo = new JTextField();
        name = new JTextField();
        email = new JTextField();
        phone = new JTextField();

        input.add(new JLabel("Reg No"));
        input.add(regNo);
        input.add(new JLabel("Name"));
        input.add(name);
        input.add(new JLabel("Email"));
        input.add(email);
        input.add(new JLabel("Phone"));
        input.add(phone);

        /* ---- Buttons ---- */
        JPanel buttons = new JPanel();

        JButton newReg = new JButton("New Registration");
        JButton load = new JButton("Existing Student");
        JButton addCourse = new JButton("Register Course");
        JButton view = new JButton("View Courses");

        buttons.add(newReg);
        buttons.add(load);
        buttons.add(addCourse);
        buttons.add(view);

        /* ---- Output Area ---- */
        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);

        add(input, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        /* ---- Button Actions ---- */

        newReg.addActionListener(e -> {
            if (regNo.getText().isEmpty() || name.getText().isEmpty()) {
                output.setText("Please fill all required fields.");
                return;
            }

            currentStudent = new Student(
                    regNo.getText(),
                    name.getText(),
                    email.getText(),
                    phone.getText()
            );

            if (currentStudent.saveStudent())
                output.setText("Student Registered Successfully");
            else
                output.setText("Registration Failed (Check DB)");
        });

        load.addActionListener(e -> {
            currentStudent = Student.loadStudent(regNo.getText());

            if (currentStudent != null) {
                name.setText(currentStudent.getName());
                email.setText(currentStudent.getEmail());
                phone.setText(currentStudent.getPhone());
                output.setText("Student Loaded Successfully");
            } else {
                output.setText("Student Not Found");
            }
        });

        addCourse.addActionListener(e -> {
            if (currentStudent == null) {
                output.setText("Load or Register Student First");
                return;
            }

            Course c = new Course("CS101", "Java Programming", 4, "Dr. Alex");
            currentStudent.registerCourse(c);
            output.setText("Course Registered:\n" + c);
        });

        view.addActionListener(e -> {
            if (currentStudent == null) {
                output.setText("No student loaded");
                return;
            }

            StringBuilder sb = new StringBuilder("Registered Courses:\n");
            for (Course c : currentStudent.getRegisteredCourses()) {
                sb.append(c).append("\n");
            }
            output.setText(sb.toString());
        });
    }

    /* ===================== MAIN METHOD ===================== */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new StudentManagementSystem().setVisible(true)
        );
    }
}
