package Reporting.AFA.Entity;

import java.math.BigDecimal;

public interface Recap {
    String getService();
    BigDecimal getMontantDepot();
    BigDecimal getMontantRetrait();
}
