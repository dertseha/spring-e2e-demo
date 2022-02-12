package com.example.e2edemo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;

    private String name;
}
