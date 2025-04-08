package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Devise.MGA;

public class TianaCas extends Cas {
    public TianaCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Cas de Tiana";
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
        var compte = new Compte(
                "Compte",
                le08Avril,
                new Argent(60_000_000, MGA)
        );
        new FluxArgent(
                "depenses",
                compte,
                le08Avril,
                le08Avril.plusMonths(8),
                1,
                new Argent(-4_000_000, MGA)
        );
        var terrain = new Materiel(
                "Terrain",
                le08Avril,
                le08Avril,
                new Argent(3_000_000, MGA),
                0.1
        );
        /*
        * Son prochain coup entrepreneurial est un projet
        *  de 6 mois du 1 juin 2025 au 31 décembre 2025 :*/
        LocalDate le01juin = LocalDate.of(2025, JUNE, 1);
        LocalDate le31Dec = LocalDate.of(2025, DECEMBER , 8);
        new FluxArgent(
                "PROJETS",
                compte,
                le01juin,
                le31Dec,
                5,
                new Argent(-5_000_000, MGA)
        );
        /*
        * . Ce projet lui rapportera en brut (cad hors dépenses) 70_000_000 Ar
        * à encaisser en 2 fois : 10% 1 mois avant le
        *  lancement (cad 1 mai 2025) et
        * 90% restant 1 mois après la fin (cad 31 janvier 2026)
        * */
        LocalDate le01mai = LocalDate.of(2025, MAY, 1);
        LocalDate le31janvier = LocalDate.of(2026, JANUARY, 31);
        new FluxArgent(
                "Encaissement1",
                compte,
                le01mai,
                le01mai,
                1,
                new Argent(70_000_000*0.1, MGA)
        );
        new FluxArgent(
                "Encaissement2",
                compte,
                le31janvier,
                le31janvier,
                1,
                new Argent(70_000_000*0.9, MGA)
        );
        /*
        * La banque lui prêtera 20_000_000 Ar le 27 juillet 2025*/
        LocalDate le27Juillet = LocalDate.of(2025, JULY, 27);

        Dette dette = new Dette(
                "dette",
                le27Juillet,
                new Argent(-20_000_000, MGA)
        );
        new FluxArgent(
                "Pret bancaire",
                compte,
                le27Juillet,
                le27Juillet,
                27,
                new Argent(20_000_000, MGA)
        );
        /*
        * 2. Tiana remboursera une
        *  mensualité de 2_000_000 Ar
        *  à compter du 27 août 2025 pendant 12 mois
        * (soit un remboursement total de 24_000_000 Ar)
        * */
        LocalDate le27Aout = LocalDate.of(2025, AUGUST, 27);
        new FluxArgent(
                "Remboursement",
                compte,
                le27Aout,
                le27Aout.plusMonths(12),
                27,
                new Argent(2_000_000, MGA)
        );
        return Set.of(compte, terrain, dette);
    }
}
