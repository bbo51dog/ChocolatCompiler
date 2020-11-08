package net.bbo51dog.chocolat.compiler.parser.node;

import net.bbo51dog.chocolat.compiler.Type;

public class NonTerminalNode implements Node {

    private final Type type;

    private final String value;

    private final Node left;

    private final Node right;

    public NonTerminalNode(Type type, String value, Node left, Node right) {
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
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
        return left;
    }

    @Override
    public Node getRight() {
        return right;
    }
}
