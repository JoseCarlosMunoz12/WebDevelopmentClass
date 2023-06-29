import { Component, OnInit } from '@angular/core';
import { ReviewsService } from '../reviews.service';
import { Review } from '../review';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {
  reviews : Review[] = [];
  constructor(private reviewService:ReviewsService){}
  ngOnInit(): void {
    this.updateListData();
  }
  updateListData(){
    this.reviewService.getAllReviews().subscribe(data => {
      this.reviews = data;
    } ,
    error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");
    });
  }
  sortBy(field: string){
  }
  deleteStudent(rid:number){
  this.reviewService.deleteReview(rid).subscribe(data =>{
      this.updateListData();
    },    error => {
      console.error("Error");
      console.error("Error" + error.message);
      console.error("ErrorStatus: " + error.status);
      console.error("ErrorName" + error.name);
      console.error("");
    });
    this.reviews = this.reviews.filter(m =>m.rid !== rid);
  }
}
