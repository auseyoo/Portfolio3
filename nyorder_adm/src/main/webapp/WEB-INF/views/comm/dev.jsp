<!-- 
	@File Name: 
	@File 설명 : 
	@UI ID : 
	@작성일 : 2022. 1. 19.
	@작성자 : YESOL
 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
//AUIGrid 생성 후 반환 ID
var mGrid;
var markers = [];
var bounds = new kakao.maps.LatLngBounds();
var map;
$(document).ready(function(){
	var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	createAUIGrid();
});

function createAUIGrid() {
	var auiGridProps = {
			rowIdField : "cstSeq",
			// 수정 가능 여부
			editable : true,
			// 필터 활성화
			enableFilter : true,
			// 상태 표시
			showStateColumn : true,
			// row Height
			headerHeight : 60
	};

	//AUIGrid 칼럼 설정		formatString : "#,##0",	style : "left",
	var columnLayout = [ 
		{	dataField : "cstSeq",	headerText : "일련번호",		width:100,	visible:false	},
		{	dataField : "agenSeq",	headerText : "대리점",		width:100	},
		{	dataField : "areaSeq",	headerText : "지역",			width:100,	style : "left"	},
		{	dataField : "cstAgenCd",headerText : "대리점코드",		width:100	},
		{	dataField : "dlvZipCd", headerText : "우편번호",		width:100	},
		{	dataField : "dlvAddr1",	headerText : "주소1",			width:250,	style : "left"	},
		{	dataField : "dlvAddr2",	headerText : "주소2",			width:250	},
	];
	
	// 실제로 #grid_wrap 에 그리드 생성
	mGrid = AUIGrid.create("#grid_wrap", columnLayout, auiGridProps);
}

function searchList(){
	$.ajax({
		url : "/comm/selectAddr.do", 
		type : 'POST', 
		data : $("#frm").serialize(),
		success : function(data) {
			AUIGrid.setGridData(mGrid, data);
			arrList = data;
		}, // success 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	}); 	
}

function searchAddr(){
	
	var rowPos = AUIGrid.getSelectedRows(mGrid);
	var addr =  rowPos[0].dlvAddr1+" "+rowPos[0].dlvAddr2;
	console.log(addr);
	var geocoder = new kakao.maps.services.Geocoder();
	// 키워드로 장소를 검색합니다
	geocoder.addressSearch(addr, placeCallback); 
}

function placeCallback (data, status, pagination) {
	console.log(data);
	if (status === kakao.maps.services.Status.OK) {
		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다

		for (var i=0; i<data.length; i++) {
			displayMarker(data[i]);	
			bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
		}	   
		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		map.setBounds(bounds);
	} 
}

function searchAddr(){
	var rowPos = AUIGrid.getSelectedRows(mGrid);
	var addr =  rowPos[0].dlvAddr1+" "+rowPos[0].dlvAddr2;
	var geocoder = new kakao.maps.services.Geocoder();
	// 키워드로 장소를 검색합니다
	geocoder.addressSearch(addr, placeCallback); 
}

//지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
	
	// 마커를 생성하고 지도에 표시합니다
	var marker = new kakao.maps.Marker({
		map: map,
		position: new kakao.maps.LatLng(place.y, place.x) 
	});

	marker.setMap(map);
	markers.push(marker);

	map.setBounds(bounds);
}
</script>
<div class="content">
	<tiles:insertAttribute name="body.breadcrumb"/>
	
	<!-- 조회 -->
	<form id="frm">
		<div class="inquiryBox">
			<dl>
				<dt>주소</dt>
				<dd>
					<input type="text" id="addr" name="addr" class="inp" value="" title="">
				</dd>
				<dd>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchList()">조회</button>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchAddr()">좌표</button>
				</dd>
			</dl>
	
			<div class="btnGroup right">
				<!-- <button type="button" id="popBtn"	class="comBtn">팝업</button> -->
			</div>
		</div>
	</form>
	<!-- 조회 -->
	
	<div class="girdBoxGroup">
		<div class="girdBox w55per">
			<div class="titleArea">
				<h3 class="tit01">그리드</h3>
			</div>
			
			<!-- grid -->
			<div class="gridBox">
				<div id="grid_wrap"></div>
			</div>
		</div>
		
		<div class="conBox w43per">
			<div class="titleArea">
				<h3 class="tit01">지도</h3>
			</div>
			
			<div id="map" style="width:500px;height:400px;"></div>
		</div>
	</div>
</div>
