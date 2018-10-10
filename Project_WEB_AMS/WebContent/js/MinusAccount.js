/**
 * 상속개념 활용한 MinusAccount 안할
 */

function MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney) {
	// MinusAccount
	Account.call(this, accountNum, accountOwner, passwd);

	this.restMoney = restMoney - borrowMoney;
	this.borrowMoney = borrowMoney;
}

MinusAccount.prototype = new Account(null, null, null, 0);

MinusAccount.prototype.constructor = MinusAccount;


MinusAccount.prototype.toString = function() {
	return "마이너스 계좌" + this.accountNum + this.accountOwner + this.restMoney + this.borrowMoney;
}