module history {
    exports history.entities;
    exports history.service;
    exports history.repository;
    exports history.config;
    exports history.manager;
    exports history.repository.hibernate;
    exports history.util;
    requires java.naming;
    requires core;
    requires spring.beans;
    requires spring.kafka;
    requires spring.tx;
    requires spring.context;
    requires java.persistence;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires spring.web;
    requires kafka.clients;
    requires org.hibernate.orm.core;
    requires lombok;
}