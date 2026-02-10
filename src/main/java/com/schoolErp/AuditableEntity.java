package com.schoolErp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
// Soft-delete global filter (optional - enable per entity if needed)
@FilterDef(name = "nonDeletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "nonDeletedFilter", condition = "is_deleted = :isDeleted")
public abstract class AuditableEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;           // Usually current User ID (from SecurityContext)

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;

    @Version
    @Column(name = "version", nullable = false)
    private Long version = 0L;        // Optimistic locking

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // -------------------------------------------------------------------------
    // Soft-delete helper methods (call these instead of hard delete)
    // -------------------------------------------------------------------------

    /**
     * Marks entity as deleted (soft delete)
     */
    public void markAsDeleted(Long deletedByUserId) {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
        this.updatedBy = deletedByUserId;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Restores a soft-deleted entity
     */
    public void restore(Long restoredByUserId) {
        this.isDeleted = false;
        this.deletedAt = null;
        this.updatedBy = restoredByUserId;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Check if entity is considered active (not soft-deleted)
     */
    @Transient
    public boolean isActive() {
        return !isDeleted;
    }
}
