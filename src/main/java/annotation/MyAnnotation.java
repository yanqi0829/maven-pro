package annotation;

public class MyAnnotation {
    @Override
    @SuppressWarnings("all")
    @IAnnotation("12")
    public String toString() {
        return "MyAnnotation{}";
    }


}

@interface IAnnotation {
    String value();
}
