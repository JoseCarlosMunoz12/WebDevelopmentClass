import { Directive, Input } from '@angular/core';
import { Validator, NG_VALIDATORS, FormControl } from '@angular/forms'
@Directive({
  selector: '[maxval]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: MaxvalDirective, multi: true }
  ]
})
export class MaxvalDirective {
  
  @Input("maxval") maxval:string;

  max: number;

  constructor() { }
  
  validate(c: FormControl) {
    this.max = parseInt(this.maxval);
    //gets the value
    let v: number = +c.value;
    //if it is not a number…
    if (isNaN(v)) {
      return { 'maxval': true, 'maxvalmess': 'You must enter a number' }
    }
    //now let's check to see if value is greater than given
    if (v > +this.max) {
      return { 'maxval': true, 'maxvalmess': 'You must enter a number no greater than ' + this.maxval  }
    }
    //all good so return null
    return null;
  }
}
