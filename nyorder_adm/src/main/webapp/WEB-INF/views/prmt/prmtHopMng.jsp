<!--
	@File Name: prmtHopMng
	@File 설명 : 판촉관리 > 팝촉홉수관리
	@UI ID : UI-APRO-0201
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
			<input type="hidden" name="updateRows" id="updateRows" />
			
			<!-- 조회 -->
			<div class="inquiryBox">
				<dl>
					<dt>분류</dt>
					<dd>
						<div class="formWrap">
						<select class="sel mr10 prdCls" name="lcls" id="lcls">
							<option value="">대분류</option>
							<c:forEach items="${lclsList}" var="i" >
								<option value="${i.code}">${i.name}</option>
							</c:forEach>
						</select>
						
						<select class="sel mr10 prdCls" name="mcls" id="mcls">
							<option value="">중분류</option>
						</select>
						
						<select class="sel mr10 prdCls" name="scls" id="scls">
							<option value="">소분류</option>
						</select>
						
						<select class="sel" name="dcls" id="dcls">
							<option value="">세분류</option>
						</select>
						</div>
					</dd>
					<dt>제품</dt>
					<dd>
						<div class="formWrap">
							<input type="text" class="inp w120 mr10" name="prdSapCd" id="prdSapCd" title="제품코드 입력" placeholder="제품코드" maxlength="20" onkeydown="javascirpt:fn_onEnterSubmit();">
							<input type="text" class="inp w160" name="prdNm" id="prdNm" title="제품명 입력" placeholder="제품명" maxlength="50" onkeydown="javascirpt:fn_onEnterSubmit();">
							<button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
						</div>
					</dd>
				</dl>
				
				<div class="btnGroup right">
					<button type="button" class="inquBtn" name="excelDownBtn" id="excelDownBtn">양식다운</button>
					<button type="button" class="inquBtn" name="excelUploadBtn" id="excelUploadBtn">엑셀업로드</button>
					<button type="button" class="inquBtn" name="saveBtn" id="saveBtn">저장</button>
				</div>
			</div>
			<!-- 조회 -->
		</form>
		
		<!-- <form method="post" enctype="multipart/form-data" id="fileUploadForm2" name="fileUploadForm2" >
			<input type="file" name="file" id="file">
		</form> -->
		<div class="titleArea">
			<p class="numState">
				<em>총 <span class="pColor01 fb" id="listCnt">0</span></em> 건 이 조회되었습니다.
			</p>
		</div>
		
		<!-- grid -->
		<div class="girdBox">
			<div id="grid_wrap"></div>
		</div>
		<!-- grid -->
	</div>
	
	<!-- <div class="modal_bg" style="display:block"></div> --> <!-- modal 배경 -->
	<div class="modal_bg" ></div>
	
    <!-- 엑셀파일 업로드 -->
    <!-- <div class="popWrap small modal_wrap h248" data-popup="modalAgencyViews" style="display:block; left:0; right:0; margin:150px auto;"> -->
    <div class="popWrap small modal_wrap h248" data-popup="modalAgencyViews" >
        <header>
            <h3>엑셀파일 업로드</h3>
            <button type="button" class="closeBtn" name="" id="modalCloseBtn"></button>
        </header>  

        <div class="popCon">
            <div class="popTitArea">
                <h3>엑셀파일 업로드</h3>
    
                <div class="popBtnArea">
                    <button type="button" class="comBtn modalCloseBtn" name="" id="modalCloseBtn2">닫기</button>
                    <button type="button" class="inquBtn" name="excelUpload" id="excelUpload" >저장</button>
                </div>
            </div>

            <div class="txtInfo pColor02">
                ※ xls, xlsx 확장자의 10MB 이하 파일만 업로드 할 수 있습니다.
            </div>
            
            <!-- <div class="titleArea">
                <h3 class="tit01">내용 <span class="pColor03">(0/400 Byte)</span></h3>
            </div> -->
			<form method="post" enctype="multipart/form-data" id="fileUploadForm" name="fileUploadForm" >
				
	            <div class="fileInfo type02">
	                <div class="formWrap type02 file">
	                    <p class="tit">파일 :</p>
	                    <p>
	                        <div class="formWrap filebox">
	                            <input class="upload" id="fileNm" value="첨부파일" placeholder="첨부파일">
	                            <label for="file">찾기</label> 
	                            <input type="file" name="file" id="file">
								
	                            <!-- <button type="button" class="delBtn" name="" id="" >삭제</button> -->
	                        </div>
	                    </p>
	                </div>
	            </div>
			</form>
        </div>
    </div>
    <!-- 엑셀파일 업로드 -->
    
    <form id="frm2" name="frm2" method="post" onsubmit="return false;" >
	</form>
    
	<script type="text/javascript">
		
		var myGridID;
		
		$(document).ready(function() {
			// AUIGrid 생성 후 반환 ID
			createAUIGrid(columnLayout);
			// 데이터 요청, 요청 성공 시 AUIGrid 에 데이터 삽입합니다.        
			
			//AUIGrid.setGridData(myGridID, gridData);
			//fn_search();
			
			// 조회 버튼 클릭
			$("#searchBtn").click(function() {
				fn_search();
			});
			
			// 저장 버튼 클릭
			$("#saveBtn").click(function() {
				fn_save();
			});
			
			// 등록 버튼 클릭
			$("#excelUploadBtn").click(function() {
				$(".modal_bg").attr("style", "display:block;");
				$(".popWrap").attr("style", "display:block; left:0; right:0; margin:150px auto;");
			});
			
			// 팝업 닫기 클릭
			$("#modalCloseBtn").click(function() {
				$("#file").val("");
				$("#fileNm").val("첨부파일");
				$(".modal_bg").removeAttr("style");
				$(".popWrap").removeAttr("style");
			});
			
			// 팝업 닫기 클릭 X
			$("#modalCloseBtn2").click(function() {
				$("#file").val("");
				$("#fileNm").val("첨부파일");
				$(".modal_bg").removeAttr("style");
				$(".popWrap").removeAttr("style");
			});
			
			// 다운로드 버튼 클릭
			$("#excelDownBtn").click(function() {
				fn_sampleDown();
			});
			
			// 엑셀 업로드 버튼 클릭
			$("#excelUpload").click(function() {
				fn_excelUpload();
			});
			
			$(".prdCls").change(function(){
				var id = $(this).attr("id");
				setMultiCombo(id);
			});
			
		});
		
		var columnLayout = [{
				dataField : "prmtHopSeq",
				headerText : "판촉홉수 시퀀스",
				visible: false,
				editable : false
			},{
				dataField : "prdSeq",
				headerText : "제품 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "puchSeq",
				headerText : "매입처 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "lcls",
				headerText : "제품대분류 코드",
				visible : false,
				editable : false
			},{
				dataField : "mcls",
				headerText : "제품중분류 코드",
				visible : false,
				editable : false
			},{
				dataField : "scls",
				headerText : "제품소분류 코드",
				visible : false,
				editable : false
			},{
				dataField : "dcls",
				headerText : "제품세분류 코드",
				visible : false,
				editable : false
			},{
				dataField : "prmtType",
				headerText : "판촉구분",
				visible : false,
				editable : false
			},{
				dataField : "lclsNm",
				headerText : "제품대분류",
	            width : "8%",
	            headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
				editable : false
	        },{
				dataField : "mclsNm",
				headerText : "제품중분류",
				width : "8%",
				headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
				editable : false
	        },{
	            dataField : "sclsNm",
				headerText : "제품소분류",
	            width : "8%",
	            headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
	            editable : false
			},{
	            dataField : "dclsNm",
				headerText : "제품세분류",
	            width : "8%",
	            headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
	            editable : false
	        },{
	            dataField : "prdSapCd",
				headerText : "제품코드",
				width : "7%",
				headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
				editable : false
	        },{
				dataField : "prdNm",
				headerText : "제품명",
				headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
				editable : false
	        },{
				dataField : "prmtTypeNm",
				headerText : "판촉구분",
	            width : "8%",
	            headerTooltip : { // 헤더 툴팁 표시 HTML 양식
	    			show : true,
	    			tooltipHtml : "어떤 경우라도 이 칼럼은 수정 불가 설정"
	    		},
	            editable : false
	        },{
				dataField : "hop1",
				headerText : "1개",
	            width : "5%",
	            dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop2",
				headerText : "2개",
				width : "5%",
				dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop3",
				headerText : "3개",
	            width : "5%",
	            dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop4",
				headerText : "4개",
	            width : "5%",
	            dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop5",
				headerText : "5개",
	            width : "5%",
	            dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop6",
				headerText : "6개",
				width : "5%",
				dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				dataField : "hop7",
				headerText : "7개 이상",
				width : "5%",
				dataType : "numeric",
	            //formatString : "#,##0.00",	// labelFunction 사용시 안먹힘
	            editRenderer : {
	            	type : "InputEditRenderer",
	            	//showEditorBtnOver : true, // 마우스 오버 시 에디터버턴 보이기
	            	onlyNumeric : true, // 0~9만 입력가능
	            	allowPoint : true, // 소수점( . ) 도 허용할지 여부
	            	allowNegative : false, // 마이너스 부호(-) 허용 여부
	            	textAlign : "right", // 오른쪽 정렬로 입력되도록 설정
	            	maxlength : 8, // 글자수 10으로 제한 (천단위 구분자 삽입(autoThousandSeparator=true)로 한 경우 구분자 포함해서 10자로 제한)
	            	autoThousandSeparator : true // 천단위 구분자 삽입 여부
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
				//rowIdField : "prmtHopSeq",
				
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
				//enableFilter : true,

				// 그룹핑 패널 사용
				//useGroupingPanel : true,
				
				// 즉, 각 나라별, 각 제품을 구매한 사용자로 그룹핑
				//groupingFields : ["lclsNm", "mclsNm", "sclsNm", "dclsNm"],

				// 최초 보여질 때 모두 열린 상태로 출력 여부
				//displayTreeOpen : true,

				// 그룹핑 후 셀 병합 실행
				//enableCellMerge : true,
				
				// enableCellMerge 할 때 실제로 rowspan 적용 시킬지 여부
				// 만약 false 설정하면 실제 병합은 하지 않고(rowspan 적용 시키지 않고) 최상단에 값만 출력 시킵니다.
				//cellMergeRowSpan : true,
				
				// 트리 컬럼(즉, 폴딩 아이콘 출력 칼럼) 을 인덱스1번으로 설정함(디폴트 0번임)
				//treeColumnIndex : 1,
				
				noDataMessage : "출력할 데이터가 없습니다.",
				
				groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다."
			};
			
			// 실제로 #grid_wrap 에 그리드 생성
			myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
			
	    }
		
		function fn_search() {
			
			if (Common.isEmpty($("#lcls").val()))
			{
				alert("대분류를 선택 해주세요.");
				$("#lcls").focus();
				return;
			}

			if (Common.isEmpty($("#mcls").val()))
			{
				alert("중분류를 선택 해주세요.");
				$("#mcls").focus();
				return;
			}

			if (Common.isEmpty($("#scls").val()))
			{
				alert("중분류를 선택 해주세요.");
				$("#scls").focus();
				return;
			}
			
			$.ajax({
				url : "/prmt/prmtHopMngList.do", 
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
		
		/* 검색 멀티콤보 설정 */
		function setMultiCombo(type) {
			
			fn_selectInit(type);
			
			var val = $("#"+type+" option:selected").val();
			if ( Common.isEmpty(val) )
			{
				return;
			}
			
			$.ajax({
				url : "/comm/selectPrdCls.do", 
				type : 'POST', 
				data : $("#frm").serialize(),
				//contentType : "application/json; charset=utf-8",
				success : function(data) {
					if ( Common.isNotEmpty(data) && data.length > 0)
					{
						var code = "";
						var name = "";
						//for (var i=0; i<=list.length; i++)
						for(key in data)
						{
							//console.log(data[key].code);
							//console.log(data[key].name);
							code = data[key].code;
							name = data[key].name;
							if (type == "lcls")
							{
								$("#mcls").append("<option value=\""+code+"\">"+name+"</option>");
							}
							else if (type == "mcls")
							{
								$("#scls").append("<option value=\""+code+"\">"+name+"</option>");
							}
							else if (type == "scls")
							{
								$("#dcls").append("<option value=\""+code+"\">"+name+"</option>");
							}
						}
					}
				},
				error : function(xhr, status) {
				}
			}); 	
		}
		
		function fn_selectInit(type) {
			if (type == "lcls")
			{
				$("#mcls").children('option:not(:first)').remove();
				$("#scls").children('option:not(:first)').remove();
				$("#dcls").children('option:not(:first)').remove();
			}
			else if (type == "mcls")
			{
				$("#scls").children('option:not(:first)').remove();
				$("#dcls").children('option:not(:first)').remove();
			}
			else if (type == "scls")
			{
				$("#dcls").children('option:not(:first)').remove();
			}
		}
		
		function fn_save() {
			
			// 그리드에서 수정된 아이템들의 묶음(배열)을 반환합니다.
			// 수정된 아이템은 최초의 데이터에서 addRow 메소드로 추가된 아이템을 수정한 경우 포함되지 않습니다
			var editedRowItems = AUIGrid.getEditedRowItems(myGridID);
			if ((Common.isEmpty(editedRowItems) || editedRowItems.length == 0)
					)
			{
				alert("수정된 데이터가 없습니다.");
				return;
			}
			
			$("#updateRows").val(JSON.stringify(editedRowItems));
			var param = $("#frm").serializeObject();
			
			$.ajax({
				url : "/prmt/prmtHopMngSave.do", 
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

		// 엑셀 업로드
		function fn_excelUpload() {
			
			var formData = new FormData($("#fileUploadForm")[0]);
			$.ajax({
				url : "/prmt/excelUpload.do",
				type : 'POST',
				data : formData,
				processData:false, 
				contentType:false,
				dataType:'json',
				enctype : 'multipart/form-data',
				success : function(data) {
					if (Common.isNotEmpty(data) 
							&& Common.isNotEmpty(data.res) 
							&& Common.isNotEmpty(data.updateCnt) 
							&& Common.isNotEmpty(data.totalCnt) 
							&& data.res 
							&& data.updateCnt > 0){
						alert(data.totalCnt + "건 중 " + data.updateCnt + "건이 완료 되었습니다.");
						$("#modalCloseBtn").trigger('click');
					}else{
						alert("저장 실패 하였습니다.\n다시 시도해 주세요.");
					}
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
			var ui = "UI-APRO-0201P1.xlsx";
			
			var frm = document.frm2;
			frm.action = "/file/" + ui + "/downloadFile.do";
			frm.submit();
		}
		
	</script>