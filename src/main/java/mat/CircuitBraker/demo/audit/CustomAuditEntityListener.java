package mat.CircuitBraker.demo.audit;

import mat.CircuitBraker.demo.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class CustomAuditEntityListener {

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setLastUpdate(LocalDateTime.now());
    }

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        baseEntity.setLastUpdate(LocalDateTime.now());
    }
}