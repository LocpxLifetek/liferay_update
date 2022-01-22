//font-zoom 1.0 (c) NKChinh - tandan.com.vn
//*****************************************
(function($,d){
$.fn.zoomIn=function(){
	if(this.hasClass('zoom-09')){this.removeClass('zoom-09');}
	else if(this.hasClass('zoom-11')){
		this.removeClass('zoom-11');
		this.addClass('zoom-12');
	}
	else if(this.hasClass('zoom-12')){
		this.removeClass('zoom-12');
		this.addClass('zoom-13');
	}
	else if(!this.hasClass('zoom-13')){
		this.addClass('zoom-11');
	}
}
$.fn.zoomOut=function(){
	if(this.hasClass('zoom-13')){this.removeClass('zoom-13');this.addClass('zoom-12');}
	else if(this.hasClass('zoom-12')){
		this.removeClass('zoom-12');
		this.addClass('zoom-11');
	}
	else if(this.hasClass('zoom-11')){
		this.removeClass('zoom-11');
	}
	else if(!this.hasClass('zoom-09')){
		this.addClass('zoom-09');
	}
}
$.fn.noZoom=function(){
	this.removeClass('zoom-09').removeClass('zoom-11').removeClass('zoom-12').removeClass('zoom-13');
}
$.fn.zoomButton=function(){
	var $t=this;
	this.click(function(){
		var obj=$($t.data('obj'));
		var z=$t.data('zoom');
		if(z=='out')obj.zoomOut();
		else if(z=='in') obj.zoomIn();
		else obj.noZoom();
	});
}
$(d).ready(function(){
	$('.btn-zoom').each(function(){$(this).zoomButton()});
});
})(jQuery,document)