import { Component, OnInit } from '@angular/core';
import { Review } from '../review';
import { Reviewer } from '../reviewer';
import { ReviewsService } from '../reviews.service';
import { ReviewerService } from '../reviewer.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {
  review: Review = new Review();
  reviewers: Reviewer[] = [];
  
  sid: number = -1;
  

  constructor(private reviewService: ReviewsService, 
    private reviewerService: ReviewerService, private router: Router, private route: ActivatedRoute) { }


  ngOnInit(): void {
    //get this sid
    if (this.route.snapshot.paramMap.get('rid') !== null) {
      //set sid to the parsed integer form of 
      this.sid = parseInt(this.route.snapshot.paramMap.get('rid')!);

      //get the student
      this.reviewService.getReview(this.sid).subscribe(
        data => {
          this.review = data;
                },
        error => {
          if (error.status = '404') {
            console.error("student doesn't exist");
            this.router.navigateByUrl('reviewlist');
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
      //sid wasn't present, so bug out
      this.router.navigateByUrl('reviewerlist');
      
    } 
    this.reviewerService.getAllReviewers().subscribe(data =>{
      this.reviewers = data;
    });
  }
  onSubmit(): void {
    console.log(this.review);
    this.reviewService.editReview(this.review).subscribe(data => {
      this.router.navigate(['/reviewlist']);
    },
      error => {
        if (error.status == 302) {
        } else {
          console.error("Error!");
          console.error("ErrorMessage: " + error.message);
          console.error("ErrorStatus: " + error.status);
          console.error("ErrorName:" + error.name);
          console.error("");
        }
      });
  }
  onSelect(value: string) {
    this.review.stars = parseFloat(value);
  }
  onUIDSelect(value: string){
    this.review.uid = parseInt(value);
  }

}
