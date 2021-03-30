package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.ProblemTest;


@Repository
public interface ProblemTestRepository extends JpaRepository<ProblemTest, Integer> {
}
