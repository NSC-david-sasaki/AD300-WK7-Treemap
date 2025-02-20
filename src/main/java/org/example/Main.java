package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Student Record Manager Demo");
        StudentRecordManager srm = new StudentRecordManager();
        Student s1 = srm.createRecord(1, "Bob", 1.0);
        Student s2 = srm.createRecord(2, "Edward", 2.0);
        Student s3 = srm.createRecord(3, "Alice", 3.0);
        Student s4 = srm.createRecord(4, "David", 4.0);
        Student s5 = srm.createRecord(5, "Ellie", 5.0);

        System.out.println("Created 5 Student Records");
        srm.displayAllRecords();

        System.out.println("Modifying gpa record for s1");
        srm.updateRecord(1,s1, "gpa", "4.0" );
        srm.displayAllRecords();

        System.out.println("Deleting student record for s4");
        srm.deleteRecord(4);
        srm.displayAllRecords();

        System.out.println("Searching for student record by gpa threshold above 3.0");
        List<Student> testList = srm.getRecordsByGpa(3.0);
        System.out.println("Found:" + testList.toString());
        System.out.println("Quitting Demo");
    }
}