import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static String dataFile = "data/GPL3.xml";

    public static void main(String[] args) throws Exception {
//        List<String> lines = null;
//        try {
//            lines = Files.readAllLines(Paths.get(dataFile));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(lines);

        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        XMLHandler handler = new XMLHandler();

        parser.parse(new File(dataFile),handler);
    }
}
