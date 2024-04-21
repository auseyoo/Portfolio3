<!--
	@File Name: prmtEmplReg
	@File 설명 : 판촉관리 > 판촉사원관리 > 등록
	@UI ID : UI-APRO-0102
	@작성일 : 2022.03.04
	@작성자 : kjin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="content">
		<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>
		
		<form id="frm" name="frm" method="post" onsubmit="return false;" >
			<input type="hidden" name="telNo" id="telNo" />
			
			<div class="titTopArea">
				<h2 class="tit01">판촉사원 등록 <a href="#" class="favor" title="즐겨찾기"></a></h2>
				
				<div class="location">
					<ul>
						<li><i class="home"></i></li>
						<li>판촉관리</li>
						<li>판촉 사원 관리</li>
					</ul>
				</div>
			</div>
			
			<div class="btnGroup ar">
				<button type="button" class="inquBtn" name="saveBtn" id="saveBtn">저장</button>
			</div>
			
			<div class="titleArea right">
				<h3 class="tit01">상세 정보</h3>
				<p class="txt01"><i class="icoRequir"></i>필수 입력 항목입니다.</p>
			</div>
			
			<div class="tblWrap">
				<table class="tbl">
					<caption>판촉사원 상세</caption>
					<colgroup>
						<col style="width:170px;">
						<col>
						<col style="width:170px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">
                                <label for="prmtEmplCd">코드</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="prmtEmplCd" id="prmtEmplCd" disabled="disabled">
                            </td>
                            <th scope="row">
                                <label for="emplSecCd">판촉사원구분</label>
                            </th>
                            <td>
                                <%-- <select class="sel w160" name="emplSecCd" id="emplSecCd">
                                    <c:forEach items="${emplSeclList}" var="i" >
										<option value="${i.commCd}">${i.commDesc4}</option>
									</c:forEach>
                                </select> --%>
                                <select class="sel w160" name="emplSecCd" id="emplSecCd">
									<option value="AGENT_PRMT_EMPL">별동대</option>
                                </select>
                                
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="emplNm" class="required" title="필수입력">성명</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="emplNm" id="emplNm" maxlength="20">
                            </td>
                            <th scope="row">
                                <label for="cntrYn">계약여부</label>
                            </th>
                            <td>
                                <select class="sel w160" name="cntrYn" id="cntrYn">
                                    <option value="Y">계약중</option>
                                    <option value="N">계약완료</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="telNo1" class="required" title="필수입력">연락처</label>
                            </th>
                            <td>
                                <div class="formWrap">
                                    <select class="sel" id="telNo1">
                                        <option value="010">010</option>
                                        <option value="011">011</option>
                                    </select>                                            
                                    <span class="dash"></span>
                                    <input type="text" class="inp" id="telNo2" maxlength="4" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this); Common.cmmVldFocusMove(this, 'telNo3');">
                                    <span class="dash"></span>
                                    <input type="text" class="inp" id="telNo3" maxlength="4" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                                </div>   
                            </td>
                            <th scope="row">
                                <label for="datepicker03" class="required" title="필수입력">생년월일</label>
                            </th>
                            <td>
                                <div class="dateWrap">
									<input type="text" class="inp " name="brthdy" id="datepicker03" maxlength="10" value="10/24/1984" >
                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker03"></button>
								</div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="inp_post_num">우편번호</label>
                            </th>
                            <td colspan="3">
                                <div class="postWrap w200">
                                    <!-- <input type="text" id="inp_post_num" class="inp" disabled="">
                                    <button type="button" class="postBtn" title="우편번호 찾기"></button> -->
                                    <input type="text" class="inp" name="zipCd" id="zipCd" readonly="readonly">
                                	<button type="button" class="postBtn" title="우편번호 찾기" onclick="fnSetPostcode('#zipCd', '#addr1');"></button>
								</div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="addr1">주소</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="addr1" id="addr1" readonly="readonly">
                            </td>
                            <th scope="row">
                                <label for="addr2">상세주소</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="addr2" id="addr2" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                        	<th scope="row">
                                <label for="bhfcSeq">지점</label>
                            </th>
                            <td colspan="3">
                                <select class="sel w160" name="bhfcSeq" id="bhfcSeq">
									<c:forEach items="${bhfcList}" var="i" >
										<option value="${i.bhfcSeq}">${i.bhfcNm}</option>
									</c:forEach>
								</select>
                            </td>
                        </tr>
                        <tr>
                        	 <th scope="row">
                                <label for="newHoffHop">신규계약 홉단가(본사)</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="newHoffHop" id="newHoffHop" maxlength="10" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="datepicker" class="required" title="필수입력">계약일</label>
                            </th>
                            <td>
                                <div class="dateWrap">
									<input type="text" class="inp " name="cntrDt" id="datepicker" maxlength="10" value="10/24/1984" >
                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker"></button>
								</div>
                            </td>
                        </tr>
						<tr>
							<th scope="row">
                                <label for="recntrHoffHop">재계약 홉단가(본사)</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="recntrHoffHop" id="recntrHoffHop" maxlength="10" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="datepicker02" class="required" title="필수입력">계약종료일</label>
                            </th>
                            <td>
                                <div class="dateWrap">
									<input type="text" class="inp " name="cntrEndDt" id="datepicker02" maxlength="10" value="10/24/1984" >
                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker02"></button>
								</div>
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="inp_pay">일당</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="chpdyCt" id="chpdyCt" maxlength="10" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="endRmk">계약종료사유</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="endRmk" id="endRmk" >
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="rmk">비고</label>
                            </th>
                            <td colspan="3">
                                <input type="text" class="inp" name="rmk" id="rmk" maxlength="50" >
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
		</form>
	</div>
	
	<script type="text/javascript">
		
		
		$(document).ready(function() {
			
			// 저장 버튼 클릭
			$("#saveBtn").click(function() {
				fn_save();
			});
			
			
		});
		
		function fn_save() {
			
			if (Common.isEmpty($("#emplNm").val()))
			{
				alert("성명을 입력해 주세요.");
				$("#emplNm").focus();
				return;
			}
			
			if (Common.isEmpty($("#telNo1").val()))
			{
				alert("연락처를 입력해 주세요.");
				$("#telNo1").focus();
				return;
			}
			
			if (Common.isEmpty($("#telNo2").val()))
			{
				alert("연락처를 입력해 주세요.");
				$("#telNo2").focus();
				return;
			}
			
			if (Common.isEmpty($("#telNo3").val()))
			{
				alert("연락처를 입력해 주세요.");
				$("#telNo3").focus();
				return;
			}
			
			if (Common.isEmpty($("#datepicker").val()))
			{
				alert("계약일을 입력해 주세요.");
				$("#datepicker").focus();
				return;
			}
			
			if (Common.isEmpty($("#datepicker02").val()))
			{
				alert("계약종료일을 입력해 주세요.");
				$("#datepicker02").focus();
				return;
			}
			
			$("#telNo").val($("#telNo1").val() + $("#telNo2").val() + $("#telNo3").val());
			
			var param = $("#frm").serializeObject();
			
			$.ajax({
				url : "/prmt/prmtEmplMngSave.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				success : function(data) {
					if (Common.isNotEmpty(data.res) && data.res && data.updateCnt > 0)
					{
						alert("저장 되었습니다.");
						location.href = "/prmt/prmtEmplMng.do";
					}
					else
					{
						alert("저장 실패 하였습니다.\n다시 시도해 주세요.");
					}
					
				}, // success 
				error : function(xhr, status, errorThrown) {
					if ( Common.IsJsonString(xhr.responseText) )
					{
						var message = JSON.parse(xhr.responseText).message;
						alert(message);
					}
					else
					{
						alert("처리중 오류가 발생 하였습니다.");
					}
				}
			});
		}
		
		
	</script>