import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * В этом примере мы создаем собственный Spliterator для чтения объектов из файла orderers.txt
 */
public class Main {

    public static void main(String[] args) {
        try (Stream<String> orders = Files.lines(Paths.get("orders.txt"))) {
            final Spliterator<String> spliterator = orders.spliterator();

            Spliterator<Order> orderSpliterator = new OrderSpliterator(spliterator);

            final boolean isParallel = false;
            Stream<Order> orderStream = StreamSupport.stream(orderSpliterator, isParallel);

            orderStream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("while get path");
            e.printStackTrace();
        }
    }
}
