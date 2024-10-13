package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * select *
     * from product
     * where selling_type in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    // Native 쿼리
    // id 기준으로 역순 정렬했을 떄 가장 상위에 있는 1개만 뽑아와라는 쿼리 -> 가장 마지막에 등록된 상품을 뽑아온다는 것
    @Query(value = "select p.product_number from Product p order by p.id desc limit 1",nativeQuery = true)
    String findLatesProductNumber();
}
