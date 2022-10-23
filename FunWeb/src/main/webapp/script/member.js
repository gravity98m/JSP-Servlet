/**
 * 회원 정보의 유효성을 체크하기 위한 함수
 */
 function joinCheck(){
	if(document.fr.id.value == ""){
		alert('아이디를 써주세요');
		fr.id.focus();
		return false;
	}
	if(document.fr.name.value == ""){
		alert('이름을 써주세요');
		fr.name.focus();
		return false;
	}
	if(document.fr.pw.value == ""){
		alert('암호는 반드시 입력해야 합니다.');
		fr.pw.focus();
		return false;
	}
		
//	if(document.fr.pw.value != document.fr.pw_check.value){
//		alert('암호가 일치하지 않습니다.');
//		fr.pw.focus();
//		return false;
//	} //암호 확인 창이 있는 경우
	return true;	
}