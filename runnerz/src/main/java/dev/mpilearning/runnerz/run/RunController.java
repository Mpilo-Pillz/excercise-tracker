package dev.mpilearning.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunController {
//        @Autowired // Not recomended makes testing harder
    private final RunRepository runRepository;
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }
    @GetMapping("/api/runs")
    List<Run> findAll() {
        return this.runRepository.findAll();
    }
}
