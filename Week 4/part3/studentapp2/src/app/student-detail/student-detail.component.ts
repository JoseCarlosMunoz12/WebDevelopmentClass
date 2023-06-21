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

  studentClass: string = "";

  student: Student;

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
