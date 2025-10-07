package c03;

// Q15: Hospital Staff System
// Comprehensive hospital management system demonstrating advanced OOP concepts

// Abstract base class for all hospital personnel
abstract class HospitalPersonnel {
    protected String personnelId;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String department;
    protected double salary;
    protected java.util.Date hireDate;
    protected boolean isActive;
    protected String shift; // morning, evening, night
    protected int yearsOfExperience;
    
    // Static variable to track total personnel
    protected static int totalPersonnel = 0;
    
    public HospitalPersonnel(String personnelId, String name, String email, String department) {
        this.personnelId = personnelId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.hireDate = new java.util.Date();
        this.isActive = true;
        this.shift = "morning";
        this.yearsOfExperience = 0;
        totalPersonnel++;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract void performDuties();
    public abstract void displayPersonnelDetails();
    public abstract String getPersonnelType();
    public abstract double calculateSalary();
    
    // Concrete methods available to all personnel
    public final void clockIn() {
        if (isActive) {
            System.out.println(name + " clocked in for " + shift + " shift");
        } else {
            System.out.println("Personnel is not active");
        }
    }
    
    public final void clockOut() {
        if (isActive) {
            System.out.println(name + " clocked out from " + shift + " shift");
        }
    }
    
    public final void displayBasicInfo() {
        System.out.println("Personnel ID: " + personnelId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Personnel Type: " + getPersonnelType());
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Shift: " + shift);
        System.out.println("Years of Experience: " + yearsOfExperience);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
    }
    
    public final void updateSalary() {
        this.salary = calculateSalary();
        System.out.println("Salary updated for " + name + ": $" + String.format("%.2f", salary));
    }
    
    // Static method
    public static int getTotalPersonnel() {
        return totalPersonnel;
    }
    
    // Getters and setters
    public String getPersonnelId() { return personnelId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public boolean isActive() { return isActive; }
    public void setShift(String shift) { this.shift = shift; }
    public void setYearsOfExperience(int years) { this.yearsOfExperience = years; }
}

// Interface for medical treatment capabilities
interface MedicalTreatment {
    void diagnosePatient(Patient patient);
    void prescribeMedication(Patient patient, String medication);
    void performProcedure(Patient patient, String procedure);
    
    default void updatePatientRecord(Patient patient, String notes) {
        System.out.println("Updating medical record for " + patient.getName() + ": " + notes);
    }
    
    default boolean isQualifiedForProcedure(String procedure) {
        return true; // Default implementation
    }
}

// Interface for administrative tasks
interface Administrative {
    void scheduleAppointment(Patient patient, String date);
    void processInsurance(Patient patient);
    void generateReport();
    void manageRecords();
    
    default void sendNotification(String recipient, String message) {
        System.out.println("Notification sent to " + recipient + ": " + message);
    }
}

// Interface for emergency response
interface EmergencyResponse {
    void respondToEmergency(String emergencyType);
    void performCPR();
    void stabilizePatient(Patient patient);
    
    default void callEmergencyServices() {
        System.out.println("🚨 Emergency services have been contacted");
    }
    
    default boolean isEmergencyCertified() {
        return true;
    }
}

// Interface for surgical procedures
interface SurgicalCapable {
    void performSurgery(Patient patient, String surgeryType);
    void assistInSurgery(String surgeonName, String surgeryType);
    boolean isSurgicallyQualified();
    
    default void prepareSurgicalSuite() {
        System.out.println("🏥 Surgical suite being prepared");
    }
    
    default void postOperativeCare(Patient patient) {
        System.out.println("Providing post-operative care for " + patient.getName());
    }
}

// Interface for pharmaceutical management
interface PharmaceuticalAccess {
    void dispenseMedication(String medication, String dosage);
    void checkDrugInteractions(String[] medications);
    void manageInventory();
    
    default boolean isControlledSubstance(String medication) {
        return medication.contains("morphine") || medication.contains("oxycodone");
    }
    
    default void logMedicationDispensed(String medication, String patient) {
        System.out.println("Medication log: " + medication + " dispensed to " + patient);
    }
}

// Doctor class implementing multiple interfaces
class Doctor extends HospitalPersonnel implements MedicalTreatment, SurgicalCapable, EmergencyResponse {
    private String specialization;
    private String medicalLicense;
    private int patientsToday;
    private boolean isBoardCertified;
    private String[] certifications;
    private Patient[] assignedPatients;
    private int patientCount;
    private static final int MAX_PATIENTS = 20;
    
    public Doctor(String personnelId, String name, String email, String department, 
                  String specialization, String medicalLicense) {
        super(personnelId, name, email, department);
        this.specialization = specialization;
        this.medicalLicense = medicalLicense;
        this.patientsToday = 0;
        this.isBoardCertified = true;
        this.certifications = new String[]{"Basic Life Support", "Advanced Cardiac Life Support"};
        this.assignedPatients = new Patient[MAX_PATIENTS];
        this.patientCount = 0;
        this.salary = calculateSalary();
    }
    
    @Override
    public void performDuties() {
        System.out.println("Dr. " + name + " is performing medical duties:");
        System.out.println("- Examining patients");
        System.out.println("- Reviewing medical charts");
        System.out.println("- Consulting with colleagues");
        System.out.println("- Patients seen today: " + patientsToday);
    }
    
    @Override
    public void displayPersonnelDetails() {
        System.out.println("\n=== Doctor Details ===");
        displayBasicInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Medical License: " + medicalLicense);
        System.out.println("Board Certified: " + (isBoardCertified ? "Yes" : "No"));
        System.out.println("Patients Today: " + patientsToday);
        System.out.println("Assigned Patients: " + patientCount);
        System.out.print("Certifications: ");
        for (int i = 0; i < certifications.length; i++) {
            System.out.print(certifications[i]);
            if (i < certifications.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    @Override
    public String getPersonnelType() {
        return "Doctor";
    }
    
    @Override
    public double calculateSalary() {
        double baseSalary = 180000; // Base salary for doctors
        
        // Adjust based on specialization
        switch (specialization.toLowerCase()) {
            case "neurosurgery":
            case "cardiothoracic surgery":
                baseSalary += 100000;
                break;
            case "orthopedic surgery":
            case "plastic surgery":
                baseSalary += 80000;
                break;
            case "emergency medicine":
            case "anesthesiology":
                baseSalary += 60000;
                break;
            case "internal medicine":
            case "family medicine":
                baseSalary += 40000;
                break;
        }
        
        // Experience bonus
        baseSalary += yearsOfExperience * 5000;
        
        return baseSalary;
    }
    
    // MedicalTreatment implementation
    @Override
    public void diagnosePatient(Patient patient) {
        System.out.println("🩺 Dr. " + name + " diagnosing patient: " + patient.getName());
        System.out.println("Examining symptoms: " + patient.getCurrentCondition());
        
        // Simulate diagnosis based on symptoms
        String diagnosis = generateDiagnosis(patient.getCurrentCondition());
        patient.setDiagnosis(diagnosis);
        
        System.out.println("Diagnosis: " + diagnosis);
        updatePatientRecord(patient, "Diagnosed with " + diagnosis);
        patientsToday++;
    }
    
    private String generateDiagnosis(String symptoms) {
        if (symptoms.toLowerCase().contains("fever")) {
            return "Viral infection";
        } else if (symptoms.toLowerCase().contains("chest pain")) {
            return "Possible cardiac issue - requires further testing";
        } else if (symptoms.toLowerCase().contains("headache")) {
            return "Tension headache";
        } else {
            return "Requires further examination";
        }
    }
    
    @Override
    public void prescribeMedication(Patient patient, String medication) {
        System.out.println("💊 Dr. " + name + " prescribing " + medication + " to " + patient.getName());
        patient.addMedication(medication);
        updatePatientRecord(patient, "Prescribed " + medication);
    }
    
    @Override
    public void performProcedure(Patient patient, String procedure) {
        if (isQualifiedForProcedure(procedure)) {
            System.out.println("🏥 Dr. " + name + " performing " + procedure + " on " + patient.getName());
            updatePatientRecord(patient, "Procedure performed: " + procedure);
        } else {
            System.out.println("Not qualified to perform " + procedure);
        }
    }
    
    // SurgicalCapable implementation
    @Override
    public void performSurgery(Patient patient, String surgeryType) {
        if (isSurgicallyQualified()) {
            prepareSurgicalSuite();
            System.out.println("🔬 Dr. " + name + " performing " + surgeryType + " surgery on " + patient.getName());
            System.out.println("Surgery duration: 2-4 hours");
            postOperativeCare(patient);
            updatePatientRecord(patient, "Surgery completed: " + surgeryType);
        } else {
            System.out.println("Not qualified to perform surgery");
        }
    }
    
    @Override
    public void assistInSurgery(String surgeonName, String surgeryType) {
        System.out.println("Dr. " + name + " assisting Dr. " + surgeonName + " in " + surgeryType);
    }
    
    @Override
    public boolean isSurgicallyQualified() {
        return specialization.toLowerCase().contains("surgery") || specialization.equals("Emergency Medicine");
    }
    
    // EmergencyResponse implementation
    @Override
    public void respondToEmergency(String emergencyType) {
        System.out.println("🚨 Dr. " + name + " responding to " + emergencyType + " emergency");
        System.out.println("Arriving at scene immediately");
    }
    
    @Override
    public void performCPR() {
        System.out.println("Dr. " + name + " performing CPR");
        System.out.println("Chest compressions and rescue breathing administered");
    }
    
    @Override
    public void stabilizePatient(Patient patient) {
        System.out.println("Dr. " + name + " stabilizing patient: " + patient.getName());
        patient.setConditionStatus("Stable");
        updatePatientRecord(patient, "Patient stabilized");
    }
    
    public void assignPatient(Patient patient) {
        if (patientCount < MAX_PATIENTS) {
            assignedPatients[patientCount++] = patient;
            patient.setAttendingPhysician(name);
            System.out.println("Patient " + patient.getName() + " assigned to Dr. " + name);
        } else {
            System.out.println("Cannot assign more patients - maximum reached");
        }
    }
    
    public void dischargePatient(Patient patient) {
        for (int i = 0; i < patientCount; i++) {
            if (assignedPatients[i].equals(patient)) {
                patient.setConditionStatus("Discharged");
                // Shift patients down
                for (int j = i; j < patientCount - 1; j++) {
                    assignedPatients[j] = assignedPatients[j + 1];
                }
                patientCount--;
                System.out.println("Patient " + patient.getName() + " discharged by Dr. " + name);
                return;
            }
        }
    }
    
    public String getSpecialization() { return specialization; }
}

// Nurse class implementing multiple interfaces
class Nurse extends HospitalPersonnel implements MedicalTreatment, EmergencyResponse, PharmaceuticalAccess {
    private String nursingLicense;
    private String[] certifications;
    private int patientsToday;
    private boolean isHeadNurse;
    private String nursingLevel; // RN, LPN, CNA
    
    public Nurse(String personnelId, String name, String email, String department, 
                 String nursingLicense, String nursingLevel) {
        super(personnelId, name, email, department);
        this.nursingLicense = nursingLicense;
        this.nursingLevel = nursingLevel;
        this.patientsToday = 0;
        this.isHeadNurse = false;
        this.certifications = new String[]{"Basic Life Support", "Medication Administration"};
        this.salary = calculateSalary();
    }
    
    @Override
    public void performDuties() {
        System.out.println("Nurse " + name + " is performing nursing duties:");
        System.out.println("- Administering medications");
        System.out.println("- Monitoring vital signs");
        System.out.println("- Providing patient care");
        System.out.println("- Assisting doctors");
        System.out.println("- Patients cared for today: " + patientsToday);
    }
    
    @Override
    public void displayPersonnelDetails() {
        System.out.println("\n=== Nurse Details ===");
        displayBasicInfo();
        System.out.println("Nursing License: " + nursingLicense);
        System.out.println("Nursing Level: " + nursingLevel);
        System.out.println("Head Nurse: " + (isHeadNurse ? "Yes" : "No"));
        System.out.println("Patients Today: " + patientsToday);
        System.out.print("Certifications: ");
        for (int i = 0; i < certifications.length; i++) {
            System.out.print(certifications[i]);
            if (i < certifications.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    @Override
    public String getPersonnelType() {
        return "Nurse";
    }
    
    @Override
    public double calculateSalary() {
        double baseSalary = 70000; // Base salary for nurses
        
        // Adjust based on nursing level
        switch (nursingLevel) {
            case "RN": // Registered Nurse
                baseSalary += 15000;
                break;
            case "LPN": // Licensed Practical Nurse
                baseSalary += 5000;
                break;
            case "CNA": // Certified Nursing Assistant
                // Base salary
                break;
        }
        
        // Head nurse bonus
        if (isHeadNurse) {
            baseSalary += 20000;
        }
        
        // Experience bonus
        baseSalary += yearsOfExperience * 2000;
        
        return baseSalary;
    }
    
    // MedicalTreatment implementation (limited scope for nurses)
    @Override
    public void diagnosePatient(Patient patient) {
        System.out.println("Nurse " + name + " cannot diagnose patients - referring to doctor");
    }
    
    @Override
    public void prescribeMedication(Patient patient, String medication) {
        System.out.println("Nurse " + name + " cannot prescribe medication - doctor authorization required");
    }
    
    @Override
    public void performProcedure(Patient patient, String procedure) {
        if (isNursingProcedure(procedure)) {
            System.out.println("👩‍⚕️ Nurse " + name + " performing " + procedure + " for " + patient.getName());
            updatePatientRecord(patient, "Nursing procedure: " + procedure);
            patientsToday++;
        } else {
            System.out.println("Procedure requires doctor authorization");
        }
    }
    
    private boolean isNursingProcedure(String procedure) {
        return procedure.toLowerCase().contains("vital signs") ||
               procedure.toLowerCase().contains("injection") ||
               procedure.toLowerCase().contains("wound care") ||
               procedure.toLowerCase().contains("iv");
    }
    
    // EmergencyResponse implementation
    @Override
    public void respondToEmergency(String emergencyType) {
        System.out.println("🚨 Nurse " + name + " responding to " + emergencyType + " emergency");
        System.out.println("Providing immediate nursing care");
    }
    
    @Override
    public void performCPR() {
        System.out.println("Nurse " + name + " performing CPR");
        System.out.println("Following BLS protocols");
    }
    
    @Override
    public void stabilizePatient(Patient patient) {
        System.out.println("Nurse " + name + " providing stabilizing care for " + patient.getName());
        updatePatientRecord(patient, "Nursing stabilization provided");
    }
    
    // PharmaceuticalAccess implementation
    @Override
    public void dispenseMedication(String medication, String dosage) {
        if (isControlledSubstance(medication)) {
            System.out.println("Controlled substance - doctor supervision required");
        } else {
            System.out.println("💊 Nurse " + name + " dispensing " + medication + " (" + dosage + ")");
        }
    }
    
    @Override
    public void checkDrugInteractions(String[] medications) {
        System.out.println("Nurse " + name + " checking drug interactions");
        for (String med : medications) {
            System.out.println("- Checking: " + med);
        }
        System.out.println("No major interactions found");
    }
    
    @Override
    public void manageInventory() {
        System.out.println("Nurse " + name + " managing medication inventory");
        System.out.println("Checking stock levels and expiration dates");
    }
    
    public void monitorVitalSigns(Patient patient) {
        System.out.println("Nurse " + name + " monitoring vital signs for " + patient.getName());
        System.out.println("Blood pressure, heart rate, temperature recorded");
        updatePatientRecord(patient, "Vital signs monitored");
    }
    
    public void setHeadNurse(boolean isHeadNurse) {
        this.isHeadNurse = isHeadNurse;
        updateSalary();
    }
    
    public String getNursingLevel() { return nursingLevel; }
}

// Administrator class implementing Administrative interface
class Administrator extends HospitalPersonnel implements Administrative {
    private String adminRole; // HR, Finance, Operations, Medical Records
    private String[] responsibilities;
    private int appointmentsScheduledToday;
    private boolean hasManagementAccess;
    
    public Administrator(String personnelId, String name, String email, String department, String adminRole) {
        super(personnelId, name, email, department);
        this.adminRole = adminRole;
        this.appointmentsScheduledToday = 0;
        this.hasManagementAccess = adminRole.equals("Operations") || adminRole.equals("HR");
        this.responsibilities = setResponsibilities();
        this.salary = calculateSalary();
    }
    
    private String[] setResponsibilities() {
        switch (adminRole) {
            case "HR":
                return new String[]{"Employee Management", "Payroll", "Benefits Administration"};
            case "Finance":
                return new String[]{"Billing", "Insurance Processing", "Budget Management"};
            case "Operations":
                return new String[]{"Facility Management", "Scheduling", "Quality Assurance"};
            case "Medical Records":
                return new String[]{"Patient Records", "Data Management", "Compliance"};
            default:
                return new String[]{"General Administration"};
        }
    }
    
    @Override
    public void performDuties() {
        System.out.println("Administrator " + name + " performing " + adminRole + " duties:");
        for (String responsibility : responsibilities) {
            System.out.println("- " + responsibility);
        }
        System.out.println("- Appointments scheduled today: " + appointmentsScheduledToday);
    }
    
    @Override
    public void displayPersonnelDetails() {
        System.out.println("\n=== Administrator Details ===");
        displayBasicInfo();
        System.out.println("Admin Role: " + adminRole);
        System.out.println("Management Access: " + (hasManagementAccess ? "Yes" : "No"));
        System.out.println("Appointments Today: " + appointmentsScheduledToday);
        System.out.print("Responsibilities: ");
        for (int i = 0; i < responsibilities.length; i++) {
            System.out.print(responsibilities[i]);
            if (i < responsibilities.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    @Override
    public String getPersonnelType() {
        return "Administrator";
    }
    
    @Override
    public double calculateSalary() {
        double baseSalary = 60000; // Base salary for administrators
        
        // Adjust based on role
        switch (adminRole) {
            case "Operations":
                baseSalary += 25000;
                break;
            case "HR":
                baseSalary += 20000;
                break;
            case "Finance":
                baseSalary += 22000;
                break;
            case "Medical Records":
                baseSalary += 15000;
                break;
        }
        
        // Management access bonus
        if (hasManagementAccess) {
            baseSalary += 10000;
        }
        
        // Experience bonus
        baseSalary += yearsOfExperience * 1500;
        
        return baseSalary;
    }
    
    // Administrative implementation
    @Override
    public void scheduleAppointment(Patient patient, String date) {
        System.out.println("📅 Administrator " + name + " scheduling appointment");
        System.out.println("Patient: " + patient.getName());
        System.out.println("Date: " + date);
        appointmentsScheduledToday++;
        sendNotification(patient.getEmail(), "Appointment scheduled for " + date);
    }
    
    @Override
    public void processInsurance(Patient patient) {
        System.out.println("💳 Processing insurance for " + patient.getName());
        System.out.println("Insurance Provider: " + patient.getInsuranceProvider());
        System.out.println("Verification completed");
    }
    
    @Override
    public void generateReport() {
        System.out.println("\n=== " + adminRole + " Report by " + name + " ===");
        System.out.println("Department: " + department);
        System.out.println("Appointments scheduled today: " + appointmentsScheduledToday);
        System.out.println("Report generated on: " + new java.util.Date());
        
        for (String responsibility : responsibilities) {
            System.out.println("- " + responsibility + ": Operational");
        }
    }
    
    @Override
    public void manageRecords() {
        System.out.println("Administrator " + name + " managing hospital records");
        System.out.println("Updating databases and ensuring compliance");
    }
    
    public void processPayroll() {
        if (adminRole.equals("HR")) {
            System.out.println("Processing payroll for all hospital personnel");
            System.out.println("Calculating salaries, benefits, and deductions");
        } else {
            System.out.println("Payroll processing requires HR access");
        }
    }
    
    public String getAdminRole() { return adminRole; }
}

// Patient class
class Patient {
    private String patientId;
    private String name;
    private String email;
    private int age;
    private String currentCondition;
    private String diagnosis;
    private String conditionStatus; // Stable, Critical, Discharged
    private String attendingPhysician;
    private String insuranceProvider;
    private String[] medications;
    private int medicationCount;
    private java.util.Date admissionDate;
    private String roomNumber;
    
    public Patient(String patientId, String name, String email, int age, String currentCondition) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.currentCondition = currentCondition;
        this.conditionStatus = "Stable";
        this.medications = new String[10];
        this.medicationCount = 0;
        this.admissionDate = new java.util.Date();
        this.insuranceProvider = "General Health Insurance";
    }
    
    public void displayPatientInfo() {
        System.out.println("\n=== Patient Information ===");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("Current Condition: " + currentCondition);
        System.out.println("Diagnosis: " + (diagnosis != null ? diagnosis : "Pending"));
        System.out.println("Status: " + conditionStatus);
        System.out.println("Attending Physician: " + (attendingPhysician != null ? attendingPhysician : "Not assigned"));
        System.out.println("Insurance: " + insuranceProvider);
        System.out.println("Room: " + (roomNumber != null ? roomNumber : "Not assigned"));
        System.out.println("Admission Date: " + admissionDate);
        
        if (medicationCount > 0) {
            System.out.print("Medications: ");
            for (int i = 0; i < medicationCount; i++) {
                System.out.print(medications[i]);
                if (i < medicationCount - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }
    
    public void addMedication(String medication) {
        if (medicationCount < medications.length) {
            medications[medicationCount++] = medication;
        }
    }
    
    // Getters and setters
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCurrentCondition() { return currentCondition; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getConditionStatus() { return conditionStatus; }
    public void setConditionStatus(String status) { this.conditionStatus = status; }
    public void setAttendingPhysician(String physician) { this.attendingPhysician = physician; }
    public String getInsuranceProvider() { return insuranceProvider; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}

// Hospital Management System
class HospitalManagementSystem {
    private HospitalPersonnel[] personnel;
    private Patient[] patients;
    private int personnelCount;
    private int patientCount;
    private static final int MAX_PERSONNEL = 500;
    private static final int MAX_PATIENTS = 1000;
    
    public HospitalManagementSystem() {
        personnel = new HospitalPersonnel[MAX_PERSONNEL];
        patients = new Patient[MAX_PATIENTS];
        personnelCount = 0;
        patientCount = 0;
    }
    
    public void addPersonnel(HospitalPersonnel person) {
        if (personnelCount < MAX_PERSONNEL) {
            personnel[personnelCount++] = person;
            System.out.println("Personnel added: " + person.getName() + " (" + person.getPersonnelType() + ")");
        } else {
            System.out.println("Maximum personnel limit reached");
        }
    }
    
    public void addPatient(Patient patient) {
        if (patientCount < MAX_PATIENTS) {
            patients[patientCount++] = patient;
            System.out.println("Patient admitted: " + patient.getName());
        } else {
            System.out.println("Hospital at maximum capacity");
        }
    }
    
    public HospitalPersonnel findPersonnelById(String personnelId) {
        for (int i = 0; i < personnelCount; i++) {
            if (personnel[i].getPersonnelId().equals(personnelId)) {
                return personnel[i];
            }
        }
        return null;
    }
    
    public Patient findPatientById(String patientId) {
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getPatientId().equals(patientId)) {
                return patients[i];
            }
        }
        return null;
    }
    
    public void displayAllPersonnel() {
        System.out.println("\n=== All Hospital Personnel ===");
        for (int i = 0; i < personnelCount; i++) {
            personnel[i].displayPersonnelDetails();
        }
    }
    
    public void displayAllPatients() {
        System.out.println("\n=== All Patients ===");
        for (int i = 0; i < patientCount; i++) {
            patients[i].displayPatientInfo();
        }
    }
    
    public void generateHospitalReport() {
        System.out.println("\n=== Hospital Management Report ===");
        System.out.println("Total Personnel: " + personnelCount);
        System.out.println("Total Patients: " + patientCount);
        System.out.println("System Total Personnel Ever: " + HospitalPersonnel.getTotalPersonnel());
        
        int doctorCount = 0, nurseCount = 0, adminCount = 0;
        double totalSalaries = 0;
        
        for (int i = 0; i < personnelCount; i++) {
            String type = personnel[i].getPersonnelType();
            totalSalaries += personnel[i].salary;
            
            switch (type) {
                case "Doctor": doctorCount++; break;
                case "Nurse": nurseCount++; break;
                case "Administrator": adminCount++; break;
            }
        }
        
        System.out.println("Doctors: " + doctorCount);
        System.out.println("Nurses: " + nurseCount);
        System.out.println("Administrators: " + adminCount);
        System.out.println("Total Annual Salaries: $" + String.format("%.2f", totalSalaries));
        
        // Patient statistics
        int stablePatients = 0, criticalPatients = 0, dischargedPatients = 0;
        for (int i = 0; i < patientCount; i++) {
            String status = patients[i].getConditionStatus();
            switch (status) {
                case "Stable": stablePatients++; break;
                case "Critical": criticalPatients++; break;
                case "Discharged": dischargedPatients++; break;
            }
        }
        
        System.out.println("Stable Patients: " + stablePatients);
        System.out.println("Critical Patients: " + criticalPatients);
        System.out.println("Discharged Patients: " + dischargedPatients);
    }
    
    public void performShiftChange(String newShift) {
        System.out.println("\n🔄 Performing shift change to " + newShift + " shift");
        
        for (int i = 0; i < personnelCount; i++) {
            if (personnel[i].isActive()) {
                personnel[i].clockOut();
                personnel[i].setShift(newShift);
                personnel[i].clockIn();
            }
        }
        
        System.out.println("Shift change completed");
    }
    
    public void handleEmergency(String emergencyType) {
        System.out.println("\n🚨 EMERGENCY ALERT: " + emergencyType);
        
        // Get all emergency response personnel
        for (int i = 0; i < personnelCount; i++) {
            if (personnel[i] instanceof EmergencyResponse && personnel[i].isActive()) {
                ((EmergencyResponse) personnel[i]).respondToEmergency(emergencyType);
            }
        }
    }
}

public class Q15_HospitalStaffSystem {
    
    public static void main(String[] args) {
        System.out.println("=== Hospital Staff Management System ===");
        
        // Create hospital management system
        HospitalManagementSystem hospital = new HospitalManagementSystem();
        
        // 1. Create different types of hospital personnel
        System.out.println("\n1. Creating Hospital Personnel:");
        
        // Doctors
        Doctor doctor1 = new Doctor("D001", "Dr. Sarah Johnson", "sarah.johnson@hospital.com", 
                                   "Emergency Medicine", "Emergency Medicine", "EM12345");
        Doctor doctor2 = new Doctor("D002", "Dr. Michael Chen", "michael.chen@hospital.com", 
                                   "Cardiology", "Cardiothoracic Surgery", "CS67890");
        Doctor doctor3 = new Doctor("D003", "Dr. Emily Rodriguez", "emily.rodriguez@hospital.com", 
                                   "Pediatrics", "Family Medicine", "FM11111");
        
        // Set experience levels
        doctor1.setYearsOfExperience(10);
        doctor2.setYearsOfExperience(15);
        doctor3.setYearsOfExperience(8);
        
        // Nurses
        Nurse nurse1 = new Nurse("N001", "Lisa Anderson", "lisa.anderson@hospital.com", 
                                "Emergency Medicine", "RN98765", "RN");
        Nurse nurse2 = new Nurse("N002", "Robert Taylor", "robert.taylor@hospital.com", 
                                "Cardiology", "RN54321", "RN");
        Nurse nurse3 = new Nurse("N003", "Maria Garcia", "maria.garcia@hospital.com", 
                                "Pediatrics", "LPN22222", "LPN");
        
        nurse1.setYearsOfExperience(12);
        nurse2.setYearsOfExperience(6);
        nurse3.setYearsOfExperience(4);
        nurse1.setHeadNurse(true); // Lisa is head nurse
        
        // Administrators
        Administrator admin1 = new Administrator("A001", "David Wilson", "david.wilson@hospital.com", 
                                               "Administration", "HR");
        Administrator admin2 = new Administrator("A002", "Jennifer Brown", "jennifer.brown@hospital.com", 
                                               "Finance", "Finance");
        Administrator admin3 = new Administrator("A003", "Thomas Davis", "thomas.davis@hospital.com", 
                                               "Operations", "Operations");
        
        admin1.setYearsOfExperience(7);
        admin2.setYearsOfExperience(5);
        admin3.setYearsOfExperience(9);
        
        // Add personnel to hospital
        hospital.addPersonnel(doctor1);
        hospital.addPersonnel(doctor2);
        hospital.addPersonnel(doctor3);
        hospital.addPersonnel(nurse1);
        hospital.addPersonnel(nurse2);
        hospital.addPersonnel(nurse3);
        hospital.addPersonnel(admin1);
        hospital.addPersonnel(admin2);
        hospital.addPersonnel(admin3);
        
        // 2. Create patients
        System.out.println("\n2. Creating Patients:");
        
        Patient patient1 = new Patient("P001", "John Smith", "john.smith@email.com", 
                                     45, "Chest pain and shortness of breath");
        Patient patient2 = new Patient("P002", "Alice Williams", "alice.williams@email.com", 
                                     32, "Severe headache and fever");
        Patient patient3 = new Patient("P003", "Bobby Jones", "bobby.jones@email.com", 
                                     8, "High fever and cough");
        Patient patient4 = new Patient("P004", "Carol Lee", "carol.lee@email.com", 
                                     28, "Broken arm from fall");
        
        patient1.setRoomNumber("101");
        patient2.setRoomNumber("102");
        patient3.setRoomNumber("201");
        patient4.setRoomNumber("103");
        
        hospital.addPatient(patient1);
        hospital.addPatient(patient2);
        hospital.addPatient(patient3);
        hospital.addPatient(patient4);
        
        // 3. Display all personnel details
        System.out.println("\n3. All Hospital Personnel:");
        hospital.displayAllPersonnel();
        
        // 4. Demonstrate polymorphism
        System.out.println("\n4. Polymorphic Operations:");
        HospitalPersonnel[] staff = {doctor1, nurse1, admin1};
        
        for (HospitalPersonnel person : staff) {
            System.out.println("\nPersonnel: " + person.getName());
            person.performDuties();
            person.clockIn();
        }
        
        // 5. Medical treatment operations
        System.out.println("\n5. Medical Treatment Operations:");
        
        // Doctor treating patients
        doctor1.assignPatient(patient1);
        doctor1.diagnosePatient(patient1);
        doctor1.prescribeMedication(patient1, "Nitroglycerin");
        doctor1.performProcedure(patient1, "ECG");
        
        doctor3.assignPatient(patient3);
        doctor3.diagnosePatient(patient3);
        doctor3.prescribeMedication(patient3, "Acetaminophen");
        
        // Nurse caring for patients
        nurse1.performProcedure(patient2, "Vital signs check");
        nurse1.monitorVitalSigns(patient2);
        nurse1.dispenseMedication("Acetaminophen", "500mg");
        
        nurse3.performProcedure(patient3, "IV insertion");
        nurse3.monitorVitalSigns(patient3);
        
        // 6. Administrative operations
        System.out.println("\n6. Administrative Operations:");
        
        admin1.scheduleAppointment(patient4, "2024-09-20 10:00 AM");
        admin1.processInsurance(patient1);
        admin1.generateReport();
        admin1.processPayroll();
        
        admin2.processInsurance(patient2);
        admin2.generateReport();
        
        // 7. Emergency response
        System.out.println("\n7. Emergency Response:");
        hospital.handleEmergency("Cardiac arrest in Room 105");
        
        // Specific emergency procedures
        doctor1.performCPR();
        doctor1.stabilizePatient(patient1);
        nurse1.respondToEmergency("Cardiac arrest");
        
        // 8. Surgical operations
        System.out.println("\n8. Surgical Operations:");
        
        if (doctor2.isSurgicallyQualified()) {
            doctor2.performSurgery(patient1, "Cardiac bypass");
        }
        
        doctor1.assistInSurgery("Dr. Michael Chen", "Cardiac bypass");
        
        // 9. Pharmaceutical operations
        System.out.println("\n9. Pharmaceutical Operations:");
        
        nurse1.checkDrugInteractions(new String[]{"Aspirin", "Warfarin", "Lisinopril"});
        nurse1.dispenseMedication("Morphine", "10mg"); // Controlled substance
        nurse1.manageInventory();
        nurse2.dispenseMedication("Ibuprofen", "200mg");
        
        // 10. Interface type checking
        System.out.println("\n10. Dynamic Interface Detection:");
        HospitalPersonnel[] allStaff = {doctor1, nurse1, admin1};
        
        for (HospitalPersonnel person : allStaff) {
            System.out.println("\n" + person.getName() + " (" + person.getPersonnelType() + "):");
            
            if (person instanceof MedicalTreatment) {
                System.out.println("  ✓ Can provide medical treatment");
            }
            if (person instanceof SurgicalCapable) {
                System.out.println("  ✓ Surgical capabilities");
            }
            if (person instanceof EmergencyResponse) {
                System.out.println("  ✓ Emergency response trained");
            }
            if (person instanceof Administrative) {
                System.out.println("  ✓ Administrative privileges");
            }
            if (person instanceof PharmaceuticalAccess) {
                System.out.println("  ✓ Pharmaceutical access");
            }
        }
        
        // 11. Shift operations
        System.out.println("\n11. Shift Operations:");
        hospital.performShiftChange("evening");
        
        // 12. Patient updates
        System.out.println("\n12. Patient Care Updates:");
        
        // Update patient conditions
        patient1.setConditionStatus("Critical");
        patient2.setConditionStatus("Stable");
        patient3.setConditionStatus("Stable");
        
        // Discharge a patient
        doctor3.dischargePatient(patient3);
        
        // 13. Salary updates
        System.out.println("\n13. Salary Management:");
        
        // Update salaries based on experience
        doctor2.setYearsOfExperience(16); // Promotion
        doctor2.updateSalary();
        
        nurse2.setYearsOfExperience(7);
        nurse2.updateSalary();
        
        // 14. Display all patients
        System.out.println("\n14. Patient Information:");
        hospital.displayAllPatients();
        
        // 15. Generate comprehensive hospital report
        System.out.println("\n15. Hospital Management Report:");
        hospital.generateHospitalReport();
        
        // 16. Additional emergency scenario
        System.out.println("\n16. Multi-Emergency Response:");
        hospital.handleEmergency("Multiple vehicle accident - trauma patients incoming");
        
        System.out.println("\n=== Hospital Staff System Summary ===");
        System.out.println("✓ Abstract classes provide personnel structure");
        System.out.println("✓ Multiple interfaces enable specialized capabilities");
        System.out.println("✓ Polymorphism enables uniform staff management");
        System.out.println("✓ Inheritance supports role specialization");
        System.out.println("✓ Encapsulation protects sensitive medical data");
        System.out.println("✓ Final methods ensure critical operation security");
        System.out.println("✓ Interface default methods provide common utilities");
        System.out.println("✓ Comprehensive medical workflow management");
        System.out.println("✓ Emergency response coordination");
        System.out.println("✓ Administrative and clinical integration");
        System.out.println("✓ Patient care tracking and management");
        System.out.println("✓ Salary calculation based on role and experience");
        System.out.println("✓ System demonstrates real-world hospital operations");
    }
}