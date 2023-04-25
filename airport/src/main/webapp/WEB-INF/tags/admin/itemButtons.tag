<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="onCancelHref" required="true" rtexprvalue="true" type="java.lang.String"%>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="OK">
    				<input type="button" value="Cancel" onclick="location.href='${onCancelHref}'">
    			</td>
    		</tr>
