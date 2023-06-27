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

@NgModule({
  declarations: [
    AppComponent,
    ReviewerListComponent,
    ReviewListComponent,
    AddReviewComponent,
    MaxvalDirective,
    MinvalDirective,
    IntegervalDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ReviewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
