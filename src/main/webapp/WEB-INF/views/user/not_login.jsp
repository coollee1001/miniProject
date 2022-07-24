<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="root" value="/"/>
<script>
	alert('로그인 해주세요')
	location.href="${root}user/login"
</script>