<!-- 
	@File Name: 
	@File 설명 : 
	@UI ID : 
	@작성일 : 2022. 1. 13.
	@작성자 : JungsuKim
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<script type="text/javascript">

$(document).ready(function() {
	/* 	
	let fileName = '<c:out value="${fileName}"/>';
	
	if(fileName == ""){
		alert("업로드 실패");		
	}else {		
		alert(fileName + "파일이 성공적으로 업로드 완료");		
	} */
	$("#fileform").submit(function(event) {
		
		event.preventDefault();
		const mdbFiles = $("#mdbFile")[0].files;
		// 파일을 여러개 선택할 수 있으므로 files 라는 객체에 담긴다.
		console.log("mdbFiles: ", mdbFiles.files)

		if(mdbFiles.length === 0){
			alert("파일은 선택해주세요");
		    return false;
		}

		if( $("#fAgenSeq").val() == ""){
			alert("조회를 해주세요");
		    return false;
		}
		
		for(let i=0; mdbFiles.length > i; i++){
			let item = mdbFiles[i];
			
			let fileName = item.name;
			
			console.log("fileName.  : " +  fileName);
			
			console.log("fileName.substr(fileName.lastIndexOf() : " +  fileName.substr(fileName.lastIndexOf(".") + 1));
			if(fileName.substr(fileName.lastIndexOf(".") + 1) != "MDB"){
				alert("MDB파일만 선택해주세요");
				return false;
			}

		}
		
		this.submit();
		
	});
});
function fnFileSubmit(_saleCd, _agenCd){
	
	var uploadUrl	= "C:/Pams21/db/Pams21_01.MDB";
	if( _saleCd == "SI"){
		uploadUrl	= "C:/NewPams21/db/Pams21_01.MDB"
	}
	$("#fAgenSeq").val(_agenCd);
	$("#sibaPtag").html("업로드 경로 : "+uploadUrl);
	
}


function fnSelBizNo(){

	var bizNo 	= $("#inp_bizno01").val() + $("#inp_bizno02").val() + $("#inp_bizno03").val();
	var item 	= { "bizNo" : bizNo , "agenCd" : $("#agenCd").val() };

	$(".sibaDiv").hide();
	$(".sibaP").html("");
	
	 $.ajax({
		url : "/comm/selectBizno.do",
		type : 'POST',
		contentType : "application/json; charset=utf-8",
	    data : JSON.stringify(item),
	    dataType : "json",
		success : function(data) {
			console.log(data);
			if( data.saleCd == "SI" ){
				$("#siDiv").show();
				fnFileSubmit(data.saleCd, data.agenCd);
			}
			else if(data.saleCd == "BA"){
				$("#baDiv").show();
				fnFileSubmit(data.saleCd, data.agenCd);
			}
			else{
				alert("사업자 번호 및 거래처 코드 조회 불가.")
			}

		}, 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	}); 
}
			
</script>    
   
<body>
<div class="exTypeWrap">
	<!-- 사업자번호 input -->
	<div class="inquiryBox type02">
		<dl>
			<dt>MDB upload</dt>
			<dt>사업자번호</dt>
			<dd>
				<div class="formWrap">
					<input type="tel" maxlength="3" id="inp_bizno01" class="inp w120 mr10 " placeholder="394" value="394" >- 
					<input type="tel" maxlength="2" id="inp_bizno02" class="inp w160" placeholder="22" value="22" >- 
					<input type="tel" maxlength="5" id="inp_bizno03" class="inp w160" placeholder="00101" value="00101" >
				</div>
			</dd>
		</dl>
	
		<!-- 거래처코드 input -->
		<dl>
			<dt></dt>
			<dt>거래처코드</dt>
			<dd>
				<input type="tel" id="agenCd" class="inp" placeholder="1200302" value="1200302">
			</dd>
			<dd>
				<button type="button" class="comBtn" onclick="javascript:fnSelBizNo();">조회</button>
			</dd>
		</dl>
	</div>
</div>
<input type="hidden" id="agenSeq" name="agenSeq" >

<div id="siDiv" class="sibaDiv" style="display: none">
</div>
<div id="baDiv" class="sibaDiv" style="display: none">
</div>
<p id="sibaPtag" class="sibaP"></p>


<div class="logWrap">
	<form name="fileform" id="fileform" action="/comm/fileUploadProcess.do" class="form-signin" method="post"  enctype="multipart/form-data">
		<input type="file" name="mdbFile" id="mdbFile"/>
		<input type="hidden" id="fAgenSeq" name="fAgenSeq">
        <input type="submit" class="comBtn" value="업로1드"/>
	</form>
</div>


</body>
</html>