package com.glucoseguardian.webbackend.storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * classe entity Paziente.
 */
@Entity
public class Paziente implements Serializable {

  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<NumeroTelefono> numeriTelefono;
  @OneToMany(mappedBy = "pazienteOggetto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Notifica> notificheInvio;
  @OneToMany(mappedBy = "pazienteDestinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Notifica> notificheRicezione;
  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Glicemia> glicemie;
  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Feedback> feedbacks;
  @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
  Terapia terapia;
  @ManyToOne
  @JoinColumn(name = "codiceFiscale")
  Dottore dottore;
  @ManyToMany
  @JoinTable(name = "pazienteTutore", joinColumns = @JoinColumn(name = "codiceFiscale"),
      inverseJoinColumns = @JoinColumn(name = "codiceFiscale"))
  List<ProfiloTutore> profiliTutore;

  @Id
  @Column(columnDefinition = "CHAR(16)")
  private String codiceFiscale;
  @Column(length = 30, nullable = false)
  private String nome;
  @Column(length = 30, nullable = false)
  private String cognome;
  @Column(nullable = false)
  private Date dataNascita;
  @Column(length = 50, nullable = false)
  private String indirizzo;
  @Column(length = 15, nullable = false)
  private String telefono;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(columnDefinition = "CHAR(1)", nullable = false)
  private String sesso;
  private String totpKey;
  @Column(length = 10, nullable = false)
  private String tipoDiabete;
  @Column(length = 100, nullable = false)
  private String comorbilita;
  @Column(length = 100, nullable = false)
  private String farmaciAssunti;
  @Column(columnDefinition = "UNSIGNED INT(1)", nullable = false)
  private int periodoDiMonitoraggio;

  /**
   * costruttore Paziente.
   */
  public Paziente(String codiceFiscale, String nome, String cognome, Date dataNascita,
      String indirizzo, String telefono, String email, String password, String sesso,
      String totpKey, String tipoDiabete, String comorbilita, String farmaciAssunti,
      int periodoDiMonitoraggio) {
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.email = email;
    this.password = password;
    this.sesso = sesso;
    this.totpKey = totpKey;
    this.tipoDiabete = tipoDiabete;
    this.comorbilita = comorbilita;
    this.farmaciAssunti = farmaciAssunti;
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
  }

  public Paziente() {

  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public Date getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  public String getTotpKey() {
    return totpKey;
  }

  public void setTotpKey(String totpKey) {
    this.totpKey = totpKey;
  }

  public String getTipoDiabete() {
    return tipoDiabete;
  }

  public void setTipoDiabete(String tipoDiabete) {
    this.tipoDiabete = tipoDiabete;
  }

  public String getComorbilita() {
    return comorbilita;
  }

  public void setComorbilita(String comorbilita) {
    this.comorbilita = comorbilita;
  }

  public String getFarmaciAssunti() {
    return farmaciAssunti;
  }

  public void setFarmaciAssunti(String farmaciAssunti) {
    this.farmaciAssunti = farmaciAssunti;
  }

  public int getPeriodoDiMonitoraggio() {
    return periodoDiMonitoraggio;
  }

  public void setPeriodoDiMonitoraggio(int periodoDiMonitoraggio) {
    this.periodoDiMonitoraggio = periodoDiMonitoraggio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Paziente paziente = (Paziente) o;
    return periodoDiMonitoraggio == paziente.periodoDiMonitoraggio && Objects.equals(codiceFiscale,
        paziente.codiceFiscale) && nome.equals(paziente.nome) && cognome.equals(paziente.cognome)
        && dataNascita.equals(paziente.dataNascita) && indirizzo.equals(paziente.indirizzo)
        && telefono.equals(paziente.telefono) && email.equals(paziente.email) && password.equals(
        paziente.password) && sesso.equals(paziente.sesso) && Objects.equals(totpKey,
        paziente.totpKey) && tipoDiabete.equals(paziente.tipoDiabete) && comorbilita.equals(
        paziente.comorbilita) && farmaciAssunti.equals(paziente.farmaciAssunti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, nome, cognome, dataNascita, indirizzo, telefono, email,
        password, sesso, totpKey, tipoDiabete, comorbilita, farmaciAssunti, periodoDiMonitoraggio);
  }

  @Override
  public String toString() {
    return "Paziente{" + "codice_fiscale='" + codiceFiscale + '\'' + ", nome='" + nome + '\''
        + ", cognome='" + cognome + '\'' + ", data_nascita=" + dataNascita + ", indirizzo='"
        + indirizzo + '\'' + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
        + ", password='" + password + '\'' + ", sesso='" + sesso + '\'' + ", totp_key='" + totpKey
        + '\'' + ", tipo_diabete='" + tipoDiabete + '\'' + ", comorbilita='" + comorbilita + '\''
        + ", farmaci_assunti='" + farmaciAssunti + '\'' + ", periodo_di_monitoraggio="
        + periodoDiMonitoraggio + '}';
  }


}