<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="featured" style="min-height: 501px !important">
	<div class="left">
		<div id="fragment-0" class="ui-tabs-panel lefts"
			style="display: block;">
			<div class="box-img">
				<div id="img">
					<img
						src="${blogsEntryDto.src}"
						alt="">
				</div>
				<div class="tit" id="title2">
					<a
						href="${url}/chitiettintuc?id=${blog.uuidBlogsEntry}">
						${blogsEntryDto.titleBlogsEntry}
					 </a>
				</div>
			</div>
			<div class="des">
				<div class="ct" id="description">${blogsEntryDto.description}</div>
			</div>
		</div>
	</div>
	<div class="right">
		<div class="ct">
			<ul id="cal-tabs" class="ui-tabs-nav rights">
				<c:forEach items="${listBlogs}" var="blog">
					<li class="ui-tabs-nav-item" id="nav-fragment-1"><a
						aria-valuetext="${blog.description}"
						aria-label="/knd/tt/PublishingImages/Tran Thanh Xuan/8.2021/bca_9220.jpg"
						href="${url}/chitiettintuc?id=${blog.uuidBlogsEntry}"> <img
							src="${blog.src}"
							alt=""> ${blog.titleBlogsEntry}
					</a></li>

				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<script>
	var posItem = document.querySelectorAll(".ui-tabs-nav-item");
	var title=document.querySelector('.tit').querySelectorAll('a');
	var descriptionContent=document.getElementsByClassName('ct');
	// console.log(title);
	var img=document.createElement('img');
	var titleFeature=document.createElement('a');
	var slideIndex=0;
	posItem.forEach((item) => {
	    item.addEventListener("mouseover", () => {
	        
	        document.getElementById('img').innerHTML="";
	        document.getElementById('title2').innerHTML="";
	        var image = item.querySelector('img').src;
	        var content=item.textContent;
	        var description=item.querySelector('a').ariaValueText;
	        var href= item.querySelector('a').href;
	        console.log(image);
	        descriptionContent[0].innerText = description; 
	        img.src= image;
	        document.getElementById('img').append(img);
	        titleFeature.href=href;
	        titleFeature.textContent=content;
	        document.getElementById('title2').append(titleFeature);
	        // console.log(title[0].textContent);
	    })
	});
	function showSlides() {
	    slideIndex++;
	    document.getElementById('title2').innerHTML = "";
	    document.getElementById('img').innerHTML = "";
	    if(slideIndex > posItem.length){
	        slideIndex=1;
	    }
	    console.log(slideIndex)
	    var image = posItem[slideIndex-1].querySelector('img').src;
	    var title= posItem[slideIndex-1].textContent;
	    var href=posItem[slideIndex-1].querySelector('a').href;
	    var description = posItem[slideIndex-1].querySelector('a').ariaValueText;
	    descriptionContent[0].innerText = description;
	    titleFeature.href = href;
	    titleFeature.textContent=title;
	    img.src = image;
	    document.getElementById('img').append(img);
	    document.getElementById('title2').append(titleFeature);
	    setTimeout(showSlides,2000);
	};
	showSlides();
</script>