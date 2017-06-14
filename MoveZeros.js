/*
Write an algorithm that takes an array and moves all of the zeros to the end, preserving the order of the other elements.
moveZeros([false,1,0,1,2,0,1,3,"a"]) // returns[false,1,1,2,1,3,"a",0,0]
*/

//SOLUTION:
var moveZeros = function (arr) {
  for(var i = 0 ; i < arr.length ; i++) {
    if (arr[i] == 0) {
      var temp = arr[i];
      arr.splice(i,1);
      arr.push(temp);
    }
  }

  return arr;
}


//TEST:
Test.assertEquals(
  JSON.stringify(moveZeros([1,2,0,1,0,1,0,3,0,1])),
  JSON.stringify([ 1, 2, 1, 1, 3, 1, 0, 0, 0, 0 ])
);
