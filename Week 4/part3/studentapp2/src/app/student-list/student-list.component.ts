import { Component, ErrorHandler, OnInit } from '@angular/core';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  //roster
  roster: Student[] = [];
  //constructor
  constructor(private studentService: StudentService) { }
  //init
  ngOnInit(): void {
    this.updateListData();
  }

  //update list data
  updateListData() {
    this.studentService.getAllStudents().subscribe(
      data => {
        this.roster = data;  
      },
      error => {
        console.error("Error!");
        console.error("ErrorMessage: " + error.message);
        console.error("ErrorStatus: " + error.status);
        console.error("ErrorName:" + error.name);
        console.error("");
      });
  }

}
