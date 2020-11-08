package net.bbo51dog.chocolat.compiler;

import static org.junit.Assert.*;

import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import net.bbo51dog.chocolat.compiler.tokenizer.Tokenizer;
import org.junit.Test;

import java.util.List;

public class TokenizerTest {

    @Test
    public void testTokenize() throws Exception {
        List<Token> tokenList = new Tokenizer("(1 + 23) * 4").tokenize();
        assertEquals(Type.LPAREN, tokenList.get(0).getType());
        assertEquals("(", tokenList.get(0).getValue());
        assertEquals(Type.NUM, tokenList.get(1).getType());
        assertEquals("1", tokenList.get(1).getValue());
        assertEquals(Type.ADD, tokenList.get(2).getType());
        assertEquals("+", tokenList.get(2).getValue());
        assertEquals(Type.NUM, tokenList.get(3).getType());
        assertEquals("23", tokenList.get(3).getValue());
        assertEquals(Type.RPAREN, tokenList.get(4).getType());
        assertEquals(")", tokenList.get(4).getValue());
        assertEquals(Type.MUL, tokenList.get(5).getType());
        assertEquals("*", tokenList.get(5).getValue());
        assertEquals(Type.NUM, tokenList.get(6).getType());
        assertEquals("4", tokenList.get(6).getValue());
    }
}