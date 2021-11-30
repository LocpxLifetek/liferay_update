package com.cp_contentChinhphu.portlet;

import com.cp_contentChinhphu.constants.cp_contentChinhphuPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_contentChinhphu",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_contentChinhphuPortletKeys.CP_CONTENTCHINHPHU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_contentChinhphuPortlet extends MVCPortlet {
	private final String linkCpvndn = "http://portal.lifetek.vn/web/lifetek/chinhphu/chinhphu/chinhphuduongnhiem";
	private final String linkCnnv = "http://portal.lifetek.vn/web/lifetek/chinhphu/chucnangnhiemvu";
	private final String linkTcphln = "http://portal.lifetek.vn/web/lifetek/chinhphu/tochucphoihopliennganh";
	private final String linkBn = "http://portal.lifetek.vn/web/lifetek/chinhphu/bonganh";
	private final String linkTtp = "http://portal.lifetek.vn/web/lifetek/chinhphu/cactinhvathanhpho";
	
	private final String linkCstt = "http://portal.lifetek.vn/web/lifetek/chinhphu/chinhsachthanhtuu";
	private final String linkTvcpqctk = "http://portal.lifetek.vn/web/lifetek/chinhphu/thanhvienchinhphuquacacthoiky";
	private final String linkTsctvcp = "http://portal.lifetek.vn/web/lifetek/chinhphu/tieusucacthanhvienchinhphu";
	private final String linkBhkn = "http://portal.lifetek.vn/web/lifetek/chinhphu/baihockinhnghiem";
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			// id lan luot CHÃ�NH PHá»¦ Ä�Æ¯Æ NG NHIá»†M, CHÃ�NH PHá»¦ VIá»†T NAM QUA CÃ�C THá»œI Ká»², NGHá»Š QUYáº¾T PHIÃŠN Há»ŒP CHÃ�NH PHá»¦, Sá»� LIá»†U NGÃ‚N SÃ�CH NHÃ€ NÆ¯á»šC
			List<Integer> assIds = Arrays.asList(195814, 195817, 195820, 195823);
			for (int i = 0; i < assIds.size(); i++) {
				AssetCategory assetC = AssetCategoryLocalServiceUtil.getAssetCategory(assIds.get(i));
				renderRequest.setAttribute("CategoryName" + (i +1), assetC.getName());
			}
			// set link trong file view
			renderRequest.setAttribute("linkCpvndn", linkCpvndn);
			renderRequest.setAttribute("linkCnnv", linkCnnv);
			renderRequest.setAttribute("linkTcphln", linkTcphln);
			renderRequest.setAttribute("linkBn", linkBn);
			renderRequest.setAttribute("linkTtp", linkTtp);
			
			renderRequest.setAttribute("linkCstt", linkCstt);
			renderRequest.setAttribute("linkTvcpqctk", linkTvcpqctk);
			renderRequest.setAttribute("linkTsctvcp", linkTsctvcp);
			renderRequest.setAttribute("linkBhkn", linkBhkn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}