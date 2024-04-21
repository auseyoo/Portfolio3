<!--
	@File Name: userGrpMng
	@File 설명 : 사용자그룹 권한관리
	@UI ID : UI-AATH-0101
	@작성일 : 2022.02.18
	@작성자 : kjin
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="content">
		<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>
		
		<form id="frm" name="frm" method="POST" >
			<input type="hidden" name="updateRows" id="updateRows" />
			
			<!-- 조회 -->
			<div class="inquiryBox">
				<dl>
					<dt>프로그램ID</dt>
					<dd>
						<input type="text" class="inp" name="menuId" id="menuId" value="" >
					</dd>
					<dt>프로그램명</dt>
					<dd>
						<input type="text" class="inp" name="menuNm" id="menuNm" value="" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dd>
						<button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
					</dd>
				</dl>
				
				<div class="btnGroup right">
					<button type="button" class="inquBtn" name="saveBtn" id="saveBtn">저장</button>
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
			
			// AUIGrid.setGridData(myGridID, gridData);
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
				dataField : "menuSeq",
				headerText : "seq",
				visible: false
			},{
	            dataField : "menuId",
				headerText : "프로그램 ID",
				width : "12%",
			},{
				dataField : "menuNm",
				headerText : "프로그램명",
				width : "12%",
				style: "auiLeft"
			},{
				headerText : "사용자 그룹",
				dataField : "myGroupField", // 그룹 헤더의 dataField 는 무의미 하지만, 접근자로  사용하기 위해 임의 지정함.(중복되지 않게 임의 지정하세요.)
				children : [{
					dataField : "type1",
					headerText : "시스템관리자",
					//width:120,
					headerRenderer : {
						type : "CheckBoxHeaderRenderer",
						dependentMode : true, 			
						position : "left" // 기본값 "bottom"
					},
					renderer : {
						type : "CheckBoxEditRenderer",
						showLabel : false, // 참, 거짓 텍스트 출력여부( 기본값 false )
						editable : true, // 체크박스 편집 활성화 여부(기본값 : false)
						checkValue : "Y", // true, false 인 경우가 기본
						unCheckValue : "N"
					}
					}, {
						dataField : "type2",
						headerText : "본사영업사원",
						//width:120,
						headerRenderer : {
						type : "CheckBoxHeaderRenderer",
						dependentMode : true, 			
						position : "left" // 기본값 "bottom"
					},
					renderer : {
						type : "CheckBoxEditRenderer",
						showLabel : false, // 참, 거짓 텍스트 출력여부( 기본값 false )
						editable : true, // 체크박스 편집 활성화 여부(기본값 : false)
						checkValue : "Y", // true, false 인 경우가 기본
						unCheckValue : "N"
					}
					}, {
						dataField : "type3",
						headerText : "지점영업사원",
						//width:120,
						headerRenderer : {
						type : "CheckBoxHeaderRenderer",
						dependentMode : true, 			
						position : "left" // 기본값 "bottom"
					},
						renderer : {
						type : "CheckBoxEditRenderer",
						showLabel : false, // 참, 거짓 텍스트 출력여부( 기본값 false )
						editable : true, // 체크박스 편집 활성화 여부(기본값 : false)
						checkValue : "Y", // true, false 인 경우가 기본
						unCheckValue : "N"
					}
					},{
						dataField : "updAdmNm",
						headerText : "수정자",
						width : "10%"
					},{
						dataField : "updDtm",
						headerText : "수정일시",
						width : "10%"
					}
				]
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
	                //rowIdField : "id",
	                
	                // 편집 가능 여부 (기본값 : false)
	                //editable : false,
	                //applyRestPercentWidth = true,
	               // autoGridHeight = true,
	                
	                // 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
	                //enterKeyColumnBase : true,
	                
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
	                
	                noDataMessage : "출력할 데이터가 없습니다.",
	                
	                groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다."
	        };
	    
	        // 실제로 #grid_wrap 에 그리드 생성
	        myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
			
	     	// 체크박스 클린 이벤트 바인딩
			AUIGrid.bind(myGridID, "rowCheckClick", function( event ) {
				//alert("rowIndex : " + event.rowIndex + ", id : " + event.item.id + ", name : " + event.item.name + ", checked : " + event.checked + ", shiftKey : " + event.shiftKey + ", shiftIndex : " + event.shiftIndex);
			});
			
			// 전체 체크박스 클릭 이벤트 바인딩
			AUIGrid.bind(myGridID, "rowAllChkClick", function( event ) {
				//alert("전체 선택  checked : " + event.checked);
			});
			
			// 에디팅 정상 종료 이벤트 바인딩
			//AUIGrid.bind(myGridID, "cellEditEnd", auiCellEditingHandler);
			
	    }
		
	    function auiCellEditingHandler(event) {
		    // 1로우의 체크박스 하나만 선택 하도록~
			if(event.type == "cellEditEnd") {
				// 체크 박스에 맞는 rowItem 얻기
				var rowItem = AUIGrid.getItemByRowIndex(myGridID, event.rowIndex);
				if (event.dataField == "type1")
				{
					rowItem.type2 = "N";
					rowItem.type3 = "N";
				}
				else if (event.dataField == "type2")
				{
					rowItem.type1 = "N";
					rowItem.type3 = "N";
				}
				else if (event.dataField == "type3")
				{
					rowItem.type1 = "N";
					rowItem.type2 = "N";
				}
				// row 데이터 업데이트
				AUIGrid.updateRow(myGridID, rowItem, event.rowIndex);
				
				//alert("Editing End : ( " + event.rowIndex  + ", " + event.columnIndex + ") : " + event.value);
			}
		}
		
		function fn_search() {
			$.ajax({
				url : "/auth/userGrpMngList.do", 
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
			
			// 그리드에서 수정된 아이템들의 묶음(배열)을 반환합니다.
			// 수정된 아이템은 최초의 데이터에서 addRow 메소드로 추가된 아이템을 수정한 경우 포함되지 않습니다
			var editedRowItems = AUIGrid.getEditedRowItems(myGridID);
			if (Common.isEmpty(editedRowItems) || editedRowItems.length == 0)
			{
				alert("수정된 데이터가 없습니다.");
				return;
			}
			
			$("#updateRows").val(JSON.stringify(editedRowItems));
			var param = $("#frm").serializeObject();
			
			$.ajax({
				url : "/auth/userGrpMngUpd.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					if (Common.isNotEmpty(data.res) && data.res)
					{
						alert("저장 되었습니다.");
						fn_search();
					}
					else
					{
						alert("저장 실패 하였습니다.\n다시 시도해 주세요.");
					}
					
				}, // success 
				error : function(xhr, status) {
					alert(xhr + " : " + status);
				},
				complete  : function(xhr, status){
					AUIGrid.removeAjaxLoader(myGridID);
		        }
			});
		}
		
	</script>