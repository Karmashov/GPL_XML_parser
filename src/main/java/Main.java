import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {

    private static String dataFile = "data/GPL4.1.xml";

    public static void main(String[] args) throws Exception {

        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        XMLItemHandler handler = new XMLItemHandler();

        parser.parse(new File(dataFile),handler);

//        BufferedReader br = new BufferedReader(new FileReader(dataFile));
//        while (br.ready()) {
//            for (int i = 0; i < 1; i++) {
//                System.out.println(br.readLine());
//            }
//        }
    }
}
