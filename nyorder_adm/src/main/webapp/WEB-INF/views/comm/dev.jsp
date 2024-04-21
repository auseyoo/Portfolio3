<!-- 
	@File Name: 
	@File ���� : 
	@UI ID : 
	@�ۼ��� : 2022. 1. 19.
	@�ۼ��� : YESOL
 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
//AUIGrid ���� �� ��ȯ ID
var mGrid;
var markers = [];
var bounds = new kakao.maps.LatLngBounds();
var map;
$(document).ready(function(){
	var container = document.getElementById("map"); //������ ���� ������ DOM ���۷���
	var options = { //������ ������ �� �ʿ��� �⺻ �ɼ�
		center: new kakao.maps.LatLng(33.450701, 126.570667), //������ �߽���ǥ.
		level: 3 //������ ����(Ȯ��, ��� ����)
	};

	map = new kakao.maps.Map(container, options); //���� ���� �� ��ü ����

	createAUIGrid();
});

function createAUIGrid() {
	var auiGridProps = {
			rowIdField : "cstSeq",
			// ���� ���� ����
			editable : true,
			// ���� Ȱ��ȭ
			enableFilter : true,
			// ���� ǥ��
			showStateColumn : true,
			// row Height
			headerHeight : 60
	};

	//AUIGrid Į�� ����		formatString : "#,##0",	style : "left",
	var columnLayout = [ 
		{	dataField : "cstSeq",	headerText : "�Ϸù�ȣ",		width:100,	visible:false	},
		{	dataField : "agenSeq",	headerText : "�븮��",		width:100	},
		{	dataField : "areaSeq",	headerText : "����",			width:100,	style : "left"	},
		{	dataField : "cstAgenCd",headerText : "�븮���ڵ�",		width:100	},
		{	dataField : "dlvZipCd", headerText : "�����ȣ",		width:100	},
		{	dataField : "dlvAddr1",	headerText : "�ּ�1",			width:250,	style : "left"	},
		{	dataField : "dlvAddr2",	headerText : "�ּ�2",			width:250	},
	];
	
	// ������ #grid_wrap �� �׸��� ����
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
	// Ű����� ��Ҹ� �˻��մϴ�
	geocoder.addressSearch(addr, placeCallback); 
}

function placeCallback (data, status, pagination) {
	console.log(data);
	if (status === kakao.maps.services.Status.OK) {
		// �˻��� ��� ��ġ�� �������� ���� ������ �缳���ϱ�����
		// LatLngBounds ��ü�� ��ǥ�� �߰��մϴ�

		for (var i=0; i<data.length; i++) {
			displayMarker(data[i]);	
			bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
		}	   
		// �˻��� ��� ��ġ�� �������� ���� ������ �缳���մϴ�
		map.setBounds(bounds);
	} 
}

function searchAddr(){
	var rowPos = AUIGrid.getSelectedRows(mGrid);
	var addr =  rowPos[0].dlvAddr1+" "+rowPos[0].dlvAddr2;
	var geocoder = new kakao.maps.services.Geocoder();
	// Ű����� ��Ҹ� �˻��մϴ�
	geocoder.addressSearch(addr, placeCallback); 
}

//������ ��Ŀ�� ǥ���ϴ� �Լ��Դϴ�
function displayMarker(place) {
	
	// ��Ŀ�� �����ϰ� ������ ǥ���մϴ�
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
	
	<!-- ��ȸ -->
	<form id="frm">
		<div class="inquiryBox">
			<dl>
				<dt>�ּ�</dt>
				<dd>
					<input type="text" id="addr" name="addr" class="inp" value="" title="">
				</dd>
				<dd>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchList()">��ȸ</button>
					<button type="button" name="" class="inquBtn" id="inp_name01" onclick="searchAddr()">��ǥ</button>
				</dd>
			</dl>
	
			<div class="btnGroup right">
				<!-- <button type="button" id="popBtn"	class="comBtn">�˾�</button> -->
			</div>
		</div>
	</form>
	<!-- ��ȸ -->
	
	<div class="girdBoxGroup">
		<div class="girdBox w55per">
			<div class="titleArea">
				<h3 class="tit01">�׸���</h3>
			</div>
			
			<!-- grid -->
			<div class="gridBox">
				<div id="grid_wrap"></div>
			</div>
		</div>
		
		<div class="conBox w43per">
			<div class="titleArea">
				<h3 class="tit01">����</h3>
			</div>
			
			<div id="map" style="width:500px;height:400px;"></div>
		</div>
	</div>
</div>
