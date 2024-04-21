<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

$(document).ready(function(){
	$("#addBkmk").on("click", function(){
		$.ajax({
			url : "/comm/updateBkmk.do", 
			type : "POST", 
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({menuSeq : $("#addBkmk").data("menuSeq")}),
			success : function(data) {
				
			}, // success 
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		}); 	
		
	});
});
</script>


<div class="titTopArea">
	<c:forEach  items="${menu}" var="i" varStatus="status">
		<c:if test="${i.menuCurrentGbn > 0}">
			<h2 class="tit01">${i.menuNm} <a href="javascript:void(0);" class="favor" title="즐겨찾기" id="addBkmk" data-menu-seq="${i.menuSeq}"></a></h2>
		</c:if>
	</c:forEach>
	<div class="location">
		<ul>
			<li><i class="home"></i></li>
			 <c:forEach  items="${menu}" var="i" varStatus="status">
			 	<li>${i.menuNm}</li>
			 </c:forEach>
		</ul>
	</div>
</div>