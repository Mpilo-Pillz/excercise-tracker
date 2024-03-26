package dev.mpilearning.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;


    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

//    @Autowired // Not recomended makes testing harder
//    private List<Run> runs = new ArrayList<>();
//
//
    List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, miles, location) values(?,?,?,?,?,?)")
                .param(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();
    }

    void update(Run run, Integer id) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, miles = ?, location ? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();
    }

    void delete(Integer id) {
       var updated = jdbcClient.sql("delete from run where id = :id")
               .param("id", id)
               .update();

        Assert.state(updated == 1, "failed to delete run " + id);
    }

    public int count() { return jdbcClient.sql("select * from run").query().listOfRows().size(); }

    public void saveAl(List<Run> runs) { runs.stream().forEach(this::create);}

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

    Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id, title, started_on, completed_on, miles, location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }
//
//    @PostConstruct
//    private void init() {
//      runs.add(new Run(1, "Monday Morning Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR));
//      runs.add(new Run(2, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6, Location.INDOOR));
//    }
}
