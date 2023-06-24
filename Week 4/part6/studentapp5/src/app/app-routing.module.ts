import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStudentComponent } from './add-student/add-student.component';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { StudentListComponent } from './student-list/student-list.component';

const routes: Routes = [
  { path: 'studentlist', component: StudentListComponent },
  { path: 'students/:sid', component: StudentDetailComponent}, 
  { path: 'addstudent', component: AddStudentComponent}, 
  { path: 'editstudent/:sid', component: EditStudentComponent}, 
  { path: '', redirectTo: 'studentlist', pathMatch: 'full'}
  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
