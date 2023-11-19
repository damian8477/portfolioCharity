package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(quantity) FROM Donation")
    Integer getSum();

    @Query("SELECT count(*) FROM Donation")
    Integer getCount();

    List<Donation> findAllByUserId(Long id);
}
