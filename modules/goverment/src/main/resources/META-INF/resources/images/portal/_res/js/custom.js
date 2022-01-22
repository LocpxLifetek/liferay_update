$(document).ready(function(){
 $('.tandan-div-module-news-article').find('img').each(function(){
  var $t=$(this);
  var td=$t.parent();
  //fix anh caption bang table
  var fixed;
  /*try
  {
  if(td[0].tagName=='TD'||td[0].parentNode.tagName=='TD'){
   var table=td.closest('table');
   var cap=null;
   try{cap=$(table.find('tr')[1]).find('td:first-child').html();}catch(e){}
   var imgDiv=$(document.createElement("div"));
   imgDiv.attr('class','img-border');
   table.after(imgDiv);
            var td=$t.closest('td');
   var html=td.html();
            td.html('');
   table.remove();
   var d1=$(document.createElement('div'));
   d1.attr('class','img-container');
   imgDiv.append(d1);
   d1.append(html);
   var d2=$(document.createElement('div'));
   d2.attr('class','img-cap');
   d2.html(cap);
   imgDiv.append(d2);
   fixed=true;
  }
  }
  catch(e){}
  if(!fixed){
	 	var cap=this.getAttribute("alt");
	 	if(!cap)return;
	 	if($('#art-content').text().indexOf(cap)==-1)
	 	$(this).after("<p class='img-cap' style='text-align:center'><em>"+cap+"</em></p>");
 	}*/
 });
 
 $('.lazyload-block-wrapper').addClass('show');
});