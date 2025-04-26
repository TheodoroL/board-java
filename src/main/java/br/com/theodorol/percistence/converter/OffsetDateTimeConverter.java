package br.com.theodorol.percistence.converter;

import java.time.OffsetDateTime;
import java.sql.Timestamp;

import static java.time.ZoneOffset.UTC;
public class OffsetDateTimeConverter {
    private OffsetDateTimeConverter(){

    }
    public static OffsetDateTime toOffsetDateTime( Timestamp value){
        return OffsetDateTime.ofInstant(value.toInstant(), UTC);
    }
}
