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
}
