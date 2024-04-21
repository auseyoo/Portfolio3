<!-- 
	@File Name: rqbod.jsp
	@File 설명 : 요청게시판
	@UI ID : 	UI-ACOM-0301
	@작성일 : 2022. 3. 15.
	@작성자 : YESOL
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	var myGridID;
	
   $(document).ready(function(){
		// AUIGrid 생성 후 반환 ID
		createAUIGrid();
		/*button click event*/
		$("#searchBtn").click(function(){
			selectRqbodList();
		});
		
		//제목 그리드 클릭 이벤트
		AUIGrid.bind(myGridID, "cellClick", function(event) {
			var row = event.item;
			$("#contentArea").html(row.nttCntt);

			$("#fileArea").html("");
			$.ajax({
	 			url : "/file/selectAtclList.do",
	 			type : 'POST',
	 			contentType : "application/json; charset=utf-8",
	 			data : JSON.stringify(row),
	 			success : function(data) {
					$("#fileArea").html($("#attachTmpl").tmpl(data));
	 			}, // success 
	 			error : function(xhr, status) {
	 				alert('수정하는 도중 오류가 발생하였습니다');
	 			}
	 		});
	 		
			//확인 업데이트
			if(row.cfmYn=='N'){
				updateCfmYn(row);
			}
			//다시 조회
			selectRqbodList();
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
				enterKeyColumnBase : true, // 엔터키가 다음 행이 아닌 다음 칼럼으로 이동할지 여부 (기본값 : false)
				fixedColumnCount : 7,
				editingOnKeyDown : true, // 키보드 입력으로 편집 모드 진입 (기본값:true임;)
				wrapSelectionMove : true,
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
				groupingMessage : "여기에 칼럼을 드래그하면 그룹핑이 됩니다.",
				// 데이터 요청, 요청 성공 시 AUIGrid 에 데이터 삽입합니다.
		};
		// 실제로 #grid_wrap 에 그리드 생성
		myGridID = AUIGrid.create("#grid_wrap", columnLayout, gridPros);
	  
	}

	// 그리드 속성 설정
	var auiGridProps = {
			//scrollHeight : 18, // 스크롤의 높이
			//scrollThumbHeight : 16, // 스크롤 썸(thumb)의 높이
			fixedColumnCount : 7
	};

	var columnLayout = [{
			dataField : "regDtm",
			headerText : "날짜",
			width : "20%",
		},{
			dataField : "agenCd",
			headerText : "대리점코드",
			width : "20%",
		},{
			dataField : "agenNm",
			headerText : "대리점명",		   
			style: "auiLeft",
			width : "25%",
		},{
			dataField : "nttSub",
			headerText : "제목",		   
			style: "auiLeft auiLink",
		},{
			dataField : "cfmYnNm",
			headerText : "상태",
			width : "13%",
		}
	];
  //왼쪽 그리드 자료실 리스트 조회
	function selectRqbodList(){
	  	$.ajax({
			url : "/cmmc/selectRqbodList.do",
			type : 'POST',
			data : $("#frm").serialize(),
			success : function(data) {
				AUIGrid.setGridData(myGridID, data.list);
				AUIGrid.resize(myGridID, $("#content").width());
				$("#totCnt").html(data.list.length);
			}, // success 
			error : function(xhr, status) {
				alert("요청게시판 목록 조회 중 오류가 발생하였습니다");
			}
		});
	}

	function updateCfmYn(row){
		$.ajax({
			url : "/cmmc/updateCfmYn.do",
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(row),
			success : function(data) {
				selectRqbodList();
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		});
	}

</script>

<!-- content -->
<div class="content">

	<!-- 조회 -->
	<form id="frm">
		<div class="inquiryBox">
			<dl>
				<dt>대리점</dt>
				<dd>
					<div class="formWrap">
						<select name="srcType" id="srcType" class="sel mr10">
							<option value="">전체</option>
							<option value="agenCd">대리점코드</option>
							<option value="agenNm">대리점명</option>
						</select> 
						<input type="text" id="srcKeyword" class="inp w160" value="" name="srcKeyword" />
					</div>
	
				</dd>
				<dt>검색어</dt>
				<dd>
					<div class="formWrap">
						<select name="srcType2" id="srcType2" class="sel mr10">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">제목+내용</option>
						</select>
						<input type="text" name="srcKeyword2" id="srcKeyword2" class="inp w160 mr10" value="" title=""></input>
						<button type="button" name="" class="comBtn" id="searchBtn">조회</button>
					</div>
				</dd>
			</dl>
		</div>
	</form>
	<!-- 조회 -->

	<div class="txtInfo pColor02">
		※ 요청 내역은 3개월이 지나면 자동 삭제 됩니다. 담당자 확인 후에는 수정/삭제할 수 없습니다.
	</div>

	<div class="girdBoxGroup type02">
		<div class="girdBox w33per">
			<div class="titleArea">
				<p class="numState">
					<em>총 <span class="pColor01 fb" id="totCnt">0</span></em> 건 이 조회되었습니다.
				</p>
			</div>
			<!-- grid -->
			<div class="girdBox">
				<div id="grid_wrap"></div>
			</div>
			<!-- grid -->
		</div>
		<div class="conBox w65per">
			<div class="titleArea right">
				<h3 class="tit01">내용</h3>
			</div>
			<div class="fileInfo">
				<textarea name="contentArea" id="contentArea" class="h500" readonly="readonly"></textarea>
				<div id="fileArea"></div>
			</div>
		</div>
	</div>

</div>
<script id="attachTmpl" type="text/x-jquery-tmpl">
<div class="formWrap type02 file">
	<p class="tit">첨부파일 :</p>
	<p>
		<button type="button" class="comBtn small" onclick="download('\${nttAtclDtlSeq}')">다운로드</button>
		<span class="name">\${fileOriNm}</span>
	</p>
</div>
</script>