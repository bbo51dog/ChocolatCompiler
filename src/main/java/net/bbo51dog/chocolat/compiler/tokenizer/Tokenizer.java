package net.bbo51dog.chocolat.compiler.tokenizer;

import net.bbo51dog.chocolat.compiler.Type;

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
        tokenList.add(new Token(Type.EOF, ""));
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

    private boolean isSeparator(char c) {
        return c == '(' || c == ')';
    }

    private Token sign() throws Exception {
        char c = nextChar();
        Type type = null;
        switch (c) {
            case '+':
                type = Type.ADD;
                break;

            case '-':
                type = Type.SUB;
                break;

            case '*':
                type = Type.MUL;
                break;

            case '/':
                type = Type.DIV;
                break;
        }
        return new Token(type, Character.toString(c));
    }

    private Token number() throws Exception {
        StringBuilder b = new StringBuilder();
        b.append(nextChar());
        while (!isEOF() && Character.isDigit(currentChar())) {
            b.append(nextChar());
        }
        return new Token(Type.NUM, b.toString());
    }

    private Token variable() throws Exception {
        StringBuilder b = new StringBuilder();
        b.append(nextChar());
        while (!isEOF() && (Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()))) {
            b.append(nextChar());
        }
        return new Token(Type.VARIABLE, b.toString());
    }

    private Token separator() throws Exception {
        Type type = null;
        char c = nextChar();
        switch (c) {
            case '(':
                type = Type.LPAREN;
                break;

            case ')':
                type = Type.RPAREN;
                break;
        }
        return new Token(type, String.valueOf(c));
    }

    private Token nextToken() throws Exception {
        skipSpace();
        if (isEOF()) {
            return null;
        }
        char c = currentChar();
        if (isSignStart(c)) {
            return sign();
        } else if (isNumberStart(c)) {
            return number();
        } else if (isVariableStart(c)) {
            return variable();
        } else if (isSeparator(c)) {
            return separator();
        } else {
            throw new Exception("Not a character for tokens");
        }
    }
}
