<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.Questionnaire"%>
<%List<Questionnaire>  questionnaires = (List<Questionnaire>)request.getAttribute("questionnaires"); %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
<title>问卷调查系统</title>
</head>
<body>
<div id="all">
   <div class = "top">
    <span>问卷调查系统</span>
   </div>
   <div class = "body">
      <div class = "body_title"><span>问卷列表</span><a href="./administratorsPage">后台管理入口</a></div>
      <div class = "body_content">
         <%if(questionnaires.size() == 0) {%>
         <span>暂无问卷</span>
         <%}
         else{ 
             for(Questionnaire temp:questionnaires){%>
                <div class = "questionnaire_name"><a href="./getAQuestionnaire?questionnaire_id=<%=temp.getId()%>"><%=temp.getName()%></a></div>
         <%  }
             } %>
      </div>
   </div>
</div>
</body>
</html>
