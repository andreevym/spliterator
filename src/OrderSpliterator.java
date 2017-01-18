import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * ЗАКАЗ
 */
public class OrderSpliterator implements Spliterator<Order> {

    private final Spliterator<String> lineSpliterator;

    public OrderSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Order> action) {
        final Order order = new Order();

        if (this.lineSpliterator.tryAdvance(order::setTitle)    &&
            this.lineSpliterator.tryAdvance(order::setFrom)     &&
            this.lineSpliterator.tryAdvance(order::setTo)       &&
            this.lineSpliterator.tryAdvance(order::setPrice)) {

            action.accept(order);
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<Order> trySplit() {
        return null; // we don't going to use parallel
    }

    @Override
    public long estimateSize() {
        return this.lineSpliterator.estimateSize() / 4;
    }

    @Override
    public int characteristics() {
        return this.lineSpliterator.characteristics();
    }
}
