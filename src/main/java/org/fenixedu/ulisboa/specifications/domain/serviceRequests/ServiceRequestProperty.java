package org.fenixedu.ulisboa.specifications.domain.serviceRequests;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.StudentCurricularPlan;
import org.fenixedu.academic.domain.degreeStructure.CycleType;
import org.fenixedu.academic.domain.degreeStructure.ProgramConclusion;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.domain.serviceRequests.documentRequests.DocumentPurposeTypeInstance;
import org.fenixedu.academic.domain.student.curriculum.ICurriculumEntry;
import org.fenixedu.academic.domain.studentCurriculum.CurriculumLine;
import org.fenixedu.academic.domain.studentCurriculum.ExternalEnrolment;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.commons.i18n.LocalizedString;
import org.fenixedu.treasury.domain.exceptions.TreasuryDomainException;
import org.joda.time.DateTime;

import pt.ist.fenixframework.Atomic;

public class ServiceRequestProperty extends ServiceRequestProperty_Base {

    protected ServiceRequestProperty() {
        super();
        setBennu(Bennu.getInstance());
    }

    protected ServiceRequestProperty(ServiceRequestSlot serviceRequestSlot) {
        this();
        setServiceRequestSlot(serviceRequestSlot);
        checkRules();
    }

    private void checkRules() {

        if (getServiceRequestSlot() == null) {
            throw new DomainException("error.ServiceRequestProperty.serviceRequestSlot.required");
        }
    }

    @Override
    protected void checkForDeletionBlockers(Collection<String> blockers) {
        super.checkForDeletionBlockers(blockers);
    }

    @Atomic
    public void delete() {
        TreasuryDomainException.throwWhenDeleteBlocked(getDeletionBlockers());

        setULisboaServiceRequest(null);
        setDocumentPurposeTypeInstance(null);
        setExecutionYear(null);
        setServiceRequestSlot(null);
        super.getCurriculumLinesSet().clear();
        super.getExternalEnrolmentsSet().clear();

        setBennu(null);
        deleteDomainObject();
    }

    public Set<ICurriculumEntry> getICurriculumEntriesSet() {
        Stream<ICurriculumEntry> curriculumEntries = super.getCurriculumLinesSet().stream().map(ICurriculumEntry.class::cast);
        Stream<ICurriculumEntry> externalEntries = super.getExternalEnrolmentsSet().stream().map(ICurriculumEntry.class::cast);
        return Stream.concat(curriculumEntries, externalEntries).collect(Collectors.toSet());
    }

    public static Stream<ServiceRequestProperty> findAll() {
        return Bennu.getInstance().getServiceRequestPropertiesSet().stream();
    }

    public static Stream<ServiceRequestProperty> findByCode(String code) {
        return ServiceRequestProperty.findAll().filter(prop -> prop.getServiceRequestSlot().getCode().equals(code));
    }

    @Atomic
    public static ServiceRequestProperty create(ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForLocale(Locale value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setLocale(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForBoolean(Boolean value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setBooleanValue(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForCycleType(CycleType value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setCycleType(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForInteger(Integer value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setInteger(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForString(String value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setString(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForLocalizedString(LocalizedString value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setLocalizedString(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForDateTime(DateTime value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setDateTime(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForExecutionYear(ExecutionYear value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setExecutionYear(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForDocumentPurposeTypeInstance(DocumentPurposeTypeInstance value,
            ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setDocumentPurposeTypeInstance(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForProgramConclusion(ProgramConclusion value, ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setProgramConclusion(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForCurricularPlan(StudentCurricularPlan value,
            ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        serviceRequestProperty.setStudentCurricularPlan(value);
        return serviceRequestProperty;
    }

    @Atomic
    public static ServiceRequestProperty createForICurriculumEntry(Collection<ICurriculumEntry> collection,
            ServiceRequestSlot serviceRequestSlot) {
        ServiceRequestProperty serviceRequestProperty = new ServiceRequestProperty(serviceRequestSlot);
        for (ICurriculumEntry entry : collection) {
            if (entry instanceof CurriculumLine) {
                serviceRequestProperty.addCurriculumLines((CurriculumLine) entry);
            } else if (entry instanceof ExternalEnrolment) {
                serviceRequestProperty.addExternalEnrolments((ExternalEnrolment) entry);
            } else {
                throw new DomainException("error.ServiceRequestProperty.curriculumEntry.not.supported");
            }
        }
        return serviceRequestProperty;
    }
}