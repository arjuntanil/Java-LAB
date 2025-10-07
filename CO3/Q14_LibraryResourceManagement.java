package c03;

// Q14: Library Resource Management System
// Comprehensive library system demonstrating advanced OOP concepts

// Abstract base class for all library resources
abstract class LibraryResource {
    protected String resourceId;
    protected String title;
    protected String[] authors;
    protected String publisher;
    protected java.util.Date publicationDate;
    protected String isbn;
    protected boolean isAvailable;
    protected String location;
    protected double price;
    protected java.util.Date dateAcquired;
    
    // Static variable to track total resources
    protected static int totalResources = 0;
    
    public LibraryResource(String resourceId, String title, String[] authors, String publisher) {
        this.resourceId = resourceId;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.isAvailable = true;
        this.dateAcquired = new java.util.Date();
        totalResources++;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract void displayResourceDetails();
    public abstract String getResourceType();
    public abstract boolean canBeReserved();
    public abstract int getMaxLoanPeriod(); // in days
    
    // Concrete methods available to all resources
    public final void checkOut(LibraryMember member) {
        if (isAvailable && canBeReserved()) {
            isAvailable = false;
            System.out.println("Resource checked out: " + title);
            System.out.println("Due date: " + calculateDueDate());
        } else {
            System.out.println("Resource not available for checkout");
        }
    }
    
    public final void checkIn() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Resource returned: " + title);
        } else {
            System.out.println("Resource was not checked out");
        }
    }
    
    public final java.util.Date calculateDueDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DAY_OF_MONTH, getMaxLoanPeriod());
        return cal.getTime();
    }
    
    public final void displayBasicInfo() {
        System.out.println("Resource ID: " + resourceId);
        System.out.println("Title: " + title);
        System.out.print("Authors: ");
        for (int i = 0; i < authors.length; i++) {
            System.out.print(authors[i]);
            if (i < authors.length - 1) System.out.print(", ");
        }
        System.out.println();
        System.out.println("Publisher: " + publisher);
        System.out.println("ISBN: " + (isbn != null ? isbn : "N/A"));
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Location: " + (location != null ? location : "Main Floor"));
        System.out.println("Price: $" + String.format("%.2f", price));
    }
    
    // Static method
    public static int getTotalResources() {
        return totalResources;
    }
    
    // Getters and setters
    public String getResourceId() { return resourceId; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

// Interface for searchable resources
interface Searchable {
    boolean matchesKeyword(String keyword);
    boolean matchesAuthor(String author);
    boolean matchesCategory(String category);
    
    default boolean matchesTitle(String titleKeyword) {
        return getTitle().toLowerCase().contains(titleKeyword.toLowerCase());
    }
    
    String getTitle();
}

// Interface for renewable resources
interface Renewable {
    boolean renew();
    int getRenewalCount();
    int getMaxRenewals();
    
    default boolean canRenew() {
        return getRenewalCount() < getMaxRenewals();
    }
    
    default void displayRenewalInfo() {
        System.out.println("Renewals: " + getRenewalCount() + "/" + getMaxRenewals());
    }
}

// Interface for reservable resources
interface Reservable {
    void reserve(LibraryMember member);
    void cancelReservation();
    boolean isReserved();
    LibraryMember getReservedBy();
    
    default void displayReservationInfo() {
        if (isReserved()) {
            System.out.println("Reserved by: " + getReservedBy().getName());
        } else {
            System.out.println("No reservations");
        }
    }
}

// Interface for digital resources
interface DigitalAccess {
    void downloadResource();
    void streamResource();
    boolean requiresSpecialSoftware();
    String getAccessUrl();
    
    default void displayDigitalInfo() {
        System.out.println("Digital Access Available");
        System.out.println("Special Software Required: " + (requiresSpecialSoftware() ? "Yes" : "No"));
    }
}

// Interface for multimedia resources
interface MultimediaCapable {
    void playMedia();
    void pauseMedia();
    void stopMedia();
    int getDurationMinutes();
    
    default void displayMediaInfo() {
        System.out.println("Duration: " + getDurationMinutes() + " minutes");
    }
}

// Book class implementing Searchable, Renewable, and Reservable
class Book extends LibraryResource implements Searchable, Renewable, Reservable {
    private String genre;
    private int pageCount;
    private String language;
    private int renewalCount;
    private final int MAX_RENEWALS = 2;
    private boolean reserved;
    private LibraryMember reservedBy;
    private String summary;
    
    public Book(String resourceId, String title, String[] authors, String publisher, 
                String genre, int pageCount) {
        super(resourceId, title, authors, publisher);
        this.genre = genre;
        this.pageCount = pageCount;
        this.language = "English";
        this.renewalCount = 0;
        this.reserved = false;
        this.price = 25.99;
    }
    
    @Override
    public void displayResourceDetails() {
        System.out.println("\n=== Book Details ===");
        displayBasicInfo();
        System.out.println("Genre: " + genre);
        System.out.println("Page Count: " + pageCount);
        System.out.println("Language: " + language);
        displayRenewalInfo();
        displayReservationInfo();
        if (summary != null) {
            System.out.println("Summary: " + summary);
        }
    }
    
    @Override
    public String getResourceType() {
        return "Book";
    }
    
    @Override
    public boolean canBeReserved() {
        return true;
    }
    
    @Override
    public int getMaxLoanPeriod() {
        return 21; // 3 weeks for books
    }
    
    // Searchable implementation
    @Override
    public boolean matchesKeyword(String keyword) {
        return title.toLowerCase().contains(keyword.toLowerCase()) ||
               genre.toLowerCase().contains(keyword.toLowerCase()) ||
               (summary != null && summary.toLowerCase().contains(keyword.toLowerCase()));
    }
    
    @Override
    public boolean matchesAuthor(String author) {
        for (String bookAuthor : authors) {
            if (bookAuthor.toLowerCase().contains(author.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean matchesCategory(String category) {
        return genre.toLowerCase().contains(category.toLowerCase());
    }
    
    // Renewable implementation
    @Override
    public boolean renew() {
        if (canRenew() && !reserved) {
            renewalCount++;
            System.out.println("Book renewed. New due date: " + calculateDueDate());
            return true;
        } else {
            System.out.println("Cannot renew book - maximum renewals reached or reserved by another member");
            return false;
        }
    }
    
    @Override
    public int getRenewalCount() {
        return renewalCount;
    }
    
    @Override
    public int getMaxRenewals() {
        return MAX_RENEWALS;
    }
    
    // Reservable implementation
    @Override
    public void reserve(LibraryMember member) {
        if (!reserved && !isAvailable) {
            reserved = true;
            reservedBy = member;
            System.out.println("Book reserved for " + member.getName());
        } else {
            System.out.println("Cannot reserve book - already reserved or available");
        }
    }
    
    @Override
    public void cancelReservation() {
        if (reserved) {
            reserved = false;
            reservedBy = null;
            System.out.println("Reservation cancelled");
        } else {
            System.out.println("No reservation to cancel");
        }
    }
    
    @Override
    public boolean isReserved() {
        return reserved;
    }
    
    @Override
    public LibraryMember getReservedBy() {
        return reservedBy;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getGenre() { return genre; }
    public int getPageCount() { return pageCount; }
}

// Magazine class implementing Searchable
class Magazine extends LibraryResource implements Searchable {
    private String issueNumber;
    private java.util.Date issueDate;
    private String frequency; // weekly, monthly, quarterly
    private String[] topics;
    
    public Magazine(String resourceId, String title, String[] authors, String publisher,
                   String issueNumber, String frequency) {
        super(resourceId, title, authors, publisher);
        this.issueNumber = issueNumber;
        this.frequency = frequency;
        this.issueDate = new java.util.Date();
        this.topics = new String[]{"Current Events", "Technology", "Lifestyle"};
        this.price = 5.99;
    }
    
    @Override
    public void displayResourceDetails() {
        System.out.println("\n=== Magazine Details ===");
        displayBasicInfo();
        System.out.println("Issue Number: " + issueNumber);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Frequency: " + frequency);
        System.out.print("Topics: ");
        for (int i = 0; i < topics.length; i++) {
            System.out.print(topics[i]);
            if (i < topics.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    @Override
    public String getResourceType() {
        return "Magazine";
    }
    
    @Override
    public boolean canBeReserved() {
        return false; // Magazines typically cannot be reserved
    }
    
    @Override
    public int getMaxLoanPeriod() {
        return 7; // 1 week for magazines
    }
    
    // Searchable implementation
    @Override
    public boolean matchesKeyword(String keyword) {
        for (String topic : topics) {
            if (topic.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return title.toLowerCase().contains(keyword.toLowerCase());
    }
    
    @Override
    public boolean matchesAuthor(String author) {
        for (String magazineAuthor : authors) {
            if (magazineAuthor.toLowerCase().contains(author.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean matchesCategory(String category) {
        for (String topic : topics) {
            if (topic.toLowerCase().contains(category.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public String getIssueNumber() { return issueNumber; }
    public String getFrequency() { return frequency; }
}

// DVD class implementing Searchable, Renewable, Reservable, and MultimediaCapable
class DVD extends LibraryResource implements Searchable, Renewable, Reservable, MultimediaCapable {
    private String genre;
    private int durationMinutes;
    private String rating; // G, PG, PG-13, R
    private String director;
    private int renewalCount;
    private final int MAX_RENEWALS = 1;
    private boolean reserved;
    private LibraryMember reservedBy;
    
    public DVD(String resourceId, String title, String[] actors, String studio,
               String genre, int durationMinutes, String director, String rating) {
        super(resourceId, title, actors, studio);
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.director = director;
        this.rating = rating;
        this.renewalCount = 0;
        this.reserved = false;
        this.price = 19.99;
    }
    
    @Override
    public void displayResourceDetails() {
        System.out.println("\n=== DVD Details ===");
        displayBasicInfo();
        System.out.println("Genre: " + genre);
        System.out.println("Director: " + director);
        System.out.println("Rating: " + rating);
        displayMediaInfo();
        displayRenewalInfo();
        displayReservationInfo();
    }
    
    @Override
    public String getResourceType() {
        return "DVD";
    }
    
    @Override
    public boolean canBeReserved() {
        return true;
    }
    
    @Override
    public int getMaxLoanPeriod() {
        return 7; // 1 week for DVDs
    }
    
    // Searchable implementation
    @Override
    public boolean matchesKeyword(String keyword) {
        return title.toLowerCase().contains(keyword.toLowerCase()) ||
               genre.toLowerCase().contains(keyword.toLowerCase()) ||
               director.toLowerCase().contains(keyword.toLowerCase());
    }
    
    @Override
    public boolean matchesAuthor(String author) {
        // For DVDs, "authors" are actors
        for (String actor : authors) {
            if (actor.toLowerCase().contains(author.toLowerCase())) {
                return true;
            }
        }
        return director.toLowerCase().contains(author.toLowerCase());
    }
    
    @Override
    public boolean matchesCategory(String category) {
        return genre.toLowerCase().contains(category.toLowerCase()) ||
               rating.toLowerCase().contains(category.toLowerCase());
    }
    
    // Renewable implementation
    @Override
    public boolean renew() {
        if (canRenew() && !reserved) {
            renewalCount++;
            System.out.println("DVD renewed. New due date: " + calculateDueDate());
            return true;
        } else {
            System.out.println("Cannot renew DVD - maximum renewals reached or reserved");
            return false;
        }
    }
    
    @Override
    public int getRenewalCount() {
        return renewalCount;
    }
    
    @Override
    public int getMaxRenewals() {
        return MAX_RENEWALS;
    }
    
    // Reservable implementation
    @Override
    public void reserve(LibraryMember member) {
        if (!reserved && !isAvailable) {
            reserved = true;
            reservedBy = member;
            System.out.println("DVD reserved for " + member.getName());
        } else {
            System.out.println("Cannot reserve DVD - already reserved or available");
        }
    }
    
    @Override
    public void cancelReservation() {
        if (reserved) {
            reserved = false;
            reservedBy = null;
            System.out.println("Reservation cancelled");
        }
    }
    
    @Override
    public boolean isReserved() {
        return reserved;
    }
    
    @Override
    public LibraryMember getReservedBy() {
        return reservedBy;
    }
    
    // MultimediaCapable implementation
    @Override
    public void playMedia() {
        System.out.println("▶️ Playing DVD: " + title);
        System.out.println("Director: " + director);
        System.out.println("Duration: " + durationMinutes + " minutes");
    }
    
    @Override
    public void pauseMedia() {
        System.out.println("⏸️ DVD playback paused");
    }
    
    @Override
    public void stopMedia() {
        System.out.println("⏹️ DVD playback stopped");
    }
    
    @Override
    public int getDurationMinutes() {
        return durationMinutes;
    }
    
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public String getRating() { return rating; }
}

// Digital Resource class implementing DigitalAccess and Searchable
class DigitalResource extends LibraryResource implements DigitalAccess, Searchable {
    private String fileFormat; // PDF, EPUB, MP3, MP4
    private double fileSizeGB;
    private String accessUrl;
    private boolean requiresSpecialSoftware;
    private String softwareRequired;
    private int downloadCount;
    private String category;
    
    public DigitalResource(String resourceId, String title, String[] authors, String publisher,
                          String fileFormat, double fileSizeGB, String category) {
        super(resourceId, title, authors, publisher);
        this.fileFormat = fileFormat;
        this.fileSizeGB = fileSizeGB;
        this.category = category;
        this.accessUrl = "https://library.digital/" + resourceId;
        this.requiresSpecialSoftware = setSpecialSoftwareRequirement();
        this.downloadCount = 0;
        this.price = 0.0; // Digital resources are typically free to access
    }
    
    private boolean setSpecialSoftwareRequirement() {
        switch (fileFormat.toUpperCase()) {
            case "PDF":
                softwareRequired = "PDF Reader";
                return false;
            case "EPUB":
                softwareRequired = "E-book Reader";
                return true;
            case "MP3":
                softwareRequired = "Audio Player";
                return false;
            case "MP4":
                softwareRequired = "Video Player";
                return false;
            default:
                softwareRequired = "Unknown";
                return true;
        }
    }
    
    @Override
    public void displayResourceDetails() {
        System.out.println("\n=== Digital Resource Details ===");
        displayBasicInfo();
        System.out.println("File Format: " + fileFormat);
        System.out.println("File Size: " + fileSizeGB + " GB");
        System.out.println("Category: " + category);
        System.out.println("Download Count: " + downloadCount);
        displayDigitalInfo();
        System.out.println("Access URL: " + accessUrl);
        if (requiresSpecialSoftware) {
            System.out.println("Required Software: " + softwareRequired);
        }
    }
    
    @Override
    public String getResourceType() {
        return "Digital Resource";
    }
    
    @Override
    public boolean canBeReserved() {
        return false; // Digital resources don't need reservations
    }
    
    @Override
    public int getMaxLoanPeriod() {
        return 14; // 2 weeks for digital resources
    }
    
    // DigitalAccess implementation
    @Override
    public void downloadResource() {
        downloadCount++;
        System.out.println("📥 Downloading " + title + " (" + fileFormat + ")");
        System.out.println("File size: " + fileSizeGB + " GB");
        System.out.println("Download #" + downloadCount);
        
        if (requiresSpecialSoftware) {
            System.out.println("⚠️ Requires " + softwareRequired + " to open");
        }
    }
    
    @Override
    public void streamResource() {
        if (fileFormat.equals("MP3") || fileFormat.equals("MP4")) {
            System.out.println("🌐 Streaming " + title + " online");
            System.out.println("Quality: High");
        } else {
            System.out.println("Streaming not available for " + fileFormat + " format");
        }
    }
    
    @Override
    public boolean requiresSpecialSoftware() {
        return requiresSpecialSoftware;
    }
    
    @Override
    public String getAccessUrl() {
        return accessUrl;
    }
    
    // Searchable implementation
    @Override
    public boolean matchesKeyword(String keyword) {
        return title.toLowerCase().contains(keyword.toLowerCase()) ||
               category.toLowerCase().contains(keyword.toLowerCase()) ||
               fileFormat.toLowerCase().contains(keyword.toLowerCase());
    }
    
    @Override
    public boolean matchesAuthor(String author) {
        for (String resourceAuthor : authors) {
            if (resourceAuthor.toLowerCase().contains(author.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean matchesCategory(String category) {
        return this.category.toLowerCase().contains(category.toLowerCase());
    }
    
    public String getFileFormat() { return fileFormat; }
    public String getCategory() { return category; }
}

// Library Member class
class LibraryMember {
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String membershipType; // Student, Faculty, Public
    private java.util.Date memberSince;
    private LibraryResource[] checkedOutResources;
    private int checkedOutCount;
    private double outstandingFees;
    private static final int MAX_CHECKOUTS = 10;
    
    public LibraryMember(String memberId, String name, String email, String membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.memberSince = new java.util.Date();
        this.checkedOutResources = new LibraryResource[MAX_CHECKOUTS];
        this.checkedOutCount = 0;
        this.outstandingFees = 0.0;
    }
    
    public void checkOutResource(LibraryResource resource) {
        if (checkedOutCount < MAX_CHECKOUTS && outstandingFees <= 10.0) {
            checkedOutResources[checkedOutCount] = resource;
            checkedOutCount++;
            resource.checkOut(this);
            System.out.println(name + " checked out: " + resource.getTitle());
        } else {
            System.out.println("Cannot check out - limit reached or outstanding fees");
        }
    }
    
    public void returnResource(LibraryResource resource) {
        for (int i = 0; i < checkedOutCount; i++) {
            if (checkedOutResources[i].equals(resource)) {
                // Shift resources down
                for (int j = i; j < checkedOutCount - 1; j++) {
                    checkedOutResources[j] = checkedOutResources[j + 1];
                }
                checkedOutCount--;
                resource.checkIn();
                System.out.println(name + " returned: " + resource.getTitle());
                return;
            }
        }
        System.out.println("Resource not found in checked out items");
    }
    
    public void displayMemberInfo() {
        System.out.println("\n=== Library Member Info ===");
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Member Since: " + memberSince);
        System.out.println("Checked Out Items: " + checkedOutCount + "/" + MAX_CHECKOUTS);
        System.out.println("Outstanding Fees: $" + String.format("%.2f", outstandingFees));
    }
    
    public void viewCheckedOutResources() {
        System.out.println("\n" + name + "'s Checked Out Resources:");
        for (int i = 0; i < checkedOutCount; i++) {
            System.out.println("- " + checkedOutResources[i].getTitle() + 
                             " (" + checkedOutResources[i].getResourceType() + ")");
        }
        if (checkedOutCount == 0) {
            System.out.println("No resources currently checked out");
        }
    }
    
    public void payFees(double amount) {
        if (amount > 0 && amount <= outstandingFees) {
            outstandingFees -= amount;
            System.out.println("Payment of $" + String.format("%.2f", amount) + " received");
            System.out.println("Remaining balance: $" + String.format("%.2f", outstandingFees));
        } else {
            System.out.println("Invalid payment amount");
        }
    }
    
    public void addFee(double amount, String reason) {
        outstandingFees += amount;
        System.out.println("Fee added: $" + String.format("%.2f", amount) + " - " + reason);
    }
    
    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getMembershipType() { return membershipType; }
    public int getCheckedOutCount() { return checkedOutCount; }
    public double getOutstandingFees() { return outstandingFees; }
}

// Library Management System
class LibraryManagementSystem {
    private LibraryResource[] resources;
    private LibraryMember[] members;
    private int resourceCount;
    private int memberCount;
    private static final int MAX_RESOURCES = 10000;
    private static final int MAX_MEMBERS = 1000;
    
    public LibraryManagementSystem() {
        resources = new LibraryResource[MAX_RESOURCES];
        members = new LibraryMember[MAX_MEMBERS];
        resourceCount = 0;
        memberCount = 0;
    }
    
    public void addResource(LibraryResource resource) {
        if (resourceCount < MAX_RESOURCES) {
            resources[resourceCount++] = resource;
            System.out.println("Resource added to library: " + resource.getTitle());
        } else {
            System.out.println("Library is at maximum capacity");
        }
    }
    
    public void addMember(LibraryMember member) {
        if (memberCount < MAX_MEMBERS) {
            members[memberCount++] = member;
            System.out.println("Member added to library: " + member.getName());
        } else {
            System.out.println("Maximum member limit reached");
        }
    }
    
    public LibraryResource[] searchByTitle(String title) {
        LibraryResource[] results = new LibraryResource[resourceCount];
        int resultCount = 0;
        
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i] instanceof Searchable) {
                if (((Searchable) resources[i]).matchesTitle(title)) {
                    results[resultCount++] = resources[i];
                }
            }
        }
        
        // Create array with exact size
        LibraryResource[] finalResults = new LibraryResource[resultCount];
        System.arraycopy(results, 0, finalResults, 0, resultCount);
        return finalResults;
    }
    
    public LibraryResource[] searchByAuthor(String author) {
        LibraryResource[] results = new LibraryResource[resourceCount];
        int resultCount = 0;
        
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i] instanceof Searchable) {
                if (((Searchable) resources[i]).matchesAuthor(author)) {
                    results[resultCount++] = resources[i];
                }
            }
        }
        
        LibraryResource[] finalResults = new LibraryResource[resultCount];
        System.arraycopy(results, 0, finalResults, 0, resultCount);
        return finalResults;
    }
    
    public LibraryResource[] searchByCategory(String category) {
        LibraryResource[] results = new LibraryResource[resourceCount];
        int resultCount = 0;
        
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i] instanceof Searchable) {
                if (((Searchable) resources[i]).matchesCategory(category)) {
                    results[resultCount++] = resources[i];
                }
            }
        }
        
        LibraryResource[] finalResults = new LibraryResource[resultCount];
        System.arraycopy(results, 0, finalResults, 0, resultCount);
        return finalResults;
    }
    
    public LibraryResource findResourceById(String resourceId) {
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].getResourceId().equals(resourceId)) {
                return resources[i];
            }
        }
        return null;
    }
    
    public LibraryMember findMemberById(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) {
                return members[i];
            }
        }
        return null;
    }
    
    public void displayAllResources() {
        System.out.println("\n=== All Library Resources ===");
        for (int i = 0; i < resourceCount; i++) {
            resources[i].displayResourceDetails();
        }
    }
    
    public void displayAvailableResources() {
        System.out.println("\n=== Available Resources ===");
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].isAvailable()) {
                System.out.println("- " + resources[i].getTitle() + 
                                 " (" + resources[i].getResourceType() + ")");
            }
        }
    }
    
    public void generateLibraryReport() {
        System.out.println("\n=== Library Management Report ===");
        System.out.println("Total Resources: " + resourceCount);
        System.out.println("Total Members: " + memberCount);
        System.out.println("System Total Resources Ever Created: " + LibraryResource.getTotalResources());
        
        int availableCount = 0, checkedOutCount = 0;
        int bookCount = 0, magazineCount = 0, dvdCount = 0, digitalCount = 0;
        
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].isAvailable()) {
                availableCount++;
            } else {
                checkedOutCount++;
            }
            
            String type = resources[i].getResourceType();
            switch (type) {
                case "Book": bookCount++; break;
                case "Magazine": magazineCount++; break;
                case "DVD": dvdCount++; break;
                case "Digital Resource": digitalCount++; break;
            }
        }
        
        System.out.println("Available Resources: " + availableCount);
        System.out.println("Checked Out Resources: " + checkedOutCount);
        System.out.println("Books: " + bookCount);
        System.out.println("Magazines: " + magazineCount);
        System.out.println("DVDs: " + dvdCount);
        System.out.println("Digital Resources: " + digitalCount);
        
        // Member statistics
        double totalFees = 0;
        int totalCheckouts = 0;
        for (int i = 0; i < memberCount; i++) {
            totalFees += members[i].getOutstandingFees();
            totalCheckouts += members[i].getCheckedOutCount();
        }
        
        System.out.println("Total Outstanding Fees: $" + String.format("%.2f", totalFees));
        System.out.println("Total Active Checkouts: " + totalCheckouts);
    }
    
    public void displaySearchResults(String query, LibraryResource[] results) {
        System.out.println("\nSearch Results for '" + query + "':");
        if (results.length == 0) {
            System.out.println("No resources found");
        } else {
            for (LibraryResource resource : results) {
                System.out.println("- " + resource.getTitle() + 
                                 " (" + resource.getResourceType() + ")" +
                                 " - " + (resource.isAvailable() ? "Available" : "Checked Out"));
            }
        }
    }
}

public class Q14_LibraryResourceManagement {
    
    public static void main(String[] args) {
        System.out.println("=== Library Resource Management System ===");
        
        // Create library management system
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        // 1. Create different types of resources
        System.out.println("\n1. Creating Library Resources:");
        
        // Books
        Book book1 = new Book("B001", "The Great Gatsby", 
                             new String[]{"F. Scott Fitzgerald"}, "Scribner", 
                             "Classic Literature", 180);
        Book book2 = new Book("B002", "To Kill a Mockingbird", 
                             new String[]{"Harper Lee"}, "J.B. Lippincott & Co.", 
                             "Classic Literature", 376);
        Book book3 = new Book("B003", "Introduction to Java Programming", 
                             new String[]{"Daniel Liang"}, "Pearson", 
                             "Computer Science", 1344);
        
        // Set summaries
        book1.setSummary("A classic American novel about the Jazz Age");
        book2.setSummary("A novel about racial injustice in the Deep South");
        book3.setSummary("Comprehensive guide to Java programming");
        
        // Magazines
        Magazine mag1 = new Magazine("M001", "National Geographic", 
                                   new String[]{"Various Contributors"}, "National Geographic Society",
                                   "October 2024", "Monthly");
        Magazine mag2 = new Magazine("M002", "Scientific American", 
                                   new String[]{"Editorial Team"}, "Springer Nature",
                                   "September 2024", "Monthly");
        
        // DVDs
        DVD dvd1 = new DVD("D001", "The Matrix", 
                          new String[]{"Keanu Reeves", "Laurence Fishburne"}, "Warner Bros",
                          "Sci-Fi", 136, "The Wachowskis", "R");
        DVD dvd2 = new DVD("D002", "Finding Nemo", 
                          new String[]{"Albert Brooks", "Ellen DeGeneres"}, "Pixar",
                          "Animation", 100, "Andrew Stanton", "G");
        
        // Digital Resources
        DigitalResource digital1 = new DigitalResource("DR001", "Open Source Software Guide", 
                                                      new String[]{"Linux Foundation"}, "Tech Publications",
                                                      "PDF", 2.5, "Technology");
        DigitalResource digital2 = new DigitalResource("DR002", "Classical Music Collection", 
                                                      new String[]{"Various Artists"}, "Digital Music Corp",
                                                      "MP3", 1.2, "Music");
        DigitalResource digital3 = new DigitalResource("DR003", "Data Science Fundamentals", 
                                                      new String[]{"Dr. Sarah Johnson"}, "Academic Press",
                                                      "EPUB", 0.8, "Education");
        
        // Add resources to library
        library.addResource(book1);
        library.addResource(book2);
        library.addResource(book3);
        library.addResource(mag1);
        library.addResource(mag2);
        library.addResource(dvd1);
        library.addResource(dvd2);
        library.addResource(digital1);
        library.addResource(digital2);
        library.addResource(digital3);
        
        // 2. Create library members
        System.out.println("\n2. Creating Library Members:");
        
        LibraryMember member1 = new LibraryMember("LM001", "Alice Johnson", "alice@email.com", "Student");
        LibraryMember member2 = new LibraryMember("LM002", "Bob Smith", "bob@email.com", "Faculty");
        LibraryMember member3 = new LibraryMember("LM003", "Carol Davis", "carol@email.com", "Public");
        
        library.addMember(member1);
        library.addMember(member2);
        library.addMember(member3);
        
        // 3. Display all resources
        System.out.println("\n3. All Library Resources:");
        library.displayAllResources();
        
        // 4. Demonstrate polymorphism
        System.out.println("\n4. Polymorphic Operations:");
        LibraryResource[] resources = {book1, mag1, dvd1, digital1};
        
        for (LibraryResource resource : resources) {
            System.out.println("\nResource: " + resource.getTitle());
            System.out.println("Type: " + resource.getResourceType());
            System.out.println("Max Loan Period: " + resource.getMaxLoanPeriod() + " days");
            System.out.println("Can be Reserved: " + resource.canBeReserved());
        }
        
        // 5. Member operations - checkouts
        System.out.println("\n5. Member Checkout Operations:");
        
        member1.checkOutResource(book1);
        member1.checkOutResource(dvd1);
        member1.checkOutResource(digital1);
        
        member2.checkOutResource(book2);
        member2.checkOutResource(mag1);
        
        member3.checkOutResource(book3);
        member3.checkOutResource(dvd2);
        
        // Display member info
        member1.displayMemberInfo();
        member1.viewCheckedOutResources();
        
        // 6. Interface-specific operations
        System.out.println("\n6. Interface-Specific Operations:");
        
        // Searchable operations
        System.out.println("\nSearchable Resources:");
        LibraryResource[] titleResults = library.searchByTitle("Java");
        library.displaySearchResults("Java", titleResults);
        
        LibraryResource[] authorResults = library.searchByAuthor("Harper Lee");
        library.displaySearchResults("Harper Lee", authorResults);
        
        LibraryResource[] categoryResults = library.searchByCategory("Technology");
        library.displaySearchResults("Technology", categoryResults);
        
        // Renewable operations
        System.out.println("\nRenewable Resources:");
        if (book1 instanceof Renewable) {
            ((Renewable) book1).renew();
            ((Renewable) book1).displayRenewalInfo();
        }
        
        if (dvd1 instanceof Renewable) {
            ((Renewable) dvd1).renew();
        }
        
        // Reservable operations
        System.out.println("\nReservable Resources:");
        if (book2 instanceof Reservable) {
            ((Reservable) book2).reserve(member3);
            ((Reservable) book2).displayReservationInfo();
        }
        
        if (dvd2 instanceof Reservable) {
            ((Reservable) dvd2).reserve(member1);
        }
        
        // Digital access operations
        System.out.println("\nDigital Access Operations:");
        if (digital1 instanceof DigitalAccess) {
            ((DigitalAccess) digital1).downloadResource();
            ((DigitalAccess) digital1).displayDigitalInfo();
        }
        
        if (digital2 instanceof DigitalAccess) {
            ((DigitalAccess) digital2).streamResource();
        }
        
        // Multimedia operations
        System.out.println("\nMultimedia Operations:");
        if (dvd1 instanceof MultimediaCapable) {
            ((MultimediaCapable) dvd1).playMedia();
            ((MultimediaCapable) dvd1).displayMediaInfo();
            ((MultimediaCapable) dvd1).pauseMedia();
            ((MultimediaCapable) dvd1).stopMedia();
        }
        
        // 7. Return operations
        System.out.println("\n7. Return Operations:");
        member1.returnResource(book1);
        member2.returnResource(mag1);
        
        // 8. Fee management
        System.out.println("\n8. Fee Management:");
        member1.addFee(5.50, "Late return fee");
        member1.addFee(2.00, "Printing charges");
        member1.displayMemberInfo();
        member1.payFees(3.50);
        member1.displayMemberInfo();
        
        // 9. Advanced search operations
        System.out.println("\n9. Advanced Search Operations:");
        LibraryResource[] sciFiResults = library.searchByCategory("Sci-Fi");
        library.displaySearchResults("Sci-Fi", sciFiResults);
        
        LibraryResource[] classicResults = library.searchByCategory("Classic");
        library.displaySearchResults("Classic", classicResults);
        
        // 10. Interface type checking
        System.out.println("\n10. Dynamic Interface Detection:");
        LibraryResource[] allResources = {book1, mag1, dvd1, digital1};
        
        for (LibraryResource resource : allResources) {
            System.out.println("\n" + resource.getTitle() + " (" + resource.getResourceType() + "):");
            
            if (resource instanceof Searchable) {
                System.out.println("  ✓ Searchable");
            }
            if (resource instanceof Renewable) {
                System.out.println("  ✓ Renewable");
            }
            if (resource instanceof Reservable) {
                System.out.println("  ✓ Reservable");
            }
            if (resource instanceof DigitalAccess) {
                System.out.println("  ✓ Digital Access");
            }
            if (resource instanceof MultimediaCapable) {
                System.out.println("  ✓ Multimedia Capable");
            }
        }
        
        // 11. Display available resources
        System.out.println("\n11. Available Resources:");
        library.displayAvailableResources();
        
        // 12. Generate comprehensive library report
        System.out.println("\n12. Library Management Report:");
        library.generateLibraryReport();
        
        // 13. Demonstrate resource location setting
        System.out.println("\n13. Resource Location Management:");
        book1.setLocation("Fiction Section - Shelf A1");
        book3.setLocation("Computer Science - Shelf CS5");
        dvd1.setLocation("Media Section - Row 3");
        digital1.setLocation("Digital Collection - Online");
        
        // 14. Final system demonstration
        System.out.println("\n14. Final System Status:");
        
        // Show member activities
        member2.viewCheckedOutResources();
        member3.viewCheckedOutResources();
        
        // Additional digital operations
        if (digital3 instanceof DigitalAccess) {
            ((DigitalAccess) digital3).downloadResource();
        }
        
        System.out.println("\n=== Library Resource Management Summary ===");
        System.out.println("✓ Abstract classes provide resource structure");
        System.out.println("✓ Multiple interfaces enable specialized capabilities");
        System.out.println("✓ Polymorphism enables uniform resource management");
        System.out.println("✓ Inheritance supports resource type specialization");
        System.out.println("✓ Encapsulation protects resource and member data");
        System.out.println("✓ Final methods ensure transaction integrity");
        System.out.println("✓ Interface default methods provide common utilities");
        System.out.println("✓ Comprehensive search functionality across resource types");
        System.out.println("✓ Member management with fee tracking");
        System.out.println("✓ Digital resource support with download/streaming");
        System.out.println("✓ Reservation and renewal systems");
        System.out.println("✓ System demonstrates real-world library management");
    }
}