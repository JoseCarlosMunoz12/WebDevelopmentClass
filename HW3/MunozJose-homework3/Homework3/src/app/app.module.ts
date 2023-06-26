import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReviewerListComponent } from './reviewer-list/reviewer-list.component';
import { ReviewListComponent } from './review-list/review-list.component';
import { ReviewsService } from './reviews.service';

@NgModule({
  declarations: [
    AppComponent,
    ReviewerListComponent,
    ReviewListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ReviewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
