import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { LoginComponent } from './login/login.component';
import { LoggedInGuard } from './logged-in.guard';

const routes: Routes = [
  {path:"login", component:LoginComponent},
 { path:"studentlist",component:StudentListComponent,canActivate:[LoggedInGuard]},
 { path:"students/:sid",component:StudentDetailComponent,canActivate:[LoggedInGuard]} ,
 { path:"addStudent",component:AddStudentComponent,canActivate:[LoggedInGuard]} ,
 { path:"editStudent/:sid",component:EditStudentComponent,canActivate:[LoggedInGuard]} ,
 { path:"",redirectTo: 'login', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
