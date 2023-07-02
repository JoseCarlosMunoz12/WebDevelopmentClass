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
  sortBy(field: string) {
    if (field == "uid") {
      this.reviews.sort((a, b) => a.uid - b.uid);
    } else if (field == "title") {
      this.reviews.sort((a, b) => {
        var nameA = a.title.toUpperCase();
        var nameB = b.title.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
        return 0;
      });
    } else if (field == "stars"){
      this.reviews.sort((a,b)=> a.stars - b.stars);
    } else if (field == 'rid'){
      this.reviews.sort((a,b) => a.rid - b.rid);
    }
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
