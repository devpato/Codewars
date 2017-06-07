/*Build Tower

Build Tower by the following given argument:
number of floors (integer and always greater than 0).

Tower block is represented as *

Python: return a list;
JavaScript: returns an Array;
C#: returns a string[];
PHP: returns an array;
C++: returns a vector<string>;
Haskell: returns a [String];
Have fun!*/

/*for example, a tower of 3 floors looks like below

[
  '  *  ', 
  ' *** ', 
  '*****'
]
and a tower of 6 floors looks like below

[
  '     *     ', 
  '    ***    ', 
  '   *****   ', 
  '  *******  ', 
  ' ********* ', 
  '***********'
]*/

//SOLUTION:
function towerBuilder(nFloors) {
  var arr = []
  var floor = "";
  var bricks = nFloors + (nFloors-1);
  var temp = bricks;
  for (var i = nFloors; i>0 ; i--){    
        floor  ="*".repeat(bricks);        
        if (temp !== bricks ) { 
            floor  = " ".repeat(bricks-(floor.replace(/[^*]/g, "").length-1)) + floor + " ".repeat(bricks-(floor.replace(/[^*]/g, "").length-1));
         }
        bricks = bricks-2;
        arr.unshift(floor);
  }
  return arr;
}

//TESTING:
Test.assertEquals(JSON.stringify(towerBuilder(1)), JSON.stringify(["*"]));
Test.assertEquals(JSON.stringify(towerBuilder(2)), JSON.stringify([" * ","***"]));
Test.assertEquals(JSON.stringify(towerBuilder(3)), JSON.stringify(["  *  "," *** ","*****"]));

