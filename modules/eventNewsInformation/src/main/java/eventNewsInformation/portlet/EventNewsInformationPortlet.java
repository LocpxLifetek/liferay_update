package eventNewsInformation.portlet;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import eventNewsInformation.constants.EventNewsInformationPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EventNewsInformation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EventNewsInformationPortletKeys.EVENTNEWSINFORMATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EventNewsInformationPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(108693);
			DynamicQuery dynamicQueryCategory = DynamicQueryFactoryUtil.forClass(AssetCategory.class);
			long categoryId = assetCategory.getCategoryId();
			Property parentCategoryProperty = PropertyFactoryUtil.forName("parentCategoryId");
			dynamicQueryCategory.add(parentCategoryProperty.eq(categoryId));
			List<AssetCategory> listAssetCategory = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQueryCategory);
			List<BlogsEntry> listBlogsEntriesDirectOperator = new ArrayList<>();
			List<DLFileEntry> listDlFileEntryDirectOperator = new ArrayList<>();
			List<BlogsEntry> listBlogsEntriesForeignInformation = new ArrayList<>();
			List<BlogsEntry> listBlogsEntriesSecurityNews = new ArrayList<>();
			List<BlogsEntry> listBlogsEntriesGoodPeople = new ArrayList<>();
			List<BlogsEntry> listBlogsEntriesPoliceActivities = new ArrayList<>();
			List<DLFileEntry> listDlFileEntryPoliceActivities = new ArrayList<>();
			List<BlogsEntry> listBlogsEntriesSocialActivities= new ArrayList<>();
			List<DLFileEntry> listDLFileEntrySocialActivities=new ArrayList<>();
			for (AssetCategory assetCategory2 : listAssetCategory) {
				System.out.println("assetCategory: " + assetCategory2.getName());
				if (assetCategory2.getCategoryId() == 108700) {
					renderRequest.setAttribute("category", assetCategory2);
					int i = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if (blogsEntry.getStatus() == 0) {

								i++;
								if (i == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImageDirectOperator", dlFileEntry);
									}

									renderRequest.setAttribute("directOperator", blogsEntry);
								} else if (i > 1 && i <= 5) {

									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										listDlFileEntryDirectOperator.add(dlFileEntry);
									}
									listBlogsEntriesDirectOperator.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				} else if (assetCategory2.getCategoryId() == 108703) {
					renderRequest.setAttribute("category1", assetCategory2);
					int j = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if (blogsEntry.getStatus() == 0) {

								j++;
								if (j == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImageForeignInformation", dlFileEntry);
									}
									renderRequest.setAttribute("foreignInformation", blogsEntry);
								} else if (j > 1 && j <= 5) {

									listBlogsEntriesForeignInformation.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				} else if (assetCategory2.getCategoryId() == 113401) {
					renderRequest.setAttribute("category2", assetCategory2);
					int j = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if (blogsEntry.getStatus() == 0) {

								j++;
								if (j == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImageSecurityNews", dlFileEntry);
									}
									renderRequest.setAttribute("securityNews", blogsEntry);
								} else if (j > 1 && j <= 5) {

									listBlogsEntriesSecurityNews.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				} else if (assetCategory2.getCategoryId() == 113404) {
					renderRequest.setAttribute("category3", assetCategory2);
					int j = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if (blogsEntry.getStatus() == 0) {

								j++;
								if (j == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImageGoodPeople", dlFileEntry);
									}
									renderRequest.setAttribute("goodPeople", blogsEntry);
								} else if (j > 1 && j <= 5) {

									listBlogsEntriesGoodPeople.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				} else if (assetCategory2.getCategoryId() == 113410) {
					renderRequest.setAttribute("category4", assetCategory2);
					int j = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if(blogsEntry.getStatus() ==0) {
								j++;
								if (j == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImagePoliceActivities", dlFileEntry);
									}
									renderRequest.setAttribute("policeActivities", blogsEntry);
								} else if (j > 1 && j <= 5) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										listDlFileEntryPoliceActivities.add(dlFileEntry);
									}
									listBlogsEntriesPoliceActivities.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				}
				else if (assetCategory2.getCategoryId() == 113407) {
					renderRequest.setAttribute("category5", assetCategory2);
					int j = 0;
					DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
							.forClass(AssetEntryAssetCategoryRel.class);
					long assetCategoryId = assetCategory2.getCategoryId();
					Property assetCategoryAndEntryProperty = PropertyFactoryUtil.forName("assetCategoryId");
					queryAssetentryAssetCategory.add(assetCategoryAndEntryProperty.eq(assetCategoryId));
					queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
							.dynamicQuery(queryAssetentryAssetCategory);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil
								.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if (assetEntry.getClassNameId() == 31201) {
							BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if(blogsEntry.getStatus() ==0) {
								j++;
								if (j == 1) {
									if (blogsEntry.getSmallImageFileEntryId() > 0) {
										DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
												.getFileEntry(blogsEntry.getSmallImageFileEntryId());
										renderRequest.setAttribute("smallImageSocialActivities", dlFileEntry);
									}
									renderRequest.setAttribute("socialActivities", blogsEntry);
								} else if (j > 1 && j <= 5) {
									
									listBlogsEntriesSocialActivities.add(blogsEntry);
								} else {
									break;
								}
							}
						}
					}
				}
			}
			// chá»‰ Ä‘áº¡o Ä‘iá»�u hÃ nh
			renderRequest.setAttribute("listBlogsEntriesDirectOperator", listBlogsEntriesDirectOperator);
			renderRequest.setAttribute("listDlFileEntryDirectOperator", listDlFileEntryDirectOperator);

			// thÃ´ng tin Ä‘á»‘i ngoáº¡i
			renderRequest.setAttribute("listBlogsEntriesForeignInformation", listBlogsEntriesForeignInformation);

			// tin an ninh tráº­t tá»±
			renderRequest.setAttribute("listBlogsEntriesSecurityNews", listBlogsEntriesSecurityNews);

			// ngÆ°á»�i tá»‘t
			renderRequest.setAttribute("listBlogsEntriesGoodPeople", listBlogsEntriesGoodPeople);

			// hoáº¡t Ä‘á»™ng chÃ­nh phá»§
			renderRequest.setAttribute("listBlogsEntriesPoliceActivities", listBlogsEntriesPoliceActivities);
			renderRequest.setAttribute("listDlFileEntryPoliceActivities", listDlFileEntryPoliceActivities);
			
			//hoat động xã hội
			renderRequest.setAttribute("listBlogsEntriesSocialActivities", listBlogsEntriesSocialActivities);
			super.doView(renderRequest, renderResponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}