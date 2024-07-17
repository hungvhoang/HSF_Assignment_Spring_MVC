package HSF301_Assignment_Spring_MVC.dtos;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseDto <T> extends ResponseEntity<ResponseDto.Payload<T>> {
    public ResponseDto(HttpStatusCode status, String message, T data) {
        super(new Payload<>(status.value(), message, data),status);
    }

    @Builder
    public static class Payload<T> {
        public int code;
        public String message;
        public T data;
    }
}