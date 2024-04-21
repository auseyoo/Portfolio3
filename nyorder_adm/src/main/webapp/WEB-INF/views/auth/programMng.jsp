<!--
	@File Name: programMng
	@File 설명 : 프로그램 권한관리
	@UI ID : UI-AATH-0301
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
					<dt>그룹 Level</dt>
					<dd>
						<select class="sel" name="menuLv" id="menuLv">
							<option value="">전체</option>
							<c:forEach items="${groupLvList}" var="i" >
								<option value="${i.menuLv}">${i.menuLv} Level</option>
							</c:forEach>
						</select>
					</dd>
					<dt>프로그램 ID</dt>
					<dd>
						<input type="text" class="inp w80" name="menuSeq" id="menuSeq" maxlength="15">
					</dd>
					<dt>프로그램명</dt>
					<dd>
						<input type="text" class="inp w160" name="menuNm" id="menuNm" maxlength="15">
					</dd>
					<dt>호출 URL</dt>
					<dd>
						<input type="text" class="inp w160" name="menuUrl" id="menuUrl" maxlength="500" onkeydown="javascirpt:fn_onEnterSubmit();">
					</dd>
					<dd>
						<button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
					</dd>
				</dl>
				
				<div class="btnGroup right">
					<button type="button" class="comBtn" name="regBtn" id="regBtn" >등록</button>
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
	
	<!-- <div class="modal_bg" style="display:block;"></div> --> <!-- modal 배경 -->
	<div class="modal_bg" ></div>
	
    <!-- 프로그램 등록 -->
    <!-- <div class="popWrap small modal_wrap h400" data-popup="modalAgencyViews" style="display:block; left:0; right:0; margin:150px auto;"> -->
    <div class="popWrap small modal_wrap h400" data-popup="modalAgencyViews" >
        <header>
            <h3>프로그램 등록</h3>
            <button type="button" class="closeBtn" name="modalCloseBtn2" id="modalCloseBtn2"></button>
        </header>  

        <div class="popCon">
            <div class="popTitArea">
                <h3>프로그램 등록</h3>
    
                <div class="popBtnArea">
                    <button type="button" class="comBtn modalCloseBtn" name="modalCloseBtn" id="modalCloseBtn">닫기</button>
                    <button type="button" class="inquBtn" name="modalRegBtn" id="modalRegBtn">저장</button>
                </div>
            </div>

            <div class="txtInfo pColor02">
                ※ 최초 등록 시 시스템관리자 그룹만 권한이 생성됩니다.  
            </div>

            <div class="titleArea">
                <h3 class="tit01">상세 정보</h3>
            </div>
			
			<form id="frm2" name="frm2" method="POST" >
            <div class="tblWrap">
                <table class="tbl">
                    <caption>상세 정보</caption>
                    <colgroup>
                        <col style="width:164px;">
                        <col>
                    </colgroup>
                    <tbody>
                    	<tr>
                            <th scope="row">
                                <label for="sel_group" class="required">상위그룹</label>
                            </th>
                            <td>
                                <select class="sel w160" name="pMenuParentSeq" id="pMenuParentSeq">
                                    <c:forEach items="${getParentMenuList}" var="i" >
										<option value="${i.menuSeq}">${i.menuNm}</option>
									</c:forEach>
									<option value="0">미선택</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="inp_proName01" >프로그램ID</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="pMenuId" id="pMenuId" placeholder="프로그램ID" disabled>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="inp_proName02" class="required" title="필수입력">프로그램명</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="pMenuNm" id="pMenuNm" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="inp_url" class="required">호출 URL</label>
                            </th>
                            <td>
                                <input type="text" class="inp" name="pMenuUrl" id="pMenuUrl" maxlength="100">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            </form>
        </div>
    </div>
    <!-- 프로그램 등록 -->
	
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
			
			// 등록 버튼 클릭
			$("#regBtn").click(function() {
				$(".modal_bg").attr("style", "display:block;");
				$(".popWrap").attr("style", "display:block; left:0; right:0; margin:150px auto;");
			});
			
			// 팝업 닫기 클릭
			$("#modalCloseBtn").click(function() {
				$(".modal_bg").removeAttr("style");
				$(".popWrap").removeAttr("style");
			});

			// 팝업 닫기 클릭 X
			$("#modalCloseBtn2").click(function() {
				$(".modal_bg").removeAttr("style");
				$(".popWrap").removeAttr("style");
			});
			
			// 상위그룹 change 이벤트
			$("#pMenuParentSeq").change(function() {
				//alert(1);
			});
			
			// 상위그룹 change 강제 이벤트
			$("#pMenuParentSeq option").eq(0).prop('selected', true);   // index는 0부터 시작
			$("#pMenuParentSeq").trigger('change');
			
			// 메뉴등록 저장
			$("#modalRegBtn").click(function() {
				fn_save2();
			});
			
		});
		
		var columnLayout = [{
				dataField : "menuLv",
				headerText : "그룹 Level",
				width : "10%",
				editable : false
				//visible: false
			},{
				dataField : "menuParentSeq",
				headerText : "상위그룹 ID",
				width : "10%",
				editable : false
			},{
				dataField : "menuSeq",
				headerText : "프로그램 ID",
				width : "10%",
				editable : false
				//visible: false
			},{
				dataField : "menuNm",
				headerText : "프로그램명",
				width : "20%",
				editable : false
			},{
				dataField : "menuUrl",
				headerText : "호출URL",
				width : "30%",
				editable : false
			},{
				dataField : "menuOrdr",
				headerText : "정렬순서",
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
			},{
				dataField : "useYn",
				headerText : "사용여부",
				width : "10%",
				renderer : {
					type : "DropDownListRenderer",
					list : posList
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
				//rowIdField : "id",
				
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
			
			// 체크박스 클린 이벤트 바인딩
			AUIGrid.bind(myGridID, "rowCheckClick", function( event ) {
				//alert("rowIndex : " + event.rowIndex + ", id : " + event.item.id + ", name : " + event.item.name + ", checked : " + event.checked + ", shiftKey : " + event.shiftKey + ", shiftIndex : " + event.shiftIndex);
			});
			
			// 전체 체크박스 클릭 이벤트 바인딩
			AUIGrid.bind(myGridID, "rowAllChkClick", function( event ) {
				//alert("전체 선택  checked : " + event.checked);
			});
			
			// 에디팅 정상 종료 이벤트 바인딩
			AUIGrid.bind(myGridID, "cellEditEnd", auiCellEditingHandler);
			
	    }
		
	    function auiCellEditingHandler(event) {
		    // 1로우의 체크박스 하나만 선택 하도록~
			if(event.type == "cellEditEnd") {
				// 체크 박스에 맞는 rowItem 얻기
				var rowItem = AUIGrid.getItemByRowIndex(myGridID, event.rowIndex);
				// row 데이터 업데이트
				AUIGrid.updateRow(myGridID, rowItem, event.rowIndex);
			}
		}
		
		function fn_search() {
			$.ajax({
				url : "/auth/programMngList.do", 
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
				url : "/auth/programMngUpd.do", 
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
		
		function fn_save2() {
			
			var param = $("#frm2").serializeObject();
			
			$.ajax({
				url : "/auth/programMngReg.do", 
				type : 'POST', 
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				success : function(data) {
					if (Common.isNotEmpty(data.res) && data.res && data.updateCnt > 0)
					{
						alert("저장 되었습니다.");
						$("#modalCloseBtn").trigger("click");
						fn_search();
					}
					else
					{
						alert("저장 실패 하였습니다.\n다시 시도해 주세요.");
					}
				}, // success 
				error : function(xhr, status) {
					alert(xhr + " : " + status);
				}
			});
		}
		
	</script>