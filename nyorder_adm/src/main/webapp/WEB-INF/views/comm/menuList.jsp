<!-- 
	@File Name: menuList.jsp
	@File 설명 : 메뉴 관리
	@UI ID : 
	@작성일 : 2022. 1. 4.
	@작성자 : YESOL
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.left{
	text-align: left;
}
</style>

<script type="text/javascript">
//AUIGrid 생성 후 반환 ID
var mGrid;

$(document).ready(function(){
	//AUIGrid 생성
	createAUIGrid();
	//그리드 데이터 조회	
	searchList();
	//버튼 바인딩
	$("#saveBtn").on("click", saveRow);
});

//AUIGrid 를 생성합니다.
function createAUIGrid(columnLayout) {
	var auiGridProps = {
			rowIdField : "menuSeq",
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
		{	dataField : "menuSeq",		headerText : "메뉴일련번호",	width:100	},
		{	dataField : "lvl1",			headerText : "LEVEL1",		width:150,	vstyle : "left"	},
		{	dataField : "lvl2",			headerText : "LEVEL2",		width:150,	vstyle : "left"	},
		{	dataField : "lvl3",			headerText : "LEVEL3",		width:150,	vstyle : "left"	},
		{	dataField : "menuNm",		headerText : "메뉴명",		width:250,	style : "left"	},
		{	dataField : "menuParentSeq",headerText : "상위메뉴코드",	width:100	},
		{	dataField : "menuOrdr",		headerText : "정렬순서",		width:100	},
		{	dataField : "menuUrl",		headerText : "URL",		width:150,	style : "left"	},
		{	dataField : "useYn",	 	headerText : "사용여부",		width:100,
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
		{	dataField : "menuType",	 headerText : "메뉴구분",		width:140,
			renderer : {
				type : "DropDownListRenderer",
				list : [	
						{"code":"SI", "value":"시판"}, 
						{"code":"BA", "value":"방판"},
						{"code":"ALL", "value":"시/방판"},
						{"code":"AD", "value":"어드민"}
				],
				keyField : "code",
				valueField : "value"
			}
		},
		{
			dataField : "storOwnrYn",
			headerText : "점주",
			width: 120,
			headerRenderer : {
				type : "CheckBoxHeaderRenderer",
				dependentMode : true, 			
				position : "bottom" // 기본값 "bottom"
			},
			renderer : {
				type : "CheckBoxEditRenderer",
				showLabel : false, // 참, 거짓 텍스트 출력여부( 기본값 false )
				editable : true, // 체크박스 편집 활성화 여부(기본값 : false)
				checkValue : "Y", // true, false 인 경우가 기본
				unCheckValue : "N"
			}
		}
	];
	
	// 실제로 #grid_wrap 에 그리드 생성
	mGrid = AUIGrid.create("#grid_wrap", columnLayout, auiGridProps);
	
}

function searchList(){
	$.ajax({
		url : "/comm/selectMenuAllList.do", 
		type : 'POST', 
		data : $("#frm").serialize(),
		success : function(data) {
			AUIGrid.setGridData(mGrid, data);
			$(".pColor01").html(data.length);
		}, // success 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	}); 	
}
function saveRow(){
	var saveData = fnGetGridListCRUD(mGrid);
	if(!saveData.length > 0){
		alert("저장할 내용이 없습니다."); return;
	}
	alert("저장");
	$.ajax({
		url : "/comm/saveMenuList.do", 
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
function resizeGrid(){
	AUIGrid.resize(mGrid, $("#content").width());
}

</script>



<div class="content">
	<tiles:insertAttribute name="body.breadcrumb"/>
	<!-- 조회 -->
	<form id="frm" method="post">
		<div class="inquiryBox">
			<dl>
				<dt>메뉴명</dt>
				<dd>
					<input type="text" id="menuNm" name="menuNm" class="inp" value="" title="성명 입력">
				</dd>
				<dd>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchList()">조회</button>
				</dd>
			</dl>

			<div class="btnGroup right">
				<button type="button" id="saveBtn"	class="comBtn">저장</button>
			</div>
		</div>
	</form>
	<!-- 조회 -->
	
	<div class="titleArea">
		<h3 class="tit01">메뉴정보</h3>
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
