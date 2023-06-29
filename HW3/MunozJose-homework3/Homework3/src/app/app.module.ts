import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReviewerListComponent } from './reviewer-list/reviewer-list.component';
import { ReviewListComponent } from './review-list/review-list.component';
import { ReviewsService } from './reviews.service';
import { AddReviewComponent } from './add-review/add-review.component';
import { MaxvalDirective } from './maxval.directive';
import { MinvalDirective } from './minval.directive';
import { IntegervalDirective } from './integerval.directive';
import { ReviewDetailComponent } from './review-detail/review-detail.component';
import { EditReviewComponent } from './edit-review/edit-review.component';
import { ReviewerService } from './reviewer.service';
import { ReviewerDetailComponent } from './reviewer-detail/reviewer-detail.component';
import { EditReviewerComponent } from './edit-reviewer/edit-reviewer.component';
import { AddReviewerComponent } from './add-reviewer/add-reviewer.component';

@NgModule({
  declarations: [
    AppComponent,
    ReviewerListComponent,
    ReviewListComponent,
    AddReviewComponent,
    MaxvalDirective,
    MinvalDirective,
    IntegervalDirective,
    ReviewDetailComponent,
    EditReviewComponent,
    ReviewerDetailComponent,
    EditReviewerComponent,
    AddReviewerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ReviewsService, ReviewerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
