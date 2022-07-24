<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var='root' value='/'/>
<script>
	alert('수정되었습니다')
	location.href = '${root}board/read?board_info_idx=${modifyContentDTO.content_board_idx}&content_idx=${modifyContentDTO.content_idx}&page=${page}'
</script>