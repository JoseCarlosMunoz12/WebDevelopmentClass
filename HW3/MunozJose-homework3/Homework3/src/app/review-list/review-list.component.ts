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
  review: any;
  constructor(private reviewService:ReviewsService){}
  ngOnInit(): void {
    this.updateListData();
  }
  updateListData(){
    this.reviewService.getAllReviews().subscribe(data =>{
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
}
