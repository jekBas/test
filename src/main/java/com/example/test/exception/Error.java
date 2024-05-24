package com.example.test.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
@JsonPropertyOrder({"id", "status", "name", "message"})
public class Error {
    private String id;
    private String status;
    private String name;
    private String message;

    public Error() {
    }

    private Error(String id, String status, String name, String message) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String status;
        private String name;
        private String message;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            return new Error(id, status, name, message);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{"
              + "id='" + id + '\''
              + ", status='" + status + '\''
              + ", name='" + name + '\''
              + ", message='" + message + '\''
              + '}';
    }
}
