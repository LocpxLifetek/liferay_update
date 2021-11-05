package ajax.pagable.portlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

public class SubmitForm extends MVCPortlet{
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
 
		// Retrieving the submited data using ParamUtil.
		double firstInput = ParamUtil.getDouble(resourceRequest, "firstInput");
		double secondInput = ParamUtil.getDouble(resourceRequest, "secondInput");
		
		// Calculating the sum.
		double sum = firstInput + secondInput;
		
		// Creating a JSON object which will contain the sum.
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		jsonResponse.put("result", sum);
 
		// Writing the result in resourceResponse writer.
		PrintWriter writer = resourceResponse.getWriter();
		writer.println(jsonResponse);
	}
}
