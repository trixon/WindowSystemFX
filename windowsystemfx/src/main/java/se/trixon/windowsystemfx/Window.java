/*
 * Copyright 2019 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.windowsystemfx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

/**
 *
 * @author Patrik Karlström
 */
public abstract class Window {

    private final StringProperty mNameProperty = new SimpleStringProperty();

    public Window() {
    }

    public String getName() {
        return mNameProperty.get();
    }

    public abstract Node getNode();

    public StringProperty nameProperty() {
        return mNameProperty;
    }

    public void setName(String name) {
        mNameProperty.set(name);
    }

    protected String modeId() {
        Class<?> clazz = getClass();
        Description id = clazz.getAnnotation(Description.class);
        if (id != null) {
            return id.modeId();
        }

        return null;
    }

    protected String preferredId() {
        Class<?> clazz = getClass();
        Description id = clazz.getAnnotation(Description.class);
        if (id != null) {
            return id.preferredID();
        }

        return getClass().getName();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface Description {

        public String preferredID();

        public String iconBase() default "";

        public String modeId();
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public static @interface Registration {

        int position() default Integer.MAX_VALUE;

        boolean openAtStartup();

    }

}
