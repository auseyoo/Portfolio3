<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	var myGridID;
	var popupParam = [];
$(document).ready(function(){
	// AUIGrid 생성 후 반환 ID
	createAUIGrid();
	searchList();

	//검색어 조회 버튼 클릭
	$("#noticeSearchBtn").click(function(){
		searchList();
 	});

	$("#regBtn").click(function(){
		$("#frm")[0].reset();
		$("#nttSub").val("");
		$("#nttAtclSeq").val("");
		$("#nttSub").keyup();
		$("#nttCntt").val("");
		$("#nttCntt").keyup();
		$("#nttSeq").val("");
		$(".appPush").show();
	});
 	
	//수정 버튼 클릭
	$("#updBtn").click(function(){
		var updateItem = AUIGrid.getSelectedItems(myGridID);
		if(!updateItem.length == 0){
			$(".appPush").hide();
			$("#frm")[0].reset();
			$("#nttSeq").val(updateItem[0].item.nttSeq);
			$("#nttAtclSeq").val(updateItem[0].item.nttAtclSeq);
			$("#nttSub").val(updateItem[0].item.nttSub);
			$("#nttSub").keyup();
			$("#nttCntt").val(updateItem[0].item.nttCntt);
			$("#nttCntt").keyup();
			if(updateItem[0].item.notiYn == 'N'){
				$("#notiN").prop("checked", true);
				}
			if(updateItem[0].item.notiYn == 'Y'){
				$("#notiY").prop("checked", true);
			}	
			if(updateItem[0].item.appPushYn == 'Y'){
				$("input:checkbox[id='appSendCheckbox']").prop("checked", true);
			}
			
			$.ajax({
	 			url : "/file/selectAtclList.do",
	 			type : 'POST',
	 			contentType : "application/json; charset=utf-8",
	 			data : JSON.stringify(updateItem[0].item),
	 			success : function(data) {
					$.each(data,function(i,k){
						$("#file"+(i+1)+"Nm").val(k.fileOriNm);
						$("#nttAtclDtlSeq"+(i+1)).val(k.nttAtclDtlSeq);
					});
	 			},
	 			error : function(xhr, status) {
	 				popupParam.data = {
 						title : "공지사항 관리",
 						message : "'수정하는 도중 오류가 발생하였습니다'",
 						showBtn2 : 'N'
	 				}
	 				layerAlert(popupParam);
	 			}
	 		});
			
		}else{
			popupParam.data = {
					title : "공지사항 관리",
					message : "선택된 글이 없습니다.",
					showBtn2 : 'N'
			}
			layerAlert(popupParam);
			document.getElementById('modalCloseBtn').click(); 
			return ;
		}	
	});
	//삭제 버튼 클릭
	$("#delBtn").click(function(){
		if(!AUIGrid.getSelectedItems(myGridID).length > 0){
			popupParam.data = {
				title : "공지사항 관리",
				message : "선택된 글이 없습니다.",
				showBtn2 : 'N'
			}
			layerAlert(popupParam);
			return;
		}
		popupParam.data = {
			title : "공지사항 관리",
			message : "<spring:message code='alert.notibod01'/>",
			btn1Func : updateNotiYn,
		}
		layerAlert(popupParam);
	});
	
	// 파일 업로드 삭제 버튼
	$("button[name='rmvFileBtn']").click(function(){
		if(confirm("첨부파일을 정말 삭제하시겠습니까?\n삭제후 복구할 수 없습니다.")){
			var target = $(this).data("targetIdx");
			
			var agent = navigator.userAgent.toLowerCase();
			if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) { 
				$("#file"+target).replaceWith( $("#"+target).clone(true) );
			}else{
				$("#file"+target).val("");
			}
			
			var param = {};
			param.nttAtclDtlSeq = $("#nttAtclDtlSeq"+target).val();
			$.ajax({
				url : "/file/rmvAtclDtl.do",
				type : 'POST',
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				success : function(data) {
					$("#file"+target+"Nm").val("");
				},
				error : function(xhr, status) {
					alert('수정하는 도중 오류가 발생하였습니다');
				}
			});
		}
	});
	
	//저장 버튼 클릭
	$("#saveBtn").click(function(){
		if($("#notiYn").val() == '' || $("#nttSub").val() == '' || $("#nttCntt").val() == ''){
			popupParam.data = {
				title : "공지사항 관리",
				message : "필수값이 입력되지 않았습니다.",
				showBtn2 : 'N'
			}
			layerAlert(popupParam);
			return;
		}
		saveNotibod();		
	});
	
	//글자수 count - 내용
	$("#nttCntt").keyup(function(e) {
		var nttCntt = $(this).val();
		$("#searchKeyword").html("(" + nttCntt.length + "/ 400)"); //실시간 글자수 카운팅
		if (nttCntt.length > 400) {
			alert("최대 400자까지 입력 가능합니다.");
			$(this).val(nttCntt.substring(0, 400));
			$('#searchKeyword').html("(400 / 최대 400자)");
		} 
	});
	$("#nttCntt").keyup();

	//글자수 count - 제목
	$("#nttSub").keyup(function(e) {
		 var nttSub = $(this).val();
		$("#searchKeyword2").html("(" + nttSub.length + "/ 50)"); //실시간 글자수 카운팅
		if (nttSub.length > 50) {
			alert("최대 50자까지 입력 가능합니다.");
			$(this).val(nttSub.substring(0, 50));
			$('#searchKeyword2').html("(50 / 최대 50자)");
		} 
	});
	$("#nttSub").keyup();
	
	//파일업로드 형식 제한
	$('input[type="file"]').change(function (e) {
		if (this.files[0].size > 10485760) {
			alert("10MB를 초과하는 파일은 등록할 수 없습니다.\n다시 확인해 주세요.");
			this.value = '';
			return;
		}			 
		var ext = this.value.match(/\.(.+)$/)[1];
		switch (ext) {
			case 'hwp' :
			case 'doc' :
			case 'docx' :
			case 'ppt' :
			case 'pptx' :
			case 'xls' :
			case 'xlsx' :
			case 'txt' :
			case 'jpg' :
			case 'jpeg' :
			case 'png' :
			case 'gif' :
			case 'bmp' :
			case 'jpeg' :
			case 'png' :
			case 'zip' :
				$('#saveBtn').attr('disabled', false);
				break;
			default:
				alert('형식(확장자)에 맞지 않은 파일은 등록할 수 없습니다.\n다시 확인해 주세요.');
				this.value = '';
				return;
		}
		var target = $(this).data("targetIdx");
		$("#file"+target+"Nm").val(this.files[0].name);
	});
});
// AUIGrid 를 생성합니다.
function createAUIGrid() {
	// 그리드 속성 설정
	var gridPros = {
			headerHeight : 29,
			rowHeight : 29,
			// 편집 가능 여부 (기본값 : false)
			editable : false,
			// 셀 선택모드 (기본값: singleCell)
			selectionMode : "singleCell",
			showRowNumColumn : false,
			showFooter : false,
			noDataMessage : "출력할 데이터가 없습니다.",
			groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다.",
	};

	// 실제로 #grid_wrap 에 그리드 생성
	myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);

	AUIGrid.bind(myGridID, "cellClick", function( event ) {
		$("#contentArea").html(event.item.nttCntt);
		$("#fileArea").html("");

		$.ajax({
 			url : "/file/selectAtclList.do",
 			type : 'POST',
 			contentType : "application/json; charset=utf-8",
 			data : JSON.stringify(event.item),
 			success : function(data) {
				// attachTmpl
				$("#fileArea").html($("#attachTmpl").tmpl(data));
				
 			}, // success 
 			error : function(xhr, status) {
 				popupParam.data = {
						title : "공지사항 관리",
						message : "수정하는 도중 오류가 발생하였습니다",
						showBtn2 : 'N'
 				}
 				layerAlert(popupParam);
 			}
 		});
	});
  
}

var columnLayout = [
	{
		dataField : "nttOrdr",
		headerText : "순서",
		width: "12%",
	},{
		dataField : "nttSub",
		headerText : "제목",
		style: "auiLeft",
	},{
		dataField : "regDtm",
		headerText : "날짜",
		width : "30%",
		dataType : "date",
		formatString : "yyyy-mm-dd"
	}
];
 //게시판 그리드 리스트 조회
function searchList() {
	$.ajax({
		url : "/cmmc/selectNotiList.do",
		type : 'POST',
		data : $("#searchForm").serialize(),
		success : function(data) {
			AUIGrid.setGridData(myGridID, data);
			$("#totCnt").html(data.length);
			
		}, // success 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	});
}
//등록 - 새로운 공지사항 글 저장
function saveNotibod(){
	var formData = new FormData($("#frm")[0]);
	$.ajax({
		url : "/cmmc/saveNotibod.do",
		type : 'POST',
		data : formData,
		processData:false, 
		contentType:false,
		dataType:'json',
		enctype : 'multipart/form-data',
		success : function(data) {
			if (Common.isNotEmpty(data.res) && data.res && data.updateCnt > 0){
				popupParam.data = {
					title : "공지사항 관리",
					message : "저장 되었습니다.",
					showBtn2 : 'N'
 				}
 				layerAlert(popupParam);
			}else{
				alert("저장 실패 하였습니다.\n다시 시도해 주세요.");
			}
		}, // success 
		error : function(xhr, status, errorThrown) {
			if ( Common.IsJsonString(xhr.responseText) )
			{
				var message = JSON.parse(xhr.responseText).message;
				popupParam.data = {
					title : "공지사항 관리",
					message : message,
					showBtn2 : 'N'
 				}
 				layerAlert(popupParam);
			}
			else{
				alert("처리중 오류가 발생 하였습니다.");
			}
		}
	});
}
//글 삭제
function updateNotiYn(){
	var selectedItem = AUIGrid.getSelectedItems(myGridID);
	$.ajax({
			url : "/cmmc/updateNotiYn.do",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(selectedItem[0].item),
		success : function(data) {
			popupParam.data = {
				title : "공지사항 관리",
				message : "삭제 되었습니다.",
				showBtn2 : 'N'
			}
			layerAlert(popupParam);
			searchList();
		}, // success 
	 	error : function(xhr, status) {
	 		alert('삭제하는 도중 오류가 발생하였습니다');
	 	}
 	});
}

function resizeGrid() {
	AUIGrid.resize(myGridID, $("#content").width());
}

function download(nttAtclDtlSeq) {
	var param = {};
	param.nttAtclDtlSeq = nttAtclDtlSeq;
	$.ajax({
		url : "/file/downloadFile.do",
		type : 'POST',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(param),
		success : function(data) {
			
		},  
		error : function(xhr, status) {
			alert('수정하는 도중 오류가 발생하였습니다');
		}
	});
}


</script>

<div class="content">
	<!-- 조회 -->
	<form id="searchForm">
		<div class="inquiryBox">
			<dl>
				<dt>검색어</dt>
				<dd>
					<div class="formWrap">
						<select id="srcType" class="sel mr10" name="srcType">
							<option value="">전체</option>
							<option value="all">제목+내용</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
						<input type="text" name="srcKeyword" id="srcKeyword" class="inp w160 mr10" value="" title=""></input>
						<button type="button" name="" class="comBtn" id="noticeSearchBtn">조회</button>
					</div>
				</dd>
			</dl>
			<div class="btnGroup right">
				<button type="button" name="" class="inquBtn" id="regBtn" onclick="openPopup('modalAgencyViews')">등록</button>
			</div>
		</div>
	</form>
	<!-- 조회 -->
	
	<div class="girdBoxGroup type02">
		<div class="girdBox w25per">
			<div class="titleArea">
				<p class="numState">
					<em>총 <span class="pColor01 fb" id="totCnt"></span></em> 건 이 조회되었습니다.
				</p>
			</div>

			<!-- grid -->
			<div class="girdBox">
				<div id="grid_wrap"></div>
			</div>
			<!--// grid -->
		</div>

		<!-- 내용 -->
		<div class="conBox w74per">
			<div class="titleArea right">
				<h3 class="tit01">내용</h3>
				<div class="btnGroup">
					<button type="button" name="" class="comBtn small" id="updBtn" onclick="openPopup('modalAgencyViews')">수정</button>
					<button type="button" name="" class="comBtn small" id="delBtn">삭제</button>
				</div>
			</div>

			<div class="fileInfo">
				<textarea name="contentArea" id="contentArea" class="h500" readonly="readonly"></textarea>
				
				<div id="fileArea">
				
				</div>
			</div>
		</div>
		<!--// 내용 -->
	</div>
</div>

<div class="modal_bg"></div>
<!-- modal 배경 -->

<!-- 공지사항 등록 -->
<div class="popWrap small modal_wrap h600" data-popup="modalAgencyViews" style="left:0; right:0; margin:150px auto;">
	<header>
		<h3>공지사항 등록</h3>
		<button type="button" name="" class="closeBtn modalCloseBtn" id="modalCloseBtn"></button>
	</header>

	<div class="popCon">
		<div class="popTitArea">
			<h3>공지사항 등록</h3>

			<div class="popBtnArea">
				<button type="button" name="" class="inquBtn" id="saveBtn">저장</button>
				<button type="button" name="" class="comBtn modalCloseBtn" id="modalCloseBtn">닫기</button>
			</div>
		</div>
		
		<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="nttSeq" 		id="nttSeq"/>
			<input type="hidden" name="nttAtclSeq"	id="nttAtclSeq"/>
			<input type="hidden" id="nttAtclDtlSeq1"/>
			<input type="hidden" id="nttAtclDtlSeq2"/>
			<input type="hidden" id="nttAtclDtlSeq3"/>
			<div class="receptCate">
				<div class="formWrap type02 right">
					<p class="tit">구분*</p>
	
					<div class="checkGroup">
						<input type="radio" name="notiYn" id="notiY" class="type01" value="Y" checked="checked"><label for="notiY"><span>공지</span></label>
						<input type="radio" name="notiYn" id="notiN" class="type01" value="N"><label for="notiN"><span>일반</span></label>
					</div>
	
					<div class="checkGroup ml20 appPush">
						<span class="mr10">앱 PUSH</span>
						<input type="checkbox" id="appSendCheckbox" name="appSendCheckbox" class="type01">
						<label for="appSendCheckbox"><span>발송</span></label>
						<input type="hidden" name="appPushYn" id="appPushYn" value="N">
					</div>
				</div>
			</div>
	
			<div class="titleArea">
				<h3 class="tit01">제목 <span class="pColor03" id="searchKeyword2"></span></h3>
			</div>
	
			<input type="text" id="nttSub" class="inp w100per mb10" value="" name="nttSub" placeholder="제목을 작성해주세요">
	
			<div class="titleArea">
				<h3 class="tit01">내용 <span class="pColor03" id="searchKeyword"></span></h3>
			</div>

			<div class="fileInfo">
				<textarea name="nttCntt" id="nttCntt" class="h205" placeholder="내용을 작성해주세요"></textarea>
	
				<div class="formWrap type02 file">
					<p class="tit">파일 :</p>
					<p>
						<div class="formWrap filebox">
							<input type="text" id="file1Nm" class="upload" placeholder="첨부파일" readonly="readonly">
							<label for="file1">찾기</label> 
							<input type="file" id="file1" name="files" data-target-idx="1">
	
							<button type="button" id="delBtn1" name="rmvFileBtn" class="delBtn" data-target-idx="2">삭제</button>
						</div>
					</p>
				</div>
				<div class="formWrap type02 file">
					<p class="tit">파일 :</p>
					<p>
						<div class="formWrap filebox">
							<input type="text" id="file2Nm" class="upload" placeholder="첨부파일" readonly="readonly">
							<label for="file2">찾기</label> 
							<input type="file" id="file2" name="files" data-target-idx="2">
							<button type="button" id="delBtn2" name="rmvFileBtn" class="delBtn" data-target-idx="2">삭제</button>
						</div>
					</p>
				</div>
				<div class="formWrap type02 file">
					<p class="tit">파일 :</p>
					<p>
						<div class="formWrap filebox">
							<input type="text" id="file3Nm" class="upload" placeholder="첨부파일" readonly="readonly">
							<label for="file3">찾기</label> 
							<input type="file" id="file3" name="files" data-target-idx="3">
	
							<button type="button" id="delBtn3" name="rmvFileBtn" class="delBtn" data-target-idx="3">삭제</button>
						</div>
					</p>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- 공지사항 등록 -->
<script id="attachTmpl" type="text/x-jquery-tmpl">
<div class="formWrap type02 file">
	<p class="tit">첨부파일 :</p>
	<p>
		<button type="button" class="comBtn small" onclick="download('\${nttAtclDtlSeq}')">다운로드</button>
		<span class="name">\${fileOriNm}</span>
	</p>
</div>
</script>