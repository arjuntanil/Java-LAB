package c02;
import java.util.Scanner;

// Program demonstrating Returning Objects from Methods
class Time {
    private int hours;
    private int minutes;
    
    // Constructor
    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        normalizeTime(); // Ensure valid time format
    }
    
    // Default constructor
    public Time() {
        this.hours = 0;
        this.minutes = 0;
    }
    
    // Method to normalize time (handle overflow)
    private void normalizeTime() {
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes = minutes % 60;
        }
        if (hours >= 24) {
            hours = hours % 24;
        }
    }
    
    // Method that returns a new Time object (sum of current time and parameter)
    public Time addTime(Time other) {
        int totalHours = this.hours + other.hours;
        int totalMinutes = this.minutes + other.minutes;
        
        Time result = new Time(totalHours, totalMinutes);
        System.out.println("Created new Time object: " + result.toString());
        return result; // Returning object
    }
    
    // Method that returns a new Time object (difference between times)
    public Time subtractTime(Time other) {
        int thisTimeInMinutes = this.hours * 60 + this.minutes;
        int otherTimeInMinutes = other.hours * 60 + other.minutes;
        
        int differenceInMinutes = Math.abs(thisTimeInMinutes - otherTimeInMinutes);
        
        int resultHours = differenceInMinutes / 60;
        int resultMinutes = differenceInMinutes % 60;
        
        return new Time(resultHours, resultMinutes); // Returning object
    }
    
    // Method that returns a copy of current object
    public Time getCopy() {
        return new Time(this.hours, this.minutes); // Returning object copy
    }
    
    // Method that returns Time object after adding minutes
    public Time addMinutes(int minutesToAdd) {
        return new Time(this.hours, this.minutes + minutesToAdd); // Returning object
    }
    
    // Method that returns Time object after adding hours
    public Time addHours(int hoursToAdd) {
        return new Time(this.hours + hoursToAdd, this.minutes); // Returning object
    }
    
    // Static method that returns the later of two times
    public static Time getLaterTime(Time time1, Time time2) {
        int time1InMinutes = time1.hours * 60 + time1.minutes;
        int time2InMinutes = time2.hours * 60 + time2.minutes;
        
        if (time1InMinutes >= time2InMinutes) {
            return new Time(time1.hours, time1.minutes); // Returning object
        } else {
            return new Time(time2.hours, time2.minutes); // Returning object
        }
    }
    
    // Static method that returns Time object representing current system time
    public static Time getCurrentTime() {
        // Simulated current time for demonstration
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int currentHour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int currentMinute = cal.get(java.util.Calendar.MINUTE);
        
        return new Time(currentHour, currentMinute); // Returning object
    }
    
    // Method to display time
    public void displayTime() {
        System.out.printf("%02d:%02d\n", hours, minutes);
    }
    
    // toString method for easy printing
    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }
    
    // Getters
    public int getHours() {
        return hours;
    }
    
    public int getMinutes() {
        return minutes;
    }
}

// Helper class demonstrating factory methods that return objects
class TimeFactory {
    
    // Factory method that returns Time object for morning
    public static Time createMorningTime() {
        return new Time(9, 0); // 9:00 AM
    }
    
    // Factory method that returns Time object for afternoon
    public static Time createAfternoonTime() {
        return new Time(13, 0); // 1:00 PM
    }
    
    // Factory method that returns Time object for evening
    public static Time createEveningTime() {
        return new Time(18, 0); // 6:00 PM
    }
    
    // Factory method that returns random time
    public static Time createRandomTime() {
        int randomHour = (int) (Math.random() * 24);
        int randomMinute = (int) (Math.random() * 60);
        return new Time(randomHour, randomMinute);
    }
}

public class Q11_ReturningObjects {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Returning Objects Demonstration ===");
        
        // Create initial Time objects
        System.out.println("\nCreating Time objects:");
        System.out.print("Enter first time - Hours: ");
        int hours1 = scanner.nextInt();
        System.out.print("Enter first time - Minutes: ");
        int minutes1 = scanner.nextInt();
        Time time1 = new Time(hours1, minutes1);
        
        System.out.print("Enter second time - Hours: ");
        int hours2 = scanner.nextInt();
        System.out.print("Enter second time - Minutes: ");
        int minutes2 = scanner.nextInt();
        Time time2 = new Time(hours2, minutes2);
        
        System.out.println("\nCreated times:");
        System.out.print("Time 1: ");
        time1.displayTime();
        System.out.print("Time 2: ");
        time2.displayTime();
        
        // Demonstrate returning objects from instance methods
        System.out.println("\n1. Adding Times (Returning Object from Instance Method):");
        Time sumTime = time1.addTime(time2); // Method returns Time object
        System.out.print("Sum of times: ");
        sumTime.displayTime();
        
        // Demonstrate returning object from method - subtraction
        System.out.println("\n2. Time Difference (Returning Object):");
        Time diffTime = time1.subtractTime(time2);
        System.out.print("Difference between times: ");
        diffTime.displayTime();
        
        // Demonstrate returning copy of object
        System.out.println("\n3. Getting Copy of Object:");
        Time copyTime = time1.getCopy();
        System.out.print("Copy of time1: ");
        copyTime.displayTime();
        System.out.println("Original and copy are different objects: " + (time1 != copyTime));
        
        // Demonstrate method chaining with returned objects
        System.out.println("\n4. Method Chaining with Returned Objects:");
        Time chainedTime = time1.addHours(2).addMinutes(30);
        System.out.print("Time1 + 2 hours + 30 minutes: ");
        chainedTime.displayTime();
        
        // Demonstrate static methods returning objects
        System.out.println("\n5. Static Methods Returning Objects:");
        Time laterTime = Time.getLaterTime(time1, time2);
        System.out.print("Later time between time1 and time2: ");
        laterTime.displayTime();
        
        // Get current system time
        Time currentTime = Time.getCurrentTime();
        System.out.print("Current system time: ");
        currentTime.displayTime();
        
        // Demonstrate factory methods
        System.out.println("\n6. Factory Methods Returning Objects:");
        Time morningTime = TimeFactory.createMorningTime();
        Time afternoonTime = TimeFactory.createAfternoonTime();
        Time eveningTime = TimeFactory.createEveningTime();
        Time randomTime = TimeFactory.createRandomTime();
        
        System.out.print("Morning time: ");
        morningTime.displayTime();
        System.out.print("Afternoon time: ");
        afternoonTime.displayTime();
        System.out.print("Evening time: ");
        eveningTime.displayTime();
        System.out.print("Random time: ");
        randomTime.displayTime();
        
        // Demonstrate complex operations with returned objects
        System.out.println("\n7. Complex Operations with Returned Objects:");
        
        // Create meeting schedule
        Time meetingStart = TimeFactory.createMorningTime();
        Time meetingDuration = new Time(1, 30); // 1 hour 30 minutes
        Time meetingEnd = meetingStart.addTime(meetingDuration);
        
        System.out.println("Meeting Schedule:");
        System.out.print("Start time: ");
        meetingStart.displayTime();
        System.out.print("Duration: ");
        meetingDuration.displayTime();
        System.out.print("End time: ");
        meetingEnd.displayTime();
        
        // Calculate time until meeting
        Time timeUntilMeeting = meetingStart.subtractTime(currentTime);
        System.out.print("Time until meeting: ");
        timeUntilMeeting.displayTime();
        
        System.out.println("\n=== Returning Objects Summary ===");
        System.out.println("✓ Methods can return objects of any class");
        System.out.println("✓ Returned objects can be newly created or existing ones");
        System.out.println("✓ Static methods can also return objects");
        System.out.println("✓ Factory methods are useful for creating specialized objects");
        System.out.println("✓ Method chaining is possible with methods that return objects");
        System.out.println("✓ Returned objects enable complex operations and data manipulation");
        
        scanner.close();
    }
}