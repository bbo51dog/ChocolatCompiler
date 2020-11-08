package net.bbo51dog.chocolat.compiler.parser.node;

import net.bbo51dog.chocolat.compiler.Type;

public class TerminalNode implements Node {

    private final Type type;

    private final String value;

    public TerminalNode(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Node getLeft() {
        return null;
    }

    @Override
    public Node getRight() {
        return null;
    }
}
