<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
	<link href="../../resources/css/admin/product.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="../../resources/ckeditor/ckeditor.js"></script>
<style type="text/css">
		.listWrap {
	width : 90%;
	margin : 0 auto;
}
</style>
</head>
<body>
<!-- 사이드 바 -->
	<div class="container">
		<h2 class="adminTitle">상품등록</h2>
		<form role = "form" class="listWrap" method="post" autocomplete="off" enctype="multipart/form-data">
			<label>1차 분류</label>
			<select class="category1">
				<option value="">전체</option>
			</select>
			<br>
			<label>2차 분류</label>
			<select class="category2" name="cno">
				<option value="">전체</option>
			</select>
			
			<div class="inputArea">
			 <label for="productName">상품명</label>
			 <input type="text" id="product_name" name="product_name" />
			</div>
			
			<div class="inputArea">
			 <label for="productPrice">상품가격</label>
			 <input type="text" id="product_price" name="product_price" />
			</div>
			
			<div class="inputArea">
			 <label for="productContent">상품소개</label>
			 <textarea rows="5" cols="50" id="product_description" name="product_description"></textarea>
			 <script>
					 var ckeditor_config = {
					   resize_enaleb : false,
					   enterMode : CKEDITOR.ENTER_BR,
					   shiftEnterMode : CKEDITOR.ENTER_P,
					   filebrowserUploadUrl : "/seller/ckUpload",
					   filebrowserUploadMethod: 'form'
					 };
					 CKEDITOR.replace("product_description", ckeditor_config);
					 
			</script>
			</div>
			
			<div class="inputArea">
			 <label for="productStock">상품수량</label>
			 <input type="text" id="product_stock" name="product_stock" />
			</div>
			
			<div class="inputArea">
 			<label for="gdsImg">이미지</label>
 			<input type="file" id="p_img" name="file" />
			 <div class="select_img"><img src="" /></div>
			 </div>
		
			<div class="inputArea">
			 	<button type="submit" id="register_Btn" class="btn btn-primary">등록</button>
			</div>
			
		</form>
	</div>
	
<script>
// 컨트롤러에서 데이터 받기
var jsonData = JSON.parse('${category}');

var cate1Arr = new Array();
var cate2Arr = new Array();
var cate1Obj = new Object();
var cate2Obj = new Object();

// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
for(var i = 0; i < jsonData.length; i++) {
 
 if(jsonData[i].big_ctg == "1") {
  cate1Obj = new Object();  //초기화
  cate1Obj.cno = jsonData[i].cno;
  cate1Obj.category_name = jsonData[i].category_name;
  cate1Arr.push(cate1Obj);
 }
}

// 1차 분류 셀렉트 박스에 데이터 삽입
var cate1Select = $("select.category1")

for(var i = 0; i < cate1Arr.length; i++) {
 cate1Select.append("<option value='" + cate1Arr[i].cno + "'>"
      + cate1Arr[i].category_name + "</option>"); 
}

$(document).on("change", "select.category1", function(){
	 
	 // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
	 for(var i = 0; i < jsonData.length; i++) {
	  
	  if(jsonData[i].big_ctg == "2") {
	   cate2Obj = new Object();  //초기화
	   cate2Obj.cno = jsonData[i].cno;
	   cate2Obj.category_name = jsonData[i].category_name;
	   cate2Obj.category_level = jsonData[i].category_level;
	   
	   cate2Arr.push(cate2Obj);
	  }
	 }
	 
	 var cate2Select = $("select.category2");
	 
	 /*
	 for(var i = 0; i < cate2Arr.length; i++) {
	   cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
	        + cate2Arr[i].cateName + "</option>");
	 }
	 */
	 
	 cate2Select.children().remove();

	 $("option:selected", this).each(function(){
	  
	  var selectVal = $(this).val();  
	  
	  cate2Select.append("<option value='" + selectVal + "'>전체</option>");
	  
	  for(var i = 0; i < cate2Arr.length; i++) {
	   if(selectVal == cate2Arr[i].category_level) {
	    cate2Select.append("<option value='" + cate2Arr[i].cno + "'>"
	         + cate2Arr[i].category_name + "</option>");
	   }
	  }
	  
	 });
	 
	});
	$("#p_img").change(function(){
	 if(this.files && this.files[0]) {
	  var reader = new FileReader;
	  reader.onload = function(data) {
	   $(".select_img img").attr("src", data.target.result).width(500);        
	  }
	  reader.readAsDataURL(this.files[0]);
	 }
	});
</script>

<script>
var regExp = /[^0-9]/gi;

$("#p_price").keyup(function(){ numCheck($(this)); });
$("#p_stock").keyup(function(){ numCheck($(this)); });

function numCheck(selector) {
   var tempVal = selector.val();
   selector.val(tempVal.replace(regExp, ""));
}
</script>
</body>
</html>