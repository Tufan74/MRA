<!DOCTYPE HTML>
<html lang='de' dir='ltr'>
<head>
	<meta charset="utf-8" />
	<title>MovieRating - ${pagetitle}</title>
	<link type="text/css" href="css/style.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
  		$(function() {
    		$( "#datepicker2" ).datepicker(
    		{
    			minDate:'today',
    			
    		});
 
  			$("#datepicker1").datepicker({
  				minDate:'today',
    			onSelect: function (dateValue, inst) {
        			$("#datepicker2").datepicker("option", "minDate", dateValue)
    			}
			});
		});
  	</script>
</head>
<body>
<div id="wrapper">
	<div id="logo">Movie Rating<br>Software Engineering Example</div>
    <ul id="navigation">
    	<li><a href="index" title="Index">View Homesite</a></li>
	<#if navtype == "ruser">
    	<li><a href="rusergui?page=rate" title="rate">rate</a></li>
	<li><a href="movieoverview" title="movieoverview">Show all movies</a></li>
	<#elseif navtype == "user">
		<li><a href="usergui?page=insertoffer" title="register"> register</a></li>
	<#else>
    	<li><a href="usergui" title="user">register</a></li>
		<li><a href="rusergui" title="ruser">rate Movie</a></li>
	</#if>
    </ul>
	<div id="content">
