import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {
    private long recordsCount;
    String name, vendor_pn, partnumber, vendor, price_usd, lastElementName;

    public XMLHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch, start, length);

//        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
//            recordsCount++;
            if (lastElementName.equals("name"))
                name = information;
//            if (lastElementName.equals("vendor_pn"))
//                vendor_pn = information;
            if (lastElementName.equals("partnumber"))
                partnumber = information;
            if (lastElementName.equals("vendor"))
                vendor = information;
            if (lastElementName.equals("price_usd"))
                price_usd = information;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (lastElementName.equals("price_usd")) {
                if (vendor.equals("CISCO")) {
                    recordsCount++;

//                    System.out.println(name +
//        //                        " - " + vendor_pn +
//                            " - " + partnumber +
//                            " - " + vendor +
//                            " - " + price_usd);

                    DBConnection.multiInsertQuery(name,
                            partnumber,
                            vendor,
                            price_usd);

                    if ((recordsCount % 1000) == 0) {
                        DBConnection.executeMultiInsert();
                    }

                    name = null;
                    vendor_pn = null;
                    partnumber = null;
                    vendor = null;
                    price_usd = null;
                }
            }
            if (qName.equals("catalog_item")) {
                DBConnection.executeMultiInsert();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
