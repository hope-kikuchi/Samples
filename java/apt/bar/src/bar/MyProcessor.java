package bar;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.*;
import javax.tools.JavaFileObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class MyProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!annotations.isEmpty()) {
            try {
                JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile("foo.AutoGeneratedClass");
                try (BufferedWriter writer = new BufferedWriter(javaFileObject.openWriter())) {
                    writer.write(
                        "package foo;\n" +
                        "import foo.MyInterface;\n" +
                        "public class AutoGeneratedClass implements MyInterface {\n" +
                        "}"
                    );
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of("foo.MyAnnotation");
    }
}
