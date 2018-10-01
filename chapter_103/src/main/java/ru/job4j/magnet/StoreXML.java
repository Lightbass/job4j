package ru.job4j.magnet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.List;

/**
 * Класс - для работы с XML.
 * @author Alexey Makarov
 * @since 01.10.2018
 * @version 0.1
 */
public class StoreXML {
    private File target;

    /**
     * Класс - контейнер для вывода полей в XML формат.
     */
    @XmlRootElement
    static class Entries {
        private List<Entry> list;

        public Entries() { }

        public Entries(List<Entry> list) {
            this.list = list;
        }

        public List<Entry> getEntry() {
            return list;
        }

        public void setEntry(List<Entry> list) {
            this.list = list;
        }
    }

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Метод сохраняет поля в формате XML.
     * @param list поля, которые нужно экспортировать в формате XML.
     * @return {@code true}, если конвертация прошла успешно, {@code false}, если нет.
     */
    public boolean save(List<Entry> list) {
        boolean result = false;
        try (OutputStream os = new FileOutputStream(target)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), os);
            result = true;
        } catch (JAXBException | IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
