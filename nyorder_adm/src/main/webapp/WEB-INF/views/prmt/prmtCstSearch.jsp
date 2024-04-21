<!-- 
	@File Name: mblPrintMng.jsp
	@File 설명 : 판촉 애음자 조회
	@UI ID : UI-PPRO-0301
	@작성일 : 2022. 2. 21.
	@작성자 : YESOL
 -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/getCommCode.tld"%>
<code:makeSelect commGrpCd="PRMT_SEC_CD" 	var="ptrmSecCdList"	name="ptrmSecCd"	all="false" />
<script type="text/javascript">
	var myGridID;
	var myGridID2;

	$(document).ready(function() {
		createAUIGrid();
		AUIGrid.setFooter(myGridID, footerLayout);
		$("#srcBtn").on("click", searchList);
	});

	// AUIGrid 를 생성합니다.
	function createAUIGrid() {
		// 실제로 #grid_wrap 에 그리드 생성
		myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
	}

	var gridPros = {
		headerHeight : 29,
		rowHeight : 29,
		// 편집 가능 여부 (기본값 : false)
		editable : false,
		// 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
		enterKeyColumnBase : true,
		showRowNumColumn : true,

		// 셀 선택모드 (기본값: singleCell)
		selectionMode : "multipleCells",
		showFooter : true,
		// 컨텍스트 메뉴 사용 여부 (기본값 : false)
		useContextMenu : true,
		// 필터 사용 여부 (기본값 : false)
		enableFilter : true,
		// 그룹핑 패널 사용
		useGroupingPanel : false,
		// 그룹핑 또는 트리로 만들었을 때 펼쳐지게 할지 여부 (기본값 : false)
		displayTreeOpen : true,
		noDataMessage : "출력할 데이터가 없습니다.",
		groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다."
	};

	var columnLayout = [{
		dataField : "areaNm",
		headerText : "지역",
		width : "6%",
	}, {
		dataField : "cstSeq",
		headerText : "애음자코드",
		width : "5%",
	}, {
		dataField : "cstNm",
		headerText : "성명",
		width : "5%"
	}, {
		dataField : "mobNo",
		headerText : "휴대폰번호",
		width : "7%"
	}, {
		dataField : "emplNm",
		headerText : "판촉사원",
		width : "5%",
	}, {
		dataField : "ptrmSecNm",
		headerText : "판촉물 구분",
		width : "5%",
	}, {
		dataField : "prtmRmk",
		headerText : "판촉물 내용",
	}, {
		dataField : "ptrmPymDt",
		headerText : "지급일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%",
	}, {
		dataField : "ptrmPymNm",
		headerText : "지급인",
		width : "6%",
	}, {
		dataField : "joinDt",
		headerText : "가입일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%"
	}, {
		dataField : "inptDt",
		headerText : "투입일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%"
	}, {
		dataField : "inputStaus",
		headerText : "투입상태",
		width : "5%"
	}, {
		dataField : "hop",
		headerText : "홉수",
		width : "5%",
		style : "auiRight"
	}, {
		dataField : "hopUntpc",
		headerText : "홉단가",
		dataType : "numeric",
		width : "6%",
		style : "auiRight"
	}, {
		dataField : "cntrMonth",	
		headerText : "계약월",
		width : "5%",
	}, {
		dataField : "prdNm",
		headerText : "제품명",
		width : "10%",
	}, {
		dataField : "weekQty",
		headerText : "주별 총수량",
		wwidth : "5%",
	}, {
		dataField : "prtmCstCt",
		headerText : "소비자부담금",
		dataType : "numeric",
		width : "6%",
		style : "auiRight",
		formatString : "#,##0"
	}, {
		dataField : "prtmCt",
		headerText : "판촉사원부담금",
		dataType : "numeric",
		width : "6%",
		style : "auiRight",
		formatString : "#,##0"
	}, {
		dataField : "stpgDt",
		headerText : "중지일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%",
	}, {
		dataField : "holdDate",
		headerText : "보류일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%",
	}, {
		dataField : "endDt",
		headerText : "계약종료일",
		dataType : "date",
		formatString : "yyyy-mm-dd",
		width : "6%",
	}];

	var footerLayout = [{
		labelText : "∑",
		positionField : "#base"
	}, {
		dataField : "day",
		positionField : "day",
		operation : "SUM",
		colSpan : 10, // 자신을 포함하여 4개의 푸터를 가로 병합함.
		labelFunction : function(value, columnValues, footerValues) {
			// return "합계 : " + AUIGrid.formatNumber(value, "#,##0");
			return "합계 : "
		}
	}, {
		dataField : "hop",
		positionField : "hop",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "hopUntpc",
		positionField : "hopUntpc",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "cntrMonth",
		positionField : "cntrMonth",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "weekQty",
		positionField : "weekQty",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "prtmCstCt",
		positionField : "prtmCstCt",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "prtmCt",
		positionField : "prtmCt",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}, {
		dataField : "promoPrice",
		operation : "SUM",
		style : "auiRight",
		colSpan : 1,
		formatString : "#,##0"
	}];

	/* 표준 제품 - 리스트 조회 */
	function searchList() {
		$.ajax({
			url : "/prmt/selectPrmtCstSearchList.do",
			type : 'POST',
			data : $("#frm").serialize(),
			success : function(data) {
				AUIGrid.setGridData(myGridID, data);
				$("#listCnt").html(data.length);
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		});
	}

	function resizeGrid() {
		AUIGrid.resize(myGridID, $("#grid_wrap").width());
	}
</script>
<div class="content">
	<form id="frm">
		<!-- 조회 -->
		<div class="inquiryBox">
			<dl>
				<dt>가입월</dt>
				<dd>
					<div class="dateWrap">
						<input type="text" name="date" value="" class="inp monthPicker"
							id="monthPicker" readonly>
						<button type="button" class="datepickerBtn" title="날짜입력"
							data-target-id="monthPicker"></button>
					</div>
				</dd>
				<dt>판촉사원</dt>
				<dd>
					<div class="formWrap">
						<select name="emplSeq" class="sel" id="emplSeq">
							<option value="">전체</option>
							<c:forEach var="empl" items="${emplList}" varStatus="status">
								<option value="${empl.emplSeq}">${empl.emplNm}</option>
							</c:forEach>
						</select>
					</div>
				</dd>
				<dt>판촉구분</dt>
				<dd>
					<div class="formWrap">
						${ptrmSecCdList}
						<input type="text" id="inp_name01" class="inp w160" value="" title="성명 입력">
						<button type="button" name="" class="comBtn" id="srcBtn">조회</button>
					</div>
				</dd>
			</dl>
		</div>
	</form>
	<!-- 조회 -->
	<div class="titleArea">
		<p class="numState">
			<em>총 <span class="pColor01 fb" id="listCnt">0</span></em> 건 이 조회되었습니다.
		</p>
	</div>
	<p>- 쿼리 수정 미 완료 -</p>

	<!-- grid -->
	<div class="girdBox">
		<div id="grid_wrap"></div>
	</div>
	<!-- grid -->

</div>
