package vn.hust.aims.entity.media.factory;


import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import vn.hust.aims.enumeration.MediaType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MediaFactoryBuilder {
    private static final Map<String, MediaFactoryInterface> mediaFactoryMap = new HashMap<>();

    static {
        registerMediaFactoryClasses();
    }

    public static void registerMediaFactoryClasses() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages("vn.hust.aims.entity.media.factory")
                .addScanners(Scanners.SubTypes));

        Set<Class<? extends MediaFactoryInterface>> mediaFactoryClasses = reflections.getSubTypesOf(
                MediaFactoryInterface.class);

        mediaFactoryClasses.forEach(mediaFactory -> {
            MediaFactoryProvider provider = mediaFactory.getAnnotation(MediaFactoryProvider.class);

            try {
                mediaFactoryMap.put(provider.value(), mediaFactory.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static MediaFactoryInterface get(MediaType mediaType) {
        return mediaFactoryMap.get(mediaType.name());
    }


}
