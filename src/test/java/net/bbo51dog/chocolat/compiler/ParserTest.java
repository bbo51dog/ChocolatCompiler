package net.bbo51dog.chocolat.compiler;

import static org.junit.Assert.*;

import net.bbo51dog.chocolat.compiler.parser.Parser;
import net.bbo51dog.chocolat.compiler.parser.node.Node;
import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import org.junit.Test;

import java.util.List;

public class ParserTest {

    @Test
    public void testExpr() throws Exception {
        Parser parser = new Parser(List.of(
            new Token(Type.NUM, "12"),
            new Token(Type.ADD, "+"),
            new Token(Type.NUM, "34"),
            new Token(Type.EOF, "")
        ));
        Node node = parser.parse();
        assertEquals(Type.ADD, node.getType());
        assertEquals(Type.NUM, node.getLeft().getType());
        assertEquals("12", node.getLeft().getValue());
        assertEquals(Type.NUM, node.getRight().getType());
        assertEquals("34", node.getRight().getValue());
    }

    @Test
    public void testMul() throws Exception {
        Parser parser = new Parser(List.of(
            new Token(Type.LPAREN, "("),
            new Token(Type.NUM, "1"),
            new Token(Type.ADD, "+"),
            new Token(Type.NUM, "2"),
            new Token(Type.RPAREN, ")"),
            new Token(Type.MUL, "*"),
            new Token(Type.NUM, "2"),
            new Token(Type.EOF, "")
        ));
        Node node = parser.parse();
        Node left = node.getLeft();
        assertEquals(Type.MUL, node.getType());
        assertEquals(Type.ADD, left.getType());
        assertEquals(Type.NUM, left.getLeft().getType());
        assertEquals("1", left.getLeft().getValue());
        assertEquals(Type.NUM, left.getRight().getType());
        assertEquals("2", left.getRight().getValue());
        assertEquals(Type.NUM, node.getRight().getType());
        assertEquals("2", node.getRight().getValue());
    }
}
