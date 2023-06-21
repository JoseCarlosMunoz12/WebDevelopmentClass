import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { StudentListComponent } from './student-list/student-list.component';

const routes: Routes = [
  { path: 'studentlist', component: StudentListComponent },
  { path: 'students/:sid', component: StudentDetailComponent}, 
  { path: '', redirectTo: 'studentlist', pathMatch: 'full'}
  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
