module history {
    exports history.service;
    exports history.entities;
    requires java.naming;
    requires core;
    requires spring.beans;
    requires spring.kafka;
    requires spring.context;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires spring.web;
}