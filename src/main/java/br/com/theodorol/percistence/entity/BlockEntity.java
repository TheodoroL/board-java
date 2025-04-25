package br.com.theodorol.percistence.entity;
import java.time.OffsetDateTime;

public class BlockEntity {
    private  Long idBlocks;
    private OffsetDateTime blockedAt;
    private  String blockReason;
    private OffsetDateTime unblockedAt;
    private  String unblockReason;

    public Long getIdBlocks() {
        return idBlocks;
    }

    public void setIdBlocks(Long idBlocks) {
        this.idBlocks = idBlocks;
    }

    public OffsetDateTime getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(OffsetDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public OffsetDateTime getUnblockedAt() {
        return unblockedAt;
    }

    public void setUnblockedAt(OffsetDateTime unblockedAt) {
        this.unblockedAt = unblockedAt;
    }

    public String getUnblockReason() {
        return unblockReason;
    }

    public void setUnblockReason(String unblockReason) {
        this.unblockReason = unblockReason;
    }
}