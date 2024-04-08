package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private int id;
    private int quantidade;
    private String nome;
    private double preco;
    private String categoria;
}
