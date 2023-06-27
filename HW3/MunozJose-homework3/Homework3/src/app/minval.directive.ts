import { Directive, Input } from '@angular/core';
import { Validator, NG_VALIDATORS, FormControl } from '@angular/forms'
@Directive({
  selector: '[minval]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: MinvalDirective, multi: true }
  ]
})
export class MinvalDirective {

  @Input("minval") minval:string;

  min: number;

  constructor() { }
  
    validate(c: FormControl) {
      this.min = parseInt(this.minval);
    //gets the value
    let v: number = +c.value;
    //if it is not a number…
    if (isNaN(v)) {
      return { 'minval': true, 'minvalmess': 'You must enter a number' }
    }
    //now let's check to see if value is greater than given
    if (v < +this.minval) {
      return { 'minval': true, 'minvalmess': 'You must enter a number no less than ' + this.minval  }
    }
    //all good so return null
    return null;
  }

}
