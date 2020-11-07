package net.bbo51dog.chocolat.compiler.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final String input;

    private int i;

    public Tokenizer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() throws Exception {
        List<Token> tokenList = new ArrayList<>();
        Token token = nextToken();
        while (token != null) {
            tokenList.add(token);
            token = nextToken();
        }
        return tokenList;
    }

    private boolean isEOF() {
        return input.length() <= i;
    }

    private char currentChar() throws Exception {
        if (isEOF()) {
            throw new Exception("No more character");
        }
        return input.charAt(i);
    }

    private char nextChar() throws Exception {
        char c = currentChar();
        ++i;
        return c;
    }

    private void skipSpace() throws Exception {
        while (!isEOF() && Character.isWhitespace(currentChar())) {
            nextChar();
        }
    }

    private boolean isSignStart(char c) {
        return c == '=' || c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isNumberStart(char c) {
        return Character.isDigit(c);
    }

    private boolean isVariableStart(char c) {
        return Character.isAlphabetic(c);
    }

    private Token sign() throws Exception {
        return new Token(TokenType.SIGN, Character.toString(nextChar()));
    }

    private Token number() throws Exception {
        StringBuilder b = new StringBuilder();
        b.append(nextChar());
        while (!isEOF() && Character.isDigit(currentChar())) {
            b.append(nextChar());
        }
        return new Token(TokenType.NUM, b.toString());
    }

    private Token variable() throws Exception {
        StringBuilder b = new StringBuilder();
        b.append(nextChar());
        while (!isEOF() && (Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()))) {
            b.append(nextChar());
        }
        return new Token(TokenType.VARIABLE, b.toString());
    }

    private Token nextToken() throws Exception {
        skipSpace();
        if (isEOF()) {
            return null;
        } else if (isSignStart(currentChar())) {
            return sign();
        } else if (isNumberStart(currentChar())) {
            return number();
        } else if (isVariableStart(currentChar())) {
            return variable();
        } else {
            throw new Exception("Not a character for tokens");
        }
    }
}
