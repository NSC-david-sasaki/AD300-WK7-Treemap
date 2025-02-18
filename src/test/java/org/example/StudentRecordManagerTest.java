package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRecordManagerTest {
    private StudentRecordManager studentRecordManager;
    private Student s1;
    private Student s2;
    private Student s3;
    private Student s4;
    private Student s5;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        studentRecordManager = new StudentRecordManager();
        s1 = studentRecordManager.createRecord(4, "Alice", 1.0);
        s2 = studentRecordManager.createRecord(1, "Bob", 2.0);
        s3 = studentRecordManager.createRecord(2, "Charlie", 3.0);
        s4 = studentRecordManager.createRecord(3, "David", 4.0);
        s5 = studentRecordManager.createRecord(5, "Ellie", 5.0);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Redirect System.out

    }

    @AfterEach
    void tearDown() {
        studentRecordManager = null;
        s1 = null;
        s2 = null;
        s3 = null;
        s4 = null;
        s5 = null;
        System.gc();
        System.setOut(originalOut); // Restore System.out after each test
    }

    @Test
    void createRecord() {
        StudentRecordManager srm = new StudentRecordManager();
        srm.createRecord(6, "Juan", 4.0);
        srm.displayAllRecords();
        assertEquals("ID: 6\n" +
                "Name: Juan\n" +
                "GPA: 4.0\n" +
                "\n", outputStream.toString());
    }

    @Test
    void updateRecord() {
        Student s6 = studentRecordManager.createRecord(6, "Juan", 4.0);
        studentRecordManager.updateRecord(6, s6, "gpa", "3.0");
        studentRecordManager.displayAllRecords();
        assertEquals("ID: 1\n" +
                "Name: Bob\n" +
                "GPA: 2.0\n" +
                "\n" +
                "ID: 2\n" +
                "Name: Charlie\n" +
                "GPA: 3.0\n" +
                "\n" +
                "ID: 3\n" +
                "Name: David\n" +
                "GPA: 4.0\n" +
                "\n" +
                "ID: 4\n" +
                "Name: Alice\n" +
                "GPA: 1.0\n" +
                "\n" +
                "ID: 5\n" +
                "Name: Ellie\n" +
                "GPA: 5.0\n" +
                "\n" +
                "ID: 6\n" +
                "Name: Juan\n" +
                "GPA: 3.0\n\n", outputStream.toString());
    }

    @Test
    void deleteRecord() {
        studentRecordManager.deleteRecord(2);
        studentRecordManager.displayAllRecords();
        assertEquals("ID: 1\n" +
                "Name: Bob\n" +
                "GPA: 2.0\n" +
                "\n" +
                "ID: 3\n" +
                "Name: David\n" +
                "GPA: 4.0\n" +
                "\n" +
                "ID: 4\n" +
                "Name: Alice\n" +
                "GPA: 1.0\n" +
                "\n" +
                "ID: 5\n" +
                "Name: Ellie\n" +
                "GPA: 5.0\n\n", outputStream.toString());
    }

    @Test
    void displayAllRecords() {
        studentRecordManager.displayAllRecords();
        assertEquals("ID: 1\n" +
                "Name: Bob\n" +
                "GPA: 2.0\n" +
                "\n" +
                "ID: 2\n" +
                "Name: Charlie\n" +
                "GPA: 3.0\n" +
                "\n" +
                "ID: 3\n" +
                "Name: David\n" +
                "GPA: 4.0\n" +
                "\n" +
                "ID: 4\n" +
                "Name: Alice\n" +
                "GPA: 1.0\n" +
                "\n" +
                "ID: 5\n" +
                "Name: Ellie\n" +
                "GPA: 5.0\n\n", outputStream.toString());
    }

    @Test
    void getRecordsByGpa() {
        List<Student> testList = studentRecordManager.getRecordsByGpa(3.0);
        assertEquals(2, testList.size());
        assertEquals("[ID: 3\n" +
                "Name: David\n" +
                "GPA: 4.0\n" +
                ", ID: 5\n" +
                "Name: Ellie\n" +
                "GPA: 5.0\n" +
                "]", testList.toString());
    }

    @Test
    void checkDuplicateRecord() {
        assertThrows(IllegalArgumentException.class, () -> studentRecordManager.createRecord(2, "John", 2.0));
    }

    @Test
    void checkEmptyRecord() {
        StudentRecordManager srm = new StudentRecordManager();
        assertThrows(RuntimeException.class, () -> srm.displayAllRecords());
    }

    @Test
    void checkDuplicateDeleteRecords() {
        studentRecordManager.deleteRecord(1);
        assertDoesNotThrow(() -> studentRecordManager.deleteRecord(1));
    }



}