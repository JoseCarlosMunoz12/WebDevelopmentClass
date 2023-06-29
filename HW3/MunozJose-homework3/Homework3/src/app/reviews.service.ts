import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from './review';
@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  private reviewURL: string;
  private singleReviewerURL: string;
  constructor(private http:HttpClient) { 
    this.reviewURL = "http://localhost:7777/reviews";
    this.singleReviewerURL = "http://localhost:7777/review";
  }
  //Review Related Functions
  getAllReviews(): Observable<Review[]>{
    return this.http.get<Review[]>(this.reviewURL);
  }
  getReview(rid:number):Observable<Review>{
    return this.http.get<Review>(this.singleReviewerURL + "/" + rid);
  }
  getReviewsByTitle(title:string):Observable<Review[]>{
    return this.http.get<Review[]>(this.reviewURL + "/" + title);
  }
  getReviewsAbove3Stars():Observable<Review[]>{
    return this.http.get<Review[]>(this.reviewURL + "/Above3Stars");
  }
  createReview(review:Review):Observable<Review>{
    return this.http.post<Review>(this.reviewURL,review);
  }
  editReview(review:Review):Observable<Review>{
    return this.http.put<Review>(this.reviewURL,review);
  }
  deleteReview(rid:number):Observable<Review>{
    return this.http.delete<Review>(this.reviewURL + "/" + rid);
  }
}
