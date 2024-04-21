<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>

<script>

$(document).ready(function(){
	$("#printBtn").click(function(){
		$("#printArea").printElement({
			printMode: 'iframe',
			overrideElementCSS:['/css/printPop.css',{ href:'/css/printPop.css',media:'print'}]
		});
	});
});

</script>
<header>
	<h1><a href="#" class="logo" title="남양유업"></a></h1>
	<div class="logInfo">
	   <ul>
		   <li><button type="button" class="comBtn" id="printBtn">출력</button></li>
	   </ul>
   </div>
</header>