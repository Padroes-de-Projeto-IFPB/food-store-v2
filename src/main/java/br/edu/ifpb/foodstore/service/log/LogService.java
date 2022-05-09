package br.edu.ifpb.foodstore.service.log;

import br.edu.ifpb.foodstore.service.log.bridge.impl.LogHandlerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogHandlerInterface logHandler;

    public void debug(String message) {
        logHandler.log("debug");
        logHandler.log(message);
    }

    public void info(String message) {
        logHandler.log(message);
    }
    public void error(String message) {
        logHandler.log("error");
        logHandler.log(message);
    }

}
