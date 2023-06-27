import { Injectable, createEnvironmentInjector } from '@angular/core';
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
  //Review Related Functions
  getAllReviews(): Observable<Review[]>{
    return this.http.get<Review[]>(this.reviewURL);
  }
  getReview(rid:number):Observable<Review>{
    return this.http.get<Review>(this.reviewURL + "/" + rid);
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
  //Reviewer Releated Functions
  getAllReviewers(): Observable<Reviewer[]>{
    return this.http.get<Reviewer[]>(this.reviewerURL);
  }
  getReviewer(uid:number): Observable<Reviewer>{
    return this.http.get<Reviewer>(this.reviewerURL + "/" + uid);
  }
  createReviewer(critic:Reviewer):Observable<Reviewer>{
    return this.http.post<Reviewer>(this.reviewerURL, critic);
  }
  editReiewer(critic:Reviewer):Observable<Reviewer>{
    return this.http.put<Reviewer>(this.reviewerURL, critic);
  }
  deleteReviewer(uid:number):Observable<Reviewer>{
    return this.http.delete<Reviewer>(this.reviewerURL + "/" +uid);
  }
}
