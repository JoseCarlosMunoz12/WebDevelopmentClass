import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerDetailComponent } from './reviewer-detail.component';

describe('ReviewerDetailComponent', () => {
  let component: ReviewerDetailComponent;
  let fixture: ComponentFixture<ReviewerDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewerDetailComponent]
    });
    fixture = TestBed.createComponent(ReviewerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
