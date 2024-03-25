package dev.mpilearning.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
//    @Autowired // Not recomended makes testing harder
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    @PostConstruct
    private void init() {
      runs.add(new Run(1, "Monday Morning Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR));
      runs.add(new Run(2, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6, Location.INDOOR));
    }
}
