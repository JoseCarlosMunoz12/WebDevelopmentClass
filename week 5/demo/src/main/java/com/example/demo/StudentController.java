/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mruth
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class StudentController {
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody final @Valid User user, HttpSession session) {
        if (users.contains(user)) {
            //user does exist
            //go get it
            User real = users.get(users.indexOf(user));
            //check password
            if (real.getPassword().compareTo(user.getPassword()) == 0) {
                //user has provided the correct credentials
                logger.info("user has logged in: " + real.getUsername());
                session.setAttribute("user", real);
                
                
                return new ResponseEntity(real, HttpStatus.OK);
            } else {
                logger.error("Password attempt failed -> User:" + user.getUsername() + "|" + user.getPassword());
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
            
            
        } else {
            logger.error("User account doesn't exist -> User:" + user.getUsername() + "|" + user.getPassword());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }        
    }
       
    
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity("logged out", HttpStatus.OK);
       
    }


    @GetMapping("/loggedIn")
    public boolean isUserLoggedIn(HttpSession session) {
        return (session.getAttribute("user") != null);
    }
    
    @GetMapping("/home")
    public ResponseEntity<String> goHome(HttpSession session){
        if( session.getAttribute("user")!= null){
            User user = (User)session.getAttribute("user");
            return new ResponseEntity(user.getUsername(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    
    //set up the logger for the entire class
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @GetMapping("/students")
    //get them all
    public ResponseEntity<List<Student>> getStudents(HttpSession session) {
        logger.info("Get all students!");
        if (session.getAttribute("user") != null) {
            return new ResponseEntity(roster, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        
        
    }
    
    @GetMapping("/students/{sid}") 
    //get a special one by id
    public ResponseEntity<Student> getStudent(@PathVariable("sid") int sid, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Student fake = new Student();
            fake.setSID(sid);
            if (roster.contains(fake)) {
                //get it
                Student real = roster.get(roster.indexOf(fake));
                logger.info("Get student: " + sid);
                return new ResponseEntity(real, HttpStatus.OK);
            } else {
                logger.info("Someone tried to get a student that doesn't exist: " + sid);
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Student> addStudent(@RequestBody @Valid final Student student, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (!roster.contains(student)) {
                roster.add(student);
                logger.info("Added Student: ");
                logger.info("" + student);
                logger.info("----");
                return new ResponseEntity(student, HttpStatus.OK);
            } else {
                logger.info("Someone tried to add a student that already exists: " + student.getSID());
                return new ResponseEntity(student, HttpStatus.FOUND);
            }
        } else {
            return new ResponseEntity(student, HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PutMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid final Student student, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (!roster.contains(student)) {
                logger.info("Someone tried to update a student that does not exist: " + student.getSID());
                return new ResponseEntity(student, HttpStatus.NOT_FOUND);
            } else {

                //get real student
                Student real = roster.get(roster.indexOf(student));
                logger.info("Edited Student From: ");
                logger.info("" + real);
                logger.info("----");
                if (student.getName().length() > 0) {
                    real.setName(student.getName());
                }
                if ((student.getGPA() >= 0) && (student.getGPA() <= 4)) {
                    real.setGPA(student.getGPA());
                }
                if (student.getMajor().length() > 0) {
                    real.setMajor(student.getMajor());
                }
                if ((student.getHours() >= 0) && (student.getHours() <= 120)) {
                    real.setHours(student.getHours());
                }
                logger.info("Edited Student To: ");
                logger.info("" + real);
                logger.info("----");

                return new ResponseEntity(student, HttpStatus.OK);
            }
        
        } else {
            return new ResponseEntity(student, HttpStatus.UNAUTHORIZED);
        }
    }
    
    @DeleteMapping("/students/{sid}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("sid") int sid, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Student destroyme = new Student();
            destroyme.setSID(sid);
            //is it there?
            if (!roster.contains(destroyme)) {
                logger.info("Someone tried to delete a student that does not exist: " + sid);
                //report it
                return new ResponseEntity(destroyme, HttpStatus.NOT_FOUND);
            } else {
                //delete it
                roster.remove(destroyme);
                logger.info("Successfully deleted student with " + sid);
                return new ResponseEntity(destroyme, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //we need a list of students
    private ArrayList<Student> roster = new ArrayList();
    private ArrayList<User> users = new ArrayList();
    //constructor for controller
    //and we need to construct list of students
    public StudentController() {
        //we need users!
        User user1 = new User();
        user1.setUsername("mike");
        user1.setPassword("mike");
        
        User user2 = new User();
        user2.setUsername("ruth");
        user2.setPassword("ruth");
        
        users.add(user1);
        users.add(user2);
        
        
        //need random number generator
        Random random = new Random();
        //need our strings
        String[] firsts = {"Mike","Ruth","Rebecca","Ann","Edward","Gus"};
        String[] lasts  = {"Brees","Hill","Winston","Book","Brooks"};
        String[] majors = {"Computer Science","Information Technology","Cyber Security","Data Analytics"};
        //generate five for our list
        for (int i=0; i<10; i++) {
            //id should be 5 digit?
            int sid = 10000 + random.nextInt(89999);
            //get a name
            String name = lasts[random.nextInt(lasts.length)] + ", " + firsts[random.nextInt(firsts.length)];
            //get a major
            String major = majors[random.nextInt(majors.length)];
            //get hours
            int hours = random.nextInt(121);
            //get gpa
            double gpa = random.nextDouble() * 4;
            //create a student
            Student s = new Student();
            s.setSID(sid);
            s.setName(name);
            s.setMajor(major);
            s.setHours(hours);
            s.setGPA(gpa);
            
            roster.add(s);
            
            
        }
        
        
    }
    
    
    
    
    
    
}
