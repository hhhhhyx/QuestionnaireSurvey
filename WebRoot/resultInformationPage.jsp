<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.ResultInformation"%>
<%@page import="entity.Question"%>
<%@page import="entity.Questionnaire"%>
<%ResultInformation  resultInformation = (ResultInformation)request.getAttribute("resultInformation"); %>
<%Questionnaire  questionnaire = (Questionnaire)request.getAttribute("questionnaire"); %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/resultInformationPage.css" rel="stylesheet" type="text/css">
<title>调查结果界面</title>
</head>
<body>
<div id="all">
   <div class = "top">
    <span>调查结果界面</span>
   </div>
   <div class = "body">
      <div class = "body_content">
         <div><span>提交时间：<%=resultInformation.getReportTime() %> 问卷题目：<%=questionnaire.getName() %></span></div>
         <%for(int i = 0;i<resultInformation.getQuestions().size();i++){%>
                   <div class = "stem"><p><%=i+1 %>.<%=resultInformation.getQuestions().get(i).getStem() %></p></div>
                   <%for(String option:resultInformation.getQuestions().get(i).getOptions()){ %>
                      <div class = "option">
                         <p><input type="radio" value="<%=option%>" name="<%=resultInformation.getQuestions().get(i).getId()%>,result"><%=option %></p>
                      </div>
                   <%}%>
                <div class="result"><p>用户的选择是：<%=resultInformation.getResults().get(i) %></p></div>
         <%}%>
         <div class="button_block">
         <div class = "button"><a href = "./administratorsPage">退出</a></div>
         </div>
      </div>
      
   </div>
</div>
</body>
</html>