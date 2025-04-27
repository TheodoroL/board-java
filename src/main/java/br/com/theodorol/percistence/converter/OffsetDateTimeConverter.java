package br.com.theodorol.percistence.converter;

import java.time.OffsetDateTime;
import java.sql.Timestamp;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.nonNull;

public class OffsetDateTimeConverter {
    private OffsetDateTimeConverter(){

    }
    public static OffsetDateTime toOffsetDateTime( Timestamp value){
            return nonNull(value) ? OffsetDateTime.ofInstant(value.toInstant(), UTC) : null;
        }
    public static Timestamp toTimestamp(final OffsetDateTime value){
        return nonNull(value) ? Timestamp.valueOf(value.atZoneSameInstant(UTC).toLocalDateTime()) : null;
    }
}
