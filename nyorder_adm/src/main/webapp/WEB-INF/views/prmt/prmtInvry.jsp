<!--
	@File Name: prmtInvry
	@File 설명 : 대리점별 판촉물 재고 현황
	@UI ID : UI-APRO-0701.html
	@작성일 : 2022.03.11
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

        fnSearchList();
        
        //바인딩
        $("#selectBtn").on("click", fnSearchList);
        
	    AUIGrid.setFooter(myGridID, footerLayout);
    });
    
   
    // AUIGrid 를 생성합니다.
    function createAUIGrid(columnLayout) {
        // 그리드 속성 설정
        var gridPros = {
                headerHeight : 29,
                rowHeight : 29,
                // 편집 가능 여부 (기본값 : false)
                editable : false,
                
                // 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
                enterKeyColumnBase : true,
                
                // 셀 선택모드 (기본값: singleCell)
                selectionMode : "singleRow",
                showFooter : true,
                
                // 컨텍스트 메뉴 사용 여부 (기본값 : false)
                useContextMenu : true,
                
                // 필터 사용 여부 (기본값 : false)
                enableFilter : true,
            
                // 그룹핑 패널 사용
                useGroupingPanel : false,

                // row Styling 함수
                rowStyleFunction : function(rowIndex, item) {
                    if(item.name == "남양요쿠르트") {
                        return "dataChange";
                    }
                    return "";
                },                
                
                // 그룹핑 또는 트리로 만들었을 때 펼쳐지게 할지 여부 (기본값 : false)
                displayTreeOpen : true,
                
                noDataMessage : "출력할 데이터가 없습니다.",
                
                groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다."
                
        };
    
        // 실제로 #grid_wrap 에 그리드 생성
        myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
        
    }

    var columnLayout = [{
            dataField : "agenCd",
			headerText : "대리점코드",
			width : "7%",
		},{
			dataField : "agenNm",
			headerText : "대리점명",
            width : "7%",
        },{
			dataField : "prdCd",
			headerText : "제품코드",
			width : "7%",
        },{
			dataField : "prdNm",
			headerText : "제품명",
            style : "auiLeft"
		},{
			dataField : "faltQty",
			headerText : "입수량",
            width : "6%",
            style : "auiRight"
		},{
			dataField : "orderQuan",
			headerText : "전일재고",
                children : [{
                    dataField : "yestdBoxQty",
                    headerText : "BOX",
                    width : "6%",
                    style : "auiRight"
                }, {
                    dataField : "yestdIddyQty",
                    headerText : "낱봉",
                    headerStyle : "auiRightBorR",
                    width : "6%",
                    style : "auiRight",
                }]
		},{
			dataField : "conQuan",
			headerText : "입고",
                children : [{
                    dataField : "wrhsBoxQty",
                    headerText : "BOX",
                    width : "6%",
                    style : "auiRight"
                }, {
                    dataField : "wrhsIddyQty",
                    headerText : "낱봉",
                    headerStyle : "auiRightBorR",
                    width : "6%",
                    style : "auiRight",
                }]
        },{
			dataField : "relQuan",
			headerText : "출고",
                children : [{
                    dataField : "dlvyBoxQty",
                    headerText : "BOX",
                    width : "6%",
                    style : "auiRight"
                }, {
                    dataField : "dlvyIddyQty",
                    headerText : "낱봉",
                    headerStyle : "auiRightBorR",
                    width : "6%",
                    style : "auiRight",
                }]
        },{
			dataField : "int",
			headerText : "재고조정",
                children : [{
                    dataField : "invryChgBoxQty",
                    headerText : "BOX",
                    width : "6%",
                    style : "auiRight"
                }, {
                    dataField : "invryChgIddyQty",
                    headerText : "낱봉",
                    headerStyle : "auiRightBorR",
                    width : "6%",
                    style : "auiRight",
                }]
        },{
			dataField : "intNow",
			headerText : "현재고",
                children : [{
                    dataField : "invryBoxQty",
                    headerText : "BOX",
                    width : "6%",
                    style : "auiRight"
                }, {
                    dataField : "invryIddyQty",
                    headerText : "낱봉",
                    headerStyle : "auiRightBorR",
                    width : "6%",
                    style : "auiRight",
                }]

		}
	];

    // 푸터 설정
    var footerLayout = [{
        labelText : "∑",
        positionField : "#base"
        }, {
            dataField : "code",
            positionField : "code",
            operation : "SUM",
            colSpan : 5, // 자신을 포함하여 4개의 푸터를 가로 병합함.
            labelFunction : function(value, columnValues, footerValues) {
               // return "합계 : " + AUIGrid.formatNumber(value, "#,##0");
               return "합계 : " 
            }
        }, {
            dataField : "yestdBoxQty",
            positionField : "yestdBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "yestdIddyQty",
            positionField : "yestdIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "wrhsBoxQty",
            positionField : "wrhsBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "wrhsIddyQty",
            positionField : "wrhsIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "dlvyBoxQty",
            positionField : "dlvyBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "dlvyIddyQty",
            positionField : "dlvyIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "invryChgBoxQty",
            positionField : "invryChgBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "invryChgIddyQty",
            positionField : "invryChgIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "invryBoxQty",
            positionField : "invryBoxQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }, {
            dataField : "invryIddyQty",
            positionField : "invryIddyQty",
            operation : "SUM",
            style : "auiRight",
            colSpan : 1, 
            labelFunction : function(value, columnValues, footerValues) {
                return " " + AUIGrid.formatNumber(value, "#,##0");
            }
        }];

    function fnSearchList(){
		
 		$.ajax({
			url : "/prmt/selectPrmtInvryList.do", 
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

</script>

<div class="content">
	<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>

	<form id="frm" name="frm" method="POST">
		<!-- 조회 -->
		<div class="inquiryBox">
	        <dl>
	            <dt>주문일</dt>
	            <dd>
	                <div class="formWrap">            
	                    <div class="dateWrap">
	                        <input type="text" name="stdrDt" value="" class="inp datepicker" id="datepicker">
	                        <button type="button" class="datepickerBtn" title="날짜입력"></button>
	                    </div>
	                </div>
	            </dd>
	            <dt>대리점코드</dt>
	            <dd>
	                <input type="text" name="agenCd" id="agenCd" class="inp w160" value="" title="대리점코드 입력">
	            </dd>
	            <dt>판촉물</dt>
	            <dd>
	                <div class="formWrap">
	                    <input type="text" id="srcPrdCd" class="inp w120 mr10" value="" name="srcPrdCd" placeholder="제품코드" maxlength="8">
						<input type="text" id="srcPrdNm" class="inp w160 mr7" value="" name="srcPrdNm" placeholder="제품명">
	                    
	                    <button type="button" name="" class="comBtn" id="selectBtn">조회</button>
	                </div>
	            </dd>
	        </dl>
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

