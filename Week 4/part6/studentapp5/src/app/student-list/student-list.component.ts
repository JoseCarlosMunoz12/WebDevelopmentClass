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
        this.sortBy("sid");
      },
      error => {
        console.error("Error!");
        console.error("ErrorMessage: " + error.message);
        console.error("ErrorStatus: " + error.status);
        console.error("ErrorName:" + error.name);
        console.error("");
      });
  }

  deleteStudent(sid: number) {
    this.studentService.deleteStudent(sid).subscribe(data => {
      //if we want to reload the page,
      //we do it here...
      //this.updateListData();

    },
      error => {
      console.error("Error!");
      console.error("ErrorMessage: " + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName:" + error.name);
      console.error("");
    });
    //or we could simply remove the student 
    this.roster = this.roster.filter(m => m.sid !== sid);

  }


  sortBy(field: String) {
    if (field == "name") {
      this.roster.sort(function(a, b) {
        var nameA = a.name.toUpperCase(); // ignore upper and lowercase
        var nameB = b.name.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        // names must be equal
        return 0;
      });
    } else if (field == "major") {
      this.roster.sort(function(a, b) {
        var nameA = a.major.toUpperCase(); // ignore upper and lowercase
        var nameB = b.major.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        // names must be equal
        return 0;
      });
    } else if (field == "hours") {
      this.roster.sort((a, b) => a.hours - b.hours);
    } else if (field == "gpa") {
      this.roster.sort((a, b) => a.gpa - b.gpa);
    } else {
      this.roster.sort((a, b) => a.sid - b.sid);
    }
  } 

}
