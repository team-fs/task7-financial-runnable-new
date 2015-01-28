<jsp:include page="template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

		
		
		<h2> Account Information </h2>
            <table class="table table-striped ">
				
				<tbody>
					<tr>
						<td>
							Name:
						</td>
						<td>
							${customer.firstname},   ${customer.lastname}
						</td>
						
					</tr>
					<tr>
						<td>
							Address:
						</td>
						<td>
							${customer.addrL1}  
						</td>
						
					</tr>
					<tr>
						<td>
							Address (line 2):
						</td>
						<td>
							${customer.addrL2}  
						</td>
						
					</tr>
					<tr>
						<td>
							City
						</td>
						<td>
							${customer.city}  
						</td>
						
					</tr>
					<tr>
						<td>
							State
						</td>
						<td>
							${customer.state}  
						</td>
						
					</tr>
					<tr>
						<td>
							Zipcode
						</td>
						<td>
							${customer.zip}  
						</td>
						
					</tr>
					
					<tr>
						<td>
							Cash Balance:
						</td>
						<td>
							$<fmt:formatNumber value="${customer.cash }" type="currency" pattern="#,##0.00" />
						</td>
						
					</tr>
				
				</tbody>
			</table>

          <div class="tab-pane" id="panel-305422">
			<table class="table">
				<thead>
					<tr align="right">
						<th>
							#
						</th>
						<th>
							Fund ID
						</th>
						<th>
							Number of shares
						</th>
						<th>
							Position Value ($)
						</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="count" value="0" />
				<c:forEach var="position" items="${positions}" >
				<c:set var="price" value="${ priceList }" />
				
				<c:set var="count" value="${count+1 }" />
					<tr align="right">
						<td>
							${count}
						</td>
						<td>
							${position.fund_id }
						</td>
						<td >
							<fmt:formatNumber value="${position.shares }" type="currency" pattern="#,###.000" />			
						</td >
						<td >
						    <fmt:formatNumber value="${price[count-1]}" type="currency" pattern="#,###.00" />
						</td>
					</tr>
				</c:forEach>			
				</tbody>
			</table>
			</div>
	

<jsp:include page="template-bottom.jsp" />
