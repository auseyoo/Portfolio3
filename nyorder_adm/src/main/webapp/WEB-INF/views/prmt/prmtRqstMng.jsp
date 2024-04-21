<!--
	@File Name: prmtRqstMng
	@File 설명 : 판촉물 요청 기간 관리
	@UI ID : UI-APRO-0301
	@작성일 : 2022.03.04
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
        $("#saveBtn").on("click", fnSavePrmtRqstMng);
    });
    
   
    // AUIGrid 를 생성합니다.
    function createAUIGrid(columnLayout) {
        // 그리드 속성 설정
        var gridPros = {
                headerHeight : 29,
                rowHeight : 29,
                // 편집 가능 여부 (기본값 : false)
                editable : true,

				showRowCheckColumn : false, // 체크박스 사용 
				wrapSelectionMove : true,
				editingOnKeyDown : true, // 키보드 입력으로 편집 모드 진입 (기본값:true임;)
                
                // 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
                enterKeyColumnBase : true,

				showRowNumColumn : true, 
                
                // 셀 선택모드 (기본값: singleCell)
                selectionMode : "singleRow",
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
                
        };
    
        // 실제로 #grid_wrap 에 그리드 생성
        myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
        
    }

    var columnLayout = [{
            dataField : "dvyfgRqstMonth",
			headerText : "납품요청월",
			width : "25%",
            style: "auiLink"
		},{
			dataField : "saleCd",
			visible:false
		},{
			dataField : "saleTxt",
			headerText : "구분",
            style: "auiLink"     
		},{
			dataField : "prmtRqstSeq",
			visible:false                       
		},{
			dataField : "agenClsDt",
			headerText : "대리점 마감",
			width : "9%",
            dataType : "date",
            dateInputFormat : "yyyymmdd", // 실제 데이터의 형식 지정
            formatString : "yyyy - mm - dd", // 실제 데이터 형식을 어떻게 표시할지 지정
            renderer : {
                type : "IconRenderer",
                iconWidth : 24, 
                iconHeight : 24,
                iconPosition : "aisleRight",
                iconTableRef :  { 
                    "default" : "../images/AUIGrid/calendar-icon.png"
                },
                onClick : function(event) {
                    AUIGrid.openInputer(event.pid);
                }
            },
            editRenderer : {
                type : "CalendarRenderer",
                defaultFormat : "yyyymmdd", // 달력 선택 시 데이터에 적용되는 날짜 형식
                showEditorBtn : false,
                showEditorBtnOver : false, // 마우스 오버 시 에디터버턴 출력 여부
                onlyCalendar : true, // 사용자 입력 불가, 즉 달력으로만 날짜입력 (기본값 : true)
                showExtraDays : true // 지난 달, 다음 달 여분의 날짜(days) 출력
            },
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
		},{
			dataField : "bhfcClsDt",
			headerText : "지점 마감",
			width : "9%",
            dataType : "date",
            dateInputFormat : "yyyymmdd", // 실제 데이터의 형식 지정
            formatString : "yyyy - mm - dd", // 실제 데이터 형식을 어떻게 표시할지 지정
            renderer : {
                type : "IconRenderer",
                iconWidth : 24, 
                iconHeight : 24,
                iconPosition : "aisleRight",
                iconTableRef :  { 
                    "default" : "../images/AUIGrid/calendar-icon.png"
                },
                onClick : function(event) {
                    AUIGrid.openInputer(event.pid);
                }
            },
            editRenderer : {
                type : "CalendarRenderer",
                defaultFormat : "yyyymmdd", // 달력 선택 시 데이터에 적용되는 날짜 형식
                showEditorBtn : false,
                showEditorBtnOver : false, // 마우스 오버 시 에디터버턴 출력 여부
                onlyCalendar : true, // 사용자 입력 불가, 즉 달력으로만 날짜입력 (기본값 : true)
                showExtraDays : true // 지난 달, 다음 달 여분의 날짜(days) 출력
            },
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
		},{
			dataField : "hoffClsDt",
			headerText : "본사 마감",
			width : "9%",
            dataType : "date",
            dateInputFormat : "yyyymmdd", // 실제 데이터의 형식 지정
            formatString : "yyyy - mm - dd", // 실제 데이터 형식을 어떻게 표시할지 지정
            renderer : {
                type : "IconRenderer",
                iconWidth : 24, 
                iconHeight : 24,
                iconPosition : "aisleRight",
                iconTableRef :  { 
                    "default" : "../images/AUIGrid/calendar-icon.png"
                },
                onClick : function(event) {
                    AUIGrid.openInputer(event.pid);
                }
            },
            editRenderer : {
                type : "CalendarRenderer",
                defaultFormat : "yyyymmdd", // 달력 선택 시 데이터에 적용되는 날짜 형식
                showEditorBtn : false,
                showEditorBtnOver : false, // 마우스 오버 시 에디터버턴 출력 여부
                onlyCalendar : true, // 사용자 입력 불가, 즉 달력으로만 날짜입력 (기본값 : true)
                showExtraDays : true // 지난 달, 다음 달 여분의 날짜(days) 출력
            },
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
		},{
			dataField : "dcsnYn",
			headerText : "확정상태 여부",
            width : "15%",		
        }
    ];

    function fnSearchList(){

 		$.ajax({
			url : "/prmt/selectPrmtRqstMngList.do", 
			type : 'POST', 
			data : $("#frm").serialize(),
			success : function(data) {
				AUIGrid.setGridData(myGridID, data);
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 	
	}

    function fnSavePrmtRqstMng(){

    	/* 저장 이벤트 호출시 날짜 체크 시작 */
		var today = new Date();
	    var year = today.getFullYear();
	    var month = ("0" + (1 + today.getMonth())).slice(-2);	    

		if($("#monthPicker").val().replace('-','') < (year+month)){
			alert("이전 연월은 저장 불가합니다.");
			return;
		}
		/* 저장 이벤트 호출시 날짜 체크 종료 */
		
		$.ajax({
			url : "/prmt/savePrmtRqstMng.do",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(AUIGrid.getGridData(myGridID)),
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
</script>

<div class="content">
	<%-- <tiles:insertAttribute name="body.breadcrumb" /> --%>
	
	<form id="frm" name="frm" method="POST" >		
		<!-- 조회 -->
		<div class="inquiryBox">
            <dl>
                <dt>납품요청월</dt>
                <dd>
                    <div class="formWrap">
                        <div class="dateWrap">
							<input type="text" name="dvyfgRqstMonth" value="" class="inp monthPicker" id="monthPicker" readonly>
							<button type="button" class="datepickerBtn" title="날짜입력" data-target-id="monthPicker"></button>
						</div>

                        <button type="button" name="" class="comBtn" id="selectBtn">조회</button>
                    </div>
                </dd>
            </dl>

            <div class="btnGroup right">
                <button type="button" name="" class="inquBtn" id="saveBtn">저장</button>
            </div>
        </div>
		<!-- 조회 -->
	</form>

    <!-- grid -->
    <div class="girdBox">
        <div id="grid_wrap"></div>
	</div>
    <!-- grid -->
    
</div>

	