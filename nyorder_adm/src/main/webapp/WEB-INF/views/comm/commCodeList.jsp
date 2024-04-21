<!-- 
	@File Name: commCodeList.jsp
	@File 설명 : 공통 코드 관리
	@UI ID : 
	@작성일 : 2022. 1. 4.
	@작성자 : YESOL
 -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">

//AUIGrid 생성 후 반환 ID
var mGrid;

$(document).ready(function(){
	//AUIGrid 생성
	createAUIGrid();
	//그리드 데이터 조회
	searchList();
	//버튼 바인딩
	$("#addBtn").on("click", addRow);
	$("#saveBtn").on("click", saveRow);
	$("#rmvBtn").on("click", removeRow);
});

function createAUIGrid() {
	var auiGridProps = {
			rowIdField : "commSeq",
			// 수정 가능 여부
			editable : true,
			// 필터 활성화
			enableFilter : true,
			// 상태 표시
			showStateColumn : true,

			// row Height
			headerHeight : 60
	};

	//AUIGrid 칼럼 설정		formatString : "#,##0",	style : "left",
	var columnLayout = [ 
		{	dataField : "commSeq",		headerText : "코드일련번호",		width:140,	visible:false	},
		{	dataField : "commGrpCd",	headerText : "코드그룹",		width:140,	maxlength: 10	},
		{	dataField : "commGrpNm",	headerText : "코드그룹명",		width:250,	maxlength: 40, 	style : "left"	},
		{	dataField : "commCd",		headerText : "코드",			width:140,	maxlength: 20	},
		{	dataField : "commNm",		headerText : "코드명",		width:250,	maxlength: 40,	style : "left"	},
		{	dataField : "ordr",		 headerText : "순서",			width:80,	
			dataType : "numeric",
			editRenderer : {
				type : "InputEditRenderer",
				onlyNumeric : true, // 0~9만 입력가능
				autoThousandSeparator : true // 천단위 구분자 삽입 여부 (onlyNumeric=true 인 경우 유효)
			}
		},
		{	dataField : "useYn",	headerText : "사용여부",		width:140,
			renderer : {
				type : "DropDownListRenderer",
				list : [	
						{"code":"Y", "value":"Y"}, 
						{"code":"N", "value":"N"}
				],
				keyField : "code",
				valueField : "value"
			}
		},
		{	dataField : "commDesc1",	headerText : "commDesc1",	width:140	},
		{	dataField : "commDesc2",	headerText : "commDesc2",	width:140	},
		{	dataField : "commDesc3",	headerText : "commDesc3",	width:140	},
		{	dataField : "commDesc4",	headerText : "commDesc4",	width:140	},
		{	dataField : "commDesc5",	headerText : "commDesc5",	width:140	},
		{	dataField : "commDesc6",	headerText : "commDesc6",	width:140	},
		{	dataField : "updDtm",		headerText : "updDtm",	  width:140,	
			editable : false,	dataType : "date",		formatString: "yyyy-mm-dd HH:MM" 
		}
	];
	
	// 실제로 #grid_wrap 에 그리드 생성
	mGrid = AUIGrid.create("#grid_wrap", columnLayout, auiGridProps);

	$("#popBtn").click(function(){
		var url = '${request.contextPath}/comm/pop/samplePop.pop';
		var popup = window.open(url, '', 'width=500px,height=500px,scrollbars=yes');
	});
}

function searchList(){
	$.ajax({
		url : "/comm/selectCommCodeList.do", 
		type : 'POST', 
		data : $("#frm").serialize(),
		success : function(data) {
			AUIGrid.setGridData(mGrid, data);
		}, // success 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	}); 	
}

function saveRow(){
	if(validateGridData()){
		var saveData = fnGetGridListCRUD(mGrid);
		if(!saveData.length > 0){
			alert("저장할 내용이 없습니다."); return;
		}
		alert("저장");
		$.ajax({
			url : "/comm/saveCommCodeList.do", 
			type : "POST", 
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(saveData),
			success : function(data) {
				alert(data);
				searchList();
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 	
	}
}
function removeRow(){
	AUIGrid.removeRow(mGrid,"selectedIndex");
}
function addRow(){
	var rowPos = "last";
	var item = {};
	item.commGrpNm = "";
	item.commGrpCd = "";
	item.commCd = "";
	item.commNm = "";
	item.useYn = "Y";

	// parameter
	// item : 삽입하고자 하는 아이템 Object 또는 배열(배열인 경우 다수가 삽입됨)
	// rowPos : rowIndex 인 경우 해당 index 에 삽입, first : 최상단, last : 최하단, selectionUp : 선택된 곳 위, selectionDown : 선택된 곳 아래

	if(!fnIsEmpty(AUIGrid.getSelectedRows(mGrid))){
		rowPos = "selectionDown";
	}
	AUIGrid.addRow(mGrid, item, rowPos);
	
}

//필수로 설정해야 하는 필드들의 값이 모두 입력되었는지 검사
function validateGridData() {
	// name 과 country 는 필수로 입력되어야 하는 필드임. 이것을 검사
	var isValid = AUIGrid.validateGridData(mGrid, ["commGrpNm", "commGrpCd"], "필수 필드는 반드시 값을 직접 입력해야 합니다.");
	// 다른 JS 에서 제공하는 toast 나 alert 으로 경고문을 띄우고자 하는 경우 다음처럼 message 파라메터 생략.
	// AUIGrid.validateGridData(mGrid, ["name", "country"]);
	if(isValid) {
		return true;
	}
	return false;
};

function resizeGrid(){
	AUIGrid.resize(mGrid, $("#content").width());
}
</script>

<div class="content">
	<tiles:insertAttribute name="body.breadcrumb"/>
	<!-- 조회 -->
	<form id="frm">
		<div class="inquiryBox">
			<dl>
				<dt>그룹코드</dt>
				<dd>
					<input type="text" id="commGrpNm" name="commGrpNm" class="inp" value="" title="">
				</dd>
				<dt>코드</dt>
				<dd>
					<input type="text" id="commNm" name="commNm" class="inp" value="" title="">
				</dd>
				<dd>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchList()">조회</button>
				</dd>
			</dl>
	
			<div class="btnGroup right">
				<button type="button" id="addBtn" 	class="comBtn">신규</button>
				<button type="button" id="saveBtn"	class="comBtn">저장</button>
				<button type="button" id="popBtn"	class="comBtn">팝업</button>
			</div>
		</div>
	</form>
	<!-- 조회 -->
	
	<div class="titleArea">
		<h3 class="tit01">직원정보</h3>
			<p class="numState">
			<em>총 <span class="pColor01 fb">24</span></em> 건 이 조회되었습니다.
		</p>
	</div>
	
	<!-- grid -->
	<div class="gridBox">
		<div id="grid_wrap"></div>
	</div>
	<!-- grid -->
</div>
