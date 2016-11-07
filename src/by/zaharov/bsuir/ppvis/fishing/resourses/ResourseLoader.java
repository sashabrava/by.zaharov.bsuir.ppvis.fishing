package by.zaharov.bsuir.ppvis.fishing.resourses;

import java.net.URL;

public final class ResourseLoader {

    private ResourseLoader() {
        super();
    }

    public static URL loaderResourseUrl(String name) {
        return ResourseLoader.class.getResource(name);
    }
}
