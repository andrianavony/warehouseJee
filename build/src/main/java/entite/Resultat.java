/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author S.ANDRIANAVONY
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultat.findAll", query = "SELECT r FROM Resultat r"),
    @NamedQuery(name = "Resultat.findByHerited", query = "SELECT r FROM Resultat r WHERE r.herited = :herited"),
    @NamedQuery(name = "Resultat.findByBatchname", query = "SELECT r FROM Resultat r WHERE r.batchname = :batchname"),
    @NamedQuery(name = "Resultat.findByLimssampleid", query = "SELECT r FROM Resultat r WHERE r.limssampleid = :limssampleid"),
    @NamedQuery(name = "Resultat.findByLimsidanalyse", query = "SELECT r FROM Resultat r WHERE r.limsidanalyse = :limsidanalyse"),
    @NamedQuery(name = "Resultat.findByAnalysename", query = "SELECT r FROM Resultat r WHERE r.analysename = :analysename"),
    @NamedQuery(name = "Resultat.findByMethodename", query = "SELECT r FROM Resultat r WHERE r.methodename = :methodename"),
    @NamedQuery(name = "Resultat.findByRepetition", query = "SELECT r FROM Resultat r WHERE r.repetition = :repetition"),
    @NamedQuery(name = "Resultat.findBySubrepetition", query = "SELECT r FROM Resultat r WHERE r.subrepetition = :subrepetition"),
    @NamedQuery(name = "Resultat.findByMeasurename", query = "SELECT r FROM Resultat r WHERE r.measurename = :measurename"),
    @NamedQuery(name = "Resultat.findByRawresultat", query = "SELECT r FROM Resultat r WHERE r.rawresultat = :rawresultat"),
    @NamedQuery(name = "Resultat.findByFormated", query = "SELECT r FROM Resultat r WHERE r.formated = :formated"),
    @NamedQuery(name = "Resultat.findByLimsidserie", query = "SELECT r FROM Resultat r WHERE r.limsidserie = :limsidserie"),
    @NamedQuery(name = "Resultat.findBySerie", query = "SELECT r FROM Resultat r WHERE r.serie = :serie"),
    @NamedQuery(name = "Resultat.findByCreationdate", query = "SELECT r FROM Resultat r WHERE r.creationdate = :creationdate"),
    @NamedQuery(name = "Resultat.findByStatutlabel", query = "SELECT r FROM Resultat r WHERE r.statutlabel = :statutlabel"),
    @NamedQuery(name = "Resultat.findByApprobationstatutlabel", query = "SELECT r FROM Resultat r WHERE r.approbationstatutlabel = :approbationstatutlabel"),
    @NamedQuery(name = "Resultat.findByAnalyseapprobationstatutlabel", query = "SELECT r FROM Resultat r WHERE r.analyseapprobationstatutlabel = :analyseapprobationstatutlabel"),
    @NamedQuery(name = "Resultat.findByApporved", query = "SELECT r FROM Resultat r WHERE r.apporved = :apporved"),
    @NamedQuery(name = "Resultat.findByApporvedby", query = "SELECT r FROM Resultat r WHERE r.apporvedby = :apporvedby"),
    @NamedQuery(name = "Resultat.findByApporveddate", query = "SELECT r FROM Resultat r WHERE r.apporveddate = :apporveddate"),
    @NamedQuery(name = "Resultat.findByResult", query = "SELECT r FROM Resultat r WHERE r.resultat = :resultat"),
    @NamedQuery(name = "Resultat.findByLimsmeasureid", query = "SELECT r FROM Resultat r WHERE r.limsmeasureid = :limsmeasureid"),
    @NamedQuery(name = "Resultat.findByLimscounteranalysesampelid", query = "SELECT r FROM Resultat r WHERE r.limscounteranalysesampelid = :limscounteranalysesampelid"),
    @NamedQuery(name = "Resultat.findByCounteranalyseidlims", query = "SELECT r FROM Resultat r WHERE r.counteranalyseidlims = :counteranalyseidlims"),
    @NamedQuery(name = "Resultat.findByCounteranalyse", query = "SELECT r FROM Resultat r WHERE r.counteranalyse = :counteranalyse"),
    @NamedQuery(name = "Resultat.findByBatch", query = "SELECT r FROM Resultat r WHERE r.batch = :batch"),
    @NamedQuery(name = "Resultat.findByLimsid", query = "SELECT r FROM Resultat r WHERE r.limsid = :limsid"),
    @NamedQuery(name = "Resultat.findByLimsbatchid", query = "SELECT r FROM Resultat r WHERE r.limsbatchid = :limsbatchid"),
    @NamedQuery(name = "Resultat.findByLimsfolderno", query = "SELECT r FROM Resultat r WHERE r.limsfolderno = :limsfolderno"),
    @NamedQuery(name = "Resultat.findByLimsidmethode", query = "SELECT r FROM Resultat r WHERE r.limsidmethode = :limsidmethode"),
    @NamedQuery(name = "Resultat.findByMethodedetail", query = "SELECT r FROM Resultat r WHERE r.methodedetail = :methodedetail"),
    @NamedQuery(name = "Resultat.findByStatut", query = "SELECT r FROM Resultat r WHERE r.statut = :statut"),
    @NamedQuery(name = "Resultat.findByApprobationstatut", query = "SELECT r FROM Resultat r WHERE r.approbationstatut = :approbationstatut"),
    @NamedQuery(name = "Resultat.findByAnalyseapprobationstatut", query = "SELECT r FROM Resultat r WHERE r.analyseapprobationstatut = :analyseapprobationstatut"),
    @NamedQuery(name = "Resultat.findByAnalysestatut", query = "SELECT r FROM Resultat r WHERE r.analysestatut = :analysestatut"),
    @NamedQuery(name = "Resultat.findByAnalysestatutlabel", query = "SELECT r FROM Resultat r WHERE r.analysestatutlabel = :analysestatutlabel"),
    @NamedQuery(name = "Resultat.findByDescription", query = "SELECT r FROM Resultat r WHERE r.description = :description"),
    @NamedQuery(name = "Resultat.findByIsvirtual", query = "SELECT r FROM Resultat r WHERE r.isvirtual = :isvirtual"),
    @NamedQuery(name = "Resultat.findByIsfinal", query = "SELECT r FROM Resultat r WHERE r.isfinal = :isfinal"),
    @NamedQuery(name = "Resultat.findByIsresultat", query = "SELECT r FROM Resultat r WHERE r.isresultat = :isresultat"),
    @NamedQuery(name = "Resultat.findByIsrequired", query = "SELECT r FROM Resultat r WHERE r.isrequired = :isrequired"),
    @NamedQuery(name = "Resultat.findByOccurence", query = "SELECT r FROM Resultat r WHERE r.occurence = :occurence"),
    @NamedQuery(name = "Resultat.findByOfficialename", query = "SELECT r FROM Resultat r WHERE r.officialename = :officialename"),
    @NamedQuery(name = "Resultat.findByMaingroup", query = "SELECT r FROM Resultat r WHERE r.maingroup = :maingroup"),
    @NamedQuery(name = "Resultat.findBySubgroup", query = "SELECT r FROM Resultat r WHERE r.subgroup = :subgroup"),
    @NamedQuery(name = "Resultat.findByMeasuremaster", query = "SELECT r FROM Resultat r WHERE r.measuremaster = :measuremaster"),
    @NamedQuery(name = "Resultat.findByMeasuredetail", query = "SELECT r FROM Resultat r WHERE r.measuredetail = :measuredetail"),
    @NamedQuery(name = "Resultat.findByValuemin", query = "SELECT r FROM Resultat r WHERE r.valuemin = :valuemin"),
    @NamedQuery(name = "Resultat.findByPreferdvaluemin", query = "SELECT r FROM Resultat r WHERE r.preferdvaluemin = :preferdvaluemin"),
    @NamedQuery(name = "Resultat.findByPreferdvaluemax", query = "SELECT r FROM Resultat r WHERE r.preferdvaluemax = :preferdvaluemax"),
    @NamedQuery(name = "Resultat.findByValuemax", query = "SELECT r FROM Resultat r WHERE r.valuemax = :valuemax"),
    @NamedQuery(name = "Resultat.findByDateofentry", query = "SELECT r FROM Resultat r WHERE r.dateofentry = :dateofentry"),
    @NamedQuery(name = "Resultat.findByUtilisateurname", query = "SELECT r FROM Resultat r WHERE r.utilisateurname = :utilisateurname"),
    @NamedQuery(name = "Resultat.findByConsidered", query = "SELECT r FROM Resultat r WHERE r.considered = :considered"),
    @NamedQuery(name = "Resultat.findByProdgroup", query = "SELECT r FROM Resultat r WHERE r.prodgroup = :prodgroup"),
    @NamedQuery(name = "Resultat.findByCopiedfrom", query = "SELECT r FROM Resultat r WHERE r.copiedfrom = :copiedfrom"),
    @NamedQuery(name = "Resultat.findByCopiedfromlimsbathcname", query = "SELECT r FROM Resultat r WHERE r.copiedfromlimsbathcname = :copiedfromlimsbathcname"),
    @NamedQuery(name = "Resultat.findByIsresultatinserted", query = "SELECT r FROM Resultat r WHERE r.isresultatinserted = :isresultatinserted")
    //,@NamedQuery(name = "Resultat.findByVersion", query = "SELECT r FROM Resultat r WHERE r.version = :version")
    })
public class Resultat implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean herited;
    @Size(max = 50)
    @Column(length = 50)
    private String batchname;
    @Size(max = 50)
    @Column(length = 50)
    private String limssampleid;
    private BigInteger limsidanalyse;
    @Size(max = 50)
    @Column(length = 50)
    private String analysename;
    @Size(max = 50)
    @Column(length = 50)
    private String methodename;
    private Short repetition;
    private Short subrepetition;
    @Size(max = 50)
    @Column(length = 50)
    private String measurename;
    @Size(max = 50)
    @Column(length = 50)
    private String rawresultat;
    @Size(max = 50)
    @Column(length = 50)
    private String formated;
    private BigInteger limsidserie;
    private BigInteger serie;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 50)
    @Column(length = 50)
    private String statutlabel;
    @Size(max = 50)
    @Column(length = 50)
    private String approbationstatutlabel;
    @Size(max = 50)
    @Column(length = 50)
    private String analyseapprobationstatutlabel;
    private Short apporved;
    @Size(max = 50)
    @Column(length = 50)
    private String apporvedby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date apporveddate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger resultat;
    private BigInteger limsmeasureid;
    @Size(max = 50)
    @Column(length = 50)
    private String limscounteranalysesampelid;
    @Size(max = 50)
    @Column(length = 50)
    private String counteranalyseidlims;
    private Short counteranalyse;
    private Batch batch;
    private BigInteger limsid;
    private BigInteger limsbatchid;
    @Size(max = 50)
    @Column(length = 50)
    private String limsfolderno;
    @Size(max = 50)
    @Column(length = 50)
    private String limsidmethode;
    private BigInteger methodedetail;
    private Boolean statut;
    private Boolean approbationstatut;
    private Boolean analyseapprobationstatut;
    private Boolean analysestatut;
    private Boolean analysestatutlabel;
    @Size(max = 50)
    @Column(length = 50)
    private String description;
    private Boolean isvirtual;
    private Boolean isfinal;
    private Boolean isresultat;
    private Boolean isrequired;
    private Short occurence;
    @Size(max = 50)
    @Column(length = 50)
    private String officialename;
    @Size(max = 50)
    @Column(length = 50)
    private String maingroup;
    @Size(max = 50)
    @Column(length = 50)
    private String subgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String measuremaster;
    @Size(max = 50)
    @Column(length = 50)
    private String measuredetail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double valuemin;
    @Column(precision = 22)
    private Double preferdvaluemin;
    @Column(precision = 22)
    private Double preferdvaluemax;
    @Column(precision = 22)
    private Double valuemax;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateofentry;
    @Size(max = 50)
    @Column(length = 50)
    private String utilisateurname;
    private Boolean considered;
    @Size(max = 50)
    @Column(length = 50)
    private String prodgroup;
    @Size(max = 50)
    @Column(length = 50)
    private String copiedfrom;
    @Size(max = 50)
    @Column(length = 50)
    private String copiedfromlimsbathcname;
    private Boolean isresultatinserted;
    /*
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //private Date version;*/
    @JoinColumn(name = "CASEFILE", referencedColumnName = "CASEFILE")
    @ManyToOne
    private Casefile casefile;
    @JoinColumn(name = "UTILISATEUR", referencedColumnName = "UTILISATEUR")
    @ManyToOne
    private Utilisateur utilisateur;
    @JoinColumn(name = "MEASURE", referencedColumnName = "MEASURE")
    @ManyToOne
    private Measure measure;
    @JoinColumn(name = "ANALYSE", referencedColumnName = "ANALYSE")
    @ManyToOne
    private Analyse analyse;
    @JoinColumn(name = "ARTICLE", referencedColumnName = "ARTICLE")
    @ManyToOne
    private Article article;
    @OneToMany(mappedBy = "copiedfromidanalyse")
    private List<Resultat> resultats;
    @JoinColumn(name = "COPIEDFROMIDANALYSE", referencedColumnName = "ANALYSE")
    @ManyToOne
    private Resultat copiedfromidanalyse;
    @JoinColumn(name = "COPIEDFROMIDBATCH", referencedColumnName = "BATCH")
    @ManyToOne
    private Batch copiedfromidbatch;
    @JoinColumn(name = "COPIEDFROMIDSAMPLE", referencedColumnName = "SAMPLE")
    @ManyToOne
    private Sample copiedfromidsample;
    @JoinColumn(name = "METHODE", referencedColumnName = "METHODE")
    @ManyToOne
    private Methode methode;
    @JoinColumn(name = "SPECIE", referencedColumnName = "SPECIE")
    @ManyToOne
    private Specie specie;
    @JoinColumn(name = "STAGE", referencedColumnName = "STAGE")
    @ManyToOne
    private Stage stage;
    @JoinColumn(name = "MODELEANALYSE", referencedColumnName = "MODELEANALYSE")
    @ManyToOne
    private Modeleanalyse modeleanalyse;
    @JoinColumn(name = "SPECIFICATION", referencedColumnName = "SPECIFICATION")
    @ManyToOne
    private Specification specification;
    @JoinColumn(name = "SAMPLE", referencedColumnName = "SAMPLE")
    @ManyToOne
    private Sample sample;

    public Resultat() {
    }

    public Resultat(BigInteger resultat) {
        this.resultat = resultat;
    }

    /*public Resultat(Long result, Date version) {
        this.result = result;
        this.version = version;
    }*/

    public Boolean getHerited() {
        return herited;
    }

    public void setHerited(Boolean herited) {
        this.herited = herited;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getLimssampleid() {
        return limssampleid;
    }

    public void setLimssampleid(String limssampleid) {
        this.limssampleid = limssampleid;
    }

    public BigInteger getLimsidanalyse() {
        return limsidanalyse;
    }

    public void setLimsidanalyse(BigInteger limsidanalyse) {
        this.limsidanalyse = limsidanalyse;
    }

    public String getAnalysename() {
        return analysename;
    }

    public void setAnalysename(String analysename) {
        this.analysename = analysename;
    }

    public String getMethodename() {
        return methodename;
    }

    public void setMethodename(String methodename) {
        this.methodename = methodename;
    }

    public Short getRepetition() {
        return repetition;
    }

    public void setRepetition(Short repetition) {
        this.repetition = repetition;
    }

    public Short getSubrepetition() {
        return subrepetition;
    }

    public void setSubrepetition(Short subrepetition) {
        this.subrepetition = subrepetition;
    }

    public String getMeasurename() {
        return measurename;
    }

    public void setMeasurename(String measurename) {
        this.measurename = measurename;
    }

    public String getRawresultat() {
        return rawresultat;
    }

    public void setRawresultat(String rawresultat) {
        this.rawresultat = rawresultat;
    }

    public String getFormated() {
        return formated;
    }

    public void setFormated(String formated) {
        this.formated = formated;
    }

    public BigInteger getLimsidserie() {
        return limsidserie;
    }

    public void setLimsidserie(BigInteger limsidserie) {
        this.limsidserie = limsidserie;
    }

    public BigInteger getSerie() {
        return serie;
    }

    public void setSerie(BigInteger serie) {
        this.serie = serie;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getStatutlabel() {
        return statutlabel;
    }

    public void setStatutlabel(String statutlabel) {
        this.statutlabel = statutlabel;
    }

    public String getApprobationstatutlabel() {
        return approbationstatutlabel;
    }

    public void setApprobationstatutlabel(String approbationstatutlabel) {
        this.approbationstatutlabel = approbationstatutlabel;
    }

    public String getAnalyseapprobationstatutlabel() {
        return analyseapprobationstatutlabel;
    }

    public void setAnalyseapprobationstatutlabel(String analyseapprobationstatutlabel) {
        this.analyseapprobationstatutlabel = analyseapprobationstatutlabel;
    }

    public Short getApporved() {
        return apporved;
    }

    public void setApporved(Short apporved) {
        this.apporved = apporved;
    }

    public String getApporvedby() {
        return apporvedby;
    }

    public void setApporvedby(String apporvedby) {
        this.apporvedby = apporvedby;
    }

    public Date getApporveddate() {
        return apporveddate;
    }

    public void setApporveddate(Date apporveddate) {
        this.apporveddate = apporveddate;
    }

    public BigInteger getResultat() {
        return resultat;
    }

    public void setResultat(BigInteger resultat) {
        this.resultat = resultat;
    }

    public BigInteger getLimsmeasureid() {
        return limsmeasureid;
    }

    public void setLimsmeasureid(BigInteger limsmeasureid) {
        this.limsmeasureid = limsmeasureid;
    }

    public String getLimscounteranalysesampelid() {
        return limscounteranalysesampelid;
    }

    public void setLimscounteranalysesampelid(String limscounteranalysesampelid) {
        this.limscounteranalysesampelid = limscounteranalysesampelid;
    }

    public String getCounteranalyseidlims() {
        return counteranalyseidlims;
    }

    public void setCounteranalyseidlims(String counteranalyseidlims) {
        this.counteranalyseidlims = counteranalyseidlims;
    }

    public Short getCounteranalyse() {
        return counteranalyse;
    }

    public void setCounteranalyse(Short counteranalyse) {
        this.counteranalyse = counteranalyse;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public BigInteger getLimsid() {
        return limsid;
    }

    public void setLimsid(BigInteger limsid) {
        this.limsid = limsid;
    }

    public BigInteger getLimsbatchid() {
        return limsbatchid;
    }

    public void setLimsbatchid(BigInteger limsbatchid) {
        this.limsbatchid = limsbatchid;
    }

    public String getLimsfolderno() {
        return limsfolderno;
    }

    public void setLimsfolderno(String limsfolderno) {
        this.limsfolderno = limsfolderno;
    }

    public String getLimsidmethode() {
        return limsidmethode;
    }

    public void setLimsidmethode(String limsidmethode) {
        this.limsidmethode = limsidmethode;
    }

    public BigInteger getMethodedetail() {
        return methodedetail;
    }

    public void setMethodedetail(BigInteger methodedetail) {
        this.methodedetail = methodedetail;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Boolean getApprobationstatut() {
        return approbationstatut;
    }

    public void setApprobationstatut(Boolean approbationstatut) {
        this.approbationstatut = approbationstatut;
    }

    public Boolean getAnalyseapprobationstatut() {
        return analyseapprobationstatut;
    }

    public void setAnalyseapprobationstatut(Boolean analyseapprobationstatut) {
        this.analyseapprobationstatut = analyseapprobationstatut;
    }

    public Boolean getAnalysestatut() {
        return analysestatut;
    }

    public void setAnalysestatut(Boolean analysestatut) {
        this.analysestatut = analysestatut;
    }

    public Boolean getAnalysestatutlabel() {
        return analysestatutlabel;
    }

    public void setAnalysestatutlabel(Boolean analysestatutlabel) {
        this.analysestatutlabel = analysestatutlabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Boolean getIsfinal() {
        return isfinal;
    }

    public void setIsfinal(Boolean isfinal) {
        this.isfinal = isfinal;
    }

    public Boolean getIsresultat() {
        return isresultat;
    }

    public void setIsresultat(Boolean isresultat) {
        this.isresultat = isresultat;
    }

    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public Short getOccurence() {
        return occurence;
    }

    public void setOccurence(Short occurence) {
        this.occurence = occurence;
    }

    public String getOfficialename() {
        return officialename;
    }

    public void setOfficialename(String officialename) {
        this.officialename = officialename;
    }

    public String getMaingroup() {
        return maingroup;
    }

    public void setMaingroup(String maingroup) {
        this.maingroup = maingroup;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getMeasuremaster() {
        return measuremaster;
    }

    public void setMeasuremaster(String measuremaster) {
        this.measuremaster = measuremaster;
    }

    public String getMeasuredetail() {
        return measuredetail;
    }

    public void setMeasuredetail(String measuredetail) {
        this.measuredetail = measuredetail;
    }

    public Double getValuemin() {
        return valuemin;
    }

    public void setValuemin(Double valuemin) {
        this.valuemin = valuemin;
    }

    public Double getPreferdvaluemin() {
        return preferdvaluemin;
    }

    public void setPreferdvaluemin(Double preferdvaluemin) {
        this.preferdvaluemin = preferdvaluemin;
    }

    public Double getPreferdvaluemax() {
        return preferdvaluemax;
    }

    public void setPreferdvaluemax(Double preferdvaluemax) {
        this.preferdvaluemax = preferdvaluemax;
    }

    public Double getValuemax() {
        return valuemax;
    }

    public void setValuemax(Double valuemax) {
        this.valuemax = valuemax;
    }

    public Date getDateofentry() {
        return dateofentry;
    }

    public void setDateofentry(Date dateofentry) {
        this.dateofentry = dateofentry;
    }

    public String getUtilisateurname() {
        return utilisateurname;
    }

    public void setUtilisateurname(String utilisateurname) {
        this.utilisateurname = utilisateurname;
    }

    public Boolean getConsidered() {
        return considered;
    }

    public void setConsidered(Boolean considered) {
        this.considered = considered;
    }

    public String getProdgroup() {
        return prodgroup;
    }

    public void setProdgroup(String prodgroup) {
        this.prodgroup = prodgroup;
    }

    public String getCopiedfrom() {
        return copiedfrom;
    }

    public void setCopiedfrom(String copiedfrom) {
        this.copiedfrom = copiedfrom;
    }

    public String getCopiedfromlimsbathcname() {
        return copiedfromlimsbathcname;
    }

    public void setCopiedfromlimsbathcname(String copiedfromlimsbathcname) {
        this.copiedfromlimsbathcname = copiedfromlimsbathcname;
    }

    public Boolean getIsresultatinserted() {
        return isresultatinserted;
    }

    public void setIsresultatinserted(Boolean isresultatinserted) {
        this.isresultatinserted = isresultatinserted;
    }

    /*public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }*/

    public Casefile getCasefile() {
        return casefile;
    }

    public void setCasefile(Casefile casefile) {
        this.casefile = casefile;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Analyse getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Analyse analyse) {
        this.analyse = analyse;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @XmlTransient
    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    public Resultat getCopiedfromidanalyse() {
        return copiedfromidanalyse;
    }

    public void setCopiedfromidanalyse(Resultat copiedfromidanalyse) {
        this.copiedfromidanalyse = copiedfromidanalyse;
    }

    public Batch getCopiedfromidbatch() {
        return copiedfromidbatch;
    }

    public void setCopiedfromidbatch(Batch copiedfromidbatch) {
        this.copiedfromidbatch = copiedfromidbatch;
    }

    public Sample getCopiedfromidsample() {
        return copiedfromidsample;
    }

    public void setCopiedfromidsample(Sample copiedfromidsample) {
        this.copiedfromidsample = copiedfromidsample;
    }

    public Methode getMethode() {
        return methode;
    }

    public void setMethode(Methode methode) {
        this.methode = methode;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Modeleanalyse getModeleanalyse() {
        return modeleanalyse;
    }

    public void setModeleanalyse(Modeleanalyse modeleanalyse) {
        this.modeleanalyse = modeleanalyse;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultat != null ? resultat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultat)) {
            return false;
        }
        Resultat other = (Resultat) object;
        if ((this.resultat == null && other.resultat != null) || (this.resultat != null && !this.resultat.equals(other.resultat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entite.Resultat[ result=" + resultat + " ]";
    }
    
}
