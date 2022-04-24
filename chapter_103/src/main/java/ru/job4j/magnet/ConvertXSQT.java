package ru.job4j.magnet;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;

/**
 * Класс - для конвертации XML в формат HTML.
 * @author Alexey Makarov
 * @since 01.10.2018
 * @version 0.1
 */
public class ConvertXSQT {

    /**
     * Метод конвертирует файл формата XML в формат HTML.
     * @param source источник для загрузки конвертируемого XML.
     * @param dest файл, куда будет записан результат конвертации.
     * @param scheme схема.
     * @return {@code true}, если конвертация прошла успешно, {@code false}, если нет.
     */
    public boolean convert(File source, File dest, File scheme) {
        boolean result = false;
        try {
            System.out.println(new String(Files.readAllBytes(scheme.toPath())));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(new ByteArrayInputStream(Files.readAllBytes(scheme.toPath()))));
            transformer.transform(
                    new StreamSource(new ByteArrayInputStream(Files.readAllBytes(source.toPath()))),
                    new StreamResult(dest));
            result = true;
        } catch (IOException | TransformerException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
