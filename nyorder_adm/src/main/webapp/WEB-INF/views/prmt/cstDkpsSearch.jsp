<!--
	@File Name: cstDkpsSearch
	@File 설명 : 판촉관리 > 애음자 음용 현황조회
	@UI ID : UI-APRO-0901
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
            <div class="inquiryBox type02 formBtnRepon">
                <div class="dlBoxWrap">
                    <div class="dlBox">
                        <dl>
                            <dt>가입월</dt>
                            <dd>
                            	<div class="formWrap">
                            		<div class="dateWrap">
	                                    <input type="text" name="sPrmtDt" class="inp" id="monthPicker" readonly>
	                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="monthPicker"></button>
	                                </div>
	                                <span class="divi02">-</span>
	                                <div class="dateWrap">
	                                	<input type="text" name="ePrmtDt" class="inp" id="monthPicker2" readonly>
	                                    <button type="button" class="datepickerBtn" title="날짜입력" data-target-id="monthPicker2"></button>
	                                </div>
                            	</div>
                            </dd>
                            <dt>지점</dt>
                            <dd>
                                <div class="formWrap">
                                    <select class="sel" name="bhfcSeq" id="bhfcSeq">
                                        <option value="">전체</option>
										<c:forEach items="${bhfcList}" var="i" >
											<option value="${i.bhfcSeq}">${i.bhfcNm}</option>
										</c:forEach>
                                    </select>
                                </div>
                            </dd>
                            <dt>대리점코드</dt>
                            <dd>
                                <div class="formWrap">
                                    <input type="text" class="inp w120" name="agenCd" id="agenCd" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();" title="대리점코드 입력">
                                </div>
                            </dd>
                            <dt>애음자코드</dt>
                            <dd>
                                <div class="formWrap">
                                    <input type="text" class="inp w120" name="cstAgenCd" id="cstAgenCd" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();" title="애음자코드 입력">
                                </div>
                            </dd>
                        </dl>
                        <dl>
                            <dt>판촉사원코드</dt>
                            <dd>
                                <div class="formWrap">
                                    <input type="text" class="inp w120" name="prmtEmplCd" id="prmtEmplCd" maxlength="10" onkeydown="javascirpt:fn_onEnterSubmit();" title="판촉사원코드 입력">
                                </div>
                            </dd>
                        </dl>
                    </div>
                    <div class="btnRepon">
                        <button type="button" class="comBtn" name="searchBtn" id="searchBtn">조회</button>
                    </div>
                    
                    <div class="btnGroup right">
						<button type="button" class="inquBtn" name="excelDownBtn" id="excelDownBtn">엑셀다운</button>
					</div>
                </div>
            </div>
            <!-- 조회 -->
		</form>
		
		<div class="titleArea">
			<p class="numState">
				<em>총 <span class="pColor01 fb">0</span></em> 건 이 조회되었습니다.
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
		var sMonth = commDate.addDate("m", -3, fnGetToDay(), "-");
		sMonth = sMonth.substring(0, 7);
		
		$(document).ready(function() {
			
			// 조회 버튼 클릭
			$("#searchBtn").click(function() {
				fn_search();
			});
			
			// 다운로드 버튼 클릭
			$("#excelDownBtn").click(function() {
				//fn_sampleDown();
				exportTo();
			});
			
			// AUIGrid 생성 후 반환 ID
			createAUIGrid(columnLayout);
			// 데이터 요청, 요청 성공 시 AUIGrid 에 데이터 삽입합니다.        
			
			
			$("#monthPicker").val(sMonth);
			$("#monthPicker2").val(fnGetToMon());
			
			
			//AUIGrid.setGridData(myGridID, gridData);
			//fn_search();
			
		});
		
		var columnLayout = [{
				dataField : "cstSeq",
				headerText : "애음자 시퀀스",
				visible: false,
				editable : false
			},{
				dataField : "bhfcSeq",
				headerText : "지점 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "agenSeq",
				headerText : "대리점 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "prmtEmplSeq",
				headerText : "판촉사원 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "cstPrdSeq",
				headerText : "애음자 제품 시퀀스",
				visible : false,
				editable : false
			},{
				dataField : "bhfcNm",
				headerText : "지점",
				width : "7%",
			},{
				dataField : "agenCd",
				headerText : "대리점코드",
				width : "7%",
	        },{
				dataField : "agenNm",
				headerText : "대리점명",
				width : "7%",
	        },{
				dataField : "cstAgenCd",
				headerText : "애음자코드",
				width : "7%",
	        },{
				dataField : "prmtDt",
				headerText : "가입일",
	        },{
				dataField : "prmtEmplCd",
				headerText : "판촉사원코드",
	            width : "7%",
	        },{
				dataField : "emplNm",
				headerText : "판촉사원명",
	            width : "7%",
	        },{
				dataField : "cntrMonth",
				headerText : "계약기간(월)",
				width : "5%",
			},{
				dataField : "prdNm",
				headerText : "계약 제품",
				width : "10%",
			},{
				dataField : "sumQty",
				headerText : "계약 총수량",
	            width : "7%",            
			},{
				dataField : "todayPd",
				headerText : "현재 제품",
				width : "7%",
	        },{
				dataField : "todayinptQt",
				headerText : "현재 총수량",
				width : "7%"
	        },{
				dataField : "todaycntrSt",
				headerText : "현재 계약상태",
				width : "7%"
	        },{
				dataField : "todaydelSt",
				headerText : "현재 투입상태",
				width : "10%"
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
				editable : false,
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
			
			if (Common.isEmpty($("#monthPicker").val()))
			{
				alert("가입월을 선택 해주세요.");
				$("#monthPicker").focus();
				return;
			}

			if (Common.isEmpty($("#monthPicker2").val()))
			{
				alert("가입월을 선택 해주세요.");
				$("#monthPicker2").focus();
				return;
			}
			
			// sMonth = sMonth.substring(0, 7);
			var sMons = $("#monthPicker").val().split("-");
			var eMons = $("#monthPicker2").val().split("-");
			var diff = commDate.monthDiff(new Date(sMons[0], sMons[1]), new Date(eMons[0], eMons[1]));
			
			if ( diff > 11 )
			{
				alert("최대 12개월 차이 내에 검색 가능 합니다.");
				$("#monthPicker2").focus();
				return;
			}
			
			$.ajax({
				url : "/prmt/cstDkpsSearchList.do", 
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
		
		function fn_sampleDown() {
			var ui = "UI-APRO-0201P1.xlsx";
			
			var frm = document.frm2;
			frm.action = "/file/" + ui + "/downloadFile.do";
			frm.submit();
		}

		// 엑셀 내보내기(Export);
		function exportTo() {
			
			// 내보내기 실행	
			AUIGrid.exportToXlsx(myGridID);
		}
		
	</script>