import { Component, OnInit } from '@angular/core';
import { ReviewsService } from '../reviews.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../review';
import { Reviewer } from '../reviewer';
import { ReviewerService } from '../reviewer.service';

@Component({
  selector: 'app-review-detail',
  templateUrl: './review-detail.component.html',
  styleUrls: ['./review-detail.component.css']
})
export class ReviewDetailComponent implements OnInit {

    constructor(private reviewService:ReviewsService,
       private router: Router, private route: ActivatedRoute){}
    rid: number = -1;
    uid: number = -1; 
    review:Review = new Review();
    ngOnInit(): void {
      //get this sid
      if (this.route.snapshot.paramMap.get('rid') !== null) {
        //set sid to the parsed integer form of 
        this.rid = parseInt(this.route.snapshot.paramMap.get('rid')!);
        //get the student
        this.reviewService.getReview(this.rid).subscribe(
          data => {
            this.review = data;
          },
          error => {
            if (error.status = '404') {
              console.error("student doesn't exist");
              this.router.navigateByUrl('reviews');
            } else {
              console.error("Error!");
              console.error("ErrorMessage: " + error.message);
              console.error("ErrorStatus: " + error.status);
              console.error("ErrorName:" + error.name);
              console.error(""); 
            }
          }
        );
      } else {
        //rid wasn't present, so bug out
        this.router.navigateByUrl('reviews');
        
      }
    }

}
