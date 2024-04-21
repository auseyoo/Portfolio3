<!--
	@File Name: agenUseConfm
	@File 설명 : 대리점 사용승인
	@UI ID : UI-AAGN-0101
	@작성일 : 2022.03.04
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
					<dt>대리점코드</dt>
					<dd>
						<input type="text" class="inp w160" name="menuSeq" id="menuSeq" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>사업자번호</dt>
					<dd>
						<input type="text" class="inp w160" name="menuNm" id="menuNm" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dt>사용승인</dt>
					<dd>
						<select class="sel" name="" id="">
							<option>전체</option>
							<option value="Y">Y</option>
							<option value="N">N</option>
						</select>
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
		var keyValueList = [{"confmYn":"Y", "value":"승인"}, {"confmYn":"0", "value":"미승인"}, {"confmYn":"N", "value":"차단"}];
		
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
				dataField : "agenSeq",
				headerText : "대리점 시퀀스",
				visible: false,
				editable : false
			},{
				dataField : "emplSeq",
				headerText : "대리점 사원 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "agenEmplSeq",
				headerText : "대리점 시퀀스 | 대리점 사원 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "agenCd",
				headerText : "대리점코드",
				width : "10%",
				editable : false
			},{
				dataField : "agenNm",
				headerText : "대리점명",
				width : "10%",
				editable : false
			},{
				dataField : "bizNo",
				headerText : "사업자번호",
				width : "10%",
				editable : false
			},{
				dataField : "agenPrst",
				headerText : "대표자명",
				width : "10%",
				editable : false
			},{
				dataField : "delgStatDt",
				headerText : "거래시작일자",
				width : "10%",
				editable : false
			},{
				dataField : "delgEndDt",
				headerText : "거래종료일자",
				width : "10%",
				editable : false
			},{
				dataField : "confmDt",
				headerText : "승인일자",
				width : "10%",
				editable : false
			},{
				dataField : "intrcpDt",
				headerText : "차단일자",
				width : "10%",
				editable : false
			
			},{
				dataField : "confmYn",
				headerText : "사용승인",
				headerTooltip  : {
					show : true,
					tooltipHtml : "승인, 미승인, 차단 콤보박스"
				},
				width : "10%",
				labelFunction : function(  rowIndex, columnIndex, value, headerText, item ) { 
					var retStr = "";
					for(var i=0,len=keyValueList.length; i<len; i++) {
						if(keyValueList[i]["confmYn"] == value) {
							retStr = keyValueList[i]["value"];
							break;
						}
					}
					return retStr == "" ? value : retStr;
				},
				editRenderer : {
					type : "ComboBoxRenderer",
					list : keyValueList, //key-value Object 로 구성된 리스트
					keyField : "confmYn", // key 에 해당되는 필드명
					valueField : "value" // value 에 해당되는 필드명
				}
	        },{
				dataField : "emplPwd",
				headerText : "임시비밀번호",
				width : "10%",
				style: "auiRight",
				editable : true,
				renderer : {
					type : "TemplateRenderer"
				},
				labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
					var template = '<div class="my_div">';
					template += '<span class="my_div_text_box right">' + value + '</span>';
					template += '</div>';
					return template;
				}
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
				url : "/agen/agenUseConfmList.do", 
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
			
			// 수정된 행 아이템들(배열) - 진짜 수정될 필드만 갖고 있음.
			// 예를들어 칼럼이 총 10개 있다고 했을 때 그 중 2개 칼럼만 수정했다면 해당 2개 칼럼만을 반환합니다.
			var editedRowItems = AUIGrid.getEditedRowColumnItems(myGridID);
			if (Common.isEmpty(editedRowItems) || editedRowItems.length == 0)
			{
				alert("수정된 데이터가 없습니다.");
				return;
			}
			
			$("#updateRows").val(JSON.stringify(editedRowItems));
			var param = $("#frm").serializeObject();
			
			$.ajax({
				url : "/agen/agenUseConfmUpd.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				beforeSend:function(xhr){
					AUIGrid.showAjaxLoader(myGridID);
				},
				success : function(data) {
					if (Common.isNotEmpty(data.res) && data.res && data.updateCnt > 0)
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