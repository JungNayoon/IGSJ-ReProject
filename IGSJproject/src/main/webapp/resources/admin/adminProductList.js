// 검색타입을 결정하는 select 태그가 카테고리로 바뀌면 시작되는 onchange 로직이다.
function changeInputTag() {
	// select 태그의 class = 'searchType'을 가져와 options [select태그관련변수명.selectedIndex].value로 option 태그의 값을 가져온다.
	let searchType = document.querySelector(".searchType");
	// select에서 선택된 option의 val
	let opVal = searchType.options[searchType.selectedIndex].value;
	
	// 만약 option 태그의 값이 'category' (select 태그 = 카테고리)라면 검색창인 input태그를 select 태그로 바꿔버린다.
	if(opVal=='cno'){
		
		$(".keyword").replaceWith
		(
		  "<select id='searchType' name='searchType' onchange='changeCategoryValue();'>"
		+	"<option value='100' name='keyword'>OUTER</option>"
		+	"<option value='200' name='keyword'>TOP</option>"
		+	"<option value='300' name='keyword'>BOTTOM</option>"
		+	"<option value='400' name='keyword'>ACC</option>"
		+	"<option value='500' name='keyword'>SHOES</option>"
		+ "</select>"
		);
		
	} else {
		
		$("#searchType").replaceWith(
			"<input type='text' id='keyword' class='keyword' name='keyword' value='<%=pageIngredient.getKeyword()%>' onkeyup='enterSearching();'>"
		);
		
		document.getElementById("keyword").value = "";
	}
	
}

/* 위의 함수에서 "이미 실행이 돼고 나온 태그"에 적용되는 로직이다. */
function changeCategoryValue() {
	/* 새롭게 생성된 select 태그의 id를 받아 option의 value를 저장한다. */
	let categorySearchType = document.getElementById("searchType");
	let secondCategoryValue = categorySearchType.options[categorySearchType.selectedIndex].value;
	
	/* 그리고 검색에 써먹기 위해 return을 시켜 값이 확정되도록 만든다. */
	return secondCategoryValue;
}

function searchingActivate() {
	let searchType = document.getElementsByName("searchType")[0].value;
	let keyword = document.getElementsByName("keyword")[0].value;
	
	/* 만약 select 태그가 카테고리(option 태그 value = 'category' 라면 changeCategoryValue 함수의 결과값을 키워드에 넣어 검색한다. */
	if(searchType == 'cno'){
		keyword = changeCategoryValue();
	}
	
	/* select 태그의 value인 searchType에 관한 option태그의 값과
	input태그에 들어간 keyword 값을 쿼리스트링으로 보내 첫페이지를 출력한다. */
	location.href = "/admin/productlist?pageNum=1" + "&searchType="
		+ searchType + "&keyword=" + keyword;
	
}

/* input 태그 안에서 enter키 (keyNumber == 13) 누르면 검색 시작. */
function enterSearching() {
	if (window.event.keyCode == 13) {
		searchingActivate();
	}
}