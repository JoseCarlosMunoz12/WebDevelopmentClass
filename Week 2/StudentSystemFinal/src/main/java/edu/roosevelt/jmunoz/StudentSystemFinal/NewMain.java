/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.jmunoz.StudentSystemFinal;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jose
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Student> roster = new ArrayList<Student>();
        Random random = new Random();
        //list of arraya for init purposes
        String[] fnames = {"Jose", "Carlos", "Maria", "Clemente"};
        String[] lnames = {"Munoz", "Hernandez", "Machuca", "Ortiz"};
        String[] majors = {"CS", "IT", "CY", "DA" };
        //creat the list of students
        for (int ii = 0; ii < 25; ii++)
        {
            //make student
            Student s = new Student();
            s.setGPA(random.nextDouble() * 4.0);
            String name = lnames[random.nextInt(lnames.length)] + ",";
            name = name + fnames[random.nextInt(fnames.length)];
            String major = majors[random.nextInt(majors.length)];
            s.setMajor(major);
            s.setName(name);
            s.setSID(random.nextInt(89999)+10000);
            roster.add(s);
        }   
        for (Student s : roster){
            System.out.println("<tr>");
            System.out.println("<td>" + s.getSID() + "</td>");            
            System.out.println("<td>" + s.getName() + "</td>");
            System.out.println("<td>" + s.getMajor() + "</td>");
            System.out.println("<td>" + (int)(s.getGPA() * 100) / 100.0 + "</td>");
            System.out.println("</tr>");
        }
    }
    
}
