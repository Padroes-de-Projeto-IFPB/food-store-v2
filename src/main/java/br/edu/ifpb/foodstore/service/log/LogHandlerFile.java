package br.edu.ifpb.foodstore.service.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LogHandlerFile implements LogHandlerInterface {

  private static final String LOG_FILE_NAME = "/tmp/log.log";

  @Override
  public void log(String message) {
    // TODO Auto-generated method stub
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
