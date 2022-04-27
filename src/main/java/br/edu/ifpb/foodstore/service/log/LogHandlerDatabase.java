package br.edu.ifpb.foodstore.service.log;

import java.util.Calendar;

import br.edu.ifpb.foodstore.domain.LogRegister;
import br.edu.ifpb.foodstore.repository.LogRegisterRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogHandlerDatabase implements LogHandlerInterface {

  private final LogRegisterRepository logRegisterRepository;

  @Override
  public void log(String message) {
    // TODO Auto-generated method stub
    LogRegister logRegister = new LogRegister();
    logRegister.setMessage(message);
    logRegister.setDate(Calendar.getInstance());
    logRegisterRepository.save(logRegister);

  }
  
}
