package org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.mobility;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.fenixedu.academic.domain.Country;
import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.academic.domain.SchoolPeriodDuration;
import org.fenixedu.bennu.TupleDataSourceBean;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.academic.domain.student.mobility.MobilityActivityType;
import org.fenixedu.academic.domain.student.mobility.MobilityProgramType;
import org.fenixedu.academic.domain.student.mobility.MobilityProgrammeLevel;
import org.fenixedu.academic.domain.student.mobility.MobilityScientificArea;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.CandidancyForm;
import org.fenixedu.ulisboa.specifications.util.ULisboaSpecificationsUtil;
import org.joda.time.LocalDate;

public class MobilityForm implements CandidancyForm {

    private static final Comparator<TupleDataSourceBean> COMPARE_BY_ID_AND_TEXT = new Comparator<TupleDataSourceBean>() {

        @Override
        public int compare(final TupleDataSourceBean o1, final TupleDataSourceBean o2) {
            if (o1.getId() == "") {
                return -1;
            } else if (o2.getId() == "") {
                return 1;
            }

            return TupleDataSourceBean.COMPARE_BY_TEXT.compare(o1, o2);
        }
    };

    protected ExecutionSemester begin;
    protected ExecutionSemester end;
    protected LocalDate beginDate;
    protected LocalDate endDate;

    protected boolean hasMobilityProgram;

    protected MobilityProgramType mobilityProgramType;
    protected MobilityActivityType mobilityActivityType;
    protected MobilityScientificArea mobilityScientificArea;

    protected SchoolPeriodDuration programDuration;

    protected MobilityProgrammeLevel originMobilityProgrammeLevel;
    protected MobilityProgrammeLevel incomingMobilityProgrammeLevel;
    protected String otherIncomingMobilityProgrammeLevel;
    protected String otherOriginMobilityProgrammeLevel;

    protected Country originCountry;
    protected Country incomingCountry;

    private List<TupleDataSourceBean> programDurationDataSource;
    private List<TupleDataSourceBean> beginDataSource;
    private List<TupleDataSourceBean> endDataSource;
    private List<TupleDataSourceBean> mobilityProgramTypeDataSource;
    private List<TupleDataSourceBean> mobilityActivityTypeDataSource;

    private List<TupleDataSourceBean> mobilityScientificAreaDataSource;
    private List<TupleDataSourceBean> mobilityProgrammeLevelDataSource;

    private List<TupleDataSourceBean> countryDataSource;

    public MobilityForm() {
        updateLists();
    }

    @Override
    public void updateLists() {
        loadDataProgramDurationDataSource();
        loadDataBeginDataSource();
        loadDataEndDataSource();
        loadDataMobilityProgramTypeDataSource();
        loadDataMobilityActivityTypeDataSource();
        loadMobilityScientificAreaDataSource();
        loadMobilityProgrammeLevelDataSource();
        loadCountryUnitDataSource();
    }

    public boolean isHasMobilityProgram() {
        return hasMobilityProgram;
    }

    public void setHasMobilityProgram(boolean hasMobilityProgram) {
        this.hasMobilityProgram = hasMobilityProgram;
    }

    private void loadMobilityProgrammeLevelDataSource() {
        final List<TupleDataSourceBean> result = Bennu.getInstance().getMobilityProgrammeLevelsSet().stream()
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getName().getContent())).collect(Collectors.toList());

        mobilityProgrammeLevelDataSource = result.stream().sorted(COMPARE_BY_ID_AND_TEXT).collect(Collectors.toList());
    }

    private void loadMobilityScientificAreaDataSource() {
        final List<TupleDataSourceBean> result = Bennu.getInstance().getMobilityScientificAreasSet().stream()
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getName().getContent())).collect(Collectors.toList());

        mobilityScientificAreaDataSource = result.stream().sorted(COMPARE_BY_ID_AND_TEXT).collect(Collectors.toList());
    }

    private void loadDataMobilityProgramTypeDataSource() {
        final List<TupleDataSourceBean> result = MobilityProgramType.findAllActive().stream()
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getName().getContent())).collect(Collectors.toList());

        mobilityProgramTypeDataSource = result.stream().sorted(COMPARE_BY_ID_AND_TEXT).collect(Collectors.toList());
    }

    private void loadDataMobilityActivityTypeDataSource() {
        final List<TupleDataSourceBean> result = MobilityActivityType.findAllActive().stream()
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getName().getContent())).collect(Collectors.toList());

        mobilityActivityTypeDataSource = result.stream().sorted(COMPARE_BY_ID_AND_TEXT).collect(Collectors.toList());
    }

    private void loadDataEndDataSource() {
        final Comparator<ExecutionSemester> comparatorBySemesterAndYear =
                Collections.reverseOrder(ExecutionSemester.COMPARATOR_BY_SEMESTER_AND_YEAR);

        final List<TupleDataSourceBean> result = ExecutionSemester.readNotClosedExecutionPeriods().stream()
                .sorted(comparatorBySemesterAndYear)
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getQualifiedName())).collect(Collectors.toList());

        endDataSource = result.stream().collect(Collectors.toList());
    }

    private void loadDataBeginDataSource() {
        final Comparator<ExecutionSemester> comparatorBySemesterAndYear =
                Collections.reverseOrder(ExecutionSemester.COMPARATOR_BY_SEMESTER_AND_YEAR);

        final List<TupleDataSourceBean> result = ExecutionSemester.readNotClosedExecutionPeriods().stream()
                .sorted(comparatorBySemesterAndYear)
                .map((cs) -> new TupleDataSourceBean(cs.getExternalId(), cs.getQualifiedName())).collect(Collectors.toList());

        beginDataSource = result.stream().collect(Collectors.toList());
    }

    private void loadDataProgramDurationDataSource() {
        final List<TupleDataSourceBean> result = Arrays.asList(SchoolPeriodDuration.values()).stream()
                .map(i -> new TupleDataSourceBean(i.name(), ULisboaSpecificationsUtil.bundle("label.SchoolPeriodDuration." + i)))
                .collect(Collectors.toList());

        programDurationDataSource = result.stream().sorted(COMPARE_BY_ID_AND_TEXT).collect(Collectors.toList());
    }

    private void loadCountryUnitDataSource() {
        final List<TupleDataSourceBean> result = Bennu.getInstance().getCountrysSet().stream().map(c -> {
            TupleDataSourceBean tuple = new TupleDataSourceBean();
            tuple.setId(c.getExternalId());
            tuple.setText(c.getLocalizedName().getContent());
            return tuple;
        }).sorted(TupleDataSourceBean.COMPARE_BY_TEXT).collect(Collectors.toList());

        countryDataSource = result;
    }

    public ExecutionSemester getBegin() {
        return begin;
    }

    public void setBegin(ExecutionSemester begin) {
        this.begin = begin;
    }

    public ExecutionSemester getEnd() {
        return end;
    }

    public void setEnd(ExecutionSemester end) {
        this.end = end;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public MobilityProgramType getMobilityProgramType() {
        return mobilityProgramType;
    }

    public void setMobilityProgramType(MobilityProgramType mobilityProgramType) {
        this.mobilityProgramType = mobilityProgramType;
    }

    public MobilityActivityType getMobilityActivityType() {
        return mobilityActivityType;
    }

    public void setMobilityActivityType(MobilityActivityType mobilityActivityType) {
        this.mobilityActivityType = mobilityActivityType;
    }

    public MobilityScientificArea getMobilityScientificArea() {
        return mobilityScientificArea;
    }

    public void setMobilityScientificArea(MobilityScientificArea mobilityScientificArea) {
        this.mobilityScientificArea = mobilityScientificArea;
    }

    public SchoolPeriodDuration getProgramDuration() {
        return programDuration;
    }

    public void setProgramDuration(SchoolPeriodDuration programDuration) {
        this.programDuration = programDuration;
    }

    public MobilityProgrammeLevel getOriginMobilityProgrammeLevel() {
        return originMobilityProgrammeLevel;
    }

    public void setOriginMobilityProgrammeLevel(MobilityProgrammeLevel originMobilityProgrammeLevel) {
        this.originMobilityProgrammeLevel = originMobilityProgrammeLevel;
    }

    public MobilityProgrammeLevel getIncomingMobilityProgrammeLevel() {
        return incomingMobilityProgrammeLevel;
    }

    public void setIncomingMobilityProgrammeLevel(MobilityProgrammeLevel incomingMobilityProgrammeLevel) {
        this.incomingMobilityProgrammeLevel = incomingMobilityProgrammeLevel;
    }

    public String getOtherIncomingMobilityProgrammeLevel() {
        return otherIncomingMobilityProgrammeLevel;
    }

    public void setOtherIncomingMobilityProgrammeLevel(String otherIncomingMobilityProgrammeLevel) {
        this.otherIncomingMobilityProgrammeLevel = otherIncomingMobilityProgrammeLevel;
    }

    public String getOtherOriginMobilityProgrammeLevel() {
        return otherOriginMobilityProgrammeLevel;
    }

    public void setOtherOriginMobilityProgrammeLevel(String otherOriginMobilityProgrammeLevel) {
        this.otherOriginMobilityProgrammeLevel = otherOriginMobilityProgrammeLevel;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public Country getIncomingCountry() {
        return incomingCountry;
    }

    public void setIncomingCountry(Country incomingCountry) {
        this.incomingCountry = incomingCountry;
    }

    public List<TupleDataSourceBean> getProgramDurationDataSource() {
        return programDurationDataSource;
    }

    public void setProgramDurationDataSource(List<TupleDataSourceBean> programDurationDataSource) {
        this.programDurationDataSource = programDurationDataSource;
    }

    public List<TupleDataSourceBean> getBeginDataSource() {
        return beginDataSource;
    }

    public void setBeginDataSource(List<TupleDataSourceBean> beginDataSource) {
        this.beginDataSource = beginDataSource;
    }

    public List<TupleDataSourceBean> getEndDataSource() {
        return endDataSource;
    }

    public void setEndDataSource(List<TupleDataSourceBean> endDataSource) {
        this.endDataSource = endDataSource;
    }

    public List<TupleDataSourceBean> getMobilityProgramTypeDataSource() {
        return mobilityProgramTypeDataSource;
    }

    public void setMobilityProgramTypeDataSource(List<TupleDataSourceBean> mobilityProgramTypeDataSource) {
        this.mobilityProgramTypeDataSource = mobilityProgramTypeDataSource;
    }

    public List<TupleDataSourceBean> getMobilityActivityTypeDataSource() {
        return mobilityActivityTypeDataSource;
    }

    public void setMobilityActivityTypeDataSource(List<TupleDataSourceBean> mobilityActivityTypeDataSource) {
        this.mobilityActivityTypeDataSource = mobilityActivityTypeDataSource;
    }

    public List<TupleDataSourceBean> getMobilityScientificAreaDataSource() {
        return mobilityScientificAreaDataSource;
    }

    public void setMobilityScientificAreaDataSource(List<TupleDataSourceBean> mobilityScientificAreaDataSource) {
        this.mobilityScientificAreaDataSource = mobilityScientificAreaDataSource;
    }

    public List<TupleDataSourceBean> getMobilityProgrammeLevelDataSource() {
        return mobilityProgrammeLevelDataSource;
    }

    public void setMobilityProgrammeLevelDataSource(List<TupleDataSourceBean> mobilityProgrammeLevelDataSource) {
        this.mobilityProgrammeLevelDataSource = mobilityProgrammeLevelDataSource;
    }

    public List<TupleDataSourceBean> getCountryUnitDataSource() {
        return countryDataSource;
    }

    public void setCountryUnitDataSource(List<TupleDataSourceBean> countryUnitDataSource) {
        this.countryDataSource = countryUnitDataSource;
    }

}
