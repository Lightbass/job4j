package ru.job4j.magnet;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.InputStream;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса ParseXML.
 * @author Alexey Makarov
 * @since 01.10.18
 * @version 0.1
 */
public class ParseXMLTest {

    public void whenGenerateNObjectsAndConvertInXMLThenParseOk(int n, long answer) {
        try (StoreSQL storeSQL = new StoreSQL(new Config())) {
            StoreXML storeXML = new StoreXML(new File("simpleXML.txt"));
            ConvertXSQT convertXSQT = new ConvertXSQT();
            storeSQL.generate(n);
            storeXML.save(storeSQL.getEntriesFromDatabase());
            File dest = new File("advancedXML.txt");
            File scheme = new File(getClass().getClassLoader().getResource("magnet/scheme.xsl").getFile());
            convertXSQT.convert(new File("simpleXML.txt"), dest, scheme);
            ParseXML parseXML = new ParseXML(dest);
            assertThat(parseXML.getSum(), is(answer));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenGenerate10ObjectsAndConvertInXMLThen55ParseOk() {
        whenGenerateNObjectsAndConvertInXMLThenParseOk(10, 55);
    }

    @Test
    public void whenGenerateBillionObjectsAndConvertInXMLThenParseOk() {
        whenGenerateNObjectsAndConvertInXMLThenParseOk(1000000, 500000500000L);
    }
}
