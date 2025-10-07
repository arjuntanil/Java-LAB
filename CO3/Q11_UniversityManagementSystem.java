package c03;

import java.util.Scanner;

// Q11: University Management System
// Comprehensive system demonstrating advanced OOP concepts

// Abstract base class for all university persons
abstract class Person {
    protected String personId;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String address;
    
    public Person(String personId, String name, String email) {
        this.personId = personId;
        this.name = name;
        this.email = email;
    }
    
    // Abstract method for displaying person details
    public abstract void displayDetails();
    
    // Abstract method for role-specific actions
    public abstract void performRole();
    
    // Common method for updating contact info
    public final void updateContactInfo(String phoneNumber, String address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        System.out.println("Contact information updated for " + name);
    }
    
    // Getters
    public String getPersonId() { return personId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Interface for entities that can be enrolled
interface Enrollable {
    void enroll(Course course);
    void unenroll(Course course);
    void viewEnrollments();
    
    default boolean canEnroll(Course course) {
        return course != null && course.hasCapacity();
    }
}

// Interface for entities that can teach
interface Teachable {
    void assignCourse(Course course);
    void unassignCourse(Course course);
    void viewTeachingLoad();
    
    default boolean canTeach(Course course) {
        return course != null;
    }
}

// Interface for entities that can be graded
interface Gradable {
    void assignGrade(String courseId, String grade);
    void viewGrades();
    double calculateGPA();
    
    static boolean isValidGrade(String grade) {
        return grade.matches("A\\+|A|A-|B\\+|B|B-|C\\+|C|C-|D|F");
    }
}

// Interface for administrative functions
interface Administrative {
    void generateReport();
    void manageRecords();
    void processPayments();
    
    default void scheduleAppointment(String date, String purpose) {
        System.out.println("Appointment scheduled for " + date + ": " + purpose);
    }
}

// Student class implementing multiple interfaces
class Student extends Person implements Enrollable, Gradable {
    private String studentId;
    private String major;
    private String yearLevel;
    private Course[] enrolledCourses;
    private String[] grades;
    private int courseCount;
    private double gpa;
    private static final int MAX_COURSES = 8;
    
    public Student(String personId, String name, String email, String studentId, String major) {
        super(personId, name, email);
        this.studentId = studentId;
        this.major = major;
        this.yearLevel = "Freshman";
        this.enrolledCourses = new Course[MAX_COURSES];
        this.grades = new String[MAX_COURSES];
        this.courseCount = 0;
        this.gpa = 0.0;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n=== Student Details ===");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Major: " + major);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("GPA: " + String.format("%.2f", gpa));
        System.out.println("Enrolled Courses: " + courseCount);
    }
    
    @Override
    public void performRole() {
        System.out.println(name + " is attending classes and studying");
    }
    
    // Enrollable implementation
    @Override
    public void enroll(Course course) {
        if (courseCount < MAX_COURSES && canEnroll(course)) {
            enrolledCourses[courseCount] = course;
            grades[courseCount] = "In Progress";
            courseCount++;
            course.addStudent(this);
            System.out.println("✓ " + name + " enrolled in " + course.getCourseName());
        } else {
            System.out.println("✗ Cannot enroll in " + course.getCourseName());
        }
    }
    
    @Override
    public void unenroll(Course course) {
        for (int i = 0; i < courseCount; i++) {
            if (enrolledCourses[i].equals(course)) {
                // Shift courses down
                for (int j = i; j < courseCount - 1; j++) {
                    enrolledCourses[j] = enrolledCourses[j + 1];
                    grades[j] = grades[j + 1];
                }
                courseCount--;
                course.removeStudent(this);
                System.out.println("✓ " + name + " unenrolled from " + course.getCourseName());
                return;
            }
        }
        System.out.println("✗ " + name + " is not enrolled in " + course.getCourseName());
    }
    
    @Override
    public void viewEnrollments() {
        System.out.println("\n" + name + "'s Enrollments:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("- " + enrolledCourses[i].getCourseName() + " (Grade: " + grades[i] + ")");
        }
    }
    
    // Gradable implementation
    @Override
    public void assignGrade(String courseId, String grade) {
        if (Gradable.isValidGrade(grade)) {
            for (int i = 0; i < courseCount; i++) {
                if (enrolledCourses[i].getCourseId().equals(courseId)) {
                    grades[i] = grade;
                    calculateGPA();
                    System.out.println("Grade " + grade + " assigned to " + name + " for " + courseId);
                    return;
                }
            }
        }
        System.out.println("Invalid grade or course not found");
    }
    
    @Override
    public void viewGrades() {
        System.out.println("\n" + name + "'s Grades:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println(enrolledCourses[i].getCourseId() + ": " + grades[i]);
        }
        System.out.println("Current GPA: " + String.format("%.2f", gpa));
    }
    
    @Override
    public double calculateGPA() {
        double totalPoints = 0;
        int gradedCourses = 0;
        
        for (int i = 0; i < courseCount; i++) {
            double points = getGradePoints(grades[i]);
            if (points >= 0) {
                totalPoints += points;
                gradedCourses++;
            }
        }
        
        gpa = gradedCourses > 0 ? totalPoints / gradedCourses : 0.0;
        return gpa;
    }
    
    private double getGradePoints(String grade) {
        switch (grade) {
            case "A+": case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return -1; // Not yet graded
        }
    }
    
    public void promoteToNextYear() {
        switch (yearLevel) {
            case "Freshman": yearLevel = "Sophomore"; break;
            case "Sophomore": yearLevel = "Junior"; break;
            case "Junior": yearLevel = "Senior"; break;
            case "Senior": yearLevel = "Graduate"; break;
        }
        System.out.println(name + " promoted to " + yearLevel);
    }
    
    public String getStudentId() { return studentId; }
    public String getMajor() { return major; }
}

// Faculty class implementing Teachable interface
class Faculty extends Person implements Teachable, Administrative {
    private String facultyId;
    private String department;
    private String position;
    private Course[] assignedCourses;
    private int courseLoad;
    private double salary;
    private static final int MAX_TEACHING_LOAD = 5;
    
    public Faculty(String personId, String name, String email, String facultyId, 
                   String department, String position) {
        super(personId, name, email);
        this.facultyId = facultyId;
        this.department = department;
        this.position = position;
        this.assignedCourses = new Course[MAX_TEACHING_LOAD];
        this.courseLoad = 0;
        this.salary = calculateBaseSalary(position);
    }
    
    private double calculateBaseSalary(String position) {
        switch (position) {
            case "Professor": return 95000;
            case "Associate Professor": return 75000;
            case "Assistant Professor": return 60000;
            case "Lecturer": return 45000;
            default: return 40000;
        }
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n=== Faculty Details ===");
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Teaching Load: " + courseLoad + "/" + MAX_TEACHING_LOAD);
    }
    
    @Override
    public void performRole() {
        System.out.println(name + " is teaching courses and conducting research");
    }
    
    // Teachable implementation
    @Override
    public void assignCourse(Course course) {
        if (courseLoad < MAX_TEACHING_LOAD && canTeach(course)) {
            assignedCourses[courseLoad] = course;
            courseLoad++;
            course.setInstructor(this);
            System.out.println("✓ " + name + " assigned to teach " + course.getCourseName());
        } else {
            System.out.println("✗ Cannot assign course to " + name + " (teaching load full)");
        }
    }
    
    @Override
    public void unassignCourse(Course course) {
        for (int i = 0; i < courseLoad; i++) {
            if (assignedCourses[i].equals(course)) {
                // Shift courses down
                for (int j = i; j < courseLoad - 1; j++) {
                    assignedCourses[j] = assignedCourses[j + 1];
                }
                courseLoad--;
                course.setInstructor(null);
                System.out.println("✓ " + name + " unassigned from " + course.getCourseName());
                return;
            }
        }
        System.out.println("✗ " + name + " is not teaching " + course.getCourseName());
    }
    
    @Override
    public void viewTeachingLoad() {
        System.out.println("\n" + name + "'s Teaching Load:");
        for (int i = 0; i < courseLoad; i++) {
            System.out.println("- " + assignedCourses[i].getCourseName() + 
                             " (Students: " + assignedCourses[i].getEnrollmentCount() + ")");
        }
        System.out.println("Total courses: " + courseLoad);
    }
    
    // Administrative implementation
    @Override
    public void generateReport() {
        System.out.println("\n=== Teaching Report for " + name + " ===");
        viewTeachingLoad();
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
    }
    
    @Override
    public void manageRecords() {
        System.out.println(name + " is managing student academic records");
    }
    
    @Override
    public void processPayments() {
        System.out.println(name + " is processing tuition payments");
    }
    
    public void promoteFaculty(String newPosition) {
        this.position = newPosition;
        this.salary = calculateBaseSalary(newPosition);
        System.out.println(name + " promoted to " + newPosition + 
                         " with new salary: $" + String.format("%.2f", salary));
    }
    
    public String getFacultyId() { return facultyId; }
    public String getDepartment() { return department; }
}

// Administrator class implementing Administrative interface
class Administrator extends Person implements Administrative {
    private String adminId;
    private String department;
    private String role;
    private double salary;
    
    public Administrator(String personId, String name, String email, 
                        String adminId, String department, String role) {
        super(personId, name, email);
        this.adminId = adminId;
        this.department = department;
        this.role = role;
        this.salary = calculateAdminSalary(role);
    }
    
    private double calculateAdminSalary(String role) {
        switch (role) {
            case "Dean": return 120000;
            case "Department Head": return 90000;
            case "Registrar": return 65000;
            case "Academic Advisor": return 55000;
            default: return 45000;
        }
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n=== Administrator Details ===");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Role: " + role);
        System.out.println("Salary: $" + String.format("%.2f", salary));
    }
    
    @Override
    public void performRole() {
        System.out.println(name + " is managing administrative tasks");
    }
    
    @Override
    public void generateReport() {
        System.out.println("\n=== Administrative Report by " + name + " ===");
        System.out.println("Department: " + department);
        System.out.println("Role: " + role);
        System.out.println("Report generated on: " + new java.util.Date());
    }
    
    @Override
    public void manageRecords() {
        System.out.println(name + " is managing university records and databases");
    }
    
    @Override
    public void processPayments() {
        System.out.println(name + " is processing university financial transactions");
    }
    
    public void conductMeeting(String topic) {
        System.out.println(name + " is conducting a meeting on: " + topic);
    }
    
    public String getAdminId() { return adminId; }
    public String getRole() { return role; }
}

// Course class
class Course {
    private String courseId;
    private String courseName;
    private String department;
    private int credits;
    private int capacity;
    private Student[] enrolledStudents;
    private int enrollmentCount;
    private Faculty instructor;
    private String schedule;
    private String room;
    
    public Course(String courseId, String courseName, String department, 
                  int credits, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.department = department;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledStudents = new Student[capacity];
        this.enrollmentCount = 0;
    }
    
    public void addStudent(Student student) {
        if (enrollmentCount < capacity) {
            enrolledStudents[enrollmentCount] = student;
            enrollmentCount++;
        }
    }
    
    public void removeStudent(Student student) {
        for (int i = 0; i < enrollmentCount; i++) {
            if (enrolledStudents[i].equals(student)) {
                // Shift students down
                for (int j = i; j < enrollmentCount - 1; j++) {
                    enrolledStudents[j] = enrolledStudents[j + 1];
                }
                enrollmentCount--;
                break;
            }
        }
    }
    
    public void displayCourseInfo() {
        System.out.println("\n=== Course Information ===");
        System.out.println("Course ID: " + courseId);
        System.out.println("Name: " + courseName);
        System.out.println("Department: " + department);
        System.out.println("Credits: " + credits);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled: " + enrollmentCount);
        System.out.println("Instructor: " + (instructor != null ? instructor.getName() : "TBA"));
        System.out.println("Schedule: " + (schedule != null ? schedule : "TBA"));
        System.out.println("Room: " + (room != null ? room : "TBA"));
    }
    
    public void listEnrolledStudents() {
        System.out.println("\nEnrolled Students in " + courseName + ":");
        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println("- " + enrolledStudents[i].getName() + 
                             " (" + enrolledStudents[i].getStudentId() + ")");
        }
    }
    
    public boolean hasCapacity() {
        return enrollmentCount < capacity;
    }
    
    public void setSchedule(String schedule, String room) {
        this.schedule = schedule;
        this.room = room;
        System.out.println("Schedule set for " + courseName + ": " + schedule + " in " + room);
    }
    
    // Getters and setters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getEnrollmentCount() { return enrollmentCount; }
    public Faculty getInstructor() { return instructor; }
    public void setInstructor(Faculty instructor) { this.instructor = instructor; }
}

// University Management System
class UniversitySystem {
    private Person[] allPersons;
    private Course[] allCourses;
    private int personCount;
    private int courseCount;
    private static final int MAX_PERSONS = 100;
    private static final int MAX_COURSES = 50;
    
    public UniversitySystem() {
        allPersons = new Person[MAX_PERSONS];
        allCourses = new Course[MAX_COURSES];
        personCount = 0;
        courseCount = 0;
    }
    
    public void addPerson(Person person) {
        if (personCount < MAX_PERSONS) {
            allPersons[personCount++] = person;
            System.out.println("Added " + person.getClass().getSimpleName() + ": " + person.getName());
        }
    }
    
    public void addCourse(Course course) {
        if (courseCount < MAX_COURSES) {
            allCourses[courseCount++] = course;
            System.out.println("Added course: " + course.getCourseName());
        }
    }
    
    public void displayAllPersons() {
        System.out.println("\n=== All University Personnel ===");
        for (int i = 0; i < personCount; i++) {
            allPersons[i].displayDetails();
        }
    }
    
    public void displayAllCourses() {
        System.out.println("\n=== All Courses ===");
        for (int i = 0; i < courseCount; i++) {
            allCourses[i].displayCourseInfo();
        }
    }
    
    public Student findStudent(String studentId) {
        for (int i = 0; i < personCount; i++) {
            if (allPersons[i] instanceof Student) {
                Student student = (Student) allPersons[i];
                if (student.getStudentId().equals(studentId)) {
                    return student;
                }
            }
        }
        return null;
    }
    
    public Faculty findFaculty(String facultyId) {
        for (int i = 0; i < personCount; i++) {
            if (allPersons[i] instanceof Faculty) {
                Faculty faculty = (Faculty) allPersons[i];
                if (faculty.getFacultyId().equals(facultyId)) {
                    return faculty;
                }
            }
        }
        return null;
    }
    
    public Course findCourse(String courseId) {
        for (int i = 0; i < courseCount; i++) {
            if (allCourses[i].getCourseId().equals(courseId)) {
                return allCourses[i];
            }
        }
        return null;
    }
    
    public void generateUniversityReport() {
        System.out.println("\n=== University System Report ===");
        System.out.println("Total Persons: " + personCount);
        System.out.println("Total Courses: " + courseCount);
        
        int studentCount = 0, facultyCount = 0, adminCount = 0;
        for (int i = 0; i < personCount; i++) {
            if (allPersons[i] instanceof Student) studentCount++;
            else if (allPersons[i] instanceof Faculty) facultyCount++;
            else if (allPersons[i] instanceof Administrator) adminCount++;
        }
        
        System.out.println("Students: " + studentCount);
        System.out.println("Faculty: " + facultyCount);
        System.out.println("Administrators: " + adminCount);
        
        System.out.println("\nReport generated on: " + new java.util.Date());
    }
}

public class Q11_UniversityManagementSystem {
    
    public static void main(String[] args) {
        System.out.println("=== University Management System ===");
        
        // Create university system
        UniversitySystem university = new UniversitySystem();
        
        // 1. Create Students
        System.out.println("\n1. Creating Students:");
        Student student1 = new Student("P001", "Alice Johnson", "alice@email.com", 
                                     "S2024001", "Computer Science");
        Student student2 = new Student("P002", "Bob Smith", "bob@email.com", 
                                     "S2024002", "Mathematics");
        Student student3 = new Student("P003", "Carol Davis", "carol@email.com", 
                                     "S2024003", "Physics");
        
        university.addPerson(student1);
        university.addPerson(student2);
        university.addPerson(student3);
        
        // 2. Create Faculty
        System.out.println("\n2. Creating Faculty:");
        Faculty faculty1 = new Faculty("P004", "Dr. John Wilson", "wilson@email.com", 
                                     "F001", "Computer Science", "Professor");
        Faculty faculty2 = new Faculty("P005", "Dr. Sarah Brown", "brown@email.com", 
                                     "F002", "Mathematics", "Associate Professor");
        Faculty faculty3 = new Faculty("P006", "Dr. Mike Chen", "chen@email.com", 
                                     "F003", "Physics", "Assistant Professor");
        
        university.addPerson(faculty1);
        university.addPerson(faculty2);
        university.addPerson(faculty3);
        
        // 3. Create Administrators
        System.out.println("\n3. Creating Administrators:");
        Administrator admin1 = new Administrator("P007", "Dr. Lisa Anderson", "anderson@email.com", 
                                               "A001", "Academic Affairs", "Dean");
        Administrator admin2 = new Administrator("P008", "Mark Taylor", "taylor@email.com", 
                                               "A002", "Student Services", "Registrar");
        
        university.addPerson(admin1);
        university.addPerson(admin2);
        
        // 4. Create Courses
        System.out.println("\n4. Creating Courses:");
        Course course1 = new Course("CS101", "Introduction to Programming", "Computer Science", 3, 30);
        Course course2 = new Course("MATH201", "Calculus II", "Mathematics", 4, 25);
        Course course3 = new Course("PHYS101", "General Physics", "Physics", 4, 20);
        Course course4 = new Course("CS201", "Data Structures", "Computer Science", 3, 25);
        
        university.addCourse(course1);
        university.addCourse(course2);
        university.addCourse(course3);
        university.addCourse(course4);
        
        // 5. Assign faculty to courses
        System.out.println("\n5. Assigning Faculty to Courses:");
        faculty1.assignCourse(course1);
        faculty1.assignCourse(course4);
        faculty2.assignCourse(course2);
        faculty3.assignCourse(course3);
        
        // Set course schedules
        course1.setSchedule("MWF 9:00-10:00", "Room 101");
        course2.setSchedule("TTh 11:00-12:30", "Room 201");
        course3.setSchedule("MWF 2:00-3:30", "Lab 301");
        course4.setSchedule("TTh 9:30-11:00", "Room 102");
        
        // 6. Student enrollments
        System.out.println("\n6. Student Enrollments:");
        student1.enroll(course1);
        student1.enroll(course2);
        student1.enroll(course4);
        
        student2.enroll(course1);
        student2.enroll(course2);
        student2.enroll(course3);
        
        student3.enroll(course3);
        student3.enroll(course2);
        
        // 7. Display system information
        System.out.println("\n7. System Information:");
        university.displayAllPersons();
        university.displayAllCourses();
        
        // 8. Demonstrate polymorphism
        System.out.println("\n8. Polymorphic Operations:");
        Person[] people = {student1, faculty1, admin1};
        for (Person person : people) {
            person.performRole();
            person.displayDetails();
        }
        
        // 9. Interface operations
        System.out.println("\n9. Interface-Specific Operations:");
        
        // Enrollable operations
        student1.viewEnrollments();
        
        // Teachable operations
        faculty1.viewTeachingLoad();
        
        // Administrative operations
        admin1.generateReport();
        admin2.manageRecords();
        
        // 10. Grading operations
        System.out.println("\n10. Grading Operations:");
        student1.assignGrade("CS101", "A");
        student1.assignGrade("MATH201", "B+");
        student1.assignGrade("CS201", "A-");
        student1.viewGrades();
        
        student2.assignGrade("CS101", "B");
        student2.assignGrade("MATH201", "A-");
        student2.assignGrade("PHYS101", "B+");
        student2.viewGrades();
        
        // 11. Advanced operations
        System.out.println("\n11. Advanced Operations:");
        
        // Promote student
        student1.promoteToNextYear();
        
        // Faculty promotion
        faculty3.promoteFaculty("Associate Professor");
        
        // Schedule appointment
        admin1.scheduleAppointment("2024-03-15", "Academic Planning Meeting");
        
        // Course enrollment details
        course1.listEnrolledStudents();
        course2.listEnrolledStudents();
        
        // 12. System statistics
        System.out.println("\n12. System Statistics:");
        university.generateUniversityReport();
        
        // 13. Demonstrate multiple interface implementation
        System.out.println("\n13. Multiple Interface Implementation:");
        System.out.println("Student implements: Enrollable, Gradable");
        System.out.println("Faculty implements: Teachable, Administrative");
        System.out.println("Administrator implements: Administrative");
        
        // 14. Interface type checking
        System.out.println("\n14. Dynamic Interface Detection:");
        Person[] allPeople = {student1, faculty1, admin1};
        
        for (Person person : allPeople) {
            System.out.println("\n" + person.getName() + " (" + person.getClass().getSimpleName() + "):");
            
            if (person instanceof Enrollable) {
                System.out.println("  ✓ Can enroll in courses");
            }
            if (person instanceof Teachable) {
                System.out.println("  ✓ Can teach courses");
            }
            if (person instanceof Gradable) {
                System.out.println("  ✓ Can receive grades");
            }
            if (person instanceof Administrative) {
                System.out.println("  ✓ Has administrative privileges");
            }
        }
        
        System.out.println("\n=== University Management System Summary ===");
        System.out.println("✓ Abstract classes provide common structure");
        System.out.println("✓ Multiple interfaces enable role-based capabilities");
        System.out.println("✓ Inheritance supports specialization");
        System.out.println("✓ Polymorphism enables uniform treatment");
        System.out.println("✓ Encapsulation protects data integrity");
        System.out.println("✓ Final methods ensure security");
        System.out.println("✓ Protected access supports inheritance");
        System.out.println("✓ System demonstrates comprehensive OOP design");
    }
}