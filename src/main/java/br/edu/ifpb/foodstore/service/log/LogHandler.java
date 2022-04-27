package br.edu.ifpb.foodstore.service.log;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogHandler implements LogHandlerInterface{
  public enum LogHandlerType { DATABASE, FILE}

  private final LogHandlerType type;

  @Autowired
  private LogHandlerDatabase logHandlerDatabase;

  @Autowired
  private LogHandlerFile logHandlerFile;

  @Override
  public void log(String message) {
    // TODO Auto-generated method stub
    assert type != null: "tipo de log deve ser definido";
    if(type.equals(LogHandlerType.DATABASE)) {
      logHandlerDatabase.log(message);
      
    }
    else if (type.equals(LogHandlerType.FILE)){
      logHandlerFile.log(message);

    }
  }

  
}
