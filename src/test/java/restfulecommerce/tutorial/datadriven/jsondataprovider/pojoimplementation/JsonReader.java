package restfulecommerce.tutorial.datadriven.jsondataprovider.pojoimplementation;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {

    public static List<Order> getOrderData(String fileName) {

        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("File not found: " + fileName);

        }

        try (Reader reader = new InputStreamReader(inputStream)) {

            Type listType = new TypeToken<List<Order>>() {
            }.getType();//That line looks scary at first, but it’s actually solving one very specific Java problem: how to preserve generic type information (like List<Order>) at runtime.
            //In Java, generics are erased at runtime (this is called type erasure)
            //So Java: List<Order> orders = new ArrayList<>();  --> At runtime, Java only sees: List --> It forgets Order
            //Problem with Gson: When you use Gson to convert JSON:  List<Order> orders = gson.fromJson(json, List.class); --> This doesn’t work correctly because Gson doesn’t know: What type is inside the list? --> So it gives you something like: List<LinkedTreeMap>
            //Solution: TypeToken --> This is where TypeToken comes in. --> It helps capture full generic type information.
            //Step-by-Step Breakdown -->
            //1. TypeToken<List<Order>>() --> This tells Java: “I want the type of List<Order>”
            //2. {} --> new TypeToken<List<Order>>() {} --> This creates an anonymous subclass --> Why?: Because Java only preserves generic type info in subclasses, not in normal objects.(This is the trick that avoids type erasure.)
            //3. .getType() --> This returns a Type object that represents:List<Order>
            //4. Final Result --> Type listType = ... -->  Now listType contains full information about List<Order>
            //  Now Gson knows: It’s a List
            //                  Each element is an Order
            // Simple Analogy
            // Think of it like this:
            //List.class → “This is a box”
            //List<Order> → “This is a box full of Orders”
            //👉 TypeToken tells Gson what’s inside the box.
//            Key Concepts You Just Learned
//            Type Erasure → Java removes generic info at runtime
//            TypeToken → Restores that info
//            Anonymous Class {} → Keeps generic type alive
//            Type → Represents full type (List<Order>)

            return new Gson().fromJson(reader, listType);

        } catch (IOException e) {

            throw new RuntimeException("Error reading the file: " + fileName);

        }

    }

}
