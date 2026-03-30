package com.akin.modules.blockchain.domain.entity;

import com.akin.modules.block.domain.entities.Block;

import java.util.ArrayList;

public class BlockChain {
    private final ArrayList<Block> _blocks;

    private BlockChain (ArrayList<Block> blocks) {
        this._blocks = blocks;
    }

    /**
     * Creates blockchain object
     * @param blocks array of blocks
     * @throws IllegalArgumentException blocks must not be an empty array
     * @return BlockChain object
     */
    public static BlockChain create(ArrayList<Block> blocks) {
        if(blocks == null || blocks.isEmpty())
            throw new IllegalArgumentException("blocks must not be empty");
        return new BlockChain(blocks);
    }

    /**
     * Adds new block to list
     * @param block recent block
     * @throws IllegalArgumentException no null blocks. Blocks must be linked
     */
    public void addBlock(Block block) {
        // validates input is not null and blocks are linked
        if(block == null)
            throw new IllegalArgumentException("block must not be null");
        Block prevBlock = this._blocks.get(_blocks.size() - 1);
        String prevHash = prevBlock.getHash();
        String blockHash = block.getHash();
        if (!prevHash.equals(blockHash))
            throw new IllegalArgumentException("block isn't linked to previous block");
        // adds block to chain
        this._blocks.add(block);
    }
}
