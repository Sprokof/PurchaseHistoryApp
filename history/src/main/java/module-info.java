module history {
    requires java.naming;
    requires core;
    requires spring.beans;
    requires spring.kafka;
    requires spring.context;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.logging;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires jakarta.persistence;
    requires com.fasterxml.jackson.databind;
    exports history.entities;
    exports history.service;
    exports history.repository;
    exports history.entities.enums;
    exports history.repository.hibernate;
}