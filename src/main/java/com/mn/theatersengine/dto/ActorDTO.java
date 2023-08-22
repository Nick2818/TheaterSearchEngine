package com.mn.theatersengine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDTO {

    private Long id;

    private String name;

    private String surname;

    private String gender;

    private Integer age;

    private Long theaterId;

}
