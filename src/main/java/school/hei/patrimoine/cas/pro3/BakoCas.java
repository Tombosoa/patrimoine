package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static java.time.Month.*;
import static java.time.Month.MAY;
import static school.hei.patrimoine.modele.Devise.MGA;

public class BakoCas extends Cas {
    public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Cas de Bako";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {
        LocalDate le08Avril = LocalDate.of(2025, APRIL, 8);
        var bni = new Compte(
                "BNI",
                le08Avril,
                new Argent(2_000_000, MGA)
        );
        var bmoi = new Compte(
                "BMOI",
                le08Avril,
                new Argent(625_000, MGA)
        );
        var coffre = new Compte(
                "coffre à la maison",
                le08Avril,
                new Argent(1_750_000, MGA)
        );
        new FluxArgent(
                "salaire",
                bni,
                le08Avril,
                le08Avril.plusMonths(8),
                2,
                new Argent(2_125_000, MGA)
        );
        new FluxArgent(
                "ponction",
                bni,
                le08Avril,
                le08Avril.plusMonths(8),
                3,
                new Argent(-200_000, MGA)
        );
        new FluxArgent(
                "epargne",
                bmoi,
                le08Avril,
                le08Avril.plusMonths(8),
                3,
                new Argent(200_000, MGA)
        );
        new FluxArgent(
                "loyer",
                bni,le08Avril,
                le08Avril.plusMonths(8),
                26,
                new Argent(-600_000, MGA)
        );
        new FluxArgent(
                "vivre",
                bni,
                le08Avril,
                le08Avril.plusMonths(8),
                1,
                new Argent(-700_000, MGA)
        );
        var ordi = new Materiel(
                "ordinateur",
                le08Avril,
                le08Avril,
                new Argent(3_000_000, MGA),
                -1.2
        );

        return Set.of(bmoi,bni, coffre, ordi);
    }
}
