package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class NataMeilleurCas implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        var nata = new Personne("Nata");
        var au02Avril25 = LocalDate.of(2025, APRIL, 02);
        var compteCourant = new Compte("BMOI", au02Avril25.minusDays(1), au02Avril25, ariary(2_000_000));
        new FluxArgent(
                "Salaire",
                compteCourant,
                LocalDate.of(2025, JUNE, 1),
                LocalDate.of(2027, DECEMBER, 31),
                3,
                ariary(1_000_000)
        );
        var trainDeVie =
                new GroupePossession(
                        "Train de vie",
                        MGA,
                        au02Avril25,
                        Set.of(
                                new FluxArgent(
                                        "Courses",
                                        compteCourant,
                                        LocalDate.of(2025, JUNE, 2),
                                        LocalDate.of(2027, DECEMBER,31),
                                        1,
                                        ariary(-500_000)
                                )
                        )
                );
        var monJoliPc = new Materiel(
                "Joli Pc très chère",
                au02Avril25,
                au02Avril25,
                ariary(4_000_000),
                -0.9
        );

        var compteEpargne = new Compte(
                "CE",
                LocalDate.of(2025, Calendar.JUNE, 1),
                ariary(0)
        );
        new TransfertArgent(
                "Salaire",
                compteCourant,
                compteEpargne,
                LocalDate.of(2025, JUNE, 1),
                LocalDate.of(2027, DECEMBER, 31),
                3,
                ariary(700_000)
        );
        return Patrimoine.of(
                "Nata meilleur cas",
                MGA,
                au02Avril25,
                nata,
                Set.of(compteCourant, compteEpargne, trainDeVie, monJoliPc)
        );
    }
}
