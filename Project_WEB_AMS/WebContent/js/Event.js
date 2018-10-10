/**
 * 이벤트 처리를 위한 function
 */

var manager = new AccountManager();
var account = new Account();
var minus = new MinusAccount();

/** 윈도우 실행 초기화 */
window.onload = function() {
	init();
	eventRegist();
	tableClear();
	ErrorCheck();
}
/** init 인원 추가 초기화 */
function init() {
	tables = document.getElementById('table').contentWindow.document
			.getElementById('my-tbody');

	// 인원 추가 하기
	manager.add(new Account("1111-1111", "일혜림", 0000, 5000000));
	manager.add(new MinusAccount("1111-2222", "이혜림", 1113, 60000, 100));
	manager.add(new Account("1111-3333", "삼혜림", 1515, 5000000));
	manager.add(new MinusAccount("1111-4444", "사혜림", 1114, 80000, 500));
	manager.add(new Account("1111-5555", "오혜림", 1112, 51000));
	manager.add(new MinusAccount('1111-6666', '육혜림', 1111, 2000, 8000));
	manager.add(new Account('1111-7777', '칠혜림', 1111, 2000));
	manager.add(new MinusAccount('1111-8888', '팔혜림', 1111, 2000, 5000));

}

/** 이벤트 등록 메서드 */
function eventRegist() {
	// 계좌 종류 선택
	document.getElementById('accountKind').onchange = accountType;
	// 조회 버튼 (get)
	document.getElementById('search_bt').onclick = getAccount;
	// 전체조회
	document.getElementById('listAll_bt').onclick = showListAll;
	// 삭제
	document.getElementById('delete_bt').onclick = deleteAccount;
	// 이름으로 조회하기 (search)
	document.getElementById('search_name_bt').onclick = searchAccount;
	// 신규등록
	document.getElementById('addNew_bt').onclick = addAccount;
}

// 계좌 종류 선택
function accountType(type) {
	// 입출금계좌의 경우
	var target = document.getElementById("accountKind");
	var kind = target.options[target.selectedIndex].value;
	//마이너스 계좌일 경우 대출금액 필드 활성화 
	if (kind == "minus") {
		document.getElementById('borrowMoney').disabled = false;
		document.getElementById('borrowMoney').placeholder = "예) 10,000";
	//입출금 계좌의 경우 대출금액 필드 비활성화
	} else {
		document.getElementById('borrowMoney').disabled = true;
		document.getElementById('borrowMoney').placeholder = " ";
	}
	// 오류발생 알람
	document.getElementsByClassName("Error")[0].style.display = "none";

}

/** 테이블 업데이트 초기화를 위한 Clear 메소드 */
function tableClear() {
	tables.innerHTML = ' ';
}

/** 계좌번호로 조회 버튼 이벤트 생성 (Get메서드) */
function getAccount(e) {
	tableClear();
	ErrorCheck();

	var accountContent = document.getElementById('accountNum').value; // 데이터입력값
	var accounts = manager.get(document.getElementById('accountNum').value); // 데이터배열
																				

	// Switch Case선택을 위한 변수 선언
	var target = document.getElementById("accountKind");
	var kind = target.options[target.selectedIndex].value;
	// 형식 지정
	var exp = /\d{4}-\d{4}$/;


	// 데이터 입력 값이 null일 경우
	if (accountContent == null || accountContent.length === 0) {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "번호가 입력되지 않았습니다. 계좌번호를 입력하세요";
		document.getElementsByClassName("Error")[1].style.display = "block";
		// 데이터 형식 확인
	} else if (!exp.test(accountContent)) {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "형식에 맞추어 입력하세요. 예) 1111-1111";
		document.getElementsByClassName("Error")[1].style.display = "block";

		// 데이터 배열에 없는 값을 조회한 경우
	} else if (accounts == null || accounts.length === 0) {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "해당하는 계좌가 없습니다. 계좌 조회 후 다시 입력하세요.";
		document.getElementsByClassName("Error")[1].style.display = "block";

		// 계좌종류를 선택하지 않도록 설정
	} else if (kind != "선택하세요") {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "계좌종류 선택을 해제시켜주세요. 해제 후 조회 가능합니다.";
		document.getElementsByClassName("Error")[1].style.display = "block";

		// 데이터 형식에 맞추어 값을 조회하였을 경우 조회 완료
	} else if (exp.test(accountContent) === true) {
		document.getElementsByClassName("Error")[5].style.display = "block";
		document.getElementsByClassName("Error")[5].lastChild.nodeValue = "계좌번호 조회 완료";
		addAccountlist(accounts);
	}
}

/** 예금주명으로 조회 버튼 이벤트 (Search 메서드) */
function searchAccount() {
	tableClear();
	ErrorCheck();

	var exp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

	var name = document.getElementById('accountOwner').value; // 데이터 입력 값
	var acc = manager.search(document.getElementById('accountOwner').value); // 데이터 배열 값

	// 입력 값 데이터가 비어 있을 경우
	if (name == null || name.length === 0) {
		document.getElementsByClassName("Error")[2].lastChild.nodeValue = "입력된 이름이 없습니다. 이름을 입력하세요";
		document.getElementsByClassName("Error")[2].style.display = "block";
	// 예금주명을 한글로 입력하지 않았을 경우
	} else if (!exp.test(name)) {
		document.getElementsByClassName("Error")[2].lastChild.nodeValue = "예금주명은 한글로 입력해 주세요.";
		document.getElementsByClassName("Error")[2].style.display = "block";

		// 데이터 배열에 없는 값을 조회한 경우
	} else if (acc == null || acc.length === 0) {
		document.getElementsByClassName("Error")[2].lastChild.nodeValue = "해당하는 계좌주명이 없습니다. 계좌 조회 후 다시 확인하여 입력하세요.";
		document.getElementsByClassName("Error")[2].style.display = "block";

	} else if (exp.test(name) === true){
		addAccountlist(acc);
	document.getElementsByClassName("Error")[5].style.display = "block";
	document.getElementsByClassName("Error")[5].lastChild.nodeValue = "계좌 조회 결과를 확인하세요.";
	}
}

/** 계좌번호로 삭제 버튼 이벤트 (remove 메서드) */
function deleteAccount() {
	tableClear();
	ErrorCheck();

	var accountValue = document.getElementById('accountNum').value; // 데이터 입력 값
	var account = manager.remove(document.getElementById('accountNum').value); // 데이터 메서드 배열
																				

	var exp = /\d{4}-\d{4}$/; // 계좌번호 형식 지정

	document.getElementsByClassName("Error")[1].lastChild.nodeValue = "삭제가 완료되었습니다.";

	// 데이터 입력 값이 Null인 경우
	if (accountValue == null || accountValue.length === 0) {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = " 입력된 계좌번호가 없습니다. 계좌번호를 입력하세요";
		document.getElementsByClassName("Error")[1].style.display = "block";
		// 데이터 형식 확인
	} else if (!exp.test(accountValue)) {
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "형식에 맞추어 입력하세요. 예) 1111-1111";
		document.getElementsByClassName("Error")[1].style.display = "block";
		// 전체 계좌 목록에 없는 값을 조회한 경우 오류 발생
	} else if(account == null || account.length === 0 ){
		document.getElementsByClassName("Error")[1].lastChild.nodeValue = "해당하는 계좌가 없습니다. 계좌 조회 후 다시 입력하세요.";
		document.getElementsByClassName("Error")[1].style.display = "block";
		// 데이터 형식과 일치하는 값을 조회한 경우
	} else if (exp.test(accountValue) === true) {
		document.getElementsByClassName("Error")[5].lastChild.nodeValue = "해당 계좌가 삭제되었습니다. 전체조회 버튼을 눌러 확인하세요.";
		document.getElementsByClassName("Error")[5].style.display = "block";
		// 데이터 배열에 없는 값을 조회한 경우
}
}
	 


/** 계좌 신규 등록 버튼 이벤트 (add 메서드) */
function addAccount() {
	tableClear();
	ErrorCheck();

	// Switch Case선택을 위한 변수 선언
	var target = document.getElementById("accountKind");
	var kind = target.options[target.selectedIndex].value;

	// 입력 값 내용
	var accountNum = document.getElementById('accountNum').value;
	var accountOwner = document.getElementById('accountOwner').value;
	var passwd = document.getElementById('passwd').value;
	var restMoney = document.getElementById('deposit').value;
	var borrowMoney = document.getElementById('borrowMoney').value;

	
	// 데이터 유효성 검사 
	var expName = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var expAccountNum =  /\d{4}-\d{4}$/;
	var expPasswd = /\d{4}$/;
	
	//Switch 함수 활용한 Add 메서드
	switch (kind) {
	/** CASE 1 : 계좌종류 (계좌를 선택하세요) 선택 후 신규등록 버튼 실행 */
	case "선택하세요":
		document.getElementsByClassName("Error")[0].lastChild.nodeValue = "계좌 종류를 선택하지 않았습니다. 계좌종류를 선택하세요";
		document.getElementsByClassName("Error")[0].style.display = "block";

		break;

	/** CASE 2 : 계좌종류 (입출금계좌) 선택 후 신규등록 버튼 실행 */
	case "plus":
	
		var accountContent = document.getElementById('accountNum').value; //데이터 입력 값 
		var pw = document.getElementById('passwd').value; //데이터 입력 값 
		var accounts = manager.get(document.getElementById('accountNum').value); // 데이터배열	
		var acc = manager.search(document.getElementById('accountOwner').value); // 데이터 배열 값

		// 비어있는 계좌번호
		if (accountNum == null || accountNum.length === 0) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "계좌번호가 입력되지 않았습니다. 계좌번호를 입력하세요";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		// 계좌번호 데이터 형식 설정
		} else if (!expAccountNum.test(accountContent)) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "형식에 맞추어 계좌번호를 입력하세요. 예) 1111-1111";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		// 계좌번호 중복 값 확인 설정 
		} else if (accounts != null) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "중복 계좌번호가 존재합니다. 확인 후 다시 적어주세요.";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		//이름 필드가 비어있을 경우
		} else if (accountOwner == null || accountOwner.length == 0) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "이름이 입력되지 않았습니다. 이름을 입력하세요";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountOwner').focus();
		//이름이 한글이 아닐 경우 	
		} else if (!expName.test(accountOwner)) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "이름을 한글로 입력해 주세요. ";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountOwner').focus();
		//중복된 이름을 작성하였을 경우 
		} else if (acc != null) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "중복된 예금주명이 존재합니다. 확인 후 다시 적어주세요.";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountNum').focus();
		//비밀번호 필드가 비어있을 경우
		} else if (passwd == null || passwd.length == 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "비밀번호를 입력하세요";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('passwd').focus();
		//비밀번호 데이터 형식 설정 (네자리 숫자가 아닐 경우)
		} else if (!expPasswd.test(pw)) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "비밀번호는 네자리 숫자만 입력 가능합니다.";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('passwd').focus();	
		//입금금액 필드가 비어있을 경우 
		} else if (restMoney == null || restMoney.length == 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "입금금액을 입력하세요";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('deposit').focus();
		
		//입금 금액에 마이너스 값이 입력되었을 경우 
		} else if (restMoney < 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "입금 금액은 마이너스 금액이 아닙니다. ";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('deposit').focus();
		//Account 계좌 등록
		} else {
			manager.add(new Account(accountNum, accountOwner, passwd,restMoney));
			document.getElementsByClassName("Error")[5].lastChild.nodeValue = "계좌 등록이 성공되었습니다. 전체조회를 통해 확인하세요. ";
			document.getElementsByClassName("Error")[5].style.display = "block";
		}
		break;

	/** CASE 3 : 계좌종류 (마이너스 계좌) 선택 후 신규등록 선택 상황 */
	case "minus":
		var accountContent = document.getElementById('accountNum').value; //데이터 입력 값 
		var pw = document.getElementById('passwd').value; //데이터 입력 값 
		var accounts = manager.get(document.getElementById('accountNum').value); // 데이터배열	
		var acc = manager.search(document.getElementById('accountOwner').value); // 데이터 배열 값

		// 비어있는 계좌번호
		if (accountNum == null || accountNum.length === 0) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "계좌번호가 입력되지 않았습니다. 계좌번호를 입력하세요";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		// 계좌번호 데이터 형식 설정
		} else if (!expAccountNum.test(accountContent)) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "형식에 맞추어 계좌번호를 입력하세요. 예) 1111-1111";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		// 계좌번호 중복 값 확인 설정 
		} else if (accounts != null) {
			document.getElementsByClassName("Error")[1].lastChild.nodeValue = "중복 계좌번호가 존재합니다. 확인 후 다시 적어주세요.";
			document.getElementsByClassName("Error")[1].style.display = "block";
			document.getElementById('accountNum').focus();
		//이름 필드가 비어있을 경우
		} else if (accountOwner == null || accountOwner.length == 0) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "이름이 입력되지 않았습니다. 이름을 입력하세요";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountOwner').focus();
		//이름이 한글이 아닐 경우 	
		} else if (!expName.test(accountOwner)) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "이름을 한글로 입력해 주세요. ";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountOwner').focus();
		//중복된 이름을 작성하였을 경우 
		} else if (acc != null) {
			document.getElementsByClassName("Error")[2].lastChild.nodeValue = "중복된 예금주명이 존재합니다. 확인 후 다시 적어주세요.";
			document.getElementsByClassName("Error")[2].style.display = "block";
			document.getElementById('accountNum').focus();
		//비밀번호 필드가 비어있을 경우
		} else if (passwd == null || passwd.length == 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "비밀번호를 입력하세요";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('passwd').focus();
		//비밀번호 데이터 형식 설정 (네자리 숫자가 아닐 경우)
		} else if (!expPasswd.test(pw)) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "비밀번호는 네자리 숫자만 입력 가능합니다.";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('passwd').focus();	
		//입금금액 필드가 비어있을 경우 
		} else if (restMoney == null || restMoney.length == 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "입금금액을 입력하세요";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('deposit').focus();
		
		//입금 금액에 마이너스 값이 입력되었을 경우 
		} else if (restMoney < 0) {
			document.getElementsByClassName("Error")[3].lastChild.nodeValue = "입금 금액은 마이너스 금액이 아닙니다. ";
			document.getElementsByClassName("Error")[3].style.display = "block";
			document.getElementById('deposit').focus();
		//대출 금액 필드가 비어있을 경우 
		} else if (borrowMoney == null || borrowMoney.length == 0) {
			document.getElementsByClassName("Error")[4].lastChild.nodeValue = "대출금액을 입력하지 않았습니다. 대출금액을 입력하세요";
			document.getElementsByClassName("Error")[4].style.display = "block";
			document.getElementById('borrowMoney').focus();
		// MinusAccount 등록
		} else {
			manager.add(new MinusAccount(accountNum, accountOwner, passwd,restMoney, borrowMoney));
			document.getElementsByClassName("Error")[5].lastChild.nodeValue = "계좌 등록이 성공되었습니다. 전체조회를 통해 확인하세요. ";
			document.getElementsByClassName("Error")[5].style.display = "block";
		}
		break;
	}
}

/** 전체 리스트 조회  (listAll 메서드) */
function showListAll() {
	tableClear();
	ErrorCheck();
	
	
	
	// 입출금계좌의 경우
	var target = document.getElementById("accountKind");
	var kind = target.options[target.selectedIndex].value;
	
	//마이너스 계좌일 경우 마이너스 계좌 리스트 인출
	if (kind == "minus") {
		var lists = manager.listAll(2);
		for (var i = 0; i < lists.length; i++) {
			addAccountlist(lists[i]);
			console.log(i + " : " + lists[i]);
		}
	//입출금 계좌의 경우 입출금 계좌의 리스트 인출
	} else if (kind == "plus") {
		var lists = manager.listAll(1);
		for (var i = 0; i < lists.length; i++) {
			addAccountlist(lists[i]);
		}
		
	//전체 조회의 경우
	} else {
		var lists = manager.listAll();
		for (var i = 0; i < lists.length; i++) {
			addAccountlist(lists[i]);
		}
	}
	
		
}

/** 계좌 목록 출력을 위한 동적 테이블 조건 */
function addAccountlist(account) {
	var result = "";
	// 마이너스 계좌일때
	if (account instanceof MinusAccount) {
		result += '<tr>';
		result += '<td class="even">마이너스</td>'
		result += '<td class="even">' + account.accountNum + '</td>';
		result += '<td class="even">' + account.accountOwner + '</td>';
		result += '<td class="even">' + account.restMoney + '</td>';
		result += '<td class="even">' + account.borrowMoney + '</td>';
		result += '</tr>';
		tables.innerHTML += result;

		// 입출금 계좌의 경우

	} else if (account instanceof Account) {
		result += '<tr>';
		result += '<td>입출금</td>'
		result += '<td>' + account.accountNum + '</td>';
		result += '<td>' + account.accountOwner + '</td>';
		result += '<td>' + account.restMoney + '</td>';
		result += '</tr>';
		tables.innerHTML += result;
	}
}

/** 에러 발생 화면 비활성화 설정 */
function ErrorCheck() {
	document.getElementsByClassName("Error")[0].style.display = "none";
	document.getElementsByClassName("Error")[1].style.display = "none";
	document.getElementsByClassName("Error")[2].style.display = "none";
	document.getElementsByClassName("Error")[3].style.display = "none";
	document.getElementsByClassName("Error")[4].style.display = "none";
	document.getElementsByClassName("Error")[5].style.display = "none";
}