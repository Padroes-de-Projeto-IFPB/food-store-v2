package br.edu.ifpb.foodstore.domain;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface Payment {
    void doPay(LogService logService);
}
