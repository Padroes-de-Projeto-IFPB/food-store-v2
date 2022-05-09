package br.edu.ifpb.foodstore.service.log.bridge.abstraction;

import br.edu.ifpb.foodstore.service.log.LogHandler;
import br.edu.ifpb.foodstore.service.log.bridge.impl.LogHandlerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name="spring.profiles.active", havingValue = "dev")
public class LogServiceDevelopment implements LogService {
    private final LogHandlerInterface logHandler;

    public void debug(String message) {
        logHandler.log("debug");
        logHandler.log(message);
    }

    public void info(String message) {
        logHandler.log("info");
        logHandler.log(message);
    }
    public void error(String message) {
        logHandler.log("error");
        logHandler.log(message);
    }
}
