<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
	var myGridID;//제목 - 왼쪽 그리드
	var myGridID2;//내용 - 오른쪽 그리드
	var addRow = false;
	var preRow = "";
	$(document).ready(function() {
		//행추가 이벤트 플레그
		// AUIGrid 생성 후 반환 ID
		createAUIGrid();
		
		/*버튼 클릭 event*/
		
		//조회버튼 클릭
		$("#searchBtn").click(function() {
			selectRcbodList();
		});
		//행추가 버튼 클릭
		$("#addRowBtn").click(function() {
			if(addRow){
				var popupParam = [];
				popupParam.data = {
						title : "자료실",
						message : "<spring:message code='alert.rcbod03'/>",
						showBtn2 : 'N'
				}
				layerAlert(popupParam);
				return;
			}
			//myGridID 행추가
			var obj = { rcbodSub : "" , regDtm : fnGetToDay(), rcbodSeq: null };
			AUIGrid.addRow(myGridID, obj, "first");
			AUIGrid.clearGridData(myGridID2);
			preRow = null;
			addRow = true;
		});
		//행삭제 이벤트
		$("#removeRowBtn").click(function(){
			var popupParam = [];
			if(!AUIGrid.getCheckedRowItems(myGridID).length > 0) {
				// 체크박스 선택여부 확인하기
				popupParam.data = {
						title : "자료실",
						message : "<spring:message code='alert.rcbod01'/>",
						showBtn2 : 'N'
				}
				layerAlert(popupParam);
				return;
			}
			popupParam.data = {
				message : "<spring:message code='alert.rcbod02'/>",
				btn1Func : rmvRcbod,
				showBtn2 : 'Y'
			}
			layerAlert(popupParam);
			
		});
		//저장 버튼 클릭
		$("#saveBtn").click(function() {
			// 공백 제거 (전체 공백시 삭제)
			removeBlankRow();		
			var titleList = AUIGrid.getSelectedItems(myGridID);
			var contList = AUIGrid.getGridData(myGridID2); 
			if(!(titleList.length > 0 && contList.length > 0)){
				var popupParam = [];
				popupParam.data = {
						title : "자료실",
						message : "<spring:message code='alert.rcbod04'/>",
						showBtn2 : 'N'
				}
				layerAlert(popupParam);
				return;
			}
	
			
			if(validateGridData() && validateGridData2()){
				$.ajax({
					url : "/cmmc/selectAgenList.do",
					type : 'POST',
					contentType: 'application/json',
					success : function(data) {
						var failCnt = 0;
						$.each(contList, function(ci, cv){
							var agenList = data;
							if(!fnIsEmpty(cv.agenCd)){
								if(! agenList.filter(function(e){ return e.agenCd === cv.agenCd }).length > 0 ){
									AUIGrid.getRowIndexesByValue(myGridID2, "agenCd", cv.agenCd).forEach(function(i){
										AUIGrid.updateRow(myGridID2,{ chkYn : "N"}, i);
									});
									failCnt++;
								}else{
									AUIGrid.getRowIndexesByValue(myGridID2, "agenCd", cv.agenCd).forEach(function(i){
										AUIGrid.updateRow(myGridID2,{ chkYn : "Y", agenSeq : agenList.filter(function(e){ return e.agenCd === cv.agenCd })[0].agenSeq}, i);
									});
								}
							}
						});
						if( failCnt > 0){
							var popupParam = [];
							popupParam.data = {
									title : "자료실",
									message : "<spring:message code='alert.rcbod06' arguments='"+failCnt+"'/>",
									showBtn2 : 'N'
							}
							layerAlert(popupParam);
							return;
						}
						var saveData = AUIGrid.getSelectedItems(myGridID)[0].item;
						saveData.contList = AUIGrid.getGridData(myGridID2);
						$.ajax({
							url : "/cmmc/saveRcbod.do",
							type : 'POST',
							contentType: 'application/json',
							data : JSON.stringify(saveData),
							success : function(data) {
								var popupParam = [];
								popupParam.data = {
										title : "자료실",
										message : data,
										showBtn2 : 'N'
								}
								layerAlert(popupParam);
								selectRcbodList();
								selectRcbodDtlList(AUIGrid.getSelectedItems(myGridID)[0].item);
							}, // success 
							error : function(xhr, status, errorThrown) {
								if ( Common.IsJsonString(xhr.responseText) )
								{
									var message = JSON.parse(xhr.responseText).message;
									alert(message);
								}
								else{
									alert("처리중 오류가 발생 하였습니다.");
								}
							}
						});
						
					}, // success 
					error : function(xhr, status) {
						alert("대리점 코드 조회 중 오류가 발생하였습니다");
					}
				});
			}
		});

		//제목 그리드 클릭 이벤트
		AUIGrid.bind(myGridID, "cellClick", function(event) {
			if(preRow != event.item.rcbodSeq){
				if(preRow == null && AUIGrid.getGridData(myGridID2).length > 0 ){
					var popupParam = [];
					popupParam.data = {
						message : "<spring:message code='alert.rcbod07'/>",
						btn1Func : function(){
							$('[data-popup="modalAlert"]').fadeOut(100);
							$('[data-popup="modalAlert"]').parent().find(".modal_bg").fadeOut(100);
							selectRcbodDtlList(event.item);
							preRow = event.item.rcbodSeq;
						},
						showBtn2 : 'Y',
						btn2Func : clickReset
					}
					layerAlert(popupParam);
				}else{
					selectRcbodDtlList(event.item);
					preRow = event.item.rcbodSeq;
				}
			}
		});

		AUIGrid.bind(myGridID, "cellEditBegin", function(event) {
			if(event.dataField == "rcbodSub" && !fnIsEmpty(event.item.rcbodSeq) ) return false; // false 반환. 기본 행위인 편집 불가
		});
		
		AUIGrid.bind(myGridID2, "pasteBegin", function( event ) {
			if(!AUIGrid.getSelectedItems(myGridID).length > 0) {
				var popupParam = [];
				popupParam.data = {
					title : "자료실",
					message : "<spring:message code='alert.rcbod01'/>",
					showBtn2 : 'N'
				}
				layerAlert(popupParam);
				return false;
			}
		})
	});

	// AUIGrid 를 생성합니다.
	function createAUIGrid() {
		// 그리드 속성 설정
		 var gridPros = {
			headerHeight : 29,
			rowHeight : 29,
			// 편집 가능 여부 (기본값 : false)
			editable : true,				
			selectionMode : "singleRow",
			rowCheckColumnWidth : 30,
			enterKeyColumnBase : true,
			enableClipboard: false,
			showRowCheckColumn : true, // 체크박스 사용
			independentAllCheckBox : true,
			rowCheckVisibleFunction : function(rowIndex, isChecked, item) {
				if(item.rcbodSeq) { 
					return true;
				}
				return false;
			}
		}
		
		var gridPros2 = {
			headerHeight : 29,
			rowHeight : 29,
			editable : true,
			selectionMode : "multipleCells",
			showRowCheckColumn : false, // 체크박스 사용
			noDataMessage : "엑셀에서 데이터를 복사(Ctrl+C) 하여 이곳에 붙여 넣기(Ctrl+V) 하십시오.",
			rowCheckColumnWidth : 30,
			enterKeyColumnBase : true,
			showEditedCellMarker : false,
			softRemoveRowMode : true
		}
		// 실제로 #grid_wrap 에 그리드 생성
		myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
		myGridID2 = AUIGrid.create("#grid_wrap2", columnLayout2, gridPros2);
	}

	var columnLayout = [{
		dataField : "rcbodSub",
		headerText : "제목",
		style: "auiLeft auiLink",
		editable : function(){
			
		},
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction: function (rowIndex, columnIndex, value, headerText, item, dataField, cItem ) {
			if(item.rcbodSeq){
				var template = value
			}else{
				var template = '<div class="my_div">';
					template += '<span class="my_div_text_box">' + value + '</span>';
					template += '</div>';
			}
			return template;
		}
	},{
		dataField : "regDtm",
		headerText : "날짜",
		width : "40%",
		editable : false,	dataType : "date",		formatString: "yyyy-mm-dd" 
	}];

	var columnLayout2 = [
	{
		dataField : "agenCd",
		headerText : "대리점코드",
		width : "15%",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value,	headerText, item) {
			var template = '<div class="my_div">';
			if(item.chkYn == "N") {
				template += '<span class="my_div_text_box center pColor02">' + value	+ '</span>';
			}else{
				template += '<span class="my_div_text_box center">' + value	+ '</span>';
			}
			template += '</div>';
			return template;
		}
	},
	{
		dataField : "invc",
		headerText : "운송장번호",
		width : "15%",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value, headerText, item) {
			var template = '<div class="my_div">';
			template += '<span class="my_div_text_box center">' + value
					+ '</span>';
			template += '</div>';
			return template;
		}
	},
	{
		dataField : "prdNm",
		headerText : "물품명",
		width : "15%",
		style : "auiLeft",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value,
				headerText, item) {
			var template = '<div class="my_div">';
			template += '<span class="my_div_text_box center">' + value
					+ '</span>';
			template += '</div>';
			return template;
		}
	},
	{
		dataField : "qty",
		headerText : "수량",
		width : "5%",
		style : "auiRight",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value,
				headerText, item) {
			var template = '<div class="my_div">';
			template += '<span class="my_div_text_box center">' + value
					+ '</span>';
			template += '</div>';
			return template;
		}
	},
	{
		dataField : "consge",
		headerText : "수하인명",
		width : "10%",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value,
				headerText, item) {
			var template = '<div class="my_div">';
			template += '<span class="my_div_text_box center">' + value
					+ '</span>';
			template += '</div>';
			return template;
		}
	},
	{
		dataField : "addr",
		headerText : "주소",
		style : "auiLeft",
		editable : true,
		renderer : {
			type : "TemplateRenderer"
		},
		labelFunction : function(rowIndex, columnIndex, value,
				headerText, item) {
			var template = '<div class="my_div">';
			template += '<span class="my_div_text_box center">' + value
					+ '</span>';
			template += '</div>';
			return template;
		},
	},{
		dataField : "chkYn",
		headerText : "chkYn",
		visible:false
	},{
		dataField : "agenSeq",
		headerText : "agenSeq",
		visible:false
	}];

	// 행 삭제 이벤트 핸들러
	function auiRemoveRowHandler(event) {
		document.getElementById("rowInfo").innerHTML = (event.type + " 이벤트 :  "
				+ ", 삭제된 행 개수 : " + event.items.length
				+ ", softRemoveRowMode : " + event.softRemoveRowMode);
	};

	//그리드 로우 삭제 이벤트
	function removeRowItem(){
		var checkedItems = AUIGrid.getCheckedRowItems(myGridID);
		var rowItem;
		for(var i=checkedItems.length-1; i>=0; i--) {
			rowItem = checkedItems[i];
			AUIGrid.removeRow(myGridID, rowItem.rowIndex);
		}
		updateRcbod();
		$(".modalCloseBtn").click();
	}

	//왼쪽 그리드 자료실 리스트 조회
	function selectRcbodList(){
		$.ajax({
			url : "/cmmc/selectRcbodList.do",
			type : 'POST',
			data : $("#frm").serialize(),
			success : function(data) {
				AUIGrid.setGridData(myGridID, data);
				$("#totCnt").html(data.length);
				addRow = false;
				preRow = "";
			}, // success 
			error : function(xhr, status) {
				alert("자료실 조회 중 오류가 발생하였습니다");
			}
		});
	
	}

	//왼쪽 그리드 셀 클릭 시 내용 조회
	function selectRcbodDtlList(obj){
		AUIGrid.clearGridData(myGridID2);
		if(obj.rcbodSeq){
			$.ajax({
				url : "/cmmc/selectRcbodDtlList.do",
				type : 'POST',
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(obj),
				success : function(data) {
					AUIGrid.setGridData(myGridID2, data);
				}, // success 
				error : function(xhr, status) {
					alert("자료실 목록 조회 중 오류가 발생하였습니다");
				}
			});
		}
 		
	}
	
	function resizeGrid() {
		AUIGrid.resize(myGridID, $("#content").width());
	}

	// 필수로 설정해야 하는 필드들의 값이 모두 입력되었는지 검사
	function validateGridData() {
		// name 과 country 는 필수로 입력되어야 하는 필드임. 이것을 검사
		if( AUIGrid.getSelectedItems(myGridID2)[0].item.rcbodSeq == null) {
			var isValid = AUIGrid.validateGridData(myGridID, ["rcbodSub"], "필수 필드는 반드시 값을 직접 입력해야 합니다.");
			if(!isValid) {
				var popupParam = [];
				popupParam.data = {
					title : "자료실",
					message : "<spring:message code='alert.rcbod04'/>",
					showBtn2 : 'N'
				}
				layerAlert(popupParam);
				return false;
			}
		}
		return true;
	};
	
	// 필수로 설정해야 하는 필드들의 값이 모두 입력되었는지 검사
	function validateGridData2() {
		var contList = AUIGrid.getGridData(myGridID2);
		var isValid;
		$.each(contList, function(i,v){
			if(	fnIsEmpty(v.agenCd) && fnIsEmpty(v.invc) && fnIsEmpty(v.prdNm) && fnIsEmpty(v.qty) && fnIsEmpty(v.consge) && fnIsEmpty(v.addr)){
				isValid = true;
			}else if(fnIsEmpty(v.agenCd) || fnIsEmpty(v.invc)||fnIsEmpty(v.prdNm)||fnIsEmpty(v.qty) || fnIsEmpty(v.consge) || fnIsEmpty(v.addr)) {
				isValid = false;
				AUIGrid.setFocus(myGridID2)
				return false;
			}else{
				isValid = true;
			}
		});
		
		if(!isValid) {
			var popupParam = [];
			popupParam.data = {
				title : "자료실",
				message : "<spring:message code='alert.rcbod05'/>",
				showBtn2 : 'N'
			}
			layerAlert(popupParam);
			return false;
		}
		return true;
	};

	function rmvRcbod(){
		var rmvData = new Array();
		var checkList = AUIGrid.getCheckedRowItems(myGridID);
		$.each(checkList, function(i,v){
			rmvData.push(v.item);
		});
		
		$.ajax({
			url : "/cmmc/delRcbodList.do",
			type : 'POST',
			contentType: 'application/json',
			data : JSON.stringify(rmvData),
			success : function(data) {
				var popupParam = [];
				popupParam.data = {
						title : "자료실",
						message : data,
						showBtn2 : 'N'
				}
				layerAlert(popupParam);
				selectRcbodList();
			}, // success 
			error : function(xhr, status, errorThrown) {
				if ( Common.IsJsonString(xhr.responseText) )
				{
					var message = JSON.parse(xhr.responseText).message;
					alert(message);
				}
				else{
					alert("처리중 오류가 발생 하였습니다.");
				}
			}
		});
	}

	function clickReset(){
		AUIGrid.setSelectionBlock(myGridID,0, 0, 0, 2);
		$('[data-popup="modalAlert"]').fadeOut(100);
		$('[data-popup="modalAlert"]').parent().find(".modal_bg").fadeOut(100);
	}
	
	function removeBlankRow(){
		var contList = AUIGrid.getGridData(myGridID2);
		$.each(contList, function(i, v){
			if( fnIsEmpty(v.agenCd) && fnIsEmpty(v.invc) && fnIsEmpty(v.prdNm) 
				&& fnIsEmpty(v.qty) && fnIsEmpty(v.consge) && fnIsEmpty(v.addr) ){
				AUIGrid.removeRow(myGridID2, i)
			}
		});
	}
</script>

<!-- content -->
<div class="content">
	<!-- 조회 -->
	<form id="frm">
		<div class="inquiryBox">
			<dl>
				<dt>검색어</dt>
				<dd>
					<div class="formWrap">
						<select id="srcType" class="sel mr10" name="srcType">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">제목+내용</option>
						</select>
						<input type="text" name="srcKeyword" id="srcKeyword" class="inp w160 mr10"></input>
						<button type="button" name="searchBtn" class="comBtn" id="searchBtn">조회</button>
					</div>
				</dd>
			</dl>
			<div class="btnGroup right">
				<button type="button" name="" class="comBtn" id="saveBtn">저장</button>
			</div>
		</div>
	</form>
	<!-- 조회 -->

	<div class="girdBoxGroup type02">
		<div class="girdBox w33per">
			<div class="titleArea right">
				<p class="numState">
					<em>총 <span class="pColor01 fb" id="totCnt"></span></em> 건 이 조회되었습니다.
				</p>
				<div class="btnGroup">
					<button type="button" name="" class="comBtn small" id="addRowBtn">행추가</button>
					<button type="button" name="" class="comBtn small" id="removeRowBtn">행삭제</button>
				</div>
			</div>

			<!-- grid -->
			<div class="girdBox">
				<div id="grid_wrap"></div>
			</div>
			<!-- grid -->
		</div>

		<div class="conBox w65per">
			<div class="titleArea right">
				<h3 class="tit01">내용</h3>
			</div>

			<!-- grid -->
			<div class="girdBox">
				<div id="grid_wrap2"></div>
			</div>
			<!-- grid -->
		</div>
	</div>
</div>
<!-- content -->
