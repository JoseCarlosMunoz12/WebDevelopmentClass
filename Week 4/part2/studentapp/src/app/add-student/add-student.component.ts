import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  //work with this student
  student: Student = new Student();

  constructor(private studentService: StudentService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.log(this.student);

    this.studentService.createStudent(this.student).subscribe(
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

}
