package designPattern.filter2;

import java.util.List;
public interface Filter {
    List<Product> filter(List<Product> products);
}