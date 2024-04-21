<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

function loginFormSubmit() {

	var formData = $("#form1").serialize();

	console.log("formData=="+JSON.stringify(formData));

	formData = formData + "&username=" + $("#bizNo").val() + "," + $("#emplCd").val();

	$.ajax({
		cache : false,
		url : "/loginProcess.do",
		type : 'POST',
		data : formData,
		success : function(data) {

			console.log("data=="+JSON.stringify(data));

			var result = '';
			$.each(data,function(index,item){
				result +='<tr><td>'+item["title"]+'</td>'
				result +='<td>'+item["name"]+'</td></tr>'
			});

			$("#tbody").html("");
			$("#table1 > tbody:last").append(result)

		}, // success

		error : function(xhr, status) {

			console.log("xhr==" + JSON.stringify(xhr));
			alert(xhr + " : " + status);
		}
	});

}


function changePass(){
	$("#loginform").attr("action","/changePassword.do").submit();
}

function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}


$(document).ready(function() {

	let loginStatus = '<c:out value="${status}"/>';

	if(loginStatus == "01"){
		alert("비밀번호가 일치하지 않습니다.");
	}else if(loginStatus == "02"){
		alert("계정정보를 확인해주세요.");
	}

	var userInputId = getCookie("userInputId");//저장된 쿠기값 가져오기
    $("input[name='username']").val(userInputId); 
     
    if($("input[name='username']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩                                           
        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 발생시
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("input[name='username']").val();
            setCookie("userInputId", userInputId, 30); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("input[name='username']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            var userInputId = $("input[name='username']").val();
            setCookie("userInputId", userInputId, 30); // 7일 동안 쿠키 보관
        }
    });
});

</script>
<body>
	<div class="logWrap">
		<header>
            <h1><a href="#" class="logo" title="남양유업"></a></h1>         
		</header>

		<div class="contentWrap">
            <!-- content -->
            <div class="login_wrap">
                <div class="content">
                  <div class="login_title">
                    <h2 class="login_mainTit">남양유업 대리점 주문시스템</h2>
                    <span class="login_susTxt">Namyang online order system</span>
                  </div>
                  <form name="loginform" id="loginform" action="/loginProcess.do" class="form-signin" method="post">
                  <div class="login_gr">
                      <div class="login_box">
                      	<h3 class="login_subTit">Login</h3>
                        <div class="formWrap">
                            <label for="inp_name01" class="required hide">사번</label>
                            <input type="text" name="username" value="" id="username" class="inp type02" placeholder="사번">                            
                        </div>
                        <div class="formWrap">
                            <label for="inp_name03" class="required hide" title="필수입력">비밀번호</label>
                            <input type="password" id="password" class="inp" value="skadid123@" name="password" placeholder="비밀번호">
                        </div>
                        <div class="formWrap">
                             <button type="submit" name="" class="inquBtn loginBtn" id="inp_name04">로그인</button>
                        </div>
                        <div class="formWrap">
                            <div class="checkGroup">
                                <input type="checkbox" id="idSaveCheck" class="type01"><label for="idSaveCheck"><span>사번 저장</span></label>
                            </div>
                        </div>                                
                    </div>
                    <div class="login_bg"><img src="../images/img_login.png" class="login_bgimg" alt="로그인 이미지"/></div>
                  </div>
                  </form>       
                </div>
                
                 <footer>
                    COPYRIGHT-NAMYANG-DAIRY-PRODUCTS-COLTD-ALL-RIGHT-RESERVED
                </footer>
            </div>
            <!-- content -->
		</div>
	</div>
</body>
