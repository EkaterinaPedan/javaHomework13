import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistsException;
import ru.netology.NotFoundException;
import ru.netology.Product;
import ru.netology.ShopRepository;

public class ShopRepositoryTest {
    ShopRepository shop = new ShopRepository();

    Product item1 = new Product(11, "Product 1", 111);
    Product item2 = new Product(22, "Product 2", 222);
    Product item3 = new Product(22, "Product 3", 333);

    @Test
    public void removeExistingElement() {
        shop.add(item1);
        shop.add(item2);

        shop.removeById(11);

        Product[] expected = {item2};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeNonExistingElement() {
        shop.add(item1);
        shop.add(item2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.removeById(33);
        });
    }

    @Test
    public void shouldAddElement() {
        shop.add(item1);
        shop.add(item2);

        Product[] expected = {item1, item2};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddElement() {
        shop.add(item1);
        shop.add(item2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shop.add(item3);
        });
    }
}
