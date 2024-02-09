import java.time.*;
import java.time.chrono.*;
import java.time.temporal.*;

public class SulaChronology extends AbstractChronology {

    private static final SulaChronology INSTANCE = new SulaChronology();

    private SulaChronology() {}

    public static SulaChronology getInstance() {
        return INSTANCE;
    }

    @Override
    public String getId() {
        return "Sula";
    }

    @Override
    public String getCalendarType() {
        return "sula";
    }

    @Override
    public ChronoLocalDate date(int prolepticYear, int month, int dayOfMonth) {
        return new SulaDate(this, prolepticYear, month, dayOfMonth);
    }

    @Override
    public ChronoLocalDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new SulaDate(this, prolepticYear, 1, 1).plusDays(dayOfYear - 1);
    }

    @Override
    public ChronoLocalDate dateEpochDay(long epochDay) {
        return new SulaDate(this, LocalDate.ofEpochDay(epochDay));
    }

    @Override
    public ChronoLocalDate date(TemporalAccessor temporal) {
        if (temporal instanceof SulaDate) {
            return (SulaDate) temporal;
        }
        return new SulaDate(this, LocalDate.from(temporal));
    }

    @Override
    public boolean isLeapYear(long prolepticYear) {
        return Year.isLeap(prolepticYear);
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        return (era == Era.CE ? yearOfEra : 1 - yearOfEra);
    }

    @Override
    public Era eraOf(int eraValue) {
        return (eraValue == 1 ? Era.CE : Era.BCE);
    }

    @Override
    public List<Era> eras() {
        return Arrays.asList(Era.values());
    }

    @Override
    public ValueRange range(ChronoField field) {
        switch (field) {
            case DAY_OF_MONTH:
                return ValueRange.of(1, 30);
            case MONTH_OF_YEAR:
                return ValueRange.of(1, 13);
            default:
                return field.range();
        }
    }
}









/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package java.time.chrono;

        import java.io.InvalidObjectException;
        import java.io.ObjectInputStream;
        import java.io.Serializable;
        import java.time.Clock;
        import java.time.DateTimeException;
        import java.time.Instant;
        import java.time.LocalDate;
        import java.time.ZoneId;
        import java.time.format.ResolverStyle;
        import java.time.temporal.ChronoField;
        import java.time.temporal.ChronoUnit;
        import java.time.temporal.TemporalAccessor;
        import java.time.temporal.TemporalAdjusters;
        import java.time.temporal.TemporalField;
        import java.time.temporal.UnsupportedTemporalTypeException;
        import java.time.temporal.ValueRange;
        import java.util.Calendar;
        import java.util.List;
        import java.util.Locale;
        import java.util.Map;
        import java.util.TimeZone;
        import sun.util.calendar.CalendarSystem;
        import sun.util.calendar.LocalGregorianCalendar;

public final class JapaneseChronology extends AbstractChronology implements Serializable {
    static final LocalGregorianCalendar JCAL = (LocalGregorianCalendar)CalendarSystem.forName("japanese");
    static final Locale LOCALE = Locale.forLanguageTag("ja-JP-u-ca-japanese");
    public static final JapaneseChronology INSTANCE = new JapaneseChronology();
    private static final long serialVersionUID = 459996390165777884L;

    private JapaneseChronology() {
    }

    public String getId() {
        return "Japanese";
    }

    public String getCalendarType() {
        return "japanese";
    }

    public JapaneseDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        if (era instanceof JapaneseEra jera) {
            return JapaneseDate.of(jera, yearOfEra, month, dayOfMonth);
        } else {
            throw new ClassCastException("Era must be JapaneseEra");
        }
    }

    public JapaneseDate date(int prolepticYear, int month, int dayOfMonth) {
        return new JapaneseDate(LocalDate.of(prolepticYear, month, dayOfMonth));
    }

    public JapaneseDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return JapaneseDate.ofYearDay((JapaneseEra)era, yearOfEra, dayOfYear);
    }

    public JapaneseDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new JapaneseDate(LocalDate.ofYearDay(prolepticYear, dayOfYear));
    }

    public JapaneseDate dateEpochDay(long epochDay) {
        return new JapaneseDate(LocalDate.ofEpochDay(epochDay));
    }

    public JapaneseDate dateNow() {
        return this.dateNow(Clock.systemDefaultZone());
    }

    public JapaneseDate dateNow(ZoneId zone) {
        return this.dateNow(Clock.system(zone));
    }

    public JapaneseDate dateNow(Clock clock) {
        return this.date(LocalDate.now(clock));
    }

    public JapaneseDate date(TemporalAccessor temporal) {
        return temporal instanceof JapaneseDate ? (JapaneseDate)temporal : new JapaneseDate(LocalDate.from(temporal));
    }

    public ChronoLocalDateTime<JapaneseDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear);
    }

    public int prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof JapaneseEra jera)) {
            throw new ClassCastException("Era must be JapaneseEra");
        } else {
            int gregorianYear = jera.getPrivateEra().getSinceDate().getYear() + yearOfEra - 1;
            if (yearOfEra == 1) {
                return gregorianYear;
            } else {
                if (gregorianYear >= -999999999 && gregorianYear <= 999999999) {
                    LocalGregorianCalendar.Date jdate = JCAL.newCalendarDate((TimeZone)null);
                    jdate.setEra(jera.getPrivateEra()).setDate(yearOfEra, 1, 1);
                    if (JCAL.validate(jdate)) {
                        return gregorianYear;
                    }
                }

                throw new DateTimeException("Invalid yearOfEra value");
            }
        }
    }

    public JapaneseEra eraOf(int eraValue) {
        return JapaneseEra.of(eraValue);
    }

    public List<Era> eras() {
        return List.of(JapaneseEra.values());
    }

    JapaneseEra getCurrentEra() {
        JapaneseEra[] eras = JapaneseEra.values();
        return eras[eras.length - 1];
    }

    public ValueRange range(ChronoField field) {
        Calendar jcal;
        int fieldIndex;
        switch (field) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
            case ALIGNED_WEEK_OF_MONTH:
            case ALIGNED_WEEK_OF_YEAR:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
            case YEAR_OF_ERA:
                jcal = Calendar.getInstance(LOCALE);
                fieldIndex = this.getCurrentEra().getPrivateEra().getSinceDate().getYear();
                return ValueRange.of(1L, (long)jcal.getGreatestMinimum(1), (long)(jcal.getLeastMaximum(1) + 1), (long)(999999999 - fieldIndex));
            case DAY_OF_YEAR:
                jcal = Calendar.getInstance(LOCALE);
                fieldIndex = 6;
                return ValueRange.of((long)jcal.getMinimum(fieldIndex), (long)jcal.getGreatestMinimum(fieldIndex), (long)jcal.getLeastMaximum(fieldIndex), (long)jcal.getMaximum(fieldIndex));
            case YEAR:
                return ValueRange.of((long)JapaneseDate.MEIJI_6_ISODATE.getYear(), 999999999L);
            case ERA:
                return ValueRange.of((long)JapaneseEra.MEIJI.getValue(), (long)this.getCurrentEra().getValue());
            default:
                return field.range();
        }
    }

    public JapaneseDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (JapaneseDate)super.resolveDate(fieldValues, resolverStyle);
    }

    ChronoLocalDate resolveYearOfEra(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        Long eraLong = (Long)fieldValues.get(ChronoField.ERA);
        JapaneseEra era = null;
        if (eraLong != null) {
            era = this.eraOf(this.range(ChronoField.ERA).checkValidIntValue(eraLong, ChronoField.ERA));
        }

        Long yoeLong = (Long)fieldValues.get(ChronoField.YEAR_OF_ERA);
        int yoe = 0;
        if (yoeLong != null) {
            yoe = this.range(ChronoField.YEAR_OF_ERA).checkValidIntValue(yoeLong, ChronoField.YEAR_OF_ERA);
        }

        if (era == null && yoeLong != null && !fieldValues.containsKey(ChronoField.YEAR) && resolverStyle != ResolverStyle.STRICT) {
            era = JapaneseEra.values()[JapaneseEra.values().length - 1];
        }

        if (yoeLong != null && era != null) {
            if (fieldValues.containsKey(ChronoField.MONTH_OF_YEAR) && fieldValues.containsKey(ChronoField.DAY_OF_MONTH)) {
                return this.resolveYMD(era, yoe, fieldValues, resolverStyle);
            }

            if (fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
                return this.resolveYD(era, yoe, fieldValues, resolverStyle);
            }
        }

        return null;
    }

    private int prolepticYearLenient(JapaneseEra era, int yearOfEra) {
        return era.getPrivateEra().getSinceDate().getYear() + yearOfEra - 1;
    }

    private ChronoLocalDate resolveYMD(JapaneseEra era, int yoe, Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        fieldValues.remove(ChronoField.ERA);
        fieldValues.remove(ChronoField.YEAR_OF_ERA);
        int moy;
        if (resolverStyle == ResolverStyle.LENIENT) {
            moy = this.prolepticYearLenient(era, yoe);
            long months = Math.subtractExact((Long)fieldValues.remove(ChronoField.MONTH_OF_YEAR), 1L);
            long days = Math.subtractExact((Long)fieldValues.remove(ChronoField.DAY_OF_MONTH), 1L);
            return this.date(moy, 1, 1).plus(months, ChronoUnit.MONTHS).plus(days, ChronoUnit.DAYS);
        } else {
            moy = this.range(ChronoField.MONTH_OF_YEAR).checkValidIntValue((Long)fieldValues.remove(ChronoField.MONTH_OF_YEAR), ChronoField.MONTH_OF_YEAR);
            int dom = this.range(ChronoField.DAY_OF_MONTH).checkValidIntValue((Long)fieldValues.remove(ChronoField.DAY_OF_MONTH), ChronoField.DAY_OF_MONTH);
            if (resolverStyle == ResolverStyle.SMART) {
                if (yoe < 1) {
                    throw new DateTimeException("Invalid YearOfEra: " + yoe);
                } else {
                    int y = this.prolepticYearLenient(era, yoe);

                    JapaneseDate result;
                    try {
                        result = this.date(y, moy, dom);
                    } catch (DateTimeException var10) {
                        result = this.date(y, moy, 1).with(TemporalAdjusters.lastDayOfMonth());
                    }

                    if (result.getEra() != era && result.get(ChronoField.YEAR_OF_ERA) > 1 && yoe > 1) {
                        throw new DateTimeException("Invalid YearOfEra for Era: " + era + " " + yoe);
                    } else {
                        return result;
                    }
                }
            } else {
                return this.date(era, yoe, moy, dom);
            }
        }
    }

    private ChronoLocalDate resolveYD(JapaneseEra era, int yoe, Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        fieldValues.remove(ChronoField.ERA);
        fieldValues.remove(ChronoField.YEAR_OF_ERA);
        int doy;
        if (resolverStyle == ResolverStyle.LENIENT) {
            doy = this.prolepticYearLenient(era, yoe);
            long days = Math.subtractExact((Long)fieldValues.remove(ChronoField.DAY_OF_YEAR), 1L);
            return this.dateYearDay(doy, 1).plus(days, ChronoUnit.DAYS);
        } else {
            doy = this.range(ChronoField.DAY_OF_YEAR).checkValidIntValue((Long)fieldValues.remove(ChronoField.DAY_OF_YEAR), ChronoField.DAY_OF_YEAR);
            return this.dateYearDay(era, yoe, doy);
        }
    }

    Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
*/
