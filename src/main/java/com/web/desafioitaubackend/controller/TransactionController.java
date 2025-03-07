package com.web.desafioitaubackend.controller;

import com.web.desafioitaubackend.dto.TransactionDto;
import com.web.desafioitaubackend.model.Transaction;
import com.web.desafioitaubackend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

   private final TransactionService transactionService;

   public TransactionController(TransactionService transactionService) {
       this.transactionService = transactionService;
   }

   @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto){
       if( transactionDto.getDataHora().isAfter(OffsetDateTime.now()) || transactionDto.getValor() <= 0){
           return ResponseEntity.unprocessableEntity().build();
       }
       transactionService.addTransaction(new Transaction(transactionDto.getValor(), transactionDto.getDataHora()));
    return ResponseEntity.status(HttpStatus.CREATED).build();
   }




}
