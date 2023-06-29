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
  constructor(private reviewerService: ReviewerService) { }

  ngOnInit(): void {
    this.updateListData();

  }
  updateListData() {
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
  sortBy(field: string) {
    if (field == "uid") {
      this.reviewers.sort((a, b) => a.uid - b.uid);
    } else if (field == "name") {
      this.reviewers.sort((a, b) => {
        var nameA = a.name.toUpperCase();
        var nameB = b.name.toUpperCase();
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
        return 0;
      });
    }
  }
}
