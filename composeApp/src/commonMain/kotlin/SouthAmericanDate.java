//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package java.time.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Objects;
import java.time.chrono.SouthAmericanChronology;

@ValueBased
public final class SouthAmericanDate extends ChronoLocalDateImpl<SouthAmericanDate> implements ChronoLocalDate, Serializable {
    private static final long serialVersionUID = 1300372329181994526L;
    private final transient LocalDate isoDate;

    public static SouthAmericanDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static SouthAmericanDate now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static SouthAmericanDate now(Clock clock) {
        return new SouthAmericanDate(LocalDate.now(clock));
    }

    public static SouthAmericanDate of(int prolepticYear, int month, int dayOfMonth) {
        return new SouthAmericanDate(LocalDate.of(prolepticYear + 1911, month, dayOfMonth));
    }

    public static SouthAmericanDate from(TemporalAccessor temporal) {
        return SouthAmericanChronology.INSTANCE.date(temporal);
    }

    SouthAmericanDate(LocalDate isoDate) {
        Objects.requireNonNull(isoDate, "isoDate");
        this.isoDate = isoDate;
    }

    public SouthAmericanChronology getChronology() {
        return SouthAmericanChronology.INSTANCE;
    }

    public SouthAmericanEra getEra() {
        return this.getProlepticYear() >= 1 ? SouthAmericanEra.ROC : SouthAmericanEra.BEFORE_ROC;
    }

    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    public ValueRange range(TemporalField field) {
        if (field instanceof ChronoField) {
            if (this.isSupported(field)) {
                ChronoField f = (ChronoField)field;
                switch (f) {
                    case DAY_OF_MONTH:
                    case DAY_OF_YEAR:
                    case ALIGNED_WEEK_OF_MONTH:
                        return this.isoDate.range(field);
                    case YEAR_OF_ERA:
                        ValueRange range = ChronoField.YEAR.range();
                        long max = this.getProlepticYear() <= 0 ? -range.getMinimum() + 1L + 1911L : range.getMaximum() - 1911L;
                        return ValueRange.of(1L, max);
                    default:
                        return this.getChronology().range(f);
                }
            } else {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
            }
        } else {
            return field.rangeRefinedBy(this);
        }
    }

    public long getLong(TemporalField field) {
        if (field instanceof ChronoField) {
            switch ((ChronoField)field) {
                case YEAR_OF_ERA:
                    int prolepticYear = this.getProlepticYear();
                    return (long)(prolepticYear >= 1 ? prolepticYear : 1 - prolepticYear);
                case PROLEPTIC_MONTH:
                    return this.getProlepticMonth();
                case YEAR:
                    return (long)this.getProlepticYear();
                case ERA:
                    return (long)(this.getProlepticYear() >= 1 ? 1 : 0);
                default:
                    return this.isoDate.getLong(field);
            }
        } else {
            return field.getFrom(this);
        }
    }

    private long getProlepticMonth() {
        return (long)this.getProlepticYear() * 12L + (long)this.isoDate.getMonthValue() - 1L;
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() - 1911;
    }

    public SouthAmericanDate with(TemporalField field, long newValue) {
        if (field instanceof ChronoField chronoField) {
            if (this.getLong(chronoField) == newValue) {
                return this;
            } else {
                switch (chronoField) {
                    case YEAR_OF_ERA:
                    case YEAR:
                    case ERA:
                        int nvalue = this.getChronology().range(chronoField).checkValidIntValue(newValue, chronoField);
                        switch (chronoField) {
                            case YEAR_OF_ERA:
                                return this.with(this.isoDate.withYear(this.getProlepticYear() >= 1 ? nvalue + 1911 : 1 - nvalue + 1911));
                            case PROLEPTIC_MONTH:
                            default:
                                break;
                            case YEAR:
                                return this.with(this.isoDate.withYear(nvalue + 1911));
                            case ERA:
                                return this.with(this.isoDate.withYear(1 - this.getProlepticYear() + 1911));
                        }
                    default:
                        return this.with(this.isoDate.with(field, newValue));
                    case PROLEPTIC_MONTH:
                        this.getChronology().range(chronoField).checkValidValue(newValue, chronoField);
                        return this.plusMonths(newValue - this.getProlepticMonth());
                }
            }
        } else {
            return (SouthAmericanDate)super.with(field, newValue);
        }
    }

    public SouthAmericanDate with(TemporalAdjuster adjuster) {
        return (SouthAmericanDate)super.with(adjuster);
    }

    public SouthAmericanDate plus(TemporalAmount amount) {
        return (SouthAmericanDate)super.plus(amount);
    }

    public SouthAmericanDate minus(TemporalAmount amount) {
        return (SouthAmericanDate)super.minus(amount);
    }

    SouthAmericanDate plusYears(long years) {
        return this.with(this.isoDate.plusYears(years));
    }

    SouthAmericanDate plusMonths(long months) {
        return this.with(this.isoDate.plusMonths(months));
    }

    SouthAmericanDate plusWeeks(long weeksToAdd) {
        return (SouthAmericanDate)super.plusWeeks(weeksToAdd);
    }

    SouthAmericanDate plusDays(long days) {
        return this.with(this.isoDate.plusDays(days));
    }

    public SouthAmericanDate plus(long amountToAdd, TemporalUnit unit) {
        return (SouthAmericanDate)super.plus(amountToAdd, unit);
    }

    public SouthAmericanDate minus(long amountToAdd, TemporalUnit unit) {
        return (SouthAmericanDate)super.minus(amountToAdd, unit);
    }

    SouthAmericanDate minusYears(long yearsToSubtract) {
        return (SouthAmericanDate)super.minusYears(yearsToSubtract);
    }

    SouthAmericanDate minusMonths(long monthsToSubtract) {
        return (SouthAmericanDate)super.minusMonths(monthsToSubtract);
    }

    SouthAmericanDate minusWeeks(long weeksToSubtract) {
        return (SouthAmericanDate)super.minusWeeks(weeksToSubtract);
    }

    SouthAmericanDate minusDays(long daysToSubtract) {
        return (SouthAmericanDate)super.minusDays(daysToSubtract);
    }

    private SouthAmericanDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new SouthAmericanDate(newDate);
    }

    public final ChronoLocalDateTime<SouthAmericanDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    public ChronoPeriod until(ChronoLocalDate endDate) {
        Period period = this.isoDate.until(endDate);
        return this.getChronology().period(period.getYears(), period.getMonths(), period.getDays());
    }

    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            boolean var10000;
            if (obj instanceof SouthAmericanDate) {
                SouthAmericanDate otherDate = (SouthAmericanDate)obj;
                if (this.isoDate.equals(otherDate.isoDate)) {
                    var10000 = true;
                    return var10000;
                }
            }

            var10000 = false;
            return var10000;
        }
    }

    public int hashCode() {
        return this.getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte)7, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.get(ChronoField.YEAR));
        out.writeByte(this.get(ChronoField.MONTH_OF_YEAR));
        out.writeByte(this.get(ChronoField.DAY_OF_MONTH));
    }

    static SouthAmericanDate readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        int month = in.readByte();
        int dayOfMonth = in.readByte();
        return SouthAmericanChronology.INSTANCE.date(year, month, dayOfMonth);
    }
}
