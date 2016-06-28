package org.fenixedu.ulisboa.specifications.domain.student;

import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.ulisboa.specifications.domain.exceptions.ULisboaSpecificationsDomainException;

import pt.ist.fenixframework.FenixFramework;

public class RegistrationExtendedInformation extends RegistrationExtendedInformation_Base {

    protected RegistrationExtendedInformation() {
        super();
        super.setBennu(Bennu.getInstance());
    }

    protected void init(final Registration registration) {
        super.setRegistration(registration);
        checkRules();

    }

    private void checkRules() {
        if (getRegistration() == null) {
            throw new ULisboaSpecificationsDomainException("error.RegistrationExtendedInformation.registration.cannot.be.null");
        }
    }

    static public void setupDeleteListener() {
        FenixFramework.getDomainModel().registerDeletionListener(Registration.class, registration ->
        {
            if (registration.getExtendedInformation() != null) {
                registration.getExtendedInformation().delete();
            }
        });
    }

    private void delete() {

        super.setRegistration(null);
        super.setBennu(null);

        super.deleteDomainObject();

    }

    static public RegistrationExtendedInformation findOrCreate(Registration registration) {
        return registration.getExtendedInformation() != null ? registration.getExtendedInformation() : create(registration);
    }

    static public RegistrationExtendedInformation create(Registration registration) {
        final RegistrationExtendedInformation result = new RegistrationExtendedInformation();
        result.init(registration);

        return result;
    }

}
