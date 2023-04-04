package com.fightclub.core.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand {

    private String name;
    private String password;
    private String repeatPassword;
    private String email;

}
