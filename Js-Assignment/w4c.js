/* Filename: [ name of this file].js
   Target html: [ what is the html file(s) linked to this js] 
   Purpose : [ a html file may have multiple js files. What does this one do?]
   Author: [ your name]
   Date written: [ ]
   Revisions:  [ your name, what, when]
*/

// [ brief comment on what the function does]
function init() {
    var btnSend = document.getElementById('send');
    btnSend.onclick = processMark;
    console.log('program continue...');
}

function processMark(){
    console.log('processMark');
    var studentID = document.getElementById('studentID').value;
    var subjMark = document.getElementById('subjMark').value;
    subjMark = Number(subjMark);
    var subjGrade = markToGrade(subjMark);

    console.log(subjGrade);
    document.getElementById('outStudentId').innerHTML = studentID;
    document.getElementById('outSubjectMark').innerHTML = subjMark;

    if(subjGrade == 'X'){
    document.getElementById('outSubjectMark').innerHTML = ' Entered mark is invalid';
    }

    return false;
}

function markToGrade(mark){
    var grade;
    if(mark < 0 || mark > 100){
        grade = 'X';
    }else if(mark < 50){
        grade = 'F';
    }else if(mark < 60){
        grade = 'P';
    }else if(mark < 70){
        grade = 'C';
    }else if(mark < 80){
        grade = 'D';
    }else if(mark <= 100){
        grade = 'HD';
    }
    return grade;
}


window.onload = init;  
