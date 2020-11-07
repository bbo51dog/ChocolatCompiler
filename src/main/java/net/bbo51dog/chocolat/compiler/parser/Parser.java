package net.bbo51dog.chocolat.compiler.parser;

import net.bbo51dog.chocolat.compiler.tokenizer.Token;

import java.util.List;

public class Parser {

    private final List<Token> tokenList;

    public Parser(List<Token> tokenList) {
        this.tokenList = tokenList;
    }
}
