import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerListComponent } from './reviewer-list.component';

describe('ReviewerListComponent', () => {
  let component: ReviewerListComponent;
  let fixture: ComponentFixture<ReviewerListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewerListComponent]
    });
    fixture = TestBed.createComponent(ReviewerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
