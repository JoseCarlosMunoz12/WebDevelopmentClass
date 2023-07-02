import { Component, OnInit } from '@angular/core';
import { ReviewerService } from '../reviewer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Reviewer } from '../reviewer';

@Component({
  selector: 'app-reviewer-detail',
  templateUrl: './reviewer-detail.component.html',
  styleUrls: ['./reviewer-detail.component.css']
})
export class ReviewerDetailComponent implements OnInit {
  uid: number = -1;
  reviewer: Reviewer = new Reviewer();
  constructor(private reviewerService:ReviewerService,
     private router: Router, private route: ActivatedRoute){}
  
  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get("uid")!= null){
      this.uid = parseInt(this.route.snapshot.paramMap.get('uid')!);
      this.reviewerService.getReviewer(this.uid).subscribe(
        data => {
          this.reviewer = data;
        },
        error =>{
          if (error.status = '404') {
            console.error("Reviewer doesn't exist");
            this.router.navigateByUrl('reviewers');
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
      this.router.navigateByUrl('reviewers');
    }
  }
}
