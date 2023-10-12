package cm.studyup.app.shared.model;

public final class ErrorData<T> {
    private final byte status;
    private final T errors;

    public ErrorData(byte status, T response) {
        this.status = status;
        this.errors = response;
    }

    public byte status() {
        return status;
    }

    public T response() {
        return errors;
    }

}
