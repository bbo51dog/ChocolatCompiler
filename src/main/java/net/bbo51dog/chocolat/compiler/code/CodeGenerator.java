package net.bbo51dog.chocolat.compiler.code;

import net.bbo51dog.chocolat.compiler.parser.node.Node;
import net.bbo51dog.chocolat.compiler.parser.node.NonTerminalNode;
import net.bbo51dog.chocolat.compiler.parser.node.NumNode;

public class CodeGenerator {

    private final Node node;

    public CodeGenerator(Node node) {
        this.node = node;
    }

    public String generate() {
        String code =
            ".intel_syntax noprefix\n" +
            ".global _main\n" +
            "_main:\n";
        code += generateFromNode(node);
        code +=
            "  pop rax\n" +
            "  ret";
        return code;
    }

    private String generateFromNode(Node node) {
        String code = "";
        if (node instanceof NumNode) {
            code += "  push " + node.getValue() + "\n";
        } else if (node instanceof NonTerminalNode) {
            code += generateFromNode(node.getLeft());
            code += generateFromNode(node.getRight());
            code +=
                "  pop rdi\n" +
                "  pop rax\n";

            switch (node.getType()) {
                case ADD:
                    code += "  add rax, rdi\n";
                    break;

                case SUB:
                    code += "  sub rax, rdi\n";
                    break;

                case MUL:
                    code += "  imul rax, rdi\n";
                    break;

                case DIV:
                    code +=
                        "  cqo\n" +
                        "  idiv rdi\n";
                    break;
            }
            code += "  push rax\n";
        }
        return code;
    }
}
