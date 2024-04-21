<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
	<meta charset="UTF-8">
	<meta name="_csrf" th:content="${_csrf.token}"/>
	<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
	<title>namyang</title>
	<!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link rel="stylesheet" type="text/css" href="/css/lib/daterangepicker.css"/>
	<link rel="stylesheet" type="text/css" href="/css/AUIGrid/grid_custom.css"/>
	<link rel="stylesheet" type="text/css" href="/css/layout.css"/>
		
	<script type="text/javascript" src="/js/lib/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript" src="/js/AUIGrid/AUIGridLicense.js"></script>
	<script type="text/javascript" src="/js/AUIGrid/AUIGrid.js"></script>
	<script type="text/javascript" src="/js/AUIGrid/FileSaver.min.js"></script>
	
	
	<script type="text/javascript" src="/js/lib/moment.min.js"></script>
	<script type="text/javascript" src="/js/lib/daterangepicker.js"></script>
	<script type="text/javascript" src="/js/datepicker_custom.js"></script>
	<script type="text/javascript" src="/js/jquery.printelement.js"></script>
	
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/common_ui.js"></script>
</head>
<script>
	

</script>
<body>
	<div class="allWrap">
		<tiles:insertAttribute name="header"/>
		<div class="contentWrap">
			<div class="rContentBox" id="printArea">
				<tiles:insertAttribute name="body"/>
			</div>
		</div>
	</div>
</body>
