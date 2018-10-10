/**
 * 
 */
function Account(accountNum, accountOwner, passwd, restMoney) {
	this.accountNum = accountNum;
	this.accountOwner = accountOwner;
	this.passwd = passwd;
	this.restMoney = restMoney;
}

Account.bankName = "KOSTA은행";

// Prototype에 메소드 저장 기능
// 계좌 개설 기능
// 계좌에 입금하는 기능
Account.prototype.deposit = function(money) {
	restMoney += moeny;
	return restMoney;
}
// 계좌에서 돈 출금하는 기능
Account.prototype.withdraw = function(moeny) {
	restMoney -= money;
	return restMoney;
}
// 계좌 비밀번호와 입력 비밀번호의 일치 확인 기능
Account.prototype.checkPasswd = function(pw) {
	return this.passwd == pw; // true
}
// toString() 메소드
Account.prototype.toString = function() {
	return "입출금계좌" + this.accountNum + this.accountOwner + this.passwd
			+ this.restMoney;
}
// equals 메소드
Account.prototype.equals = function(account) {
	if (this.toString() === account.toString()) {
		return true;
	}
	return false;
}