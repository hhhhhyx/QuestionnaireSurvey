<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.ResultInformation"%>
<%List<ResultInformation>  resultInformationList = (List<ResultInformation>)request.getAttribute("resultInformationList"); %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/administratorsPage.css" rel="stylesheet" type="text/css">
<title>管理员界面</title>
</head>
<body>
<div id="all">
   <div class = "top">
    <span>管理员界面——问卷调查结果</span>
   </div>
   <div class = "body">
      <div class = "body_title"><span>问卷调查结果列表</span><a href="./main">进入主界面</a></div>
      <div class = "body_content">
         <%if(resultInformationList==null) {%>
         <span>暂无问卷调查结果</span>
         <%}
         else{
             for(int i = 0;i<resultInformationList.size();i++){%>
                <div class = "questionnaire_name"><a href="./getAResultInformation?resultInformation_id=<%=resultInformationList.get(i).getId() %>">问卷结果<%=i+1 %></a><span class="time"><%=resultInformationList.get(i).getReportTime() %></span></div>
         <%  }
             } %>
      </div>
   </div>
</div>
</body>
</html>
