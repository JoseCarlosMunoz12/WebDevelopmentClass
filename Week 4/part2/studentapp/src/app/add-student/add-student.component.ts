import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  //work with this student
  student: Student = new Student();
  sidAlreadyExists:boolean = false;

  constructor(private studentService: StudentService, 
    private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  logout(){
    this.userService.logout();
  }
  onSubmit(form: NgForm): void {
    console.log(this.student);

    this.studentService.createStudent(this.student).subscribe(
      data => {
        //don't need to do anything here...
        //we succesfully added student, so
        //go back to the lists
        this.router.navigate(['/studentlist']);
        this.sidAlreadyExists = false;
      },
      error => {
        if(error.status == '302'){
          this.sidAlreadyExists = true;
          form.controls['sid'].reset();
        }else {
        
          console.error("Error!");
          console.error("ErrorMessage: " + error.message);
          console.error("ErrorStatus: " + error.status);
          console.error("ErrorName:" + error.name);
          console.error("");
         }
        
      }
    );
  }

}
