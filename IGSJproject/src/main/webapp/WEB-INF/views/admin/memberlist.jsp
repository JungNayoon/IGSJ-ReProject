<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ezen.project.IGSJ.utils.pagination.PageIngredient"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(Admin)회원 목록 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!--
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
-->
<style>
#paging a {
	text-decoration: none;
	margin: 10px;
	font-family: inherit;
	font-size: medium;
	font-weight: bold;
}

.listWrap {
	width: 90%;
	margin: 0 auto;
}

th{
	background: gray;
}

th, td {
	text-align: center;
	vertical-align: middle;
	padding: 15px;
	border-bottom: 1px solid #e9e9e9;
}
</style>
</head>
<body>

	<div class="container" align="center">
		<div class="listWrap">
			<div>
				<h2 class="adminTitle">회원 목록</h2>
			</div>
			<table class="table table-striped table-hover align-middle table-bordered" style="border-spacing: 0;">
				<thead class="table-dark" style="text-align: center; vertical-align: middle;">
					<tr>
						<th style="width: fit-content;">아이디</th>
						<th style="width: fit-content;">이름</th>
						<th style="width: fit-content;">생년월일</th>
						<th style="width: fit-content;">가입일자</th>
						<th style="width: fit-content;">회원유형</th>
						<th style="width: fit-content;">관리</th>
					</tr>
				</thead>
				<tbody style="text-align: center; vertical-align: middle;">
				
					<c:forEach var="adminMemberList" items="${adminMemberList }">
						<tr>
							<td align="right">
								<a href="${contextPath}/member/memberDetail?userId=${adminMemberList.userId}" title="상세정보조회">${adminMemberList.userId}</a>
							</td>
							<td align="right">${adminMemberList.userName}</td>
							<td align="right">${adminMemberList.userBirth}</td>
							<td align="right">
								<fmt:formatDate value="${adminMemberList.userJoinDate}" pattern="yyyy.MM.dd  hh:mm" />
							</td>
							<c:choose>
								<c:when test="${adminMemberList.userVerify == 128 }">
									<td>관리자</td>
								</c:when>
								<c:when test="${adminMemberList.userVerify == 5 }">
									<td>판매자</td>
								</c:when>
								<c:otherwise>
									<td>일반회원</td>
								</c:otherwise>
							</c:choose>
							<td>
								<a href="/admin/membermodifypage?userId=${adminMemberList.userId }">[수정]</a>
								<br>
								<a href="/admin/memberdelete">[삭제]</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 시작 -->
			<%
			PageIngredient pageIngredient = (PageIngredient) request.getAttribute("page");

			int selectedPageNum = (int) request.getAttribute("selectedPageNum");

			/* 이전페이지 버튼만들기 */
			if (pageIngredient.isPrevPage() == true) {
			%>
			<span>
				<a href="/admin/memberlist?pageNum=<%=pageIngredient.getStartPage() - 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">◀이전</a>
			</span>
			<%
			}

			/* 페이지 쫙(1,2,3,4...) 출력하기 */
			for (int i = pageIngredient.getStartPage(); i <= pageIngredient.getEndPage(); i++) {

			if (selectedPageNum != i) {
			%>
			<span>
				<a id="notSelectedPage" href="/admin/memberlist?pageNum=<%=i%><%=pageIngredient.getSearchTypeAndKeyword()%>"><%=i%></a>
			</span>
			<%
			} else if (selectedPageNum == i) {
			%>
			<span>
				<b style="font-size: 22px"><%=i%></b>
			</span>
			<%
			}
			}

			/* 다음버튼 만들기 */
			if (pageIngredient.isNextPage() == true) {
			%>
			<span>
				<a href="/admin/memberlist?pageNum=<%=pageIngredient.getEndPage() + 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">다음▶</a>
			</span>
			<%
			}
			%>
			<!-- 페이징 끝 -->
		</div>

		<!-- 게시글 검색기능 -->
		<div>
			<select class="searchType" name="searchType" onchange="changeInputTag()">
				<option value="userId" <%=pageIngredient.getSearchType().equals("userId") ? "selected" : ""%>>아이디</option>
				<option value="userName" <%=pageIngredient.getSearchType().equals("userName") ? "selected" : ""%>>이름</option>
				<option value="userVerify" <%=pageIngredient.getSearchType().equals("userVerify") ? "selected" : ""%>>회원유형</option>
			</select>
			<input type="text" id="keyword" class="keyword" name="keyword" value="<%=pageIngredient.getKeyword()%>" onkeyup="enterSearching();">
			<button id="searchingActivate" type="button" onclick="searchingActivate();">검색</button>
		</div>
		<!-- 게시글 검색기능 끝 -->

	</div>

	<script src="/resources/admin/adminMemberList.js"></script>
</body>
</html>