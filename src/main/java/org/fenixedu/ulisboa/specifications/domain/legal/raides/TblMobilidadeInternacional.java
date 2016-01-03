package org.fenixedu.ulisboa.specifications.domain.legal.raides;


public class TblMobilidadeInternacional extends RaidesData implements IMatricula {
    
    protected String idEstab;
    protected String idAluno;
    protected String curso;
    protected String ramo;
    protected String anoLectivo;
    protected String anoCurricular;
    protected String primeiraVez;
    protected String regimeFrequencia;
    protected String ectsInscrito;
    protected String progMobilidade;
    protected String outroPrograma;
    protected String tipoProgMobilidade;
    protected String duracaoPrograma;
    protected String nivelCursoOrigem;
    protected String outroNivelCurOrigem;
    protected boolean valid = true;


    @Override
    public void markAsInvalid() {
        this.valid = false;
    }

    /*
     * GETTERS & SETTERS
     */

    @Override
    public boolean isValid() {
        return valid;
    }

    public String getIdEstab() {
        return idEstab;
    }

    public void setIdEstab(String idEstab) {
        this.idEstab = idEstab;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(String anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

    public String getAnoCurricular() {
        return anoCurricular;
    }

    public void setAnoCurricular(String anoCurricular) {
        this.anoCurricular = anoCurricular;
    }

    public String getPrimeiraVez() {
        return primeiraVez;
    }

    public void setPrimeiraVez(String primeiraVez) {
        this.primeiraVez = primeiraVez;
    }

    public String getRegimeFrequencia() {
        return regimeFrequencia;
    }

    public void setRegimeFrequencia(String regimeFrequencia) {
        this.regimeFrequencia = regimeFrequencia;
    }

    public String getEctsInscrito() {
        return ectsInscrito;
    }

    public void setEctsInscrito(String ectsInscrito) {
        this.ectsInscrito = ectsInscrito;
    }

    public String getProgMobilidade() {
        return progMobilidade;
    }

    public void setProgMobilidade(String progMobilidade) {
        this.progMobilidade = progMobilidade;
    }

    public String getOutroPrograma() {
        return outroPrograma;
    }

    public void setOutroPrograma(String outroPrograma) {
        this.outroPrograma = outroPrograma;
    }

    public String getTipoProgMobilidade() {
        return tipoProgMobilidade;
    }

    public void setTipoProgMobilidade(String tipoProgMobilidade) {
        this.tipoProgMobilidade = tipoProgMobilidade;
    }

    public String getDuracaoPrograma() {
        return duracaoPrograma;
    }

    public void setDuracaoPrograma(String duracaoPrograma) {
        this.duracaoPrograma = duracaoPrograma;
    }

    public String getNivelCursoOrigem() {
        return nivelCursoOrigem;
    }

    public void setNivelCursoOrigem(String nivelCursoOrigem) {
        this.nivelCursoOrigem = nivelCursoOrigem;
    }

    public String getOutroNivelCurOrigem() {
        return outroNivelCurOrigem;
    }

    public void setOutroNivelCurOrigem(String outroNivelCurOrigem) {
        this.outroNivelCurOrigem = outroNivelCurOrigem;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}