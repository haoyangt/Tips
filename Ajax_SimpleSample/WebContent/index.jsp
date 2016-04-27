<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('input[type="button"]').click(function(){
			$.ajax({
				type:'POST',
				url:'TestServlet',
				//data:$("#form").serializeArray(),
				data:{"data1":"this is data1","data2":"this is data2"},
				success:function(text) {
					var da = JSON.parse(text);
					$('#result').html(da.result1+"<br />"+da.result2);
				}
			});	
		});
	});
</script>
</head>
<body>
<input type="button" value="AJAX" />
<!-- <form action="" id="form">
<input  type="text" value="this is data1" name="data1"/>
</form> -->
<div id="result"></div>
</body>
</html>