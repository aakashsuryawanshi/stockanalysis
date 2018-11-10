<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/resources" var="baseFileUrl" />

<script type="text/javascript" src="${baseFileUrl}/vendors/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${baseFileUrl}/vendors/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="${baseFileUrl}/vendors/angular/angular.min.js"></script>
<script type="text/javascript" src="${baseFileUrl}/vendors/angular/angular-route.min.js"></script>

<script type="text/javascript" src="${baseFileUrl}/js/app.js"></script>
<script type="text/javascript" src="${baseFileUrl}/js/restAPI.js"></script>
<script type="text/javascript" src="${baseFileUrl}/js/controllers/HomeController.js"></script>
