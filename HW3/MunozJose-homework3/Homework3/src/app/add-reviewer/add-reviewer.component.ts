import { Component, OnInit } from '@angular/core';
import { ReviewerService } from '../reviewer.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Reviewer } from '../reviewer';
import { from } from 'rxjs';

@Component({
  selector: 'app-add-reviewer',
  templateUrl: './add-reviewer.component.html',
  styleUrls: ['./add-reviewer.component.css']
})
export class AddReviewerComponent implements OnInit {
  reviewer: Reviewer = new Reviewer();
  uidAlreadeyExists:Boolean = false;
  constructor(private reviewerService: ReviewerService, private router: Router){}
  ngOnInit(): void {
    
  }
  onSubmit(form: NgForm):void{
    console.log(this.reviewer);
    this.reviewerService.createReviewer(this.reviewer).subscribe(
      data =>{
        this.uidAlreadeyExists = false;
        this.router.navigate(['/reviewerlist']);
      },
      error=>{
        if(error.status == 302){
          this.uidAlreadeyExists = true;
          form.controls['uid'].reset();
        } else {
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
