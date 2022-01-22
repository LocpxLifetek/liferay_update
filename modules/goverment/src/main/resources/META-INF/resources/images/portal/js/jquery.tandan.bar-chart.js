/*! Tandan bar chart  -v0.1  -05-08-2014
* http://tandan.com.vn
* By Chinh Nguyen */
(function(){
if(!$){try{console.error('Tandan.BarChart: Include jquery first!');}catch(e){}return;}
function getPercent(val, total){
	if(val==0)return 0;
	return ((val/total)*100).toFixed(2);
}
$.fn.tdBarChart=function(op){
	var total=this.data('number')||0;
	var sel=this.data('select');
	this.find('>li').each(function(){
		var $t=$(this);
		var num=$t.data('number')||0;
		var per=getPercent(num,total);
		var fill=null;
		var $bar=$t.find('.bar');
		if(!op){
			fill=$('<div class="fill"></div>');
			$bar.append(fill)
			$bar.append('<div class="count">'+num+'/'+total+'</div>');
			$bar.append('<div class="per">'+per+'%</>');
		}
		else if(op=='refresh'){
			fill=$t.find('.fill');
			$bar.find('.count').text(num+'/'+total);
			$bar.find('.per').text(per+'%');
		}
		
		if(per>0)fill.css('width',per+"%");
		else fill.css('width',"1px");
	}).find('.selected').removeClass('selected');
	this.find('>li[data-id="'+sel+'"]').addClass('selected')
};
})();