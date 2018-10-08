/**
 * 학생 객체의 추상화 
 */

var kosta = {};
kosta.school = {};
kosta.school.Student= function(name, korean, math, english, science){
	this.name = name;
	this.korean = korean;
	this.math = math;
	this.english = english;
	this.science = science;

}
