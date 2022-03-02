package goverment.url;

public class UrlCurrentPorlet {
	public String urlCurrentPorlet(String urlCurrent,String layout,String baseUrl) {
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
		String urlCurrrents=urlSite;
		if(urlSite.contains("group") ==true) {
			urlCurrrents=urlSite.replace("group", "web");
		}
		return urlCurrrents;
	}
}
