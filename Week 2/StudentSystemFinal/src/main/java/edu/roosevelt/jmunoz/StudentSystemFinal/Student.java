package edu.roosevelt.jmunoz.StudentSystemFinal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author josec
 */
public class Student {

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", SID=" + sid + ", GPA=" + gpa + ", major=" + major + ", Hours=" + hours + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.sid;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return this.sid == other.sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSID() {
        return sid;
    }

    public void setSID(int SID) {
        this.sid = SID;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int Hours) {
        this.hours = Hours;
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double GPA) {
        this.gpa = GPA;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 30, message = "Name must be no more thatn 30 characters long")
    private String name;
    @Min(value = 10000, message = "SID must be a 5 digit value")
    @Max(value = 99999, message = "SID must be a 5 digit value")
    private int sid;
    @Range(min = 0, max=4, message = "Range must be between 4.0 and 0.0")
    private double gpa;
    @NotBlank(message = "Major cannot be blank")
    @Size(max = 30, message = "Major must be no more thatn 30 characters long")
    private String major;
    @PositiveOrZero(message="Hours must be equal or greeater than zero")
    private int hours;
    
}
