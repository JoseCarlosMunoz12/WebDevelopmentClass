import { Component, OnInit } from '@angular/core';
import { Reviewer } from '../reviewer';
import { ReviewsService } from '../reviews.service';

@Component({
  selector: 'app-reviewer-list',
  templateUrl: './reviewer-list.component.html',
  styleUrls: ['./reviewer-list.component.css']
})
export class ReviewerListComponent implements OnInit {
  reviewers: Reviewer[] = [];
  constructor(private reviewService: ReviewsService){}

  ngOnInit(): void {
    
  }

}
