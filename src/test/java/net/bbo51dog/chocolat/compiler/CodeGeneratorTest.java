package net.bbo51dog.chocolat.compiler;

import static org.junit.Assert.*;

import net.bbo51dog.chocolat.compiler.code.CodeGenerator;
import net.bbo51dog.chocolat.compiler.parser.node.Node;
import net.bbo51dog.chocolat.compiler.parser.node.NonTerminalNode;
import net.bbo51dog.chocolat.compiler.parser.node.NumNode;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void codeGenerateTest() {
        Node node = new NonTerminalNode(Type.ADD, "+", new NumNode("1"), new NumNode("2"));
        String code = new CodeGenerator(node).generate();
        String expected =
            ".intel_syntax noprefix\n" +
            ".global _main\n" +
            "_main:\n" +
            "  push 1\n" +
            "  push 2\n" +
            "  pop rdi\n" +
            "  pop rax\n" +
            "  add rax, rdi\n" +
            "  push rax\n" +
            "  pop rax\n" +
            "  ret";
        assertEquals(expected, code);
    }
}
