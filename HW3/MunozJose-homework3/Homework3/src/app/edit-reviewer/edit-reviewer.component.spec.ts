import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReviewerComponent } from './edit-reviewer.component';

describe('EditReviewerComponent', () => {
  let component: EditReviewerComponent;
  let fixture: ComponentFixture<EditReviewerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditReviewerComponent]
    });
    fixture = TestBed.createComponent(EditReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
