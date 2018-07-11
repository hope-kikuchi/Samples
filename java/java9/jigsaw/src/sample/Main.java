package sample;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        ModuleLayer bootLayer = ModuleLayer.boot();

        // foo �����[�g���W���[���Ƃ������W���[���O���t(Configuration)���쐬
        ModuleFinder moduleFinder = ModuleFinder.of(Paths.get("foo/classes"));
        ModuleFinder emptyFinder = ModuleFinder.of();
        Set<String> roots = Set.of("foo");

        Configuration bootConfiguration = bootLayer.configuration();
        Configuration newConfiguration = bootConfiguration.resolve(moduleFinder, emptyFinder, roots);

        // �V���� ModuleLayer ���쐬
        ModuleLayer newLayer = bootLayer.defineModulesWithOneLoader(newConfiguration, ClassLoader.getSystemClassLoader());

        // foo ���W���[������ Foo �N���X�����[�h���� hello() ���\�b�h�����s
        ClassLoader fooClassLoader = newLayer.findLoader("foo");
        Class<?> fooClass = fooClassLoader.loadClass("foo.Foo");
        Object fooInstance = fooClass.getConstructor().newInstance();
        Method helloMethod = fooClass.getMethod("hello");
        helloMethod.invoke(fooInstance);
    }
}