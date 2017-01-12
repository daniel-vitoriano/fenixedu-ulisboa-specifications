<%@page import="org.fenixedu.ulisboa.specifications.ui.degrees.extendeddegreeinfo.CourseGroupDegreeInfoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<spring:url var="datatablesUrl" value="/javaScript/dataTables/media/js/jquery.dataTables.latest.min.js" />
<spring:url var="datatablesBootstrapJsUrl" value="/javaScript/dataTables/media/js/jquery.dataTables.bootstrap.min.js"></spring:url>
<script type="text/javascript" src="${datatablesUrl}"></script>
<script type="text/javascript" src="${datatablesBootstrapJsUrl}"></script>
<spring:url var="datatablesCssUrl" value="/CSS/dataTables/dataTables.bootstrap.min.css" />

<link rel="stylesheet" href="${datatablesCssUrl}" />
<spring:url var="datatablesI18NUrl" value="/javaScript/dataTables/media/i18n/${portal.locale.language}.json" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/dataTables/dataTables.bootstrap.min.css" />

<!-- Choose ONLY ONE:  bennuToolkit OR bennuAngularToolkit -->
${portal.angularToolkit()}
<%--${portal.toolkit()}--%>

<link href="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/css/dataTables.responsive.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/js/dataTables.responsive.js"></script>
<link href="${pageContext.request.contextPath}/webjars/datatables-tools/2.2.4/css/dataTables.tableTools.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/webjars/datatables-tools/2.2.4/js/dataTables.tableTools.js"></script>
<link href="${pageContext.request.contextPath}/webjars/select2/4.0.0-rc.2/dist/css/select2.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/webjars/select2/4.0.0-rc.2/dist/js/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootbox/4.4.0/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/static/fenixedu-ulisboa-specifications/js/omnis.js"></script>

<script src="${pageContext.request.contextPath}/webjars/angular-sanitize/1.3.11/angular-sanitize.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/angular-ui-select/0.11.2/select.min.css" />
<script src="${pageContext.request.contextPath}/webjars/angular-ui-select/0.11.2/select.min.js"></script>

<%-- TITLE --%>
<div class="page-header">
    <h1>
        <spring:message code="label.manageDegreeDocumentInfo.update" />
        <small></small>
    </h1>
</div>

<%-- NAVIGATION --%>
<div class="well well-sm" style="display: inline-block">
    <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
    &nbsp;
    <a class="" href="${pageContext.request.contextPath}<%= CourseGroupDegreeInfoController.READ_URL %>/${degreeDocumentInfo.externalId}">
        <spring:message code="label.event.back" />
    </a> 
</div>

<c:if test="${not empty infoMessages}">
    <div class="alert alert-info" role="alert">
        <c:forEach items="${infoMessages}" var="message">
            <p>
                <span class="glyphicon glyphicon glyphicon-ok-sign" aria-hidden="true">&nbsp;</span> ${message}
            </p>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty warningMessages}">
    <div class="alert alert-warning" role="alert">
        <c:forEach items="${warningMessages}" var="message">
            <p>
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span> ${message}
            </p>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty errorMessages}">
    <div class="alert alert-danger" role="alert">
        <c:forEach items="${errorMessages}" var="message">
            <p>
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span> ${message}
            </p>
        </c:forEach>
    </div>
</c:if>

<script>

angular.module('angularApp', ['ngSanitize', 'ui.select', 'bennuToolkit']).controller('angularController', ['$scope', function($scope) {

    $scope.object= ${degreeDocumentInfoBeanJson};
    $scope.postBack = createAngularPostbackFunction($scope);
    
    $scope.booleanvalues = [
            { name : '<spring:message code="label.no"/>', value : false },
            { name : '<spring:message code="label.yes"/>', value : true } 
    ];
    
}]);
</script>

<form name='form' method="post" class="form-horizontal" ng-app="angularApp" ng-controller="angularController"
    action="${pageContext.request.contextPath}<%= CourseGroupDegreeInfoController.UPDATE_URL %>/${ degreeDocumentInfo.externalId }">

    <input name="postback" type="hidden" value="${pageContext.request.contextPath}<%= CourseGroupDegreeInfoController.CREATE_POSTBACK_URL %>" />
    <input name="bean" type="hidden" value="{{ object }}" />
    
    <div class="panel panel-default">
        <div class="panel-body">        
            <div class="form-group row">
                <div class="col-sm-2 control-label">
                    <spring:message code="label.CourseGroupDegreeInfo.executionYear" />
                </div>

                <div class="col-sm-10">
                    <input id="degreeDocumentInfo_executionYear" readonly="true" class="form-control" type="text" value="${ degreeDocumentInfo.extendedDegreeInfo.degreeInfo.executionYear.qualifiedName }" />
<!--                     <ui-select  id="degreeDocumentInfo_extendedDegreeInfo" on-select="onExtendedDegreeInfoChange($item, $model)" name="extendedDegreeInfo" ng-model="$parent.object.extendedDegreeInfo" theme="bootstrap"> -->
<!--                         <ui-select-match >{{$select.selected.text}}</ui-select-match>  -->
<!--                         <ui-select-choices  repeat="extendedDegreeInfo.id as extendedDegreeInfo in object.extendedDegreeInfoDataSource | filter: $select.search"> -->
<!--                             <span ng-bind-html="extendedDegreeInfo.text | highlight: $select.search"></span> -->
<!--                         </ui-select-choices>  -->
<!--                     </ui-select> -->
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-2 control-label">
                    <spring:message code="label.CourseGroupDegreeInfo.degree" />
                </div>

                <div class="col-sm-10">
                    <input id="degreeDocumentInfo_degree" readonly="true" class="form-control" type="text" value="${ degreeDocumentInfo.extendedDegreeInfo.degreeInfo.name.content }" />
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-2 control-label">
                    <spring:message code="label.CourseGroupDegreeInfo.degreeCurricularPlan" />
                </div>

                <div class="col-sm-10">
                    <input id="degreeDocumentInfo_degreeCurricularPlan" readonly="true" class="form-control" type="text" value="${ degreeDocumentInfo.courseGroup.parentDegreeCurricularPlan.presentationName }" />
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-2 control-label">
                    <spring:message code="label.CourseGroupDegreeInfo.courseGroup" />
                </div>

                <div class="col-sm-10">
                    <ui-select id="degreeDocumentInfo_courseGroup" name=""
                        ng-model="$parent.object.courseGroup"
                        theme="bootstrap"> 
                        <ui-select-match >
                            {{$select.selected.text}}
                        </ui-select-match> 
                        <ui-select-choices repeat="element.id as element in object.courseGroupDataSource | filter: $select.search">
                            <span ng-bind-html="element.text | highlight: $select.search"></span>
                        </ui-select-choices>
                    </ui-select>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-2 control-label">
                    <spring:message code="label.DegreeDocumentInfo.degreeName" />
                </div>

                <div class="col-sm-10">
                    <input id="degreeDocumentInfo_degreeName"
                        class="form-control" type="text" 
                        ng-localized-string="object.name" />
                </div>
            </div>
        </div>
        <div class="panel-footer">
            <input type="submit" class="btn btn-primary" role="button" value="<spring:message code="label.submit" />" />
        </div>
    </div>
</form>