package br.edu.ifpb.foodstore.service.log.bridge;

import br.edu.ifpb.foodstore.service.log.bridge.abstraction.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogServiceClient {

    /***
     * Injeção de múltiplas implementações
     */
    private final List<LogService> logServiceInterfaceList;

    public void log() {
        logServiceInterfaceList.forEach( logService -> logService.info("teste"));
    }

}
