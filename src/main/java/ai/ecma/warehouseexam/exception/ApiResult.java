package ai.ecma.warehouseexam.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.function.Supplier;

/**
 * CLIENT TARAFGA QAYTADIGAN BARCHA RESPONSE'LAR UCHUN RAMKA
 *
 * @author Muhammad Mo'minov
 * @since 01.12.2021
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private Boolean success = false;
    private String message;
    private T data;
    private List<ErrorData> errors;


    //RESPONSE WITH BOOLEAN (SUCCESS OR FAIL)
    private ApiResult(Boolean success) {
        this.success = success;
    }


    //SUCCESS RESPONSE WITH DATA
    public ApiResult(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    //SUCCESS RESPONSE WITH DATA AND MESSAGE
    public ApiResult(T data, Boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    //SUCCESS RESPONSE WITH MESSAGE
    public ApiResult(String message) {
        this.message = message;
        this.success = Boolean.TRUE;
    }

    public ApiResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }


    //ERROR RESPONSE WITH ERROR DATA LIST
    public ApiResult(List<ErrorData> errors) {
        this.success = false;
        this.errors = errors;
    }

    /**
     * Data bor va success bulsa datani qaytaradi. Aks holda berilgan Exceptionga otadi.
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exception) throws X {
        if (data != null && success) {
            return data;
        } else {
            throw exception.get();
        }
    }

    /**
     * Agar success = false bo'lsa berilgan exceptionga otadi
     */
    public <X extends Throwable> void noSuccessThrow(Supplier<? extends X> exception) throws X {
        if (!success) throw exception.get();
    }


    public static <E> ApiResult<E> successResponse(E data) {
        return new ApiResult<>(data, true);
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(data, true, message);
    }

    public static <E> ApiResult<E> successResponse() {
        return new ApiResult<>(true);
    }

    public static ApiResult<String> successResponse(String message) {
        return new ApiResult<>(message);
    }



    public static ApiResult<ErrorData> errorResponse(List<ErrorData> errors) {
        return new ApiResult<>(errors);
    }

    public static ApiResult<ErrorData> errorResponse(String msg) {
        return new ApiResult<>(msg,Boolean.FALSE);
    }




}
