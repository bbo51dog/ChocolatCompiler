package net.bbo51dog.chocolat.compiler;

import net.bbo51dog.chocolat.compiler.parser.Parser;
import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import net.bbo51dog.chocolat.compiler.tokenizer.Tokenizer;

import java.util.List;

public class ChocolatCompiler {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please pass your code as an argument");
            System.exit(1);
        }
        String input = args[0];
        Tokenizer tokenizer = new Tokenizer(input);
        try {
            List<Token> tokenList = tokenizer.tokenize();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
