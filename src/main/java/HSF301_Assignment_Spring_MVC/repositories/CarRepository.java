package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c WHERE c.carName LIKE CONCAT('%', :name, '%')")
    List<Car> findCarsByName(@Param("name") String name);

    @Query(value = "SELECT \n" +
            "    ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNumber,\n" +
            "    c.*\n" +
            "FROM \n" +
            "    cars c\n" +
            "WHERE \n" +
            "    c.car_name LIKE CONCAT('%', :carName , '%')\n" +
            "ORDER BY \n" +
            "    RowNumber\n" +
            "OFFSET ( :page - 1) * 6 ROWS \n" +
            "FETCH NEXT 6 ROWS ONLY", nativeQuery = true)
    List<Car> getAllCarsByPageFilterByName(@Param("carName") String carName, @Param("page") Integer page);

    @Query(value = "SELECT Count(*) FROM cars c", nativeQuery = true)
    int getCarsQuantity();
}

