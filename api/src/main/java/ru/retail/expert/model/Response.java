package ru.retail.expert.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T> {
    private Status status;
    private T data;

    public Response(Integer code, String desc) {
        this.status = new Status(code, desc);
    }

    public Response(T data) {
        this.status = new Status(1, "success");
        this.data = data;
    }

    public Response() {
        this.status = new Status();
    }
}
