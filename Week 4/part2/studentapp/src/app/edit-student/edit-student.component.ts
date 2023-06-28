import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})
export class EditStudentComponent implements OnInit {

  student: Student = new Student();
  
  sid: number = -1;
  
  constructor(private studentService: StudentService, private router: Router, private route: ActivatedRoute, private userService: UserService) { }


  onSubmit(): void {
    console.log(this.student);

    this.studentService.editStudent(this.student).subscribe(
      data => {
        //don't need to do anything here...
        //we succesfully added student, so
        //go back to the lists
        this.router.navigate(['/studentlist']);

      },
      error => {
        
          console.error("Error!");
          console.error("ErrorMessage: " + error.message);
          console.error("ErrorStatus: " + error.status);
          console.error("ErrorName:" + error.name);
          console.error(""); 
        
      }
    );
  }

  logout(){
    this.userService.logout();
  }
  
  ngOnInit(): void {
    //get this sid
    if (this.route.snapshot.paramMap.get('sid') !== null) {
      //set sid to the parsed integer form of 
      this.sid = parseInt(this.route.snapshot.paramMap.get('sid')!);

      //get the student
      this.studentService.getStudent(this.sid).subscribe(
        data => {
          this.student = data;
         


        },
        error => {
          if (error.status = '404') {
            console.error("student doesn't exist");
            this.router.navigateByUrl('studentlist');
          } else {

          
            console.error("Error!");
            console.error("ErrorMessage: " + error.message);
            console.error("ErrorStatus: " + error.status);
            console.error("ErrorName:" + error.name);
            console.error(""); 
          }
        }
      );


    } else {
      //sid wasn't present, so bug out
      this.router.navigateByUrl('studentlist');
      
    }
    
    



  }
}
