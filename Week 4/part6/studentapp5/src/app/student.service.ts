import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student';



@Injectable({
  providedIn: 'root'
})

export class StudentService {
  //server string to simply service requests
  serverURL: string;

  constructor(private http: HttpClient) {
    this.serverURL = "http://localhost:7777/students";
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.serverURL);
  }

  getStudent(sid : number): Observable<Student> {
    return this.http.get<Student>(this.serverURL + "/" + sid);
  }

  deleteStudent(sid: number): Observable<Student> {
    return this.http.delete<Student>(this.serverURL + "/" + sid);
  }

  createStudent(student: Student): Observable<Student>   {
    return this.http.post<Student>(this.serverURL, student);
  }

  updateStudent(student: Student): Observable<Student>   {
    return this.http.put<Student>(this.serverURL, student);
  }





}
