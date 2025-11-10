package com.tryspringboot.hw2_s133334.Model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team{
    private Long TeamID;
    private String TeamName;
    private String continent;
    private int Won;
    private int Drawn;
    private int Lost;
}

