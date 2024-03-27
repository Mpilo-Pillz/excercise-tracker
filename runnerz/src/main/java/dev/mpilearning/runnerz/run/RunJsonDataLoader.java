package dev.mpilearning.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;

public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public void run(String... args) throws Exception {
    if(runRepository.count() == 0) {
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
            Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
            log.info("Reading {} runs from data and saving to in-memory collection.", allRuns.runs().size());
            runRepository.saveAl(allRuns.runs());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    } else {
        log.info("Not loading Runs from JSON data becuase the collection contains data");
    }
    }
}
