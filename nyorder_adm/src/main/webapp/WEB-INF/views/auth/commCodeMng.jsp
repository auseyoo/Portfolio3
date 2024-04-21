<!--
	@File Name: commCodeMng
	@File 설명 : 공통코드 관리
	@UI ID : UI-AATH-0401
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
			<input type="hidden" name="addRows" id="addRows" />
			<input type="hidden" name="removeRows" id="removeRows" />
			
			<!-- 조회 -->
			<div class="inquiryBox">
				<dl>
					<dt>그룹코드</dt>
					<dd>
						<input type="text" class="inp w120" name="commGrpCd" id="commGrpCd" maxlength="15" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>그룹코드명</dt>
					<dd>
						<input type="text" class="inp w120" name="commGrpNm" id="commGrpNm" maxlength="15" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>공통코드</dt>
					<dd>
						<input type="text" class="inp w120" name="commCd" id="commCd" maxlength="15" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>공통코드명</dt>
					<dd>
						<input type="text" class="inp w120" name="commNm" id="commNm" maxlength="15" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>
                        사용여부
                    </dt>
                    <dd>
                        <div class="formWrap">
                            <select class="sel" name="useYn" id="useYn">
                                <option value="" selected="selected">전체</option>
                                <option value="Y">사용</option>
                                <option value="N">미사용</option>
                            </select>
                        <button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>                                
                        </div>
                    </dd>
					<!-- <dd>
						<button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
					</dd> -->
				</dl>
				
				<div class="btnGroup right">
					<button type="button" class="inquBtn" name="saveBtn" id="saveBtn">저장</button>
				</div>
				
			</div>
			<!-- 조회 -->
		</form>
		
		<div class="titleArea right">
			<p class="numState">
				<em>총 <span class="pColor01 fb" id="listCnt">24</span></em> 건 이 조회되었습니다.
			</p>
			<div class="btnGroup">
				<button type="button" class="comBtn small" name="addRowBtn" id="addRowBtn" >행추가</button>
				<button type="button" class="comBtn small" name="removeRowBtn" id="removeRowBtn" >행삭제</button>
			</div>
		</div>
		
		<!-- grid -->
		<div class="girdBox">
			<div id="grid_wrap"></div>
		</div>
		<!-- grid -->
	</div>
	
	<script type="text/javascript">
		
		var myGridID;
		var posList =  ["Y","N"];
		
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
			
			// 행추가
			$("#addRowBtn").click(function() {
				var newItem = {
						"commGrpCd" : "이 값을 변경하세요.",
						"commGrpNm" : "이 값을 변경하세요."
				};
				AUIGrid.addRow(myGridID, newItem, "first");
			});
			
			// 행삭제
			$("#removeRowBtn").click(function() {
				var list = AUIGrid.getCheckedRowItems(myGridID);
				if(list.length < 1) {
					alert('삭제할 데이터를 선택해주세요');
					return;
				}
				AUIGrid.removeRow(myGridID, list.map(item => item.rowIndex));
			});
			
		});
		
		var columnLayout = [{
				dataField : "commSeq",
				headerText : "seq",
				visible: false
			},{
				dataField : "commGrpCd",
				headerText : "그룹코드",
				width : "10%",
				headerTooltip : { // 헤더 툴팁 표시 HTML 양식
					show : true,
					tooltipHtml : "기본적으로 수정 불가, 단 새 행 추가한 경우는 편집 허용"
				},
				editable : true,
				editRenderer : {
					maxlength : 20
				}
			},{
				dataField : "commGrpNm",
				headerText : "그룹코드명",
				width : "10%",
				headerTooltip : { // 헤더 툴팁 표시 HTML 양식
					show : true,
					tooltipHtml : "기본적으로 수정 불가, 단 새 행 추가한 경우는 편집 허용"
				},
				editable : true,
				editRenderer : {
					maxlength : 60
				}
			},{
				dataField : "commCd",
				headerText : "공통코드",
				width : "10%",
				editable : true,
				editRenderer : {
					maxlength : 20
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commNm",
				headerText : "공통코드명",
				width : "10%",
				editable : true,
				editRenderer : {
					maxlength : 60
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "ordr",
				headerText : "정렬순서",
				width : "8%",
				editable : true,
				editRenderer : {
					// 0~9만 입력가능
					onlyNumeric : true,
					// 소수점( . ) 도 허용할지 여부
					allowPoint : false, 
					// 마이너스 부호(-) 허용 여부
					allowNegative : false,
					maxlength : 3
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commDesc1",
				headerText : "참조1",
				width : "15%",
				editable : true,
				editRenderer : {
					maxlength : 50
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commDesc2",
				headerText : "참조2",
				width : "15%",
				editable : true,
				editRenderer : {
					maxlength : 50
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commDesc3",
				headerText : "참조3",
				width : "15%",
				editable : true,
				editRenderer : {
					maxlength : 50
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commDesc4",
				headerText : "참조4",
				width : "15%",
				editable : true,
				editRenderer : {
					maxlength : 50
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "commDesc5",
				headerText : "참조5",
				width : "15%",
				editable : true,
				editRenderer : {
					maxlength : 50
				},
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
			},{
				dataField : "useYn",
				headerText : "사용여부",
				width : "8%",
				renderer : {
					type : "DropDownListRenderer",
					list : posList
				}
			},{
				dataField : "updAdmNm",
				headerText : "수정자",
				width : "8%"
			},{
				dataField : "updDtm",
				headerText : "수정일시",
				width : "12%"
			}
		];
		
		// AUIGrid 를 생성합니다.
	    function createAUIGrid(columnLayout) {
	        // 그리드 속성 설정
	        var gridPros = {
				headerHeight : 29,	
				rowHeight : 29,
				showRowCheckColumn : true,
				
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

				// 붙여넣기로 새행이 생길 때 begin 이벤트 발생 시키지 않을지 여부
				// 기본값 : false
				//notBeginEventNewRowsOnPaste : true,
				
				// 붙여넣기로 새행이 생길 때 칼럼의 editable=false 설정한 칼럼의 값을
				// 새행이 생기는 경우에도 고칠 수 없을지 여부 (기본값 : false)
				// false 인 경우에는 붙여넣기로 새행이 생길 때 클립보드 데이터가 적용됩니다.
				// true 인 경우에는 붙여넣기로 새행이 생길 때 클립보드 데이터 적용되지 않습니다.
				//uneditableNewRowsOnPaste : true
				
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

			// 에디팅 시작 이벤트 바인딩
			AUIGrid.bind(myGridID, "cellEditBegin", function(event) {
				// rowIdField 설정 값 얻기
				var rowIdField = AUIGrid.getProp(event.pid, "rowIdField");
				
				if(event.dataField == "commGrpCd" || event.dataField == "commGrpNm") {
					// 추가된 행 아이템인지 조사하여 추가된 행인 경우만 에디팅 진입 허용
					if(AUIGrid.isAddedById(event.pid, event.item[rowIdField])) {
						return true; 
					} else {
						return false; // false 반환하면 기본 행위 안함(즉, cellEditBegin 의 기본행위는 에디팅 진입임)
					}
				}
				return true; // 다른 필드들은 편집 허용
			});

			// 에디팅 종료 이벤트 바인딩
			AUIGrid.bind(myGridID, "cellEditEnd", function(event) {
				// rowIdField 설정 값 얻기
				var rowIdField = AUIGrid.getProp(event.pid, "rowIdField");
				
				if(event.dataField == "commGrpCd") {
					//alert("rowIndex : " + event.rowIndex + ", columnIndex : " + event.columnIndex + " cellEditEnd");
					fn_grpCdCnt(event.value, event.oldValue, event.rowIndex);
				}
			});
		}
		
	    function fn_grpCdCnt(value, oldValue, rowIndex) {
		    
		    var cnt = 0;
		    var param = { "commGrpCd" : value };
		    
			$.ajax({
				url : "/auth/commGrpCodeCnt.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					if ( (Common.isNotEmpty(data.selCnt) && data.selCnt > 0) )
					{
						//alert("기등록된 그룹코드 입니다.\n 다른 값으로 입력 바랍니다.");
						var newItem = {
								"commGrpCd" : "이 값을 변경하세요.",
								"commGrpNm" : "이 값을 변경하세요."
						};
						//AUIGrid.updateRow(myGridID, newItem, rowIndex);
					}
				}, // success 
				error : function(xhr, status) {
					alert(xhr + " : " + status);
				},
				complete  : function(xhr, status) {
					AUIGrid.removeAjaxLoader(myGridID);
		        }
			});

			return cnt;
		}
		
		function fn_search() {
			$.ajax({
				url : "/auth/commCodeMngList.do", 
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
			var addRowItems = AUIGrid.getAddedRowItems(myGridID);
			var removeRowItems = AUIGrid.getRemovedItems(myGridID);
			if ((Common.isEmpty(editedRowItems) || editedRowItems.length == 0)
					&& (Common.isEmpty(addRowItems) || addRowItems.length == 0)
					&& (Common.isEmpty(removeRowItems) || removeRowItems.length == 0)
					)
			{
				alert("수정된 데이터가 없습니다.");
				return;
			}

			var isValid = AUIGrid.validateChangedGridData(myGridID
					, ["commGrpCd", "commGrpNm", "commCd", "commNm"]
					, "반드시 유효한 값을 직접 입력해야 합니다.");
			if(!isValid) {
				alert("필수 입력 값을 확인해 주세요.");
				return;
			}
			
			$("#updateRows").val(JSON.stringify(editedRowItems));
			$("#addRows").val(JSON.stringify(addRowItems));
			$("#removeRows").val(JSON.stringify(removeRowItems));
			var param = $("#frm").serializeObject();
			
			$.ajax({
				url : "/auth/commCodeMngUpd.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					if ( (Common.isNotEmpty(data.res) && data.res) 
							&& (data.updateCnt > 0 || data.insertCnt > 0 ||data.delCnt > 0)
							)
					{
						alert("저장 되었습니다.");
						fn_search();
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
				},
				complete  : function(xhr, status){
					AUIGrid.removeAjaxLoader(myGridID);
		        }
			});
		}
		
	</script>