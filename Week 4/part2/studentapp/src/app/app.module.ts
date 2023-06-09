import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentService } from './student.service';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { FormsModule } from '@angular/forms';
import { MaxvalDirective } from './maxval.directive';
import { MinvalDirective } from './minval.directive';
import { IntegervalDirective } from './integerval.directive';
import { UserService } from './user.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    StudentDetailComponent,
    AddStudentComponent,
    EditStudentComponent,
    MaxvalDirective,
    MinvalDirective,
    IntegervalDirective,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [StudentService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
