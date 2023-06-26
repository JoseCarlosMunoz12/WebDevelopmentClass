import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReviewListComponent } from './review-list/review-list.component';
import { ReviewerListComponent } from './reviewer-list/reviewer-list.component';

const routes: Routes = [
  { path:'reviewlist',component:ReviewListComponent },
  { path:'reviewerlist',component:ReviewerListComponent },
  { path:'',redirectTo: 'reviewlist', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
