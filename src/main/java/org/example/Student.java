package org.example;

public class Student {
    private String name;
    private double gpa;
    private int studentId;

    public Student(int studentId, String name, double gpaString) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpaString;
    }

    public void updateParam(String key, String value) {
        switch (key) {
            case "name":
                this.name = value;
                break;
            case "gpa":
                this.gpa = Double.parseDouble(value);
                break;
            case "studentId":
                this.studentId = Integer.parseInt(value);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return ("ID: "+this.studentId + "\nName: "+this.name+"\nGPA: "+this.gpa+"\n");
    }

    public int getStudentId() {
        return studentId;
    }
    public String getName(){
        return this.name;
    }
    public double getGpa() {
        return this.gpa;
    }
}
