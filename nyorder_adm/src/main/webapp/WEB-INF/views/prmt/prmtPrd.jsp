<!--
	@File Name: prmtPrd
	@File 설명 : 판촉물 요청 자재 관리
	@UI ID : UI-APRO-0401
	@작성일 : 2022.03.07
	@작성자 : JUNGAE
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
    var myGridID;
   
   	$(document).ready(function(){
   	   	
        createAUIGrid(columnLayout);   

        $("#dtlInfoArea").html($("#detailTmpl").tmpl({}));

    	$(".prdCls").change(function(){
    		var target = $(this).data("clsTarget");
    		setMultiCombo(target);
    	});
        
        //fnSearchList();

        //바인딩
        $("#selectBtn").on("click", fnSearchList);     
        $("#saveBtn").on("click", fnSavePrmtPrd);
        $("#copyBtn").on("click", fnCopyPrmtPrd);

        $("#frmDownBtn").click(function() {
			fn_sampleDown();
		});
		
        $("#exlUpldBtn").on("click", function(){
    		$("#fileNm").val("");
  			$("#file").val("");
        	openPopup('prmtPrdUploadPop');
		});
        $("#modalCloseBtn").on("click", function(){
        	closePopup('prmtPrdUploadPop');
		});
        $("#uploadBtn").on("click", fnExcelUpload);        

    	AUIGrid.bind(myGridID, "cellClick", function(event) {
    		searchDetail(event.item);
    	});

    	//파일업로드 형식 제한
    	$('input[type="file"]').change(function (e) {
    		if (this.files[0].size > ${uploadMaxSize}) {
    			alert('<spring:message code="alert.prmtExcUpld03" />');
    			this.value = '';
    			return;
    		}			 
    		var ext = this.value.match(/\.(.+)$/)[1];
    		if(ext.toUpperCase() != 'XLS' && ext.toUpperCase() != 'XLSX'){
    			alert('<spring:message code="alert.prmtExcUpld02" />');
				this.value = '';
				return;
			}
    		$("#fileNm").val(this.files[0].name);
    	});

    	// 파일 업로드 삭제 버튼
    	$("button[name='rmvFileBtn']").click(function(){
    		$("#fileNm").val("");
  			$("#file").val("");
    	});    	
    });
    
   
    // AUIGrid 를 생성합니다.
    function createAUIGrid(columnLayout) {
        
    	// 그리드 속성 설정
        var gridPros = {
				headerHeight : 58,
				rowHeight : 29,
				// 편집 가능 여부 (기본값 : false)
				editable : false,
				
				// 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
				enterKeyColumnBase : true,
				
				// 셀 선택모드 (기본값: singleCell)
				selectionMode : "multipleCells",
		
				showFooter : false,
				
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
		}
    
        // 실제로 #grid_wrap 에 그리드 생성
        myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
        
    }

    var columnLayout = [
    	{
    		dataField : "useYn", // 이미지렌더러로 체크박스 모양 만들기
    		headerText : "소요량<br/>등록",
    		width: "6%",
    		renderer: {
    			type: "CheckBoxEditRenderer",
    			editable: true,
    			imgHeight : 15,
    			imgTableRef : {
    				"true" : "/images/check_on.png",
    				"false" : "/images/check_default.png",
    				"default" : "/images/check_on.png"
    			},
    		}
    	},{
    		dataField : "prdSapCd",
    		headerText : "제품 코드",
    		width : "10%",
    		style : "auiLink" 
    	},{
    		dataField : "prdNm",
    		headerText : "제품명",
    		style : "auiLeft"	
    	},{
    		dataField : "lclsNm",
    		headerText : "제품<br/>대분류",
    		width : "11%"
    	},{
    		dataField : "mclsNm",
    		headerText : "제품<br/>중분류",
    		width : "11%"
    	},{
    		dataField : "sclsNm",
    		headerText : "제품<br/>소분류",
    		width : "11%"
    	},{
    		dataField : "dclsNm",
    		headerText : "제품<br/>세분류",
    		width : "11%"
    	},{
    		dataField : "prdTypeNm",
    		headerText : "자재유형",
    		width : "11%"
    	},{
    		dataField : "useYnTx",
    		headerText : "상태",
    		width : "11%"
    	},
    	
    ];


	/* 검색 멀티콤보 설정 */
	function setMultiCombo(type){
		$("#"+type+"cls option").remove();
		switch(parseInt($("#"+type+"cls").data("clsLvl"))){
			case 2 : {
				$("#mcls option").remove();
				$("#mcls").append("<option value=''>중분류</option>");
			}
			case 3 : {
				$("#scls option").remove();
				$("#scls").append("<option value=''>소분류</option>");
			}
			case 4 : {
				$("#dcls option").remove();
				$("#dcls").append("<option value=''>세분류</option>");
			}
		}
		if(fnIsEmpty($("#lcls").val())){ return;}
		$.ajax({
			url : "/comm/selectPrdCls.do", 
			type : 'POST', 
			data : $("#frm").serialize(),
			success : function(data) {
				$("#"+type+"cls").append($("#codeTmpl").tmpl({"codeList": data}));
			},
			error : function(xhr, status) {
			}
		}); 
	}

	/* 상세 조회 */
	function searchDetail(row){
		$.ajax({
			url : "/std/selectStdPrdMngDetail.do", 
			type : "POST", 
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(row),
			success : function(data) {
				data.price = setComma(parseFloat(data.spprc) + parseFloat(data.vatSpprc));
				data.spprc = setComma(data.spprc);
				data.vatSpprc = setComma(data.vatSpprc);
				$("#dtlInfoArea").html($("#detailTmpl").tmpl(data));
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		});
	}
    
    function fnSearchList(){
		/*
    	if( fnIsEmpty($("#lcls").val())){
    		var popupParam = [];
    		popupParam.data = {
    			title : "제품 조회",
    	 	 	message : "<spring:message code='alert.noSelect' arguments="${'대분류'}"/>",
    	 	 	showBtn2 : 'N'
    		}
    		layerAlert(popupParam);
    		return;
    	}
    	if( fnIsEmpty($("#mcls").val())){
    		var popupParam = [];
    		popupParam.data = {
    			title : "제품 조회",
    	 	 	message : "<spring:message code='alert.noSelect' arguments="${'중분류'}"/>",
    	 	 	showBtn2 : 'N'
    		}
    		layerAlert(popupParam);
    		return;
    	}
    	if( fnIsEmpty($("#scls").val())){
    		var popupParam = [];
    		popupParam.data = {
    			title : "제품 조회",
    	 	 	message : "<spring:message code='alert.noSelect' arguments="${'소분류'}"/>",
    	 	 	showBtn2 : 'N'
    		}
    		layerAlert(popupParam);
    		return;
    	}
    	if( fnIsEmpty($("#dcls").val())){
    		var popupParam = [];
    		popupParam.data = {
    			title : "제품 조회",
    	 	 	message : "<spring:message code='alert.noSelect' arguments="${'세분류'}"/>",
    	 	 	showBtn2 : 'N'
    		}
    		layerAlert(popupParam);
    		return;
    	}
		*/
    	$("#dtlInfoArea").html($("#detailTmpl").tmpl({}));
    		
 		$.ajax({
			url : "/prmt/selectPrmtPrdList.do", 
			type : 'POST', 
			data : $("#frm").serialize(),
			success : function(data) {
				AUIGrid.setGridData(myGridID, data);
				$("#listCnt").text(data.length);
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 	
	}

    function fnSavePrmtPrd(){

    	/* 저장 이벤트 호출시 날짜 체크 시작 */
		var today = new Date();
	    var year = today.getFullYear();
	    var month = ("0" + (1 + today.getMonth())).slice(-2);	    

	    var selMonth = $("#monthPicker").val().replace('-','');

		if(selMonth < (year+month)){
			alert("이전 연월은 저장 불가합니다.");
			return;
		}
		/* 저장 이벤트 호출시 날짜 체크 종료 */
		
		var saveData = new Array();

		var item = new Object();
		item.rqstMonth = selMonth;
		saveData.push(item);	
		
		$.each(AUIGrid.getGridData(myGridID), function(idx,item){
			if(item.useYn == true){
				item.rqstMonth = selMonth;
				saveData.push(item);	
			}
		});
		
		/* if(!saveData.length > 0){
			alert("저장할 정보가 없습니다.\r\n다시 확인해 주세요.");
			return;
		} */

		$.ajax({
			url : "/prmt/savePrmtPrd.do",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(saveData),
			success : function(data) {
				//alert( data );
				alert("저장되었습니다.");
				
				//리스트 조회
				fnSearchList();
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 
	}

    function fnCopyPrmtPrd(){
        
		$.ajax({
			url : "/prmt/copyPrmtPrd.do",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : null,
			success : function(data) {
				//alert( data );
				alert("복사되었습니다.");

				//리스트 조회
				fnSearchList();
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 
	}

	function fnFrmDown(){
		alert('개발 예정임');
	}
	
	function fnExcelUpload(){

		if($("#file").val() == ''){
			alert('<spring:message code="alert.prmtExcUpld01" />');
			return;	
		}

		var ext = $("#file").val().match(/\.(.+)$/)[1];
		if(ext.toUpperCase() != 'XLS' && ext.toUpperCase() != 'XLSX'){
			alert('<spring:message code="alert.prmtExcUpld02" />');
			this.value = '';
			return;
		}

		if ($("#file")[0].files[0].size > ${uploadMaxSize}) {
			alert('<spring:message code="alert.prmtExcUpld03" />');
			this.value = '';
			return;
		}
		
		$("#rqstMonthHid").val($("#monthPicker").val());
		var formData = new FormData($("#frmUpload")[0]);
		$.ajax({
			url : "/prmt/prmtPrdUpload.do",
			type : 'POST',
			data : formData,
			processData:false, 
			contentType:false,
			dataType:'json',
			enctype : 'multipart/form-data',
			success : function(data) {
				var msg = null;
				if(data.compFlag == "Y"){
					msg = '<spring:message code="alert.prmtExcUpld05" arguments="' + data.totCnt + ',' + data.failCnt + '"  />';
				}else if(data.compFlag == "N"){
					msg = '<spring:message code="alert.prmtExcUpld04" arguments="' + data.failMsg + '" />';
				}
				alert(msg);
				closePopup('prmtPrdUploadPop');		
				fnSearchList();		
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

	function fn_sampleDown() {
		var ui = "UI-APRO-0401.xlsx";
		
		var frm = document.frm2;
		frm.action = "/file/" + ui + "/downloadFile.do";
		frm.submit();
	}
</script>

<div class="content">
	<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>

	<form id="frm" name="frm" method="POST">
		<input type="hidden" name="prdType" id="prdType" value="HAW2">	<!-- 판촉물 -->

		<!-- 조회 -->
		<div class="inquiryBox row">
			<div class="dlRowBoxWrap">
				<div class="dlBox">
					<dl>
						<dt>납품요청월</dt>
						<dd>
							<div class="dateWrap">
								<input type="text" name="rqstMonth" value="" class="inp monthPicker" id="monthPicker" readonly="">
								<button type="button" class="datepickerBtn" title="날짜입력" data-target-id="datepicker"></button>
							</div>
						</dd>
						<dt>분류</dt>
						<dd>
							<div class="formWrap">
								<select name="lcls" class="sel w120 mr10 prdCls" id="lcls" data-cls-target="m" data-cls-lvl="1">
									<option value="">대분류</option>
									<c:forEach items="${lclsList}" var="i" >
										<option value="${i.code}">${i.name}</option>
									</c:forEach>
								</select>
								<select name="mcls" class="sel w120 mr10 prdCls" id="mcls" data-cls-target="s" data-cls-lvl="2">
									<option value="">중분류</option>
								</select>
								<select name="scls" class="sel w120 mr10 prdCls" id="scls" data-cls-target="d" data-cls-lvl="3">
									<option value="">소분류</option>
								</select>
							</div>
						</dd>
					</dl>
					<dl>
						<dt>
							<select name="dcls" class="sel w120" id="dcls" data-cls-lvl="4">
								<option value="">세분류</option>
							</select>
						</dt>
						<dt>판촉물</dt>
						<dd>
							<div class="formWrap">
								<input type="text" id="srcPrdCd" class="inp w120 mr10" value="" name="srcPrdCd" placeholder="제품코드" maxlength="8">
								<input type="text" id="srcPrdNm" class="inp w160 mr7" value="" name="srcPrdNm" placeholder="제품명">
							</div>
						</dd>
					</dl>
				</div>

				<div class="btnVer">
					<button type="button" name="" class="comBtn" id="selectBtn">조회</button>
				</div>
			</div>

			<div class="btnGroup right">
				<button type="button" name="" class="comBtn" id="frmDownBtn">양식다운</button>
				<button type="button" name="" class="comBtn" id="exlUpldBtn">엑셀업로드</button>
				<button type="button" name="" class="comBtn" id="copyBtn">전월복사</button>
				<button type="button" name="" class="inquBtn" id="saveBtn">저장</button>
			</div>
		</div>
		<!-- 조회 -->
	</form>

	<div class="girdBoxGroup">
		<!-- grid -->
		<div class="girdBox w55per">
			<div class="titleArea right">
				<p class="numState">
					<em>총 <span class="pColor01 fb" id="listCnt">0</span></em> 건 이 조회되었습니다.
				</p>
			</div>
			<div id="grid_wrap"></div>
		</div>

		<div class="conBox w43per">
			<div class="titleArea right">
				<h3 class="tit01">세부 정보</h3>
			</div>

			<!-- 세부정보 -->
			<div id="dtlInfoArea"></div>
			<!-- 세부정보 -->
		</div>
	</div>

</div>

<form id="frm2" name="frm2" method="post" onsubmit="return false;" >
</form>
	
<div class="modal_bg"></div> <!-- modal 배경 -->

<!-- 엑셀파일 업로드 -->
<div class="popWrap small modal_wrap h248" data-popup="prmtPrdUploadPop" style="left:0; right:0; margin:150px auto;">
    <header>
        <h3>엑셀파일 업로드</h3>
        <button type="button" name="" class="closeBtn" id="modalCloseBtn"></button>
    </header>  

	<form id="frmUpload" name="frmUpload" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="rqstMonth" value="" id="rqstMonthHid">
    <div class="popCon">
        <div class="popTitArea">
            <h3>엑셀파일 업로드</h3>

            <div class="popBtnArea">
                <button type="button" name="" class="comBtn modalCloseBtn" id="modalCloseBtn">닫기</button>
                <button type="button" name="" class="inquBtn" id="uploadBtn">저장</button>
            </div>
        </div>

        <div class="txtInfo pColor02">
            ※ xls, xlsx 확장자의 10MB 이하 파일만 업로드 할 수 있습니다.
        </div>

        <div class="fileInfo type02">
            <div class="formWrap type02 file">
                <p class="tit">파일 :</p>
                <p>
                    <div class="formWrap filebox">
                        <input class="upload" id="fileNm" value="첨부파일" placeholder="첨부파일">
                        <label for="file">찾기</label> 
                        <input type="file" id="file" name="file">

                        <button type="button" id="rmvFileBtn" name="rmvFileBtn" class="delBtn">삭제</button>
                    </div>
                </p>
            </div>
        </div>
    </div>
    </form>
</div>
<!-- 엑셀파일 업로드 -->

<script id="codeTmpl" type="text/x-jquery-tmpl">
{{each(i, e) codeList }}
	<option value='\${e.code}'>\${e.name}</option>
{{/each}}
</script>

<script id="detailTmpl" type="text/x-jquery-tmpl">
<form id="detailForm">
<div class="tblWrap">
	<table class="tbl">
		<caption>세부 정보</caption>
		<colgroup>
			<col style="width:132px;">
			<col>
			<col style="width:132px;">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">제품코드</th>
				<td>
					<p>\${prdSapCd}</p>
				</td>
				<th scope="row">자재유형</th>
				<td><p>\${prdTypeNm}</p></td>
			</tr>
			<tr>
				<th scope="row">제품명</th>
				<td colspan="3"><p>\${prdNm}</p></td>
			</tr>
			<tr>
				<th scope="row">제품 대분류</th>
				<td><p>\${lclsNm}</p></td>
				<th scope="row">제품 중분류</th>
				<td><p>\${mclsNm}</p></td>
			</tr>
			<tr>
				<th scope="row">제품 소분류</th>
				<td><p>\${sclsNm}</p></td>
				<th scope="row">제품 세분류</th>
				<td><p>\${dclsNm}</p></td>
			</tr>
			
			<tr>
				<th scope="row">주문여부</th>
				<td><p>\${ordUseYnTx}</p></td>
				<th scope="row"></th>
				<td></td>
			</tr>
			<tr>
				<th scope="row">BOX 입수량</th>
				<td class="ar"><p>\${faltQty}</p></td>
				<th scope="row">낱봉기준</th>
				<td>낱봉기준</td>
			</tr>
			<tr>
				<th scope="row">규격</th>
				<td class="ar"><p>\${prdStrd}</p></td>
				<th scope="row">단위</th>
				<td><p>\${prdUnit}</p></td>
			</tr>
			<tr>
				<th scope="row">낱봉주문</th>
				<td><p>\${iddyUntYn}</p></td>
				<th scope="row"></th>
				<td></td>
			</tr>
			<tr>
				<th scope="row">과세구분</th>
				<td>${taxtNm}</td>
				<th scope="row">매입단가</th>
				<td>
					<p>\${price}</p>
				</td>
			</tr>
			<tr>
				<th scope="row">공급가</th>
				<td class="ar">
					<p>\${spprc}</p>
				</td>
				<th scope="row">VAT</th>
				<td>
					<p>\${vatSpprc}</p>
				</td>
			</tr>

		</tbody>
	</table>
</div>
</form>
</script>	