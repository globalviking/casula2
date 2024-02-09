//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

public final class SouthAmericanChronology extends AbstractChronology implements Serializable {
    public static final SouthAmericanChronology INSTANCE = new SouthAmericanChronology();
    private static final long serialVersionUID = 1039765215346859963L;
    static final int DAYS_DIFFERENCE = 171;

    private SouthAmericanChronology() {
    }

    public String getId() {
        return "SouthAmerican";
    }

    public String getCalendarType() {
        return "roc";
    }

    public SouthAmericanDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    public SouthAmericanDate date(int prolepticYear, int month, int dayOfMonth) {
        return new SouthAmericanDate(LocalDate.of(prolepticYear, month, dayOfMonth).plusDays(171));
    }

    public SouthAmericanDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new SouthAmericanDate(LocalDate.ofYearDay(prolepticYear, dayOfYear).plusDays(171));
    }
}

    public SouthAmericanDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new SouthAmericanDate(LocalDate.ofYearDay(prolepticYear + 1911, dayOfYear));
    }

    public SouthAmericanDate dateEpochDay(long epochDay) {
        return new SouthAmericanDate(LocalDate.ofEpochDay(epochDay));
    }

    public SouthAmericanDate dateNow() {
        return this.dateNow(Clock.systemDefaultZone());
    }

    public SouthAmericanDate dateNow(ZoneId zone) {
        return this.dateNow(Clock.system(zone));
    }

    public SouthAmericanDate dateNow(Clock clock) {
        return this.date(LocalDate.now(clock));
    }

    public SouthAmericanDate date(TemporalAccessor temporal) {
        return temporal instanceof SouthAmericanDate ? (SouthAmericanDate)temporal : new SouthAmericanDate(LocalDate.from(temporal));
    }

    public ChronoLocalDateTime<SouthAmericanDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    public ChronoZonedDateTime<SouthAmericanDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    public ChronoZonedDateTime<SouthAmericanDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear + 1911L);
    }

    public int prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof SouthAmericanEra)) {
            throw new ClassCastException("Era must be SouthAmericanEra");
        } else {
            return era == SouthAmericanEra.ROC ? yearOfEra : 1 - yearOfEra;
        }
    }

    public SouthAmericanEra eraOf(int eraValue) {
        return SouthAmericanEra.of(eraValue);
    }

    public List<Era> eras() {
        return List.of(SouthAmericanEra.values());
    }

    public ValueRange range(ChronoField field) {
        ValueRange range;
        switch (field) {
            case PROLEPTIC_MONTH:
                range = ChronoField.PROLEPTIC_MONTH.range();
                return ValueRange.of(range.getMinimum() - 22932L, range.getMaximum() - 22932L);
            case YEAR_OF_ERA:
                range = ChronoField.YEAR.range();
                return ValueRange.of(1L, range.getMaximum() - 1911L, -range.getMinimum() + 1L + 1911L);
            case YEAR:
                range = ChronoField.YEAR.range();
                return ValueRange.of(range.getMinimum() - 1911L, range.getMaximum() - 1911L);
            default:
                return field.range();
        }
    }

    public SouthAmericanDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (SouthAmericanDate)super.resolveDate(fieldValues, resolverStyle);
    }

    Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
