package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.Correction;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.Set;

import static java.time.Month.APRIL;
import static java.time.Month.JULY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class NataPireCas extends Cas {
    private final Compte financeur;

    public NataPireCas(){
        super(LocalDate.of(2025, APRIL, 2), LocalDate.MAX,new Personne("Nata"));
        financeur = new Compte("Espèces", LocalDate.MIN, ariary(0));
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Nata";
    }

    @Override
    protected void init() {
        new FluxArgent(
                "Init compte Espèces", financeur, LocalDate.of(2025, JULY, 2), ariary(7_000_000)
        );
    }

    @Override
    protected void suivi() {
        new Correction(new FluxArgent("Correction à la hausse", financeur, ajd, ariary(50_000)));
    }

    @Override
    public Set<Possession> possessions() {
        return Set.of();
    }
}
