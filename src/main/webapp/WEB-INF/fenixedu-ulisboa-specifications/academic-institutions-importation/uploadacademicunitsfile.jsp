<%@page import="org.fenixedu.ulisboa.specifications.ui.legal.academicinstitutions.importation.AcademicInstitutionsImportationController"%>
<%@page import="org.fenixedu.academic.domain.Country"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<spring:url var="datatablesUrl" value="/javaScript/dataTables/media/js/jquery.dataTables.latest.min.js"/>
<spring:url var="datatablesBootstrapJsUrl" value="/javaScript/dataTables/media/js/jquery.dataTables.bootstrap.min.js"></spring:url>
<script type="text/javascript" src="${datatablesUrl}"></script>
<script type="text/javascript" src="${datatablesBootstrapJsUrl}"></script>
<spring:url var="datatablesCssUrl" value="/CSS/dataTables/dataTables.bootstrap.min.css"/>

<link rel="stylesheet" href="${datatablesCssUrl}"/>
<spring:url var="datatablesI18NUrl" value="/javaScript/dataTables/media/i18n/${portal.locale.language}.json"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/dataTables/dataTables.bootstrap.min.css"/>

${portal.toolkit()}

<link href="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/css/dataTables.responsive.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/js/dataTables.responsive.js"></script>
<link href="${pageContext.request.contextPath}/webjars/datatables-tools/2.2.4/css/dataTables.tableTools.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/webjars/datatables-tools/2.2.4/js/dataTables.tableTools.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootbox/4.4.0/bootbox.js" ></script>
<script src="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/js/omnis.js"></script>

<%-- TITLE --%>
<div class="page-header">
	<h1><spring:message code="label.AcademicInstitutionsImportationController.upload.academic.units.title" />
		<small></small>
	</h1>
</div>

<%-- NAVIGATION --%>
<div class="well well-sm" style="display:inline-block">

	<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&nbsp;
	<a class="" href="${pageContext.request.contextPath}/<%= AcademicInstitutionsImportationController.VIEW_ACADEMIC_UNITS_URL %>">
		<spring:message code="label.back"/>
	</a>

</div>

<c:if test="${not empty infoMessages}">
	<div class="alert alert-info" role="alert">

		<c:forEach items="${infoMessages}" var="message">
			<p>
				<span class="glyphicon glyphicon glyphicon-ok-sign"
					aria-hidden="true">&nbsp;</span> ${message}
			</p>
		</c:forEach>

	</div>
</c:if>
<c:if test="${not empty warningMessages}">
	<div class="alert alert-warning" role="alert">

		<c:forEach items="${warningMessages}" var="message">
			<p>
				<span class="glyphicon glyphicon-exclamation-sign"${pageContext.request.contextPath}
					aria-hidden="true">&nbsp;</span> ${message}
			</p>
		</c:forEach>

	</div>
</c:if>
<c:if test="${not empty errorMessages}">
	<div class="alert alert-danger" role="alert">

		<c:forEach items="${errorMessages}" var="message">
			<p>
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true">&nbsp;</span> ${message}
			</p>
		</c:forEach>

	</div>
</c:if>


<script type="text/javascript">
      function processUpload(externalId) {
        $('#uploadModal').modal('toggle')
      }
</script>

<div>
	<form action="${pageContext.request.contextPath}/<%= AcademicInstitutionsImportationController.UPDATE_ACADEMIC_UNITS_URL %>" 
		method="post">
		
		<input type="hidden" name="bean" value="${jsonOfficialAcademicUnitsBeans}" />
		
		<button type="submit" class="btn btn-default btn-xs" value=""><spring:message code="label.event.update" /></button>
	</form>
</div>

<h2><spring:message code="label.AcademicInstitutionsImportationController.official.academic.units" /></h2>

<table class="table" id="officialAcademicUnitsTable" >
	<thead>
		<tr>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.official.code" /></th>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.academicUnit.name" /></th>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.degreeDesignations.number" /></th>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.academic.unit.registered" /></th>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.academic.unit.name.updatable" /></th>
			<th scope="row" class="col-xs-3"><spring:message code="label.AcademicInstitutionsImportationController.degree.designations.number.different" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="unit" items="${officialAcademicUnits}">
			<tr>
				<td><c:out value="${unit.code}" /></td>
				<td>
					<p><strong><c:out value="${unit.name}" /></strong></p>
					<p><em><c:out value="${unit.existingAcademicUnit.nameI18n.content}" /></em></p>
				</td>
				<td>
					<p><strong><c:out value="${unit.degreeDesignationsBeanList.size()}" /></strong></p>
					<p><em><c:out value="${unit.existingAcademicUnit.degreeDesignationSet.size()}" /></em></p>
				</td>
				<td>
					<spring:message code='${unit.academicUnitRegistered ? "label.true" : "label.false"}' />
				</td>
				<td>
					<spring:message code='${unit.nameDifferent ? "label.true" : "label.false"}' />
				</td>
				<td>
					<spring:message code='${unit.degreeDesignationsSizeDifferent ? "label.true" : "label.false"}' />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>

$(document).ready(function() {
	function dataTables(tableid, showsearchbox, showtools,pagination, pagecontext,i18nurl, columnDefs) {
		var dom = "";
		if (showsearchbox == true && showtools == true) {
			dom = '<"col-sm-6"l><"col-sm-3"f><"col-sm-3"T>rtip'; //FilterBox = YES && ExportOptions = YES
		} else if (showsearchbox == true && showtools == false) {
			dom = '<"col-sm-6"l><"col-sm-6"f>rtip'; // FilterBox = YES && ExportOptions = NO
		} else if (showsearchbox == false && showtools == true) {
			dom = 'T<"clear">lrtip'; // FilterBox = NO && ExportOptions = YES
		} else {
			dom = '<"col-sm-6"l>rtip'; // FilterBox = NO && ExportOptions = NO
		}
		var table = $('#'+tableid)
				.DataTable({language : {
					url : i18nurl,			
				},
				"bDeferRender" : true,
				"bPaginate" : pagination,
				"dom" : dom, 
				"tableTools" : {
					"sSwfPath" : pagecontext + "/webjars/datatables-tools/2.2.4/swf/copy_csv_xls_pdf.swf"
				},
				"columnDefs": columnDefs
		});
		
		table.columns.adjust().draw();
		
		$('#' + tableid +' tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
		});
	}
	
	dataTables(
			"officialAcademicUnitsTable",
			true,
			true,
			true,
			"${pageContext.request.contextPath}",
			"${datatablesI18NUrl}",
			[ { "width": "5%", "targets": 0 }, { "width": "5%", "targets": 2 } ]);

});

</script>