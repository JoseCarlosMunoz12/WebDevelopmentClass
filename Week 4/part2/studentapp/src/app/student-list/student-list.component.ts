import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  //roster
  roster: Student[] = [];
student: any;
  //constructor
  constructor(private studentService: StudentService){}
  //init
  ngOnInit(): void {    
    this.updateListData();
  }

  //update list data
  updateListData(){
    this.studentService.getAllStudents().subscribe(data => {
      this.roster = data;
    },
    error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");
    });
  }
}
