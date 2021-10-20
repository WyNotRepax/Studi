package de.hsos.mannschaftssport.control.dto;

public class Error {
    public static class Source {
        private String pointer;

        public Source(String pointer) {
            this.pointer = pointer;
        }

        public String getPointer() {
            return pointer;
        }

        public void setPointer(String pointer) {
            this.pointer = pointer;
        }
    }

    private Long code;
    private Long status;
    private Source source;
    private String title;
    private String detail;

    public Error(){}

    public Error(Long code, Long status, Source source, String title,  String detail){
        this.code = code;
        this.status = status;
        this.source = source;
        this.title = title;
        this.detail = detail;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ErrorBuilder type(ErrorType errorType){
        return new ErrorBuilder().type(errorType);
    }

    public static ErrorBuilder source(String source){
        return new ErrorBuilder().source(source);
    }

    public static ErrorBuilder detail(String detail){
        return new ErrorBuilder().detail(detail);
    }

    public static ErrorBuilder badData(){
        return new ErrorBuilder().badData();
    }
    public static ErrorBuilder notFound(){
        return new ErrorBuilder().notFound();
    }

    public static class ErrorBuilder{
        private Error error;
        
        public ErrorBuilder(){
            this.error = new Error();
        }

        public Error build(){
            return this.error;
        }

        public ErrorBuilder type(ErrorType errorType){
            this.error.setCode(errorType.code);
            this.error.setStatus(errorType.status);
            this.error.setTitle(errorType.title);
            return this;
        }

        public ErrorBuilder source(String source){
            this.error.setSource(new Source(source));
            return this;
        }

        public ErrorBuilder detail(String detail){
            this.error.setDetail(detail);
            return this;
        }

        public ErrorBuilder badData(){
            return this.type(ErrorType.BAD_DATA);
        }

        public ErrorBuilder notFound(){
            return this.type(ErrorType.NOT_FOUND);
        }
    }

    public enum ErrorType{
        BAD_DATA(1,400,"Bad Data"),
        NOT_FOUND(2,404,"Not Found");

        public final long code;
        public final long status;
        public final String title;

        private ErrorType(long code, long status, String title){
            this.code = code;
            this.status = status;
            this.title = title;
        }
    }
}
