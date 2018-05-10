package de.augsburg1871.handball.rankingservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Rank {

    private int rang;
    private String mannschaft;
    private int begegnungen;
    private int siege;
    private int unentschieden;
    private int niederlagen;
    private String tore;
    private int torDifferenz;
    private String punkte;
    private String hinweis;

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getMannschaft() {
        return mannschaft;
    }

    public void setMannschaft(String mannschaft) {
        this.mannschaft = mannschaft;
    }

    public int getBegegnungen() {
        return begegnungen;
    }

    public void setBegegnungen(int begegnungen) {
        this.begegnungen = begegnungen;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public int getUnentschieden() {
        return unentschieden;
    }

    public void setUnentschieden(int unentschieden) {
        this.unentschieden = unentschieden;
    }

    public int getNiederlagen() {
        return niederlagen;
    }

    public void setNiederlagen(int niederlagen) {
        this.niederlagen = niederlagen;
    }

    public String getTore() {
        return tore;
    }

    public void setTore(String tore) {
        this.tore = tore;
    }

    public int getTorDifferenz() {
        return torDifferenz;
    }

    public void setTorDifferenz(int torDifferenz) {
        this.torDifferenz = torDifferenz;
    }

    public String getPunkte() {
        return punkte;
    }

    public void setPunkte(String punkte) {
        this.punkte = punkte;
    }

    public String getHinweis() {
        return hinweis;
    }

    public void setHinweis(String hinweis) {
        this.hinweis = hinweis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Rank rank = (Rank) o;

        return new EqualsBuilder()
                .append(mannschaft, rank.mannschaft)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mannschaft)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Rang", rang)
                .append("Mannschaft", mannschaft)
                .toString();
    }

}
