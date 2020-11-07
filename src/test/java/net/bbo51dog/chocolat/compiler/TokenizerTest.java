package net.bbo51dog.chocolat.compiler;

import static org.junit.Assert.*;

import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import net.bbo51dog.chocolat.compiler.tokenizer.TokenType;
import net.bbo51dog.chocolat.compiler.tokenizer.Tokenizer;
import org.junit.Test;

import java.util.List;

public class TokenizerTest {

    @Test
    public void testTokenize() throws Exception {
        List<Token> tokenList = new Tokenizer("1 + 23").tokenize();
        assertEquals(tokenList.get(0).getType(), TokenType.NUM);
        assertEquals(tokenList.get(0).getValue(), "1");
        assertEquals(tokenList.get(1).getType(), TokenType.SIGN);
        assertEquals(tokenList.get(1).getValue(), "+");
        assertEquals(tokenList.get(2).getType(), TokenType.NUM);
        assertEquals(tokenList.get(2).getValue(), "23");
    }
}