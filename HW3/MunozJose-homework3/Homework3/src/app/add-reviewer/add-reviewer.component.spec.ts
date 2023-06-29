import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReviewerComponent } from './add-reviewer.component';

describe('AddReviewerComponent', () => {
  let component: AddReviewerComponent;
  let fixture: ComponentFixture<AddReviewerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddReviewerComponent]
    });
    fixture = TestBed.createComponent(AddReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
