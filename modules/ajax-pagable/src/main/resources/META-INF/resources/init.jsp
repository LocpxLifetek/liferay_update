<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<script type= "text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>


<liferay-theme:defineObjects />

<portlet:defineObjects />

		<div class="news-left">
			
				<div class="hotNews">
				
					<div>
						<a class="tinmoi">${blogEntry.title}</a>
					</div>
				</div>
		
		</div>


<p onclick="callServeResource2()">This is resourceURL callServeResource2</p>

<script type="text/javascript">
function callServeResource2(){
    AUI().use('aui-io-request', function(A){
        A.io.request('${resourceUrl1}', {
               method: 'post',
               data: {
                   <portlet:namespace />param2: 'value2',
               },
               on: {
                   success: function() {
                    alert(this.get('responseData'));
                   }
              }
        });
     });
}
</script>