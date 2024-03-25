package dev.mpilearning.runnerz.run;

import java.time.LocalDateTime;

/**
 * Immutable
 * Saves up doing toString, contructior, Getters, Setters, equals etc
 * */
public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {

}
