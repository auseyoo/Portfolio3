<!--
	@File Name: prmtAgenRqstBhfc
	@File 설명 : 판촉물 소요량 요청 관리 (지점)
	@UI ID : UI-APRO-0501.html
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

   		$('#monthPicker').MonthPicker({ 
   		    OnAfterChooseMonth: function() { 
   		        fnSearchRqstMonth();
   		    } 
   		});
   		$("#saleCd").on("change", fnSearchRqstMonth);
   		
   		createAUIGrid(columnLayout);        

   		fnSearchList();

        //바인딩
        $("#selectBtn").on("click", fnSearchList);  
        $("#saveBtn").on("click", fnSavePrmtAgenRqst);
        AUIGrid.bind(myGridID, "cellEditEnd", auiCellEditingHandler);
        
	    AUIGrid.setFooter(myGridID, footerLayout);
    });
    
   
    // AUIGrid 를 생성합니다.
    function createAUIGrid(columnLayout) {
        // 그리드 속성 설정
        var gridPros = {
                headerHeight : 29,
                rowHeight : 29,
                // 편집 가능 여부 (기본값 : false)
                editable : true,
                
                // 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
                enterKeyColumnBase : true,

				showRowNumColumn : true, 
                
                // 셀 선택모드 (기본값: singleCell)
                selectionMode : "singleRow",
                showFooter : true,
                
                // 컨텍스트 메뉴 사용 여부 (기본값 : false)
                useContextMenu : true,
                
                // 필터 사용 여부 (기본값 : false)
                enableFilter : true,
            
                // 그룹핑 패널 사용
                useGroupingPanel : false,
                
                
                // 그룹핑 또는 트리로 만들었을 때 펼쳐지게 할지 여부 (기본값 : false)
                displayTreeOpen : true,
                
                noDataMessage : "출력할 데이터가 없습니다.",
                
                groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다.",

                // row Styling 함수
                rowStyleFunction : function(rowIndex, item) {
                    if(item.diffQtyAh != 0) {
                        return "dataChange";
                    }
                    return "";                    
                }, 
                
        };
    
        // 실제로 #grid_wrap 에 그리드 생성
        myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
        
    }

    var columnLayout = [
    	{
            dataField : "prmtAgenSeq",
			visible:false
		},    	{
            dataField : "bhfcSeq",
			visible:false
		},    	{
            dataField : "puchSeq",
			visible:false
		},    	{
            dataField : "prdSeq",
			visible:false
		},    	{
            dataField : "agenOrdDtm",
			visible:false
		},    	{
            dataField : "bhfcDcsnDtm",
			visible:false
		}, {
            dataField : "hoffDcsnDtm",
			visible:false
		}, {
            dataField : "updPart",
			visible:false
		},		
		{
            dataField : "bhfcNm",
			headerText : "지점",
            width : "5%"
		},{
			dataField : "agenCd",
			headerText : "대리점코드",
            width : "6%"
        },{
			dataField : "agenNm",
			headerText : "대리점명",
            dataType : "numeric",
			width : "6%"		
		},{
			dataField : "prdSapCd",
			headerText : "제품코드",
			width : "6%",
        },{
			dataField : "prdNm",
			headerText : "제품명",
            style: "auiLeft",
		},{
			dataField : "freeYn",
			headerText : "유상<br/>여부",
            width : "5%",
        },{
			dataField : "faltQty",
			headerText : "입수량",
            width : "4%",
            style: "auiRight",
        },{
			dataField : "iddyOrdYn",
			headerText : "낱봉<br/>주문",
            width : "4%",          
        },{
			dataField : "myQuantity",
			headerText : "대리점 입력수량",
                children : [{
                    dataField : "agenBoxQty",
                    headerText : "BOX",                    
                    width : "5%",
                    style: "auiRight",
                }, {
                    dataField : "agenIddyQty",
                    headerText : "낱봉",
                    width : "5%",
                    style: "auiRight",
                }, {
                    dataField : "agenQty",
                    headerText : "총수량",
                    headerStyle : "auiRightBorR",
                    width : "5%",
                    style: "auiRight"                
                }]
        },{
			dataField : "storeQuan",
			headerText : "지점 확정수량",
                children : [{
                    dataField : "bhfcBoxQty",
                    headerText : "BOX",
                    width : "5%",
                    style: "auiRight",
                }, {
                    dataField : "bhfcIddyQty",
                    headerText : "낱봉",
                    width : "5%",
                    style: "auiRight",
				}, {
                    dataField : "bhfcQty",
                    headerText : "총수량",
					headerStyle : "auiRightBorR",
                    width : "5%",
                    style: "auiRight",
                }]
			},{
			dataField : "mainQuan",
			headerText : "본사 확정수량",
                children : [{
                    dataField : "hoffBoxQty",
                    headerText : "BOX",
                    width : "5%",
                    style: "auiRight",
                    editable : true,
                    dataType : "numeric",
                    editRenderer : {
                           type : "InputEditRenderer",
                           onlyNumeric : true, // 0~9 까지만 허용
    				},
                    renderer : {
                        type : "TemplateRenderer"
                    },
                    // dataField 로 정의된 필드 값이 HTML 이라면 labelFunction 으로 처리할 필요 없음.
                    labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
                        var template = '<div class="my_div">';
                        template += '<span class="my_div_text_box right">' + value + '</span>';
                        template += '</div>';
                        return template;
                    }
                }, {
                    dataField : "hoffIddyQty",
                    headerText : "낱봉",
                    width : "5%",
                    style: "auiRight",
                    editable : true,
                    dataType : "numeric",
                    editRenderer : {
                           type : "InputEditRenderer",
                           onlyNumeric : true, // 0~9 까지만 허용
    				},
                    renderer : {
                        type : "TemplateRenderer"
                    },
                    labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
                    	var template = ''
                           	if(item.iddyOrdYn == '불가')
       						{
                                   template = ''
                            }else{							
                            	template = '<div class="my_div">';
                                template += '<span class="my_div_text_box center">' + value + '</span>';
                                template += '</div>';
                            }
                        return template;
                    }
				}, {
                    dataField : "hoffQty",
                    headerText : "총수량",
                    width : "5%",
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
                }]
        },{
            dataField : "diffQtyAh",
			headerText : "차이수량<br/>(대리점-본사)",
			width : "6%",
            style: "auiRight",
		},{
			dataField : "updNm",
			headerText : "최종수정자",
			width : "8%"
        },{
			dataField : "updDtm",
			headerText : "최종수정일시",
			dataType : "date",
			formatString : "yyyy-mm-dd",
			width : "8%",
			labelFunction : function (rowIndex, columnIndex, value, headerText, item ) {
            	var template = ''
            	if(value == null)
				{
                    template = ''
                }else{
                	template = AUIGrid.formatDate(value, "yyyy-mm-dd")
                }
                return template;
            }
		}
	];

    // 푸터 설정
    var footerLayout = [{
        labelText : "∑",
        positionField : "#base"
        }, {
            dataField : "day",
            positionField : "day",
            operation : "SUM",
            colSpan : 8, // 자신을 포함하여 4개의 푸터를 가로 병합함.
            labelFunction : function(value, columnValues, footerValues) {
               // return "합계 : " + AUIGrid.formatNumber(value, "#,##0");
               return "합계 : " 
            }
        }, {
            dataField : "agenBoxQty",
            positionField : "agenBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "agenIddyQty",
            positionField : "agenIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "agenQty",
            positionField : "agenQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "bhfcBoxQty",
            positionField : "bhfcBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "bhfcIddyQty",
            positionField : "bhfcIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "bhfcQty",
            positionField : "bhfcQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "hoffBoxQty",
            positionField : "hoffBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "hoffIddyQty",
            positionField : "hoffIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "hoffQty",
            positionField : "hoffQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }        
        }, {
            dataField : "diffQtyAh",
            positionField : "diffQtyAb",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
         	}
        }];

    function fnSearchList(){
    		
 		$.ajax({
			url : "/prmt/selectPrmtAgenRqstList.do", 
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

	function fnSavePrmtAgenRqst(){

    	/* 날짜 체크 시작 */
		var today = new Date();
	    var year = today.getFullYear();
	    var month = ("0" + (1 + today.getMonth())).slice(-2);	    
	    var today = new Date(year, Number(today.getMonth()), Number(today.getDate()), 0, 0, 0);

	    var selMonth = $("#monthPicker").val().replace('-','');

		if(selMonth != (year+month)){
			alert("저장은 당월만 가능합니다.");
			return;
		}

		// 선택한 연월 체크 - 저장 가능한 연월인지 체크
		if($("#prmtRqstSeq").val() == ''){
			alert("선택하신 요청월은 판촉물 소요량 요청 불가합니다.");
			return;
		}
		
	    var selHoffClsDt = $("#hoffClsDt").val();
	    selHoffClsDt = new Date(selHoffClsDt.substring(0, 4), Number(selHoffClsDt.substring(4, 6)) -1, Number(selHoffClsDt.substring(6, 8)));
		
	 	// 저장/전송 가능한 날짜인지 체크 - 선택한 월의 최대 대리점주문일과 현재 날짜 비교하기
		if(today > selHoffClsDt){
			alert("저장 가능일자가 경과되어 불가합니다.");
			return;
		}
		
		/* 날짜 체크 종료 */
		
		var updPart = $("#updPart").val();
		var saleCd = $("#saleCd").val(); 
		var saveData = new Array();
		
		$.each(AUIGrid.getGridData(myGridID), function(idx,item){
			item.updPart = updPart;
			item.dvyfgRqstMonth = selMonth;
			item.saleCd = saleCd;
			saveData.push(item);	
		});

		$.ajax({
			url : "/prmt/savePrmtAgenRqstHoff.do",
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

	// 수량변경 계산 처리
 	function auiCellEditingHandler(event) {
		var item = event.item;

 		var faltQty =  0;		//입수량
     	var agenBoxQty = 0;		//대리점 주문 box
 		var agenIddyQty = 0;	//대리점 주문 낱봉
 		var agenQty = 0;		//대리점 주문 총수량
 		var bhfcBoxQty = 0;		//지점 확정 box
 		var bhfcIddyQty = 0;	//지점 확정 낱봉
 		var bhfcQty = 0;		//지점 확정 총수량
 		var hoffBoxQty = 0;		//본사 확정 box
 		var hoffIddyQty = 0;	//본사 확정 낱봉
 		var hoffQty = 0;		//본사 확정 총수량
 		var diffQtyAb = 0;		//대리점-지점 차이수량
 		var diffQtyAh = 0;		//대리점-본사 차이수량
 
 		if(event.dataField == "bhfcBoxQty" || event.dataField == "bhfcIddyQty")
 		{
 			agenBoxQty = (Number(item.agenBoxQty)) ? Number(item.agenBoxQty) : 0;		
 			agenIddyQty = (Number(item.agenIddyQty)) ? Number(item.agenIddyQty) : 0;		
 			faltQty = ( Number(item.faltQty) ) ? Number(item.faltQty) : 0;				
 			agenQty = Number( (faltQty * agenBoxQty) + agenIddyQty);

 			bhfcBoxQty = (Number(item.bhfcBoxQty)) ? Number(item.bhfcBoxQty) : 0;		
 			bhfcIddyQty = (Number(item.bhfcIddyQty)) ? Number(item.bhfcIddyQty) : 0;	
 			bhfcQty = Number((faltQty * bhfcBoxQty) + bhfcIddyQty);

 			diffQtyAb = (agenQty - bhfcQty);
 			
 			AUIGrid.updateRow(myGridID, {'bhfcQty': bhfcQty}, event.rowIndex);
 			AUIGrid.updateRow(myGridID, {'diffQtyAb': diffQtyAb}, event.rowIndex);
 		}	
 		else if(event.dataField == "hoffBoxQty" || event.dataField == "hoffIddyQty")
 		{
 			agenBoxQty = (Number(item.agenBoxQty)) ? Number(item.agenBoxQty) : 0;		
 			agenIddyQty = (Number(item.agenIddyQty)) ? Number(item.agenIddyQty) : 0;		
 			faltQty = ( Number(item.faltQty) ) ? Number(item.faltQty) : 0;				
 			agenQty = Number( (faltQty * agenBoxQty) + agenIddyQty);

 			hoffBoxQty = (Number(item.hoffBoxQty)) ? Number(item.hoffBoxQty) : 0;		
 			hoffIddyQty = (Number(item.hoffIddyQty)) ? Number(item.hoffIddyQty) : 0;	
 			hoffQty = Number((faltQty * hoffBoxQty) + hoffIddyQty);

 			diffQtyAh = (agenQty - hoffQty);
 			
 			AUIGrid.updateRow(myGridID, {'hoffQty': hoffQty}, event.rowIndex);
 			AUIGrid.updateRow(myGridID, {'diffQtyAh': diffQtyAh}, event.rowIndex);
 		}
 	}

	function fnSearchRqstMonth(){
 	 	
		$.ajax({
			url : "/prmt/selectDvyfgRqstMonth.do", 
			type : 'POST', 
			data : $("#frm").serialize(),
			success : function(data) {
				if(data.list != null){
					$("#prmtRqstSeq").val(data.list.prmtRqstSeq);
					$("#dvyfgRqstMonth").val(data.list.dvyfgRqstMonth);
					$("#hoffClsDt").val(data.list.hoffClsDt);
				}else{
					$("#prmtRqstSeq").val('');
					$("#dvyfgRqstMonth").val('');
					$("#hoffClsDt").val('');
				}				
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 	
	}
</script>

<div class="content">
	<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>

	<form id="frm" name="frm" method="POST">
		<input type="hidden" name="updPart" id="updPart" value="H">
		<input type="hidden" id="prmtRqstSeq" value="${rqstMonth.prmtRqstSeq}">
		<input type="hidden" id="dvyfgRqstMonth" value="${rqstMonth.dvyfgRqstMonth}">
		<input type="hidden" id="hoffClsDt" value="${rqstMonth.hoffClsDt}">	
		
		<!-- 조회 -->
		<div class="inquiryBox row">
			<div class="dlRowBoxWrap">
				<div class="dlBox">
					<dl>
						<dt>납품요청월</dt>
						<dd>
							<div class="dateWrap">
								<input type="text" name="dvyfgRqstMonth" value="" class="inp monthPicker" id="monthPicker" readonly>
								<button type="button" class="datepickerBtn" title="날짜입력" data-target-id="monthPicker"></button>
							</div>
						</dd>
						<dt>지점</dt>
						<dd>
							<select class="sel wm100" name="bhfcSeq" id="bhfcSeq">
								<c:forEach items="${bhfcList}" var="i" >
									<option value="${i.bhfcSeq}">${i.bhfcNm}</option>
								</c:forEach>
							</select>
						</dd>
						<dt>대리점코드</dt>
						<dd>
							<input type="text" name="agenCd" id="agenCd" class="inp w160" value="">
						</dd>
					</dl>
					<dl>
						<dt>구분</dt>
						<dd>
							<select class="sel wm100" name="saleCd" id="saleCd">
								<c:forEach items="${saleCd}" var="i" >
									<option value="${i.commCd}">${i.commNm}</option>
								</c:forEach>
							</select>
						</dd>
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
				<button type="button" name="" class="comBtn" id="saveBtn">저장</button>
			</div>
		</div>
		<!-- 조회 -->
	</form>

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

