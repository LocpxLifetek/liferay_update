package goverment.url;

import org.osgi.service.component.annotations.Component;


public class UrlCurrentPorlet {
	public String urlCurrentPorlet(String urlCurrent,String layout) {
//		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//		Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
		String[] url=urlCurrent.split(layout);
		String urlSite=null;
		int j=0;
		for (String string : url) {
			j++;
			if(j==1) {
				urlSite=string;
			}
		}
		return urlSite;
	}
}
