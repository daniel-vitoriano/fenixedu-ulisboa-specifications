package org.fenixedu.ulisboa.specifications.ui.legal.report.raides;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.fenixedu.academic.domain.candidacy.IngressionType;
import org.fenixedu.academic.domain.student.RegistrationProtocol;
import org.fenixedu.bennu.IBean;
import org.fenixedu.bennu.TupleDataSourceBean;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.ulisboa.specifications.domain.legal.raides.RaidesInstance;
import org.fenixedu.ulisboa.specifications.util.ULisboaConstants;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RaidesInstanceBean implements IBean {

    private String passwordToZip;
    private Set<RegistrationProtocol> mobilityAgreements;
    private Set<RegistrationProtocol> enrolledAgreements;
    
    private Set<IngressionType> degreeChangeIngressions;
    private Set<IngressionType> degreeTransferIngressions;
    private Set<IngressionType> ingressionsForGeneralAccessRegime;

    private List<TupleDataSourceBean> ingressionTypesDataSource;

    private List<TupleDataSourceBean> registrationProtocolsDataSource;

    public RaidesInstanceBean(final RaidesInstance raidesInstance) {
        setPasswordToZip(raidesInstance.getPasswordToZip());
        
        
        setMobilityAgreements(Sets.newHashSet(raidesInstance.getMobilityAgreementsSet()));
        setEnrolledAgreements(Sets.newHashSet(raidesInstance.getEnrolledAgreementsSet()));
        
        setDegreeChangeIngressions(Sets.newHashSet(raidesInstance.getDegreeChangeIngressionsSet()));
        setDegreeTransferIngressions(Sets.newHashSet(raidesInstance.getDegreeTransferIngressionsSet()));
        setIngressionsForGeneralAccessRegime(Sets.newHashSet(raidesInstance.getGeneralAccessRegimeIngressionsSet()));
        
        loadDataSources();
    }

    private void loadDataSources() {
        this.ingressionTypesDataSource = Lists.newArrayList();
        this.ingressionTypesDataSource.add(ULisboaConstants.SELECT_OPTION);

        this.ingressionTypesDataSource.addAll(Bennu.getInstance().getIngressionTypesSet().stream()
                .map(i -> new TupleDataSourceBean(i.getExternalId(), i.getDescription().getContent()))
                .collect(Collectors.toList()));

        this.registrationProtocolsDataSource = Lists.newArrayList();
        this.registrationProtocolsDataSource.add(ULisboaConstants.SELECT_OPTION);

        this.registrationProtocolsDataSource.addAll(Bennu.getInstance().getRegistrationProtocolsSet()
                .stream()
                .map(r -> new TupleDataSourceBean(r.getExternalId(), r.getDescription().getContent()))
                .collect(Collectors.toList()));
    }

    public String getPasswordToZip() {
        return passwordToZip;
    }

    public void setPasswordToZip(String passwordToZip) {
        this.passwordToZip = passwordToZip;
    }

    public Set<RegistrationProtocol> getMobilityAgreements() {
        return mobilityAgreements;
    }

    public void setMobilityAgreements(Set<RegistrationProtocol> mobilityAgreements) {
        this.mobilityAgreements = mobilityAgreements;
    }
    
    public Set<RegistrationProtocol> getEnrolledAgreements() {
        return enrolledAgreements;
    }
    
    public void setEnrolledAgreements(Set<RegistrationProtocol> enrolledAgreements) {
        this.enrolledAgreements = enrolledAgreements;
    }

    public Set<IngressionType> getDegreeChangeIngressions() {
        return degreeChangeIngressions;
    }

    public void setDegreeChangeIngressions(Set<IngressionType> degreeChangeIngressions) {
        this.degreeChangeIngressions = degreeChangeIngressions;
    }

    public Set<IngressionType> getDegreeTransferIngressions() {
        return degreeTransferIngressions;
    }

    public void setDegreeTransferIngressions(Set<IngressionType> degreeTransferIngressions) {
        this.degreeTransferIngressions = degreeTransferIngressions;
    }

    public List<TupleDataSourceBean> getIngressionTypesDataSource() {
        return ingressionTypesDataSource;
    }

    public Set<IngressionType> getIngressionsForGeneralAccessRegime() {
        return ingressionsForGeneralAccessRegime;
    }

    public void setIngressionsForGeneralAccessRegime(Set<IngressionType> ingressionsForGeneralAccessRegime) {
        this.ingressionsForGeneralAccessRegime = ingressionsForGeneralAccessRegime;
    }

}
