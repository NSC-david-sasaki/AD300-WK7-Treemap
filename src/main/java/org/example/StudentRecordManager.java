package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StudentRecordManager {
    private TreeMap<Integer, Student> records;

    public StudentRecordManager() {
        this.records = new TreeMap<>(); // Initialize records
    }

    private boolean isExistingStudentIdRecord(int studentId) {
        return records != null && records.containsKey(studentId);
    }

    public Student createRecord(int studentId, String aName, double aGpa){
        if(!isExistingStudentIdRecord(studentId)) {
            Student record = new Student(studentId, aName, aGpa);
            this.records.put(studentId, record);
            return record;
        }
        throw new IllegalArgumentException("Student id " + studentId + " already exists");
    }

    public void updateRecord(int studentId, Student aStudent, String key, String value){
        aStudent.updateParam(key, value);
        records.put(studentId, aStudent);
    }

    public void deleteRecord(int studentId){
        records.remove(studentId);
    }

    public void displayAllRecords(){
        if (records.isEmpty()) {
            throw new RuntimeException("No records found");
        }

        for (Map.Entry<Integer, Student> entry : records.entrySet()) {
            if (entry.getValue() != null) {
                System.out.println(entry.getValue());
            } else {
                System.out.println("Student with ID " + entry.getKey() + " has null data.");
            }
        }
    }

    public List<Student> getRecordsByGpa(double gpaThreshold){
        List<Student> result = new ArrayList<>();
        for (Integer key : records.keySet()) {
            if(records.get(key).getGpa() > gpaThreshold){
                result.add(records.get(key));
            }
        }
        return result;
    }
}
