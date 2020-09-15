<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.Questionnaire"%>
<%@page import="entity.Question"%>
<%Questionnaire  questionnaire = (Questionnaire)request.getAttribute("questionnaire"); %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/showQuestionnaire.css" rel="stylesheet" type="text/css">
<title>问卷填写界面</title>
</head>
<body>
<div id="all">
   <div class = "top">
    <span><%=questionnaire.getName() %></span>
   </div>
   <div class = "body">
      <div class = "body_content">
         <form action="./addAResult">
            <%if(questionnaire.getQuestions().size() == 0) {%>
            <span>暂无问卷</span>
            <div class="button_block">
                <div class = "button"><a href = "./main">退出</a></div>
             </div>
            <%}
            else{
            	int i = 1;
                for(Question temp:questionnaire.getQuestions()){%>
                   <div class = "stem"><span><%=i++ %>.<%=temp.getStem() %></span></div>
                   <%for(String option:temp.getOptions()){ %>
                      <div class = "option">
                         <p><input type="radio" value="<%=option%>" name="<%=temp.getId()%>,result"><%=option %></p>
                      </div>
                   <%}
                }%>
                <input type="hidden" name="questionnaire_id" value="<%=questionnaire.getId() %>"/>
                <div class="button_block">
                <input class = "button" type="submit" value="提交" />
                <div class = "button"><a href = "./main">退出</a></div>
                </div>
             <%}%>
         </form>
      </div>
      
   </div>
</div>
</body>
</html>
