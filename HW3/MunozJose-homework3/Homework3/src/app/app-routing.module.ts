import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReviewListComponent } from './review-list/review-list.component';
import { ReviewerListComponent } from './reviewer-list/reviewer-list.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { ReviewDetailComponent } from './review-detail/review-detail.component';
import { EditReviewComponent } from './edit-review/edit-review.component';
import { AddReviewerComponent } from './add-reviewer/add-reviewer.component';
import { EditReviewerComponent } from './edit-reviewer/edit-reviewer.component';

const routes: Routes = [
  { path:'reviewlist',component:ReviewListComponent },
  { path:'reviewerlist',component:ReviewerListComponent },
  { path:'addReview', component:AddReviewComponent},
  { path:'editReview/:rid', component:EditReviewComponent},
  { path: "reviews/:rid",component:ReviewDetailComponent},
  { path: "reviewers/:uid", component:ReviewerListComponent},
  { path: "addReviewer", component:AddReviewerComponent},
  { path: "editReviewer/:uid", component:EditReviewerComponent},
  { path:'',redirectTo: 'reviewlist', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
