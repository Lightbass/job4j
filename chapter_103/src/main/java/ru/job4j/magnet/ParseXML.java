package ru.job4j.magnet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ParseXML extends DefaultHandler {
    private long result;
    private File file;

    /**
     * Инициализирует поле файла, из которого будет загружаться XML.
     * @param file файл.
     */
    public ParseXML(File file) {
        this.file = file;
    }

    /**
     * Метод складывает все числа находящиеся в полях entry.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            result += Integer.parseInt(attributes.getValue(0));
        }
    }

    /**
     * Метод возвращает сумму полей.
     * @return сумма.
     */
    public long getSum() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
