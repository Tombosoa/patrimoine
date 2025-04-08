package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.pro3.BakoCas;
import school.hei.patrimoine.cas.pro3.TianaCas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Devise.MGA;

public class CasSetSupplier implements Supplier<CasSet> {
  @Override
  public CasSet get() {
    LocalDate ajd = LocalDate.of(2025, APRIL, 8);
    LocalDate fin = LocalDate.of(2025, DECEMBER, 31);
    var bakoCas = new BakoCas(
            ajd,
            fin,
            Map.of(new Personne("Bako"), 1.0)
    );
    LocalDate finTiana = LocalDate.of(2026, MARCH, 31);
    var tianaCas = new TianaCas(
            ajd,
            finTiana,
            Map.of(new Personne("Tiana"), 1.0)
    );
    return new CasSet(Set.of(tianaCas), new Argent(19_293_424, MGA));
  }
}

