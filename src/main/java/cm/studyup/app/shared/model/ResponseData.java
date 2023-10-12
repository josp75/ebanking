package cm.studyup.app.shared.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public final class ResponseData<T> {
    private final byte status = 1;
    private final T response;

    public ResponseData( T response) {
        this.response = response;
    }

    public byte status() {
        return status;
    }

    public T response() {
        return response;
    }

}

