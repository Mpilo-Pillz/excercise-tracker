package dev.mpilearning.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

        Optional<Run> run = runRepository.findById(id);

        if(run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run Not Found");
        }
        return run.get();
    }

   @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }
}
