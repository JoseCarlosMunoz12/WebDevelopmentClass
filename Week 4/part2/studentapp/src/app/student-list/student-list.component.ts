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

  sortBy(field: string){
    if(field == "sid"){
      this.roster.sort((a,b)=>a.sid - b.sid);      
    } else if(field == "name"){
      this.roster.sort((a,b)=>{
        var nameA = a.name.toUpperCase();
        var nameB = b.name.toUpperCase();
        if(nameA < nameB){
          return -1;
        }
        if( nameA > nameB){
          return 1;
        }
        return 0;
      });
    } else if(field == "major"){
      this.roster.sort((a,b)=>{
        var nameA = a.major.toUpperCase();
        var nameB = b.major.toUpperCase();
        if(nameA < nameB){
          return -1;
        }
        if( nameA > nameB){
          return 1;
        }
        return 0;
      });
    } else if(field == "hours"){ 
      this.roster.sort((a,b)=>a.hours - b.hours);
    } else if(field=="gpa"){
      this.roster.sort((a,b)=>a.gpa - b.gpa);
    }


  }

  deleteStudent(sid:number){
  this.studentService.deleteStudent(sid).subscribe(data =>{
      this.updateListData();
    },    error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");
    });
    this.roster = this.roster.filter(m =>m.sid !== sid);
  }
}
