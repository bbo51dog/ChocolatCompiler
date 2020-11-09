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
        if (consume(Type.ADD)) {
            node = new NonTerminalNode(Type.ADD, currentToken().getValue(), node, mul());
        } else if (consume(Type.SUB)) {
            node = new NonTerminalNode(Type.SUB, currentToken().getValue(), node, mul());
        }
        return node;
    }

    private Node mul() throws Exception {
        Node node = unary();
        if (consume(Type.MUL)) {
            node = new NonTerminalNode(Type.MUL, currentToken().getValue(), node, unary());
        } else if (consume(Type.DIV)) {
            node = new NonTerminalNode(Type.DIV, currentToken().getValue(), node, unary());
        }
        return node;
    }

    private Node unary() throws Exception {
        Node node;
        if (consume(Type.ADD)) {
            node = primary();
        } else if (consume(Type.SUB)) {
            node = new NonTerminalNode(Type.SUB, "-", new NumNode("0"), primary());
        } else {
            node = primary();
        }
        return node;
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
