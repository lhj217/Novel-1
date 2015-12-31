<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
        <title>内容</title>
    
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1,user-scalable=no" name="viewport" />	
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<meta name="keywords" content="雅耀,广告,代理,服务,茶叶,茶艺,保险">
	<meta name="description" content="雅耀（湖南）科技有限公司是一个线上线下结合形成一站式的综合性服务公司，业务涉及广泛，如广告，代理，茶叶，茶艺，保险，服务，活动等等">
	
		<link rel="stylesheet" type="text/css" href="resources/css/jquery.mobile-1.4.5.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/base.css">
	<script type="text/javascript" src="resources/js/jQuery1.11.3.js"></script>
 	<script type="text/javascript" src="resources/js/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript" src="resources/js/base.js"></script>
	<script type="text/javascript">
			$(document).ready(function(){
				var url, element, interval, runid;
				$('#start').click(function(){
					url = $('#url').val();
					element = $('#element').val();
					interval = $('#interval').val();				
					//Run for first time
					$('#msg').html('请耐心等待, 页面抓取中 ...').fadeIn(400);
					//$('#content').html('');
					$.post('siteproxy', {url:url, elem:element}, function(data){
						$('#content').html(data);
						$('#msg').html('抓取已完成').delay(1500).fadeOut(400);
					})					
					
					runid = setInterval(			
					function getInfo(){
						$('#msg').html('请耐心等待, 页面抓取中 ...').fadeIn(400);
						//$('#content').html('');
						$.post('siteproxy', {url:url, elem:element}, function(data){
							$('#content').html(data);
							$('#msg').html('抓取已完成').delay(1500).fadeOut(400);
						})
					}, interval*1000);
				});
				
				$('#stop').click(function(){
					$('#msg').html('抓取已暂停').fadeIn(400).delay(1500);
					clearInterval(runid);
				});
			});
		</script>
    </head>
    <body>
    
    </body>
</html>
