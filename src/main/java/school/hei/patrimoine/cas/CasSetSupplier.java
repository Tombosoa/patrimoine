package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.pro3.BakoCas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Personne;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
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
    return new CasSet(Set.of(bakoCas), new Argent(11_341_575, MGA));
  }
}

