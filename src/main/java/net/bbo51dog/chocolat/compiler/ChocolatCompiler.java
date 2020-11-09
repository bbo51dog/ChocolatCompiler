package net.bbo51dog.chocolat.compiler;

import net.bbo51dog.chocolat.compiler.code.CodeGenerator;
import net.bbo51dog.chocolat.compiler.parser.Parser;
import net.bbo51dog.chocolat.compiler.parser.node.Node;
import net.bbo51dog.chocolat.compiler.tokenizer.Token;
import net.bbo51dog.chocolat.compiler.tokenizer.Tokenizer;

import java.io.*;
import java.util.List;

public class ChocolatCompiler {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please pass your file path");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File " + args[0] + " not found");
            System.exit(1);
        }
        String fileName = file.getName();
        if (!fileName.substring(fileName.lastIndexOf('.')).equals(".choc")) {
            System.out.println("File extension must be .choc");
            System.exit(1);
        }
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String string = reader.readLine();
            while (string != null){
                builder.append(string).append("\n");
                string = reader.readLine();
            }
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String input = builder.toString();
        Tokenizer tokenizer = new Tokenizer(input);
        String code = null;
        try {
            List<Token> tokenList = tokenizer.tokenize();
            Parser parser = new Parser(tokenList);
            Node node = parser.parse();
            CodeGenerator generator = new CodeGenerator(node);
            code = generator.generate();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            File writeFile = new File(fileName.substring(0, fileName.lastIndexOf('.')) + ".s");
            FileWriter fw = new FileWriter(writeFile);
            fw.write(code);
            fw.flush();
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
