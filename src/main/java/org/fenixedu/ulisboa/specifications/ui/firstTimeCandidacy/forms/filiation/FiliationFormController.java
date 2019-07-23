package org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.filiation;

import static org.fenixedu.bennu.FenixeduUlisboaSpecificationsSpringConfiguration.BUNDLE;
import static org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.FirstTimeCandidacyController.FIRST_TIME_START_URL;

import java.util.Set;

import org.fenixedu.academic.domain.Country;
import org.fenixedu.academic.domain.District;
import org.fenixedu.academic.domain.DistrictSubdivision;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.student.Student;
import org.fenixedu.academic.predicate.AccessControl;
import org.fenixedu.bennu.core.i18n.BundleUtil;
import org.fenixedu.bennu.spring.portal.BennuSpringController;
import org.fenixedu.ulisboa.specifications.domain.Parish;
import org.fenixedu.ulisboa.specifications.domain.PersonUlisboaSpecifications;
import org.fenixedu.ulisboa.specifications.domain.student.access.StudentAccessServices;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.FirstTimeCandidacyController;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.CandidancyForm;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.FormAbstractController;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.householdinfo.ResidenceInformationFormController;
import org.fenixedu.ulisboa.specifications.ui.firstTimeCandidacy.forms.personalinfo.PersonalInformationFormController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Sets;

import pt.ist.fenixframework.Atomic;

@BennuSpringController(value = FirstTimeCandidacyController.class)
@RequestMapping(FiliationFormController.CONTROLLER_URL)
public class FiliationFormController extends FormAbstractController {

    public static final String CONTROLLER_URL = FIRST_TIME_START_URL + "/{executionYearId}/filiationform";

    @Override
    protected String getControllerURL() {
        return CONTROLLER_URL;
    }

    @Override
    protected String getFormVariableName() {
        return "filiationForm";
    }

    @Override
    protected String fillGetScreen(ExecutionYear executionYear, Model model, RedirectAttributes redirectAttributes) {

        fillFormIfRequired(model);

        addInfoMessage(BundleUtil.getString(BUNDLE, "label.firstTimeCandidacy.fillFiliation.info"), model);
        return "fenixedu-ulisboa-specifications/firsttimecandidacy/angular/filiationform/fillfiliation";
    }

    @Override
    protected void fillPostScreen(ExecutionYear executionYear, CandidancyForm candidancyForm, Model model,
            RedirectAttributes redirectAttributes) {
        StudentAccessServices.triggerSyncPersonToExternal(AccessControl.getPerson());
    }

    private void fillFormIfRequired(Model model) {
        if (!model.containsAttribute(getFormVariableName())) {
            FiliationForm form = new FiliationForm();
            Person person = AccessControl.getPerson();
            form.setSecondNationality(person.getSecondNationality());

            form.setFirstNationality(person.getCountry());
            form.setCountryOfBirth(person.getCountryOfBirth());
            if (form.getCountryOfBirth() == null) {
                form.setCountryOfBirth(Country.readDefault());
            }
            District district = District.readByName(person.getDistrictOfBirth());
            if (district != null) {
                form.setDistrictOfBirth(district);
                DistrictSubdivision districtSubdivision =
                        district.getDistrictSubdivisionByName(person.getDistrictSubdivisionOfBirth());
                form.setDistrictSubdivisionOfBirth(districtSubdivision);
                if (districtSubdivision != null) {
                    form.setParishOfBirth(Parish.findByName(districtSubdivision, person.getParishOfBirth()).orElse(null));
                }
            }

            form.setFatherName(person.getNameOfFather());
            form.setMotherName(person.getNameOfMother());

            setForm(form, model);
        }
    }

    @Override
    protected boolean validate(ExecutionYear executionYear, CandidancyForm candidancyForm, Model model) {
        if (!(candidancyForm instanceof FiliationForm)) {
            addErrorMessage(BundleUtil.getString(BUNDLE, "error.FiliationFormController.wrong.form.type"), model);
        }

        return validate((FiliationForm) candidancyForm, model);
    }

    private boolean validate(FiliationForm form, Model model) {
        final Set<String> result = validateForm(form, getStudent(model).getPerson());

        for (final String message : result) {
            addErrorMessage(message, model);
        }

        return result.isEmpty();
    }

    private Set<String> validateForm(FiliationForm form, final Person person) {
        final Set<String> result = Sets.newLinkedHashSet();

        if (form.getFirstNationality() == null) {
            result.add(BundleUtil.getString(BUNDLE, "error.candidacy.FilliationForm.firstNationality.required"));
        }

        if (form.getCountryOfBirth() == null) {
            result.add(BundleUtil.getString(BUNDLE, "error.candidacy.FilliationForm.countryNationality.required"));
        }

        return result;
    }

    @Override
    protected void writeData(ExecutionYear executionYear, CandidancyForm candidancyForm, Model model) {
        writeData((FiliationForm) candidancyForm);
    }

    @Atomic
    private void writeData(FiliationForm form) {
        Person person = AccessControl.getPerson();
        person.setSecondNationality(form.getSecondNationality());

        person.setCountry(form.getFirstNationality());

        person.setCountryOfBirth(form.getCountryOfBirth());

        if (form.getDistrictOfBirth() != null) {
            person.setDistrictOfBirth(form.getDistrictOfBirth().getName());
        } else {
            person.setDistrictOfBirth(null);
        }

        if (form.getDistrictSubdivisionOfBirth() != null) {
            person.setDistrictSubdivisionOfBirth(form.getDistrictSubdivisionOfBirth().getName());
        } else {
            person.setDistrictSubdivisionOfBirth(null);
        }

        if (form.getParishOfBirth() != null) {
            person.setParishOfBirth(form.getParishOfBirth().getName());
        } else {
            person.setParishOfBirth(null);
        }

        person.setNameOfFather(form.getFatherName());
        person.setNameOfMother(form.getMotherName());
    }

    @Override
    protected String backScreen(ExecutionYear executionYear, Model model, RedirectAttributes redirectAttributes) {
        return redirect(urlWithExecutionYear(PersonalInformationFormController.CONTROLLER_URL, executionYear), model,
                redirectAttributes);
    }

    @Override
    protected String nextScreen(ExecutionYear executionYear, Model model, RedirectAttributes redirectAttributes) {
        return redirect(urlWithExecutionYear(ResidenceInformationFormController.CONTROLLER_URL, executionYear), model,
                redirectAttributes);
    }

    @Override
    public boolean isFormIsFilled(ExecutionYear executionYear, Student student) {
        return false;
    }

    @Override
    protected Student getStudent(Model model) {
        return AccessControl.getPerson().getStudent();
    }

}
