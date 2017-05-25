/*
Write a function that takes an (unsigned) integer as input, and returns the number of bits that are equal to one in the binary representation of that number.

Example: The binary representation of 1234 is 10011010010, so the function should return 5 in this case
*/

//SOLUTION:

var countBits = function(n) {
 var num = parseInt(n,10).toString(2).replace(/[^1]/g, "").length;
 return num = parseInt(num);
}

//TEST:

Test.assertEquals(countBits(0), 0);
Test.assertEquals(countBits(4), 1);
Test.assertEquals(countBits(7), 3);
Test.assertEquals(countBits(9), 2);
Test.assertEquals(countBits(10), 2);
