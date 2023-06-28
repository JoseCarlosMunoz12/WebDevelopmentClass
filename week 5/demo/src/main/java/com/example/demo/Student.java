/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author mruth
 */
public class Student {

    @Override
    public String toString() {
        return "Student{" + "SID=" + SID + ", name=" + name + ", major=" + major + ", hours=" + hours + ", GPA=" + GPA + '}';
    }
    //@Min(value = 10000, message = "SID must be at least 5 digits long")
    @Positive(message = "SID must be a five digit number")
    @Max(value = 99999, message = "SID must be at most a 5 digit number")
    private int SID;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.SID;
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
        if (this.SID != other.SID) {
            return false;
        }
        return true;
    }
    @NotBlank(message = "Name is required and cannot be blank")
    private String name;
    @NotBlank(message = "Major is required and cannot be blank")
    private String major;
    
    
    @Range(min = 0, max = 120, message = "Hours must be between 0 and 120")
    private int hours;
    
    
    @PositiveOrZero(message = "GPA must be positive")
    @DecimalMax(value = "4.0", message = "GPA must be between 0.0 and 4.0")
    private double GPA;

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
    
}
