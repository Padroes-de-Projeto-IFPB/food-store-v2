package br.edu.ifpb.foodstore.service.log.bridge.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RequiredArgsConstructor
@Slf4j
@Component
@ConditionalOnProperty(name="log.type", havingValue = "file")
public class LogHandlerFile implements LogHandlerInterface {

    private static final String LOG_FILE_NAME = "/tmp/log.log";

    @Override
    public void log(String message) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_NAME, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            printWriter.printf("%s: %s\n", format.format(Calendar.getInstance().getTime()), message);
            printWriter.close();
        } catch (IOException ioException) {
            log.error("Fail to write to log file");
        }
    }

}
