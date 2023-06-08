package edu.roosevelt.jmunoz.StudentSystemFinal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author josec
 */
@RestController
public class StudentController {
    
    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    private ArrayList<Student> roster = new ArrayList<Student>();
    
    public StudentController()
    {
        Random random = new Random();
        //list of arraya for init purposes
        String[] fnames = {"Jose", "Carlos", "Maria", "Clemente"};
        String[] lnames = {"Munoz", "Hernandez", "Machuca", "Ortiz"};
        String[] majors = {"CS", "IT", "CY", "DA" };
        //creat the list of students
        logger.debug("Going to create student:");
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
            logger.debug(s.toString());
            roster.add(s);
        }       
    }
    
    
    @GetMapping("/students")
    public ArrayList<Student> getStudents()
    {
        logger.debug("GetAllStudents: " + roster.size());
        return roster;
    }
    
    @GetMapping("/student{sid}")
    public ResponseEntity<Student> getStudent(@PathVariable("sid")int sid){
        logger.debug("Get Student:" + sid);
        Student fake = new Student();
        fake.setSID(sid);
        if (roster.contains(fake)){
            Student real =  roster.get( roster.indexOf(fake));            
            logger.debug("Get Student:" + sid + "s" + real.toString());
            return new ResponseEntity(real, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/students/bymajor/{major}")
    public ArrayList<Student> getStudentsByMajor(@PathVariable("major") String major){
        //create an empty list
        ArrayList<Student> list = new ArrayList();
        //loop thorugh roster and get matching
        for (Student s : roster){
            if(s.getMajor().equals(major))
                list.add(s);
        }
        return list;
    }
    
    @GetMapping("/students/bymajor/{major}/gpalessthan/{gpa}")
    public ArrayList<Student> getStudentsMajorNLessThanGPA(@PathVariable("major") String major,
            @PathVariable("gpa") double gpa){
        //create an empty list
        ArrayList<Student> list = new ArrayList();
        //loop thorugh roster and get matching
        for (Student s : roster){
            if(s.getMajor().equals(major) && s.getGPA() < gpa)
                list.add(s);
        }
        return list;
    }
    
    @PostMapping(value = "/students", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody @Valid final Student student){
        //is student already there?
        if(roster.contains(student))
            return new ResponseEntity(student, HttpStatus.FOUND);
        //add student
        roster.add(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }
    
    @PutMapping(value = "/students", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> editStudent(@RequestBody @Valid final Student student){
        if(roster.contains(student)){
            Student real = roster.get(roster.indexOf(student));
             //now edit real student
             if(student.getGPA() > 0 && student.getGPA() <= 4.0)
                 real.setGPA(student.getGPA());
             if(student.getName() != null)
                 real.setName(student.getName());
             if(student.getMajor() != null)
                 real.setMajor(student.getMajor());
             return new ResponseEntity(student, HttpStatus.OK);             
        }
        else{
            return new ResponseEntity(student, HttpStatus.NOT_FOUND);
        }
    }
    
   @DeleteMapping("student/{sid}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("sid") int sid){
        Student fake = new Student();
        fake.setSID(sid);
        if(roster.contains(fake)){
            Student real = roster.get(roster.indexOf(fake));
            roster.remove(fake);
            return new ResponseEntity(real, HttpStatus.OK);            
        } else{
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }        
    }
    
}
