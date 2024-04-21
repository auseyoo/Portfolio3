<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>남양유업 대리점 주문시스템 - 에러페이지</title>
<!-- <link rel="stylesheet" type="text/css" href="../css/AUIGrid/grid_custom.css"/> -->
<link rel="stylesheet" type="text/css" href="../css/layout.css"/>
<script type="text/javascript" src="../js/lib/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/lib/jquery-ui.js"></script>
<!-- <script type="text/javascript" src="../js/AUIGrid/AUIGridLicense.js"></script>
<script type="text/javascript" src="../js/AUIGrid/AUIGrid.js"></script> -->
<script type="text/javascript" src="../js/common_ui.js"></script>
</head>
<body>
    <!-- Error -->
	<div class="errorWrap">
		<header>
            <h1><a href="#" class="logo" title="남양유업"></a></h1>         
		</header>
        <div class="contentWrap">
            <!-- content -->
            <div class="error_con">
                <div class="content">
                    <div class="error_img">
                        <!-- 404에러 이미지 -->
                        <img src="../images/img_error_404.png" alt="404이미지"/> 
                        
                        <!-- 500에러 이미지 -->
                        <!-- <img src="../images/img_error_500.png" alt="500이미지"/>  -->
                    </div>
                    <p class="error_title">
                        <!-- 404에러 메세지 -->
                        죄송합니다.<br/>
                        요청하신 페이지를 찾을 수 없습니다.

                        <!-- 500에러 메세지 -->
                        <!-- 죄송합니다.<br/>
                        서비스에 접속할 수 없습니다. -->
                    </p>
                    <div class="error_text">
                        <p>
                            접속하시려는 페이지의 주소(URL)가 잘못 입력되거나,<br/>
                            페이지의 주소가 변경 또는 삭제 되어 해당 페이지에 접속 할 수 없습니다.<br/>
                        </p>
                        <p class="bold">
                            입력하신 주소(URL)를 다시 한번 확인 해주시기 바라며,<br/>
                            문의사항은 담당지점 및 영업사원에게 문의 하여 주시기 바랍니다.
                        </p>
                        <p class="last">감사합니다.</p>
                    </div>
                    <div class="formWrap jCenter">
                        <button type="button" name="" class="comBtn large w200" id="inp_goMain">메인으로</button>
                    </div>
                </div>
                 <footer>
                    COPYRIGHT-NAMYANG-DAIRY-PRODUCTS-COLTD-ALL-RIGHT-RESERVED
                </footer>
            </div>
        </div>
    </div>
    <!--// Error -->
    
    <script type="text/javascript">
    	$(document).ready(function() {

    		// 메인 버튼 클릭
			$("#inp_goMain").click(function() {
				location.href = "/comm/main.do";
			});
			
    	});
    </script>
    
</body>
</html>