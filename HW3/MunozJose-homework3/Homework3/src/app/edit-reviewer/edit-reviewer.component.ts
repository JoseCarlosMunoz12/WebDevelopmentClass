import { Component, OnInit } from '@angular/core';
import { Reviewer } from '../reviewer';
import { ReviewerService } from '../reviewer.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-reviewer',
  templateUrl: './edit-reviewer.component.html',
  styleUrls: ['./edit-reviewer.component.css']
})
export class EditReviewerComponent implements OnInit{
  reviewer: Reviewer = new Reviewer();
  uid: number = -1;
  constructor(private reviewerService: ReviewerService,
    private router: Router, private route: ActivatedRoute ){}
  ngOnInit(): void {
    if( this.route.snapshot.paramMap.get('uid') !== null){
      this.uid = parseInt(this.route.snapshot.paramMap.get('uid')!);
      this.reviewerService.getReviewer(this.uid).subscribe(
        data => {
          this.reviewer = data;
        },
        error => {
          if(error.status == '404'){
            console.error("student doesn't exist");
            this.router.navigateByUrl('reviewerlist');
          }else {          
            console.error("Error!");
            console.error("ErrorMessage: " + error.message);
            console.error("ErrorStatus: " + error.status);
            console.error("ErrorName:" + error.name);
            console.error(""); 
          }
        }
      );
    } else {
      this.router.navigateByUrl('reviewerlist');
    }
  }

  onSubmit(): void{
    console.log(this.reviewer);
    this.reviewerService.editReviewer(this.reviewer).subscribe(
      data => {
        this.router.navigateByUrl('reviewerlist');
      },
      error => {
        if(error == 302){
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
