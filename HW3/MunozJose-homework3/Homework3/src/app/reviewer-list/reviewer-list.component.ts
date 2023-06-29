import { Component, OnInit } from '@angular/core';
import { Reviewer } from '../reviewer';
import { ReviewsService } from '../reviews.service';
import { ReviewerService } from '../reviewer.service';

@Component({
  selector: 'app-reviewer-list',
  templateUrl: './reviewer-list.component.html',
  styleUrls: ['./reviewer-list.component.css']
})
export class ReviewerListComponent implements OnInit {
  reviewers: Reviewer[] = [];
  constructor(private reviewerService: ReviewerService){}

  ngOnInit(): void {
    this.updateListData();
    
  }
  updateListData(){
    this.reviewerService.getAllReviewers().subscribe(
      data => {
        this.reviewers = data;
      },
      error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");

      }
    );
  }
  sortBy(field: string){
  }
  deleteStudent(uid:number){
  this.reviewerService.deleteReviewer(uid).subscribe(data =>{
      this.updateListData();
    },    error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");
    });
    this.reviewers = this.reviewers.filter(m =>m.uid !== uid);
  }

}
