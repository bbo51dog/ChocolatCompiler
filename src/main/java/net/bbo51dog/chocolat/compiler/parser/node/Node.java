package net.bbo51dog.chocolat.compiler.parser.node;

import net.bbo51dog.chocolat.compiler.Type;

public interface Node {

    Type getType();
    String getValue();
    Node getLeft();
    Node getRight();
}
