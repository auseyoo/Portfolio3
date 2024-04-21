<!--
	@File Name: prmtEmplReg
	@File 설명 : 판촉관리 > 판촉사원관리 > 상세
	@UI ID : UI-APRO-0102
	@작성일 : 2022.03.04
	@작성자 : kjin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="content">
		<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>
		
		<form id="frm" name="frm" method="post" onsubmit="return false;" >
			<input type="hidden" name="prmtEmplSeq" id="prmtEmplSeq" value="${param.p_prmtEmplSeq }"/>
			<input type="hidden" name="prmtEmplHisSeq" id="prmtEmplHisSeq" value="${prmtEmplInfo.prmtEmplHisSeq }"/>
			<input type="hidden" name="telNo" id="telNo" />
			<input type="hidden" name="org_cntrDt" id="org_cntrDt" value="${prmtEmplInfo.cntrDt}" />
			<input type="hidden" name="org_cntrEndDt" id="org_cntrEndDt" value="${prmtEmplInfo.cntrEndDt}" />
			<input type="hidden" name="org_bhfcSeq" id="org_bhfcSeq" value="${prmtEmplInfo.bhfcSeq}" />
			<input type="hidden" name="emplSecCd" id="emplSecCd" value="${prmtEmplInfo.emplSecCd }">
			
			<div class="titTopArea">
				<h2 class="tit01">판촉사원 상세 <a href="#" class="favor" title="즐겨찾기"></a></h2>
				
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
                                <input type="text" class="inp" name="prmtEmplCd" id="prmtEmplCd" readonly="readonly" value="${prmtEmplInfo.prmtEmplCd }">
                            </td>
                            <th scope="row">
                                <label for="emplSecNm">판촉사원구분</label>
                            </th>
                            <td>
                            	<input type="text" class="inp" name="emplSecNm" id="emplSecNm" readonly="readonly" value="${prmtEmplInfo.emplSecNm }">
                                <%-- <select class="sel w160" name="emplSecCd" id="emplSecCd">
                                    <c:forEach items="${emplSeclList}" var="i" >
                                    	<c:choose>
                                    		<c:when test="${i.commCd == prmtEmplInfo.emplSecCd}">
                                    			<option value="${i.commCd}" selected="selected">${i.commDesc4}</option>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<option value="${i.commCd}" >${i.commDesc4}</option>
                                    		</c:otherwise>
                                    	</c:choose>
									</c:forEach>
                                </select> --%>
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="emplNm" class="required" title="필수입력">성명</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="emplNm" id="emplNm" maxlength="20" value="${prmtEmplInfo.emplNm }">
                            </td>
                            <th scope="row">
                                <label for="cntrYn">계약여부</label>
                            </th>
                            <td>
                                <select class="sel w160" name="cntrYn" id="cntrYn">
                                	<option value="Y" ${prmtEmplInfo.cntrYn == 'Y' ? 'selected="selected"' : '' }>계약중</option>
                                   	<option value="N" ${prmtEmplInfo.cntrYn == 'N' ? 'selected="selected"' : '' }>계약완료</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="telNo1" class="required" title="필수입력">연락처</label>
                            </th>
                            <td>
                                <div class="formWrap">
                                	<c:set var="tel" value="${fn:split(prmtEmplInfo.telNo, '-')}" />
                                    <select class="sel" id="telNo1">
                                        <option value="010" ${tel[0] == '010' ? 'selected="selected"' : '' }>010</option>
                                        <option value="011" ${tel[0] == '011' ? 'selected="selected"' : '' }>011</option>
                                    </select>                                            
                                    <span class="dash"></span>
                                    <input type="text" class="inp" id="telNo2" maxlength="4" value="${tel[1]}" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this); Common.cmmVldFocusMove(this, 'telNo3');">
                                    <span class="dash"></span>
                                    <input type="text" class="inp" id="telNo3" maxlength="4" value="${tel[2]}" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                                </div>   
                            </td>
                            <th scope="row">
                                <label for="datepicker03" class="required" title="필수입력">생년월일</label>
                            </th>
                            <td>
                                <div class="dateWrap">
									<input type="text" class="inp " name="brthdy" id="datepicker03" maxlength="10" value="" >
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
                                    <input type="text" class="inp" name="zipCd" id="zipCd" readonly="readonly" value="${prmtEmplInfo.zipCd}" >
                                	<button type="button" class="postBtn" title="우편번호 찾기" onclick="fnSetPostcode('#zipCd', '#addr1');"></button>
								</div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="addr1">주소</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="addr1" id="addr1" readonly="readonly" value="${prmtEmplInfo.addr1}" >
                            </td>
                            <th scope="row">
                                <label for="addr2">상세주소</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="addr2" id="addr2" maxlength="50" value="${prmtEmplInfo.addr2}" >
                            </td>
                        </tr>
                        <tr>
                        	<th scope="row">
                                <label for="bhfcSeq">지점</label>
                            </th>
                            <td colspan="3">
                                <select class="sel w160" name="bhfcSeq" id="bhfcSeq">
									<c:forEach items="${bhfcList}" var="i" >
										<option value="${i.bhfcSeq}" ${i.bhfcSeq == prmtEmplInfo.bhfcSeq ? 'selected="selected"' : '' }>${i.bhfcNm}</option>
									</c:forEach>
								</select>
                            </td>
                        </tr>
                        <tr>
                        	 <th scope="row">
                                <label for="newHoffHop">신규계약 홉단가(본사)</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="newHoffHop" id="newHoffHop" maxlength="10" value="${prmtEmplInfo.newHoffHop}" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="datepicker" class="required" title="필수입력">계약일</label>
                            </th>
                            <td>
                            	<div class="formWrap type02">
	                                <div class="dateWrap">
										<input type="text" class="inp " name="cntrDt" id="datepicker" maxlength="10" value="${prmtEmplInfo.cntrDt}" >
	                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker"></button>
									</div>
									<p class="txt01 pColor02">계약일 변경시 판촉사원코드가 재발번 됩니다.</p>
								</div>
                            </td>
                        </tr>
						<tr>
							<th scope="row">
                                <label for="recntrHoffHop">재계약 홉단가(본사)</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="recntrHoffHop" id="recntrHoffHop" maxlength="10" value="${prmtEmplInfo.recntrHoffHop}" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="datepicker02" class="required" title="필수입력">계약종료일</label>
                            </th>
                            <td>
                                <div class="dateWrap">
									<input type="text" class="inp " name="cntrEndDt" id="datepicker02" maxlength="10" value="${prmtEmplInfo.cntrEndDt}" >
                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker02"></button>
								</div>
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="inp_pay">일당</label>
                            </th>
                            <td>
                                <input type="text" class="inp ar" name="chpdyCt" id="chpdyCt" maxlength="10" value="${prmtEmplInfo.chpdyCt}" style="IME-MODE:disabled;" onkeyup="Common.cmmVldNumChk(this);">
                            </td>
                            <th scope="row">
                                <label for="endRmk">계약종료사유</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="endRmk" id="endRmk" value="${prmtEmplInfo.endRmk}" >
                            </td>
                        </tr>
						<tr>
                            <th scope="row">
                                <label for="rmk">비고</label>
                            </th>
                            <td colspan="3">
                                <input type="text" class="inp" name="rmk" id="rmk" maxlength="50" value="${prmtEmplInfo.rmk}" >
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
		</form>
		
		<div class="titleArea">
			<h3 class="tit01">근무 이력 </h3>
		</div>
		
		<div class="txtInfo pColor02">
            ※ 판촉사원명과 생년월일을 기준으로 근무이력이 표시됩니다.
        </div>
		
		<!-- grid -->
		<div class="girdBox h205">
			<div id="grid_wrap"></div>
		</div>
		<!-- grid -->
	</div>
	
	<script type="text/javascript">
		
		var myGridID;
		var brthdy = strToDate("${prmtEmplInfo.brthdy}");
		var cntrDt = strToDate("${prmtEmplInfo.cntrDt}");
		var cntrEndDt = strToDate("${prmtEmplInfo.cntrEndDt}");
		
		$(document).ready(function() {
			
			// AUIGrid 생성 후 반환 ID
			createAUIGrid(columnLayout);
			
			// 저장 버튼 클릭
			$("#saveBtn").click(function() {
				fn_save();
			});
			
			$("#datepicker03").data('daterangepicker').setStartDate(brthdy);
			$("#datepicker").data('daterangepicker').setStartDate(cntrDt);
			$("#datepicker02").data('daterangepicker').setStartDate(cntrEndDt);

			// 데이터 요청, 요청 성공 시 AUIGrid 에 데이터 삽입합니다.  
			fn_search();
			
		});
		
		var columnLayout = [{
				dataField : "prmtEmplHisSeq",
				headerText : "판촉사원 근무이력 시퀀스",
				visible: false,
				editable : false
			},{
				dataField : "prmtEmplSeq",
				headerText : "판촉사원 시퀀스",
				visible: false,
				editable : false
			},{
				dataField : "bhfcSeq",
				headerText : "지점 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "emplSeq",
				headerText : "대리점 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "prmtEmplCd",
				headerText : "판촉사원코드",
				width : "11%",
				editable : false
			},{
				dataField : "bhfcNm",
				headerText : "지점",
				width : "11%",
				editable : false
			},{
				dataField : "newHoffHop",
				headerText : "신규계약 홉단가(본사)",
				width : "15%",
				editable : false
			},{
				dataField : "recntrHoffHop",
				headerText : "재계약 홉단가(본사)",
				width : "15%",
				editable : false
			},{
				dataField : "chpdyCt",
				headerText : "일당",
				width : "15%",
				editable : false
			},{
				dataField : "cntrDt",
				headerText : "계약일",
				width : "11%",
				editable : false
			},{
				dataField : "cntrEndDt",
				headerText : "계약종료일",
				width : "11%",
				editable : false
			},{
				dataField : "endRmk",
				headerText : "종료사유",
				width : "11%",
				editable : false
			}
		];
		
		// AUIGrid 를 생성합니다.
	    function createAUIGrid(columnLayout) {
	        // 그리드 속성 설정
	        var gridPros = {
				headerHeight : 29,	
				rowHeight : 29,
				//showRowCheckColumn : false,
				
				// rowIdField 설정
				rowIdField : "agenEmplSeq",
				
				// 편집 가능 여부 (기본값 : false)
				editable : true,
				wrapSelectionMove : true,
				editingOnKeyDown : true, // 키보드 입력으로 편집 모드 진입 (기본값:true임;)
				
				// 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
				enterKeyColumnBase : true,
				
				// 셀 선택모드 (기본값: singleCell)
				selectionMode : "singleRow",
				
				// 컨텍스트 메뉴 사용 여부 (기본값 : false)
				useContextMenu : true,                
				
				// 필터 사용 여부 (기본값 : false)
				enableFilter : true,
				
				// 그룹핑 패널 사용
				useGroupingPanel : false,
				
				// 그룹핑 또는 트리로 만들었을 때 펼쳐지게 할지 여부 (기본값 : false)
				displayTreeOpen : true,
				
				// 트리 컬럼(즉, 폴딩 아이콘 출력 칼럼) 을 인덱스1번으로 설정함(디폴트 0번임)
				//treeColumnIndex : 1,
				
				noDataMessage : "출력할 데이터가 없습니다.",
				
				groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다."
			};
			
			// 실제로 #grid_wrap 에 그리드 생성
			myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
			
	    }
		
		function fn_search() {
			$.ajax({
				url : "/prmt/prmtEmplHisList.do", 
				type : 'POST', 
				data : $("#frm").serialize(),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					AUIGrid.setGridData(myGridID, data);
					//$("#listCnt").text(data.length);
				}, // success 
				error : function(xhr, status) {
					alert(xhr + " : " + status);
				},
				complete  : function(xhr, status){
					AUIGrid.removeAjaxLoader(myGridID);
		        }
			});
		}
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
				url : "/prmt/prmtEmplDtlUpt.do", 
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