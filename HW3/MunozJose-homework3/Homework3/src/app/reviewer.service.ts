import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reviewer } from './reviewer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewerService {
  private reviewerURL: string;

  constructor(private http:HttpClient) {
    this.reviewerURL = "http://localhost:7777/reviewers";
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
  editReviewer(critic:Reviewer):Observable<Reviewer>{
    return this.http.put<Reviewer>(this.reviewerURL, critic);
  }
  deleteReviewer(uid:number):Observable<Reviewer>{
    return this.http.delete<Reviewer>(this.reviewerURL + "/" +uid);
  }
}
