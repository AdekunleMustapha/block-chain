package modules.blockchain.domain.entities;
import modules.blockchain.domain.valueobjects.Hash;

import java.util.Date;

/**
 * Block Entity - Aggregate entity
 * Stores transaction data, prev hash and timestamp to generate a new hash
 * */
public class Block {
    private final Hash _hash;
    private final Hash _prevHash;
    private final String _data;
    private final Date _timestamp;

    /**
     * @param hash hash of block
     * @param prevHash hash of previous block
     * @param data array of transactions
     * @param timestamp time of creation of block
     */
    private Block(Hash hash, Hash prevHash, String data, Date timestamp) {
        this._hash = hash;
        this._prevHash = prevHash;
        this._data = data;
        this._timestamp = timestamp;
    }

    /**
     * Creates a block
     *
     * @param prevHash the previous block signature
     * @param data the transactions for the block
     * @param timestamp the time of block creation
     * @return block object
     */
    public static Block create (Hash prevHash, String data, Date timestamp) {
        // validating input
        if (prevHash == null) {
            throw new IllegalArgumentException("prevHash is null");
        }
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (timestamp == null) {
            timestamp = new Date();
        }
        Hash hash = Hash.create(prevHash.getHash(), data, timestamp.toString());
        return new Block(hash, prevHash, data, timestamp);
    }

    public String getHash() {
        return _hash.getHash();
    }
    public String getPrevHash() {
        return _prevHash.getHash();
    }
    public String getData() {
        return _data;
    }
    public Date getTimestamp() {
        return _timestamp;
    }
    public String toString() {
        return "data: " + this.getData() + "\n" + "hash: " + this.getHash() + "\n"
                + "prevHash: " + this.getPrevHash() + "\n" + "timestamp: " + this.getTimestamp();
    }
}
