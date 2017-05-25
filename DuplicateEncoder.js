/*
The goal of this exercise is to convert a string to a new string where each character in the new string is '(' if that character appears only once in the original string, or ')' if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

Examples:

"din" => "((("

"recede" => "()()()"

"Success" => ")())())"

"(( @" => "))(("

*/

//Solution:

function duplicateEncode(word){
    word = word.toLowerCase();
    var letter = [];
    for(var i=0;i<word.length;i++){
      var r = new RegExp(word[i].replace(/[^a-zA-Z0-9]/g,"\\" + word[i]),'g');
      if(word.match(r).length>1){
        letter.push(')');
      } else {
        letter.push('(');
      }
    }
    return letter.join('');
}

//Test:
Test.assertEquals(duplicateEncode("din"),"(((");
Test.assertEquals(duplicateEncode("recede"),"()()()");
Test.assertEquals(duplicateEncode("Success"),")())())","should ignore case");
Test.assertEquals(duplicateEncode("(( @"),"))((");
