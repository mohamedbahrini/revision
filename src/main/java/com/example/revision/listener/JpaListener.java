package com.example.revision.listener;

import com.example.revision.model.Audit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class JpaListener<T extends Audit> {
    private final static Logger LOGGER = LoggerFactory.getLogger(JpaListener.class);

    @PrePersist
    void onPrePersist(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PrePersist {}", entity.getClass());
    }

    @PostPersist
    void onPostPersist(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PostPersist {}", entity.getClass());
    }

    @PostLoad
    void onPostLoad(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PostLoad {}", entity.getClass());
    }

    @PreUpdate
    void onPreUpdate(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PreUpdate {}", entity.getClass());
    }

    @PostUpdate
    void onPostUpdate(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PostUpdate {}", entity.getClass());
    }

    @PreRemove
    void onPreRemove(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PreRemove {}", entity.getClass());
    }

    @PostRemove
    void onPostRemove(T entity) {
        //ContextWrapper.getContext().getBean(AUDIT_REPOSITORY, AuditRepository.class);
        LOGGER.info("@PostRemove {}", entity.getClass());
    }

}
