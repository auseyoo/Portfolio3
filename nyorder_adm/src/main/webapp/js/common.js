var pageSize = 20;
var gridObj = [];

/**
 * 
 */
$(function() {
	
	$.ajaxSetup({
		//dataType    : "json",
       	contentType: "application/x-www-form-urlencoded; charset=UTF-8"
	});
	
    $(document).ajaxSend(function (e, xhr, options) {
		
		//var token = $("meta[name='_csrf']").attr("content");
		//var header = $("meta[name='_csrf_header']").attr("content");
		//console.log("header==" + header + ", token == " + token);
    	//xhr.setRequestHeader(header, token);
    });
    
    
	/* 팝업 닫기 버튼 이벤트 바인드*/
/*	$('.modalCloseBtn').on('click', function(){
		var target = $(this).closest(".modal_wrap").data('popup');
		closePop(target);
	});*/
});



	function gSetGridData(paramObj) {
		
		let gridId = "";		
		
		if(gridObj.length > 0){
			$.each(gridObj, function(index, item){
				
				console.log("gSetGridData  item.gridId==" + item.gridId + ",   ::" + paramObj.gridId);
				if(item.gridId == paramObj.gridId){
					paramObj.data.pageNo = 1;
					paramObj.noRequest = false;
					gridObj[index] = paramObj;	
					gridId = item.gridId; 						
				}			
			});	
			if(gridId == ""){
				paramObj.data.pageNo = 1;
				gridObj[gridObj.length] = paramObj;
				console.log("gSetGridData  .paramObj==" + JSON.stringify(paramObj));
				
			}
		}else{
			paramObj.data.pageNo = 1;
			gridObj[gridObj.length] = paramObj;
			console.log("gSetGridData  11111.paramObj==" + JSON.stringify(paramObj));
			
		}
		gGridAjax(paramObj);
	}
	
	function gVScollChangeHandelr(event) {
		var paramObj;

		//console.log("event.event==" + JSON.stringify(event));
		
		// 스크롤 위치가 마지막과 일치한다면 추가 데이터 요청함
		if(event.position == event.maxPosition) {
			
			$.each(gridObj, function(index, item){
				
				//console.log("gVScollChangeHandelr  .item==" + JSON.stringify(item));	
				
				if(item.gridId == event.pid){
					paramObj = item;				
				}			
			});
			
			//console.log("gVScollChangeHandelr  .paramObj==" + JSON.stringify(paramObj));	
			
			if(paramObj.noRequest == true){
				
				return;
			}
			
			paramObj.noRequest = true;
			paramObj.data.pageNo = paramObj.data.pageNo+1;
			
			gGridAjax(paramObj);
		}
	}
	

	function gGridAjax(paramObj){
		
		console.log("gGridAjax  paramObj==" + JSON.stringify(paramObj));
				
		$.ajax({
	        cache : false,
	        url : paramObj.url, 
	        type : 'POST', 
	        data : paramObj.data, 
	        success : function(data) {
				//console.log("gGridAjax  data==" + JSON.stringify(data));
				
	            if(paramObj.data.pageNo > 1){
	            	AUIGrid.appendData(paramObj.gridId, data);
	
	            	paramObj.noRequest = false;
	            }else{
	            	AUIGrid.setGridData(paramObj.gridId, data);
	            }
	
	            if(data.length < pageSize){
	            	paramObj.noRequest = true;
	            }
	        	
	        }, // success 
	        error : function(xhr, status) {
	        	console.log("xhr==" + JSON.stringify(xhr));
	            alert(xhr + " : " + status);
	        }
	    });		
		
	}



/*
 * 숫자 콤마 (1,000)
 */
function setComma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

/*
 * 숫자 콤마 제거 (1000)
 */
function uncomma(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, '');
}

function numberRenderer(e){
	console.log(e);
}

/*
 * 기본 Null 체크 
 */
function fnIsEmpty(str){
	if(typeof str == "undefined" || str == null || str == "")
    	return true;
	else
		return false ;
}

function fnGetGridListCRUD(gridId){
	var list = new Array();
	// 추가된 행 아이템들(배열)
	$.each(AUIGrid.getAddedRowItems(gridId), function(k,v){
		v.crudMode = "C";
		list.push(v);
	});
	$.each(AUIGrid.getEditedRowItems(gridId), function(k,v){
		v.crudMode = "U";
		list.push(v);
	});
	$.each(AUIGrid.getRemovedItems(gridId), function(k,v){
		v.crudMode = "D";
		list.push(v);
	});
	return list;
}

function fnCommCode(commGrpCd){
	var frm = $('<form></form>');
	
	frm.append("commGrpCd",  "<input type='hidden' name='commGrpCd' value='"+commGrpCd+ "'>");
	
	$.ajax({
        url : "/commselectCommCodeList.do", 
        type : 'POST', 
        data : frm.serialize(),
        success : function(data) {
        	console.log(data)
        }, // success 
        error : function(xhr, status) {
            alert(xhr + " : " + status);
        }
    }); 	
}

//다음 우편번호 팝업
function fnSetPostcode(zipCd, addr1) {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            
            console.log("data  :: " + JSON.stringify(data));

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementById('zipCd').value = data.zonecode;
            //document.getElementById("addr1").value = roadAddr;
            //document.getElementById("addr2").value = data.jibunAddress;

			$(zipCd).val(data.zonecode);
			$(addr1).val(roadAddr);
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                //document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                //document.getElementById("sample4_extraAddress").value = '';
            }

            /**
            //var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.            
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
            */
        }
    }).open();
}

/**
 * 오늘 날짜 (YYYY-MM-DD)
 */
function fnGetToDay(){
	var today = new Date();
	var year = today.getFullYear();
	var month = (today.getMonth()+1) < 10? '0'+(today.getMonth()+1) : (today.getMonth()+1);
	var date = (today.getDate()) < 10? '0'+today.getDate() : today.getDate() ;
	today = year+"-"+month+"-"+date;
	return today;
}
/**
 * 오늘 날짜 (YYYY-MM-DD)
 */
function fnGetToDayKor(){
	var today = new Date();
	var year = today.getFullYear();
	var month = (today.getMonth()+1) < 10? '0'+(today.getMonth()+1) : (today.getMonth()+1);
	var date = (today.getDate()) < 10? '0'+today.getDate() : today.getDate() ;
	today = year+"년 "+month+"월 "+date+"일";
	return today;
}

/**
 * 입력된 Date Type 을 (YYYY-MM-DD)
 */
function fnConvertDate(v){
	var year = v.getFullYear();
	var month = (v.getMonth()+1) < 10? '0'+(v.getMonth()+1) : (v.getMonth()+1);
	var date = (v.getDate()) < 10? '0'+v.getDate() : v.getDate() ;
	var day = year+"-"+month+"-"+date;
	return day;
}

/**
 * 오늘 날짜 (YYYY-MM)
 */
function fnGetToMon(){
	var today = new Date();
	var year = today.getFullYear();
	var month = (today.getMonth()+1) < 10? '0'+(today.getMonth()+1) : (today.getMonth()+1);
	today = year+"-"+month;
	return today;
}

/**
 * 입력받은 yyyyMMdd 형식의 string 날짜 형태를 Date 로 변환
 */
function strToDate(date_str)
{
	date_str = date_str.replace("-", "").replace("-", "");
	var yyyyMMdd = String(date_str);
	var sYear = yyyyMMdd.substring(0,4);
	var sMonth = yyyyMMdd.substring(4,6);
	var sDate = yyyyMMdd.substring(6,8);
	
	//alert("sYear :"+sYear +"   sMonth :"+sMonth + "   sDate :"+sDate);
	return new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
}

function layerAlert(popupParam){
	let popupObj = popupParam.data;
	$("#alertTitle").text(popupObj.title);
	$("#alertMessage").text(popupObj.message);
	
	if(popupObj.showBtn1 != 'N'){
		$("#alertBtn1").show();
		if(fnIsEmpty(popupObj.btn1Label)){
			$("#alertBtn1").text("확인");
		}else{			
			$("#alertBtn1").text(popupObj.btn1Label);	
		}
		$("#alertBtn1").off("click");
		if(!fnIsEmpty(popupObj.btn1Func)){
			$("#alertBtn1").removeClass("modalCloseBtn");
			$("#alertBtn1").on("click", function(){			
				popupObj.btn1Func();
			});
		}else{
			$("#alertBtn1").off("click");
			$("#alertBtn1").addClass("modalCloseBtn");
		}
	}else{
		$("#alertBtn1").hide();
	}
	
	if(popupObj.showBtn2 != 'N'){
		$("#alertBtn2").show();
		if(fnIsEmpty(popupObj.btn2Label)){
			$("#alertBtn2").text("취소");
		}else{			
			$("#alertBtn2").text(popupObj.btn2Label);	
		}
		$("#alertBtn2").off("click");
		if(!fnIsEmpty(popupObj.btn2Func)){
			$("#alertBtn2").removeClass("modalCloseBtn");
			$("#alertBtn2").on("click", function(){			
				popupObj.btn2Func();
			});
		}else{
			$("#alertBtn2").addClass("modalCloseBtn");
		}
	}else{
		$("#alertBtn2").hide();
	}
	var modalPop = $('[data-popup="modalAlert"]');
    var modalW = $(modalPop).outerWidth();
    var modalH = $(modalPop).outerHeight();
	$('[data-popup="modalAlert"]').css({top: '50%', left: '50%', marginTop: - modalH / 2, marginLeft: - modalW / 2});
	$('[data-popup="modalAlert"]').fadeIn(150);
	$('[data-popup="modalAlert"]').parent().find(".modal_bg").fadeIn(100);
	
	$('.modalCloseBtn').on('click', function(){
		var target = $(this).closest(".modal_wrap").data('popup');
		$('[data-popup="'+target+'"]').fadeOut(100);
		$(this).closest(".modal_wrap").parent().find(".modal_bg").fadeOut(100);
	});
}

/* 팝업 오픈 이벤트 */
function openPopup(id){
	var modalW = $('[data-popup="'+id+'"]').outerWidth();
	var modalH = $('[data-popup="'+id+'"]').outerHeight();
	var targetBg = $('[data-popup="'+id+'"]').data("targetBg");
	$('[data-popup="'+id+'"]').css({top: '50%', left: '50%', marginTop: - modalH / 2, marginLeft: - modalW / 2});
	$('[data-popup="'+id+'"]').fadeIn(100);
	$('[data-popup="'+id+'"]').parent().find(".modal_bg").fadeIn(100);
	if(typeof resizeGrid == 'function' ){
		resizeGrid();
	}
	$('.modalCloseBtn').on('click', function(){
		closePopup(id);
	});
}

/* 팝업 닫기 이벤트 */
function closePopup(id){
	$('[data-popup="'+id+'"]').fadeOut(100);
	$('[data-popup="'+id+'"]').parent().find(".modal_bg").fadeOut(100);
}

function getCdtlInfo(targetFunc){
	
    $.ajax({
        cache : false,
        url : "/comm/getCdtlInfo.do", 
        type : 'POST', 
        data : {}, 
		async: false,
        success : function(data) {
	
			console.log("getCdtlInfo data==" + JSON.stringify(data));
			
			targetFunc(data);			
                    	
        }, // success 
        error : function(xhr, status) {
        	console.log("getCdtlInfo error xhr==" + JSON.stringify(xhr));
            //alert(xhr + " : " + status);
        }
    });	
}


// ========================================== Common util ==========================================>
var Common = new Object();

	/**
	 * <pre>
	 * @Desc        :  숫자 0 및 배열, 객체의 빈값도 전부 null 체크
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : common.isEmpty
	 * @return
	 */
	Common.isEmpty = function(obj) {
		if( obj === "" || obj === null || obj == undefined || obj == "null"
				|| (obj !== null && typeof obj === "object" && !Object.keys(obj).length) 
				)
		{
			return true;
		}
		else
		{
			return false ;
		}
	};
	
	/**
	 * <pre>
	 * @Desc        :  Not null 체크
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : common.isNotEmpty
	 * @return
	 */
	Common.isNotEmpty = function(obj) {
		return !Common.isEmpty(obj);
	};
	
	/**
	 * JSON 체크
	 * @param str	
	 * @auther : 김경진
	 * 작성일자 : 2020.07.08
	 */
	Common.IsJsonString = function(str) {
		try {
			var json = JSON.parse(str);
			return (typeof json === 'object');
		} catch (e) {
			return false;
		}
	};
	
	/**
	 * 함수 설명 : 숫자만 입력
	 * 작성자 : 김경진
	 * 작성일자 : 2022.03.08
	 * @param obj = input obj
	 */
	Common.cmmVldNumChk = function(obj) {
		obj.value = obj.value.replace(/[^0-9]/g,'');
	};
	
	/**
	 * 작성자 : 김경진
	 * 설명 : Input textbox 입력한 글자수가 maxLength 와 같으면 다음 Input textbox 로 포커스 이동 
	 * @param obj
	 * @param next_id
	 * @return
	 */
	Common.cmmVldFocusMove = function(obj, next_id) {
		if(obj.value.length>=obj.maxLength)
			document.getElementById(next_id).focus();
	};
// ========================================== common util ==========================================//

// ========================================== Common date ==========================================>
var commDate	= new Object();

	/** 
	 * 특정 날짜에 대해 지정한 값만큼 가감(+-)한 날짜를 반환
	 * 
	 * 입력 파라미터 -----
	 * pInterval : "y" 는 연도 가감, "m" 은 월 가감, "d" 는 일 가감
	 * pAddVal  : 가감 하고자 하는 값 (정수형)
	 * pYyyymmdd : 가감의 기준이 되는 날짜
	 * pDelimiter : pYyyymmdd 값에 사용된 구분자를 설정 (없으면 "" 입력)
	 * 
	 * 반환값 ----
	 * yyyymmdd 또는 함수 입력시 지정된 구분자를 가지는 yyyy?mm?dd 값
	 *
	 * 사용예 ---
	 * 2008-01-01 에 3 일 더하기 ==> addDate("d", 3, "2008-08-01", "-");
	 * 20080301 에 8 개월 더하기 ==> addDate("m", 8, "20080301", "");
	 */
	commDate.addDate = function(pInterval, pAddVal, pYyyymmdd, pDelimiter) {
		var yyyy;
		var mm;
		var dd;
		var cDate;
		var oDate;
		var cYear, cMonth, cDay;
		
		if (pDelimiter != "") {
			pYyyymmdd = pYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
		}
		
		yyyy = pYyyymmdd.substr(0, 4);
		mm  = pYyyymmdd.substr(4, 2);
		dd  = pYyyymmdd.substr(6, 2);
		
		if (pInterval == "y") {
			yyyy = (yyyy * 1) + (pAddVal * 1); 
		} else if (pInterval == "m") {
			mm  = (mm * 1) + (pAddVal * 1);
		} else if (pInterval == "d") {
			dd  = (dd * 1) + (pAddVal * 1);
		}
		
		cDate = new Date(yyyy, mm - 1, dd) // 12월, 31일을 초과하는 입력값에 대해 자동으로 계산된 날짜가 만들어짐.
		cYear = cDate.getFullYear();
		cMonth = cDate.getMonth() + 1;
		cDay = cDate.getDate();
		
		cMonth = cMonth < 10 ? "0" + cMonth : cMonth;
		cDay = cDay < 10 ? "0" + cDay : cDay;
		
		if (pDelimiter != "") {
			return cYear + pDelimiter + cMonth + pDelimiter + cDay;
		} else {
			return cYear + cMonth + cDay;
		}
	};
	
	/**
	 * @description : 차이의 개월 수 계산
	 * @param dateFrom		Date
	 * @param dateTo		Date
	 * @returns	차이수
	 */
	commDate.monthDiff = function(dateFrom, dateTo) {
		return dateTo.getMonth() - dateFrom.getMonth() +
		(12 * (dateTo.getFullYear() - dateFrom.getFullYear()))
	};
	
	
// ========================================== Common date ==========================================//