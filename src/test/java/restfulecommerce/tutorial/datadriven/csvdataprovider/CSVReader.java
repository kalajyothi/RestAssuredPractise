package restfulecommerce.tutorial.datadriven.csvdataprovider;

import lombok.SneakyThrows;
import tools.jackson.databind.MappingIterator;
import tools.jackson.dataformat.csv.CsvMapper;
import tools.jackson.dataformat.csv.CsvSchema;

import java.io.InputStream;
import java.util.List;

public class CSVReader {

    @SneakyThrows
    public static List<Order> getOrderData(String fileName) {

        InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("File not found: " + fileName);
        }

        CsvSchema schema = CsvSchema.emptySchema().withHeader();


        MappingIterator<Order> iterator;
//        try {
        iterator = new CsvMapper().readerFor(Order.class).with(schema).readValues(inputStream);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return iterator.readAll(); //“Read all remaining rows from the CSV and convert them into a List of Order objects.”

    }


}




