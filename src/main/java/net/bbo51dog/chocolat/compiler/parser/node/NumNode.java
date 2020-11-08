package net.bbo51dog.chocolat.compiler.parser.node;

import net.bbo51dog.chocolat.compiler.Type;

public class NumNode extends TerminalNode {

    private final int num;

    public NumNode(String value) {
        super(Type.NUM, value);
        this.num = Integer.parseInt(value);
    }

    public int getNum() {
        return num;
    }
}
