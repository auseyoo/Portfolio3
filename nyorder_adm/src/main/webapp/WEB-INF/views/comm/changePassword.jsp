<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<script>
    
function inputValidation(){
	
	var passwordRule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$/;
	if(!passwordRule.test($('#password').val().trim())){
		console.log("비밀번호 실패");
		return false;
	}
	
	if($('#password').val() != $('#password2').val()){
		console.log("비밀번호 확인 실패 :: " + $('#password').val() + ", :: " + $('#password2').val());
		return false;
	}
	console.log("비밀번호 성공");	
	return true;
	
}    

function changePasswordBtn(){
	if(inputValidation()){
		$("#passform").submit();
		
	}	
}
    
    
</script>
</head>

<body>
	<div class="logWrap pwWrap">
		<header>
            <h1><a href="#" class="logo" title="남양유업"></a></h1>         
		</header>

		<div class="contentWrap">
            <!-- content -->
            <div class="login_wrap">
                <div class="content">
                    <div class="login_box">
                        <h2 class="login_mainTit">비밀번호 변경</h2>
                        <span class="login_susTxt">Change password</span>       
                        <form name="passform" id="passform" action="/savePassword.do" class="form-signin" method="post">
                        
                        	<input type="hidden" id="admCd" class="inp" name="admCd" value="<c:out value='${userInfo.admCd}'/>">                        	
                        	<input type="hidden" id="admSeq" class="inp" name="admSeq" value="<c:out value='${userInfo.admSeq}'/>" >
                                        
	                        <div class="formWrap jEnd">
	                            
	                            <label for="inp_pw01" class="required badge" title="새 비밀번호">새 비밀번호</label>
	                            <input type="password" id="password" class="inp" name="password" minlength="4" maxlength="12">
	                           
	                            <p class="inpText">영문+숫자+특수문자 조합으로 8자리 이상</p>
	                        </div>
	                        
	                        <div class="formWrap jEnd">
	                            
	                            <label for="inp_pw02" class="required badge" title="비밀번호 확인">비밀번호 확인</label>
	                            <input type="password" id="password2" class="inp" name="password2" minlength="6" maxlength="12">
	                            
	                            <p class="inpText">비밀번호를 재입력해주세요.</p>
	                        </div>
	                        <div class="formWrap jCenter">
	                             <button type="submit" name="" class="inquBtn large" id="btn_pwEdit" onclick="changePasswordBtn();" >비밀번호 변경</button>
	                        </div>
                        </form>
                                     
                    </div>
                       
                </div>
                
                 <footer>
                    COPYRIGHT-NAMYANG-DAIRY-PRODUCTS-COLTD-ALL-RIGHT-RESERVED
                </footer>
            </div>
            <!-- content -->
		</div>
	</div>
</body>
</html>