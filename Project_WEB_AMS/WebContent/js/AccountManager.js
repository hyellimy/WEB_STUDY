/**
 * 은행 계좌 관리 <계좌번호, 해당계좌정보> 로 정보 관리
 */
var kosta = {};
kosta.bank = {};
kosta.bank.AccountManager = {};

function AccountManager() {
   // 배열 선언
   this.accounts = new Array();
}

// 계좌 추가 메소드 add
AccountManager.prototype.add = function(acc) {
   if (acc instanceof Account) {
        this.accounts.push(acc);
   } else {
      return;
   }
}

// 계좌 목록 출력 list
AccountManager.prototype.listAll = function(type) {
	var tempm = [];
	var temp = [];

	for ( var i in this.accounts) {
		if (this.accounts[i] instanceof MinusAccount)
			tempm.push(this.accounts[i]);
		else {
			temp.push(this.accounts[i]);
		}
	}
	
	//마이너스일 경우 
	if (type == 2) {
		return tempm;
	// 입출금 계좌의 경우 
	} else if (type == 1) {
		return temp;
	} else {
		return this.accounts;
	}

}

// 계좌 번호 조회 : 해당 계좌 배열 반환 get
// 배열 내 어카운트
AccountManager.prototype.get = function (accountNum) {
   // 계좌 배열 반환
   for ( var i in this.accounts) {
         if (this.accounts[i].accountNum === accountNum) {
            return this.accounts[i];
         }
   }
   return null;
}

// 계좌주명 검색 : 해당 계좌 배열 반환 search
AccountManager.prototype.search = function(accountOwner) {
   for ( var i in this.accounts) {
         if (accountOwner === this.accounts[i].accountOwner) {
            return this.accounts[i];
         }
      }
      return null;
}

// 해당 계좌 삭제 메서드 remove
AccountManager.prototype.remove = function(accountNum) {
   for (var i in this.accounts) {
      if (this.accounts[i].accountNum === accountNum) {
         this.accounts.splice(i, 1);
         return this.accounts[i];
      }
   }
   return null;
}