module operations {
    requires core;
    requires spring.context;
    requires spring.web;
    requires spring.beans;
    requires kafka.clients;
    requires spring.kafka;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires com.fasterxml.jackson.databind;
    requires ch.qos.logback.core;
    requires ch.qos.logback.classic;
    requires org.slf4j;
    exports operations.controller;
    exports operations.service;

}