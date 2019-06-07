package org.fenixedu.ulisboa.specifications;

import org.fenixedu.commons.configuration.ConfigurationInvocationHandler;
import org.fenixedu.commons.configuration.ConfigurationManager;
import org.fenixedu.commons.configuration.ConfigurationProperty;

public class ULisboaConfiguration {

    @ConfigurationManager(description = "ULisboa Configuration")
    public static interface ConfigurationProperties {

        @ConfigurationProperty(key = "gradescale.typequalitative.logic.class")
        public String typeQualitativeGradeScaleLogic();

        @ConfigurationProperty(key = "domain.academic.enrolments.EnrolmentEvaluationsDependOnAcademicalActsBlocked",
                defaultValue = "true")
        public Boolean getEnrolmentsInEvaluationsDependOnAcademicalActsBlocked();

        @ConfigurationProperty(key = "domain.academic.enrolmentPredicate.override", defaultValue = "true")
        public Boolean getEnrolmentPredicateOverride();

        @ConfigurationProperty(key = "domain.academic.registrationRegimeVerifier.override", defaultValue = "true")
        public Boolean getRegistrationRegimeVerifierOverride();

        @ConfigurationProperty(key = "domain.academic.curricularYearCalculator.override", defaultValue = "true")
        public Boolean getCurricularYearCalculatorOverride();

        @ConfigurationProperty(key = "domain.academic.curricularYearCalculator.cached", defaultValue = "true")
        public Boolean getCurricularYearCalculatorCached();

        @ConfigurationProperty(key = "domain.academic.curriculumGradeCalculator.visualizer",
                defaultValue = "/academicAdminOffice/student/registration/curriculumGradeCalculator.jsp")
        public String getCurriculumGradeCalculatorVisualizer();

        @ConfigurationProperty(key = "domain.academic.curriculumGradeCalculator.override",
                defaultValue = "org.fenixedu.ulisboa.specifications.domain.student.curriculum.CurriculumGradeCalculator")
        public String getCurriculumGradeCalculator();

        @ConfigurationProperty(key = "domain.academic.curriculumGradeCalculator.rawGradeRoundingMode", defaultValue = "HALF_UP")
        public String getCurriculumGradeCalculatorRawGradeRoundingMode();

        @ConfigurationProperty(key = "domain.academic.curriculumGradeCalculator.rawGradeRoundingMode.forDegrees",
                defaultValue = "")
        public String getCurriculumGradeCalculatorRawGradeRoundingModeForDegrees();

        @ConfigurationProperty(key = "domain.academic.curricularYearConfiguration.initialize", defaultValue = "false")
        public Boolean getCurricularPeriodConfigurationInitialize();

        @ConfigurationProperty(key = "domain.academic.curricularRules.ApprovalsAwareOfCompetenceCourse", defaultValue = "true")
        public Boolean getCurricularRulesApprovalsAwareOfCompetenceCourse();

        @ConfigurationProperty(key = "domain.academic.curricularRules.ApprovalsAwareOfCompetenceCourse.studentScope",
                defaultValue = "false")
        public Boolean getCurricularRulesApprovalsAwareOfCompetenceCourseAtStudentScope();

        @ConfigurationProperty(key = "domain.academic.curriculumAggregator.firstExecutionYearName", defaultValue = "2016/2017")
        public String getCurriculumAggregatorFirstExecutionYearName();


        @ConfigurationProperty(key = "services.sas.activeStudents.enabled", defaultValue = "false")
        public Boolean getActiveStudentsServoceEnable();

        @ConfigurationProperty(key = "services.sas.activeStudents.threadNumber", defaultValue = "20")
        public Integer getActiveStudentsThreadNumber();

        @ConfigurationProperty(key = "executionCourse.more.funcs", defaultValue = "false")
        public Boolean getShowAllExecutionCourseFuncs();

        @ConfigurationProperty(key = "support.active", defaultValue = "true")
        public Boolean getSupportActive();

        @ConfigurationProperty(key = "support.accessExpression", defaultValue = "anyone")
        public String getSupportAccessControlExpression();

        @ConfigurationProperty(key = "documentation.active", defaultValue = "false")
        public Boolean getDocumentationActive();

        @ConfigurationProperty(key = "ldap.integration.useCustomGivenNames", defaultValue = "false")
        public Boolean getUseCustomGivenNames();

        @ConfigurationProperty(key = "ldap.integration.sendHashedPassword", defaultValue = "true")
        public Boolean getSendHashedPassword();

        @ConfigurationProperty(key = "domain.academic.student.enrolment.automaticSchoolClassEnrolmentMethod",
                defaultValue = "FILL_FIRST")
        public String getAutomaticSchoolClassEnrolmentMethod();
    }

    public static ConfigurationProperties getConfiguration() {
        return ConfigurationInvocationHandler.getConfiguration(ConfigurationProperties.class);
    }

}
