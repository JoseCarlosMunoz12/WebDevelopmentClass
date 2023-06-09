import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  sid: number = -1;

  lowGPA: boolean = false;

  className: String = "";
  student: Student = new Student();

  constructor(private studentService: StudentService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    //get this sid
    if (this.route.snapshot.paramMap.get('sid') !== null) {
      //set sid to the parsed integer form of 
      this.sid = parseInt(this.route.snapshot.paramMap.get('sid')!);
      //get the student
      this.studentService.getStudent(this.sid).subscribe(
        data => {
          this.student = data;
          if (this.student.gpa < 2) {
            this.lowGPA = true;
          }
          if (this.student.hours < 30) {
            this.className = "Freshman";
          } else if (this.student.hours < 30) {
            this.className = "Sophomore";
          } else if (this.student.hours < 30) {
            this.className = "Junior";
          } else {
            this.className = "Senior";
          }


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
