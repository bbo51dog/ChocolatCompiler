package net.bbo51dog.chocolat.compiler.parser;

import net.bbo51dog.chocolat.compiler.parser.node.Node;
import net.bbo51dog.chocolat.compiler.parser.node.NonTerminalNode;
import net.bbo51dog.chocolat.compiler.parser.node.NumNode;
import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import net.bbo51dog.chocolat.compiler.Type;

import java.util.List;

public class Parser {

    private final List<Token> tokenList;

    private int i = 0;

    public Parser(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public Node parse() throws Exception {
        return expr();
    }

    private boolean consume(Type type) {
        if (type != currentToken().getType()) {
            return false;
        }
        i++;
        return true;
    }

    private void expect(Type type) throws Exception {
        if (!consume(type)) {
            throw new Exception("Type " + type.name() + " expected, " + currentToken().getType().name() + " found");
        }
    }

    private Token currentToken() {
        return tokenList.get(i);
    }

    private Node expr() throws Exception {
        Node node = mul();

        for (;;) {
            if (consume(Type.ADD)) {
                node = new NonTerminalNode(Type.ADD, currentToken().getValue(), node, mul());
            } else if (consume(Type.SUB)) {
                node = new NonTerminalNode(Type.SUB, currentToken().getValue(), node, mul());
            } else {
                return node;
            }
        }
    }

    private Node mul() throws Exception {
        Node node = primary();

        for (;;) {
            if (consume(Type.MUL)) {
                node = new NonTerminalNode(Type.MUL, currentToken().getValue(), node, primary());
            } else if (consume(Type.DIV)) {
                node = new NonTerminalNode(Type.DIV, currentToken().getValue(), node, primary());
            } else {
                return node;
            }
        }
    }

    private Node primary() throws Exception {
        if (consume(Type.LPAREN)) {
            Node node = expr();
            expect(Type.RPAREN);
            return node;
        }
        Node node = new NumNode(currentToken().getValue());
        expect(Type.NUM);
        return node;
    }
}
