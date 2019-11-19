package parse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class XMLParser {

    //file to Object
    public <T extends Serializable> T deserializable(Class<T> deserizableClass, String xmlFilePath) throws JAXBException, FileNotFoundException {

        File xmlFile = new File(xmlFilePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(deserizableClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        T t = (T) unmarshaller.unmarshal(xmlFile);

        return t;
    }

    //Object to File
    public <T extends Serializable> File serializable(T serizableClass, String xmlFilePath) throws JAXBException, IOException {

        File file = new File(xmlFilePath);
        JAXBContext context = JAXBContext.newInstance(serizableClass.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(serizableClass, file);

        return file;
    }

}
