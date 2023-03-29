<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userInfo.userId }회원 상세 정보</title>
<link rel="stylesheet" href="/resources/admin/adminMemberModify.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="user-main">
		<div class="user-profile">
			<form action="/admin/membermodify" method="post" id="edit-user" name="edit-user">
				<div class="user-pro">
				
					<h2>${userInfo.userId }정보 관리</h2>
					<hr />
					<div class="user">
						<b>아이디</b>
						<input title="아이디는 수정이 불가능합니다" type="text" id="userId" name="userId" placeholder="아이디는 수정이 불가능합니다." value="${userInfo.userId}" readonly="readonly" />
						<div id="idError" class="error"></div>
						<br />
						<b>비밀번호</b>
						<input type="password" id="userPwd" name="userPwd" />
						<div id="pwdError" class="error"></div>
						<br />
						<b>이름</b>
						<input title="이름은 별도의 인증과정을 거쳐 수정이 가능합니다" type="text" id="userName" value="${userInfo.userName}" readonly="readonly" />
						<div id="nameError" class="error"></div>
						<br />
						<b>생년월일</b>
						<input title="생년월일은 수정이 불가능합니다"type="text" id="userBirth" value="${userInfo.userBirth}" readonly="readonly" />
						<br />
						<b>이메일</b>
						<input type="email" id="userEmail" name="userEmail" value="${userInfo.userEmail}" />
						<div id="emailError" class="error"></div>
						<br />
						<b>가입일</b>
						<input type="text" title="가입일은 수정이 불가능합니다" id="userJoinDate" value="<fmt:formatDate value="${userInfo.userJoinDate}" pattern = "yyyy-MM-dd"/>" readonly="readonly"/>
						<br />
						<b>전화번호</b>
						<input title="휴대전화 번호 수정은 별도의 인증과정이 필요합니다." type="tel" id="PhoneNumber" value="${userInfo.userPhoneNumber}" readonly="readonly" />
						<div id="phoneError" class="error"></div>
						<br />
						<b>회원구분</b>
							<c:choose>
								<c:when test="${userInfo.userVerify == 128}">
									<span>관리자</span>
								</c:when>
								<c:when test="${userInfo.userVerify == 5}">
									<span>판매자</span>
								</c:when>
								<c:otherwise>
									<span>일반회원</span>
								</c:otherwise>
							</c:choose>
						<div id="phoneError" class="error"></div>
						<br />
						<div class="address">
							<b>주소</b>
							<input id="postAddress" name="postAddress" type="text" placeholder="우편번호" value="${userAddressInfo.postAddress }" readonly="readonly"/>
							<button type="button" onclick="findAddr()">우편번호 검색</button>
							<input id="address" name="address" type="text" placeholder="주소" value=${userAddressInfo.address } readonly="readonly" />
							<br />
							<div id="addressError" class="error"></div>
							<input id="detailAddress" name="detailAddress" type="text" placeholder="상세주소 입력" ${userAddressInfo.detailAddress } />
							<div id="detailAddError" class="error"></div>
						</div>
					</div>
				</div>
				<hr />
				<!--수정버튼-->
				<div class="user-update">
					<button type=submit id="update-btn" name="update-btn" onclick="editInfo()">수정</button>
				</div>
				<div class="user-withdrawal">
					<button type="button" id="withdrawal-btn" name="withdrawal-btn" onclick="removeMember()">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="/resources/admin/adminMemberModify.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>