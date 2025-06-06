package br.com.theodorol.percistence.entity;

import java.util.stream.Stream;

public enum BoardColumnKindEnum {
    INITIAL, FINAL, CANCEL, PENDING;

    public static  BoardColumnKindEnum findByName(String name){
        return Stream.of(BoardColumnKindEnum.values())
                .filter(b -> b.name().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
