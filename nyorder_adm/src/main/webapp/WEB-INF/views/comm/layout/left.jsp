<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
$(document).ready(function(){
	//lnb
	setLeftMenu();
	
});

function setLeftMenu(){
	
	/**
	$('.lnb > ul > li.depth1').click(function(e){		
		//e.preventDefault();
		
		//debugger;
		
		console.log("leftmenu depth1==" , e);
		console.log("leftmenu depth1==" , this);

		if ($(this).hasClass("active")){
			$(this).removeClass("active").find(".depth2").slideUp(300);			
		}else{
			//$(".lnb > ul > li.active .depth2").slideUp(300);
			//$(".lnb > ul > li.active").removeClass("active");
			//$(this).addClass("active").find(".depth2").slideDown(300);
			
			$(".lnb > ul > li.active .depth2").slideUp(300);
			$(".lnb > ul > li.active").removeClass("active");
			$(this).addClass("active").find(".depth2").slideDown(300);
			

			$(".depth2 a").click(function(){
				//return false;
				//e.stopPropagation();
			});
		}
	});
	
	
	
	$('.lnb > ul > li.depth1').click(function(e){		
		//e.preventDefault();	

		if ($(this).hasClass("active")){
			$(this).removeClass("active").find(".depth2").slideUp(300);			
		}else{
			$(".lnb > ul > li.active .depth2").slideUp(300);
			$(".lnb > ul > li.active").removeClass("active");
			$(this).addClass("active").find(".depth2").slideDown(300);

			$(".depth2 a").click(function(){
				//return false;
				//e.stopPropagation();
			});
		}
	});
	*/
}

function switchContent(bodyUrl, e, _this){
	
	console.log("switchContent e==" , e);
	console.log("switchContent this==" , _this);
	e.stopPropagation();
	if(fnIsEmpty(bodyUrl)){
		alert("메뉴를 확인해주세요.");
		return false;
	}
	
	$(".lnb > ul > li > ul.depth2 li a.active").removeClass("active");
	$(_this).addClass("active");
	
	$('#bodyTile').children().remove();
    $('#bodyTile').load(bodyUrl);		
	
	console.log("switchContent bodyUrl==" + bodyUrl);
	/**
	$.ajax({
		type: "get",
		url : bodyUrl,
		dataType: 'html',
		success : function(data){
			
			//console.log("switchContent data==" + data);
			
			$('#bodyTile').children().remove();
    		$('#bodyTile').html(data);

		},
		error : function(xhr, status) {
        	console.log("switchContent error xhr==" + JSON.stringify(xhr));
            //alert(xhr + " : " + status);
        }
	})
	*/
	
}

</script>

<!-- lnb -->
			<div class="lnbWrap">
                <!-- 검색영역 -->
                <div class="leftSearBox">
                    <input type="text" name="" value="" class="inp type02">
                    <button type="button" class="searBtn" name="" title="검색하기"></button>
                </div>
                <!-- 검색영역 -->

                <div class="lnbTabsWrap">
                    <ul class="tabs etcMenu">
                        <li class="active" rel="mtab01"><a href="#" class="allMenu" title="전체메뉴"><span>전체메뉴</span></a></li>
                        <li class="" rel="mtab02"><a href="#" class="favSearch" title="즐겨찾기"><span>즐겨찾기</span></a></li>
                    </ul>

                    <!-- <a href="#" class="favSearch" title="즐겨찾기"><span>즐겨찾기</span></a> -->

                    <div id="mtab01" class="tabContent lnb" style="">
                        <ul>
                        
                        	<c:forEach  items="${adminMenu}" var="i" varStatus="status">
							<c:if test="${i.level eq '1'}">
                        
                            	<li class="depth1 <c:if test='${i.menuSeq eq activeParentSeq }'>active</c:if>" id="${i.menuSeq} ${activeParentSeq}">
                            		<a href="#" title="${i.menuNm}"><span>${i.menuNm}</span></a>
									<ul class="depth2">									
									<c:forEach  items="${adminMenu}" var="j">
										
										<c:if test="${i.menuSeq == j.menuParentSeq }">
									  		<li  id="${i.menuSeq} ${j.menuParentSeq}">
									  			<a href="<c:url value='${j.menuUrl}'/>"<c:if test='${j.menuSeq eq activeSeq }'>class="active"</c:if>>${j.menuNm}</a>									  								
											</li>
									  	</c:if>
									</c:forEach>
                                	</ul>
                            	</li>
                            </c:if>
                       	</c:forEach>     	
                            
                            
                        </ul>
                    </div>

                    <div id="mtab02" class="tabContent favor" style="display: none;">
                        <ul>
                            <li><a href="#">거래처 관리</a></li>
                            <li><a href="#">거래처별 단가 관리</a></li>
                            <li><a href="#">직원 관리</a></li>
                        </ul>
                    </div>
                </div>

                <a href="#" class="sideBtn"></a>
			</div>
            <!-- lnb -->
			
