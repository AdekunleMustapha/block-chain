package com.akin.modules.block.application.usecases;

import com.akin.modules.block.domain.entities.Block;
import com.akin.modules.block.domain.valueobjects.Hash;

public class CreateBlockUseCase {

    public static String execute(String prevHash, String ...data) {
        System.out.println("Executing CreateBlockUseCase...");
        StringBuilder transactionBuilder = new StringBuilder();
        for(String transaction : data){
            transactionBuilder.append(transaction);
        }
        String transactionData = transactionBuilder.toString();
        System.out.printf("%s\n", transactionData);

        if(prevHash == null) {
            prevHash = "0";
        }
        Block block = Block.create(Hash.reconstitute(prevHash), transactionData, null);
        System.out.println("New block created!");
        System.out.printf("%s\n", block);
        return block.getHash();
    }
}
