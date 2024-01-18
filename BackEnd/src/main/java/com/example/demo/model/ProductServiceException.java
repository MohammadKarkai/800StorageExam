package com.example.demo.model;
    public class ProductServiceException extends Exception {

        public ProductServiceException() {
            super();
        }

        public ProductServiceException(String message) {
            super(message);
        }

        public ProductServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }


