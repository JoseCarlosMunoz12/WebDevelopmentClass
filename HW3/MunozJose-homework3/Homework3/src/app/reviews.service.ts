import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from './review';
import { Reviewer } from './reviewer';
@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  private reviewURL: string;
  private reviewerURL: string;
  constructor(private http:HttpClient) { 
    this.reviewURL = "http://localhost:7777/reviews";
    this.reviewerURL = "http://localhost:7777/reviewers";
  }
  getAllReviews(): Observable<Review[]>{
    return this.http.get<Review[]>(this.reviewURL);
  }
  getAllReviewers(): Observable<Reviewer[]>{
    return this.http.get<Reviewer[]>(this.reviewerURL);
  }
}
