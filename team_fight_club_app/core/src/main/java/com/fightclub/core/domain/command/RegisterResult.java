package com.fightclub.core.domain.command;

import java.util.Objects;

public class RegisterResult {


    private String error;
    private boolean success;

    public RegisterResult(String error, boolean success) {
        this.error = error;
        this.success = success;
    }

    public RegisterResult() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterResult that = (RegisterResult) o;
        return success == that.success && error.equals(that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, success);
    }

    @Override
    public String toString() {
        return "RegisterResult{" +
                "error='" + error + '\'' +
                ", success=" + success +
                '}';
    }
}
