<%--

    Copyright © 2002 Instituto Superior Técnico

    This file is part of FenixEdu Academic.

    FenixEdu Academic is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu Academic is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu Academic.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@page import="org.fenixedu.academic.domain.person.IDDocumentType"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>
<%@ page isELIgnored="true"%>

<html:xhtml/>

<bean:define id="person" name="personBean" property="person" />

<h2><bean:message key="link.student.viewPersonalData" bundle="ACADEMIC_OFFICE_RESOURCES"/></h2>

<p class="mtop2">
    <html:link page="/student.do?method=visualizeStudent" paramId="studentID" paramName="student" paramProperty="externalId">
        <bean:message key="link.student.back" bundle="ACADEMIC_OFFICE_RESOURCES"/>
    </html:link>
</p>

<h3 class="mbottom025"><bean:message key="label.person.title.personal.info" bundle="ACADEMIC_OFFICE_RESOURCES" /></h3>
<fr:view name="personBean" schema="student.personalData-withoutProfessionDetails" >
    <fr:layout name="tabular" >
        <fr:property name="classes" value="tstyle1 thright thlight mtop0"/>
        <fr:property name="columnClasses" value="width14em,"/>
    </fr:layout>
</fr:view>

<bean:define id="personBean" name="personBean" type="org.fenixedu.academic.dto.person.PersonBean" />

<h3 class="mbottom025"><bean:message key="label.identification" bundle="ACADEMIC_OFFICE_RESOURCES" /></h3>
<fr:view name="personBean" >
    <fr:schema type="org.fenixedu.academic.dto.person.PersonBean" bundle="ACADEMIC_OFFICE_RESOURCES" > 
        <fr:slot name="idDocumentType" key="label.idDocumentType" />
        <fr:slot name="documentIdNumber" key="label.identificationNumber"  />
        <% if(personBean.getIdDocumentType() == IDDocumentType.IDENTITY_CARD) { %>
        <fr:slot name="identificationDocumentSeriesNumber" key="label.PersonBean.identificationDocumentSeriesNumber" />
        <% } %>
        <fr:slot name="documentIdEmissionLocation" />
        <fr:slot name="documentIdEmissionDate" />
        <fr:slot name="documentIdExpirationDate" />
    </fr:schema>

    <fr:layout name="tabular" >
        <fr:property name="classes" value="tstyle1 thlight thright mtop025"/>
        <fr:property name="columnClasses" value="width14em,,tdclear tderror1"/>
    </fr:layout>
</fr:view>
    
<h3 class="mbottom025"><bean:message key="label.person.title.filiation" bundle="ACADEMIC_OFFICE_RESOURCES" /></h3>
<fr:view name="personBean" schema="student.filiation-edit" >
    <fr:layout name="tabular" >
        <fr:property name="classes" value="tstyle1 thright thlight mtop0"/>
        <fr:property name="columnClasses" value="width14em,"/>
    </fr:layout>
</fr:view>


<h3 class="mbottom025"><bean:message key="label.person.title.fiscalInformation" /></h3>
<fr:view name="person">
	<fr:schema type="org.fenixedu.academic.domain.Person" bundle="ACADEMIC_OFFICE_RESOURCES" >
	
		<logic:notEmpty name="person" property="fiscalAddress">
			<fr:slot name="this" layout="format" key="label.socialSecurityNumber" bundle="ACADEMIC_OFFICE_RESOURCES">
				<fr:property name="format" value="${fiscalAddress.countryOfResidence.code} ${socialSecurityNumber}" />
			</fr:slot>
		</logic:notEmpty>
		
		<logic:empty name="person" property="fiscalAddress">
			<fr:slot name="this" key="label.socialSecurityNumber" bundle="ACADEMIC_OFFICE_RESOURCES">
				<fr:property name="format" value="${socialSecurityNumber}" />
			</fr:slot>
		</logic:empty>
		
			<fr:slot name="fiscalAddress">
				<fr:property name="format" value="${uiFiscalPresentationValue}" />
			</fr:slot>
	</fr:schema>
	<fr:layout name="tabular" >
		<fr:property name="classes" value="tstyle1 thright thlight mtop0"/>
		<fr:property name="columnClasses" value="width14em,"/>
	</fr:layout>
</fr:view>

<h3 class="mbottom025"><bean:message key="label.person.title.addressesInfo" bundle="ACADEMIC_OFFICE_RESOURCES" /></h3>
<logic:notEmpty name="personBean" property="sortedPhysicalAdresses">
    <fr:view name="personBean" property="sortedPhysicalAdresses" >
    
		<fr:schema type="org.fenixedu.academic.domain.contacts.PhysicalAddress" bundle="ACADEMIC_OFFICE_RESOURCES">
			<fr:slot name="defaultContact" key="label.partyContacts.defaultContact">
				<fr:property name="trueLabel" value="label.partyContacts.view.trueLabel" />
				<fr:property name="falseLabel" value="label.partyContacts.view.falseLabel" />
				<fr:property name="bundle" value="ACADEMIC_OFFICE_RESOURCES" />
			</fr:slot>
			<fr:slot name="type" />
			<fr:slot name="activeAndValid" key="label.partyContacts.isActiveAndValid">
				<fr:property name="trueLabel" value="label.yes.capitalized"/>
				<fr:property name="falseLabel" value="label.no.capitalized"/>
				<fr:property name="bundle" value="APPLICATION_RESOURCES" />
			</fr:slot>
			<fr:slot name="fiscalAddress">
				<fr:property name="trueLabel" value="label.partyContacts.view.trueLabel" />
				<fr:property name="falseLabel" value="label.partyContacts.view.falseLabel" />
				<fr:property name="bundle" value="ACADEMIC_OFFICE_RESOURCES" />
			</fr:slot>
			<fr:slot name="address" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="area" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="areaCode" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="areaOfAreaCode" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="parishOfResidence" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="districtSubdivisionOfResidence" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="districtOfResidence" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
			<fr:slot name="countryOfResidenceName" key="label.countryOfResidence" layout="null-as-label">
				<fr:property name="label" value="-" />
			</fr:slot>
		</fr:schema>
    
        <fr:layout name="tabular" >
            <fr:property name="classes" value="tstyle1 thlight mtop05" />
        </fr:layout>
    </fr:view>
</logic:notEmpty>

<logic:empty name="personBean" property="sortedPhysicalAdresses">
    <p>
        <em><bean:message key="label.partyContacts.no.physicalAddresses" bundle="ACADEMIC_OFFICE_RESOURCES" /></em>
    </p>
</logic:empty>



<h3 class="mbottom025"><bean:message key="label.person.title.contactInfo" bundle="ACADEMIC_OFFICE_RESOURCES" /></h3>

<bean:message key="label.phones" bundle="ACADEMIC_OFFICE_RESOURCES" />
<logic:notEmpty name="personBean" property="sortedPhones">
    <fr:view name="personBean" property="sortedPhones" schema="contacts.Phone.view">
        <fr:layout name="tabular" >
            <fr:property name="classes" value="tstyle1 thlight mtop05" />
        </fr:layout>
    </fr:view>
</logic:notEmpty>
<logic:empty name="personBean" property="sortedPhones">
    <p>
        <em><bean:message key="label.partyContacts.no.phones" bundle="ACADEMIC_OFFICE_RESOURCES" /></em>
    </p>
</logic:empty>

<bean:message key="label.mobilePhones" bundle="ACADEMIC_OFFICE_RESOURCES" />
<logic:notEmpty name="personBean" property="sortedMobilePhones">
    <fr:view name="personBean" property="sortedMobilePhones" schema="contacts.MobilePhone.view">
        <fr:layout name="tabular" >
            <fr:property name="classes" value="tstyle1 thlight mtop05" />
        </fr:layout>
    </fr:view>
</logic:notEmpty>
<logic:empty name="personBean" property="sortedMobilePhones">
    <p>
        <em><bean:message key="label.partyContacts.no.mobilePhones" bundle="ACADEMIC_OFFICE_RESOURCES" /></em>
    </p>
</logic:empty>

<bean:message key="label.email" bundle="ACADEMIC_OFFICE_RESOURCES" />
<logic:notEmpty name="personBean" property="sortedEmailAddresses">
    <fr:view name="personBean" property="sortedEmailAddresses" schema="contacts.EmailAddress.view">
        <fr:layout name="tabular" >
            <fr:property name="classes" value="tstyle1 thlight mtop05" />
        </fr:layout>
    </fr:view>
</logic:notEmpty>

<logic:empty name="personBean" property="sortedEmailAddresses">
    <p>
        <em><bean:message key="label.partyContacts.no.emailAddresses" bundle="ACADEMIC_OFFICE_RESOURCES" /></em>
    </p>
</logic:empty>

<bean:message key="label.webAddresses" bundle="ACADEMIC_OFFICE_RESOURCES" />
<logic:notEmpty name="personBean" property="sortedWebAddresses">
    <fr:view name="personBean" property="sortedWebAddresses" schema="contacts.WebAddress.view">
        <fr:layout name="tabular" >
            <fr:property name="classes" value="tstyle1 thlight mtop05" />
        </fr:layout>
    </fr:view>
</logic:notEmpty>

<logic:empty name="personBean" property="sortedWebAddresses">
    <p>
        <em><bean:message key="label.partyContacts.no.webAddresses" bundle="ACADEMIC_OFFICE_RESOURCES" /></em>
    </p>
</logic:empty>

<jsp:include page="viewPersonalData-ulisboa-specifications.jsp"/>
    
<p class="mtop2">
    <html:link page="/student.do?method=visualizeStudent" paramId="studentID" paramName="student" paramProperty="externalId">
        <bean:message key="link.student.back" bundle="ACADEMIC_OFFICE_RESOURCES"/>
    </html:link>
</p>
