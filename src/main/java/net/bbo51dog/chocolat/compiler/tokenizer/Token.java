package net.bbo51dog.chocolat.compiler.tokenizer;

import net.bbo51dog.chocolat.compiler.Type;

public class Token {

    private final Type type;

    private final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
