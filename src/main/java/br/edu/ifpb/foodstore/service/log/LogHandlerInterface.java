package br.edu.ifpb.foodstore.service.log;

import br.edu.ifpb.foodstore.domain.LogRegister;
import br.edu.ifpb.foodstore.repository.LogRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public interface LogHandlerInterface {

    public void log(String message);

}
