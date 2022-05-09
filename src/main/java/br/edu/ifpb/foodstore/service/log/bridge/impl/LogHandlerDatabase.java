package br.edu.ifpb.foodstore.service.log.bridge.impl;

import br.edu.ifpb.foodstore.domain.LogRegister;
import br.edu.ifpb.foodstore.repository.LogRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@RequiredArgsConstructor
@Slf4j
@Component
@ConditionalOnProperty(name="log.type", havingValue = "db")
public class LogHandlerDatabase implements LogHandlerInterface {

    private final LogRegisterRepository logRegisterRepository;

    @Override
    public void log(String message) {
        LogRegister logRegister = new LogRegister();
        logRegister.setMessage(message);
        logRegister.setDate(Calendar.getInstance());
        logRegisterRepository.save(logRegister);
    }

}
