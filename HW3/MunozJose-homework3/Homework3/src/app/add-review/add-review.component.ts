import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Review } from '../review';
import { ReviewsService } from '../reviews.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {
  review: Review = new Review();
  ridAlreadyExists:Boolean = false;
  uidExists = true;
  constructor (private reviewService: ReviewsService, private router: Router){}
  ngOnInit(): void {    
  }
  onSubmit(form: NgForm): void{
    console.log(this.review);
    this.reviewService.createReview(this.review).subscribe(
      data =>{
        this.ridAlreadyExists = false;
        this.router.navigate(['/reviewlist']);
      },
      error=>{
        if(error.status == 302){
          this.ridAlreadyExists =true;
          form.controls['rid'].reset();
        } else{        
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
