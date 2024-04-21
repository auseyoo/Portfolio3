<!--
	@File Name: prmtEmplMng
	@File 설명 : 판촉관리 > 판촉사원관리
	@UI ID : UI-APRO-0101
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
			<input type="hidden" name="p_prmtEmplSeq" id="p_prmtEmplSeq" />
			<input type="hidden" name="p_bhfcSeq" id="p_bhfcSeq" />
			<input type="hidden" name="p_prmtEmplCd" id="p_prmtEmplCd" />
			
			<!-- 조회 -->
			<div class="inquiryBox">
				<dl>
					<dt>지점</dt>
					<dd>
						<div class="formWrap">
							<select class="sel" name="bhfcSeq" id="bhfcSeq">
								<option value="">전체</option>
								<c:forEach items="${bhfcList}" var="i" >
									<option value="${i.bhfcSeq}">${i.bhfcNm}</option>
								</c:forEach>
							</select>
						</div>
					</dd>
					<dt>판촉사원</dt>
					<dd>
						<div class="formWrap">
							<input type="text" class="inp w120 mr10" name="prmtEmplCd" id="prmtEmplCd" title="판촉사원코드 입력" placeholder="판촉사원코드" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();">
                            <input type="text" class="inp w160" name="emplNm" id="emplNm" title="성명 입력" placeholder="성명" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();">
						</div>
					</dd>
					<dt>계약여부</dt>
					<dd>
						<div class="formWrap">
							<select class="sel" name="cntrYn" name="cntrYn" >
								<option value="">전체</option>
								<option value="Y">계약중</option>
								<option value="N">계약종료</option>
							</select>
							<button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
						</div>
					</dd>
				</dl>
				
				<div class="btnGroup right">
					<button type="button" class="inquBtn" name="saveBtn" id="saveBtn">등록</button>
				</div>
			</div>
			<!-- 조회 -->
		</form>
		
		<div class="titleArea">
			<p class="numState">
				<em>총 <span class="pColor01 fb" id="listCnt">24</span></em> 건 이 조회되었습니다.
			</p>
		</div>
		
		<!-- grid -->
		<div class="girdBox">
			<div id="grid_wrap"></div>
		</div>
		<!-- grid -->
	</div>
	
	<script type="text/javascript">
		
		var myGridID;
		
		$(document).ready(function() {
			// AUIGrid 생성 후 반환 ID
			createAUIGrid(columnLayout);
			// 데이터 요청, 요청 성공 시 AUIGrid 에 데이터 삽입합니다.        
			
			//AUIGrid.setGridData(myGridID, gridData);
			fn_search();
			
			// 조회 버튼 클릭
			$("#searchBtn").click(function() {
				fn_search();
			});
			
			// 저장 버튼 클릭
			$("#saveBtn").click(function() {
				fn_save();
			});
			
			
		});
		
		var columnLayout = [{
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
				dataField : "prmtEmplCd",
				headerText : "판촉사원코드",
				width : "15%",
				style: "auiLink",
				editable : false
			},{
				dataField : "emplNm",
				headerText : "성명",
				width : "15%",
				style: "auiLink",
				editable : false
			},{
				dataField : "bhfcNm",
				headerText : "지점",
				width : "10%",
				editable : false
			},{
				dataField : "telNo",
				headerText : "연락처",
				width : "10%",
				editable : false
			},{
				dataField : "brthdy",
				headerText : "생년월일",
				width : "10%",
				editable : false
			},{
				dataField : "emplSecNm",
				headerText : "판촉사원구분",
				width : "10%",
				editable : false
			},{
				dataField : "cntrNm",
				headerText : "계약여부",
				width : "10%",
				editable : false
			},{
				dataField : "cntrDt",
				headerText : "계약일",
				width : "10%",
				editable : false
			},{
				dataField : "cntrEndDt",
				headerText : "계약종료일",
				width : "10%",
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

			// 에디팅 시작 이벤트 바인딩
			AUIGrid.bind(myGridID, "cellClick", function(event) {
				if(event.dataField == "prmtEmplCd" || event.dataField == "emplNm") {
					var item = AUIGrid.getItemByRowIndex(myGridID, event.rowIndex);
					fn_goDetail(item.prmtEmplSeq, item.bhfcSeq, item.prmtEmplCd);
				}
			});
	    }
		
		function fn_search() {
			$.ajax({
				url : "/prmt/prmtEmplMngList.do", 
				type : 'POST', 
				data : $("#frm").serialize(),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					AUIGrid.setGridData(myGridID, data);
					$("#listCnt").text(data.length);
				}, // success 
				error : function(xhr, status) {
					alert(xhr + " : " + status);
				},
				complete  : function(xhr, status){
					AUIGrid.removeAjaxLoader(myGridID);
		        }
			});
		}
		
		function fn_onEnterSubmit() {
			var keyCode = window.event.keyCode;
			if(keyCode == 13)
			{
				fn_search();
			}
		}
		
		function fn_save() {
			location.href = "/prmt/prmtEmplReg.do";
		}
		
		function fn_goDetail(prmtEmplSeq, bhfcSeq, prmtEmplCd) {
			$("#p_prmtEmplSeq").val(prmtEmplSeq);
			$("#p_bhfcSeq").val(bhfcSeq);
			$("#p_prmtEmplCd").val(prmtEmplCd);
			
			var frm = document.frm;
			frm.action = "/prmt/prmtEmplDtl.do";
			frm.submit();
		}
		
	</script>