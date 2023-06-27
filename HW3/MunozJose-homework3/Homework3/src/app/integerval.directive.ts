import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, FormControl } from '@angular/forms'

@Directive({
  selector: '[intval]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: IntegervalDirective, multi: true }
  ]

})
export class IntegervalDirective {

  
  constructor() { }
  
  validate(c: FormControl) {
    //gets the value
    let v: number = +c.value;
    //if it is not a number…
    if (isNaN(v)) {
      return { 'notint': true, 'notintmess': 'needs to be integer' }
    }
    //now we mod with 1… if 0, it’s an integer
    if (v % 1 != 0) {
      return { 'notint': true, 'notintmess': 'needs to be integer' }
    }
    //it is an integer so return null
    return null;
  }


}