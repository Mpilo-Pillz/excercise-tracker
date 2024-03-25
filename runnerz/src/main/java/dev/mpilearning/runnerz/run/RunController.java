package dev.mpilearning.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {
//        @Autowired // Not recomended makes testing harder
    private final RunRepository runRepository;
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }
    @GetMapping("")
    List<Run> findAll() {
        return this.runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        return runRepository.findById(id);
    }
}
