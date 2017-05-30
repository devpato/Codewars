/Write a function called validParentheses that takes a string of parentheses, and determines if the order of the parentheses is valid. validParentheses should return true if the string is valid, and false if it's invalid.

Examples:
validParentheses( "()" ) => returns true
validParentheses( ")(()))" ) => returns false
validParentheses( "(" ) => returns false
validParentheses( "(())((()())())" ) => returns true

All input strings will be nonempty, and will only consist of open parentheses '(' and/or closed parentheses ')'*/

//SOLUTION:
function validParentheses(parens){
  var open = parens.split("(").length-1;
  var closed = parens.split(")").length-1;
  if(open == closed) {
    return true
  } else {
    return false;
  }
}


//TEST:

Test.assertEquals(validParentheses( "()" ), true);
Test.assertEquals(validParentheses( "())" ), false);
