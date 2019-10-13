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

/**
 *
 * @author Patrik Karlström
 */
public class WindowSystemComponent {

    protected final StringProperty mNameProperty = new SimpleStringProperty();

    public WindowSystemComponent() {
    }

    protected ModeLayout getLayout() {
        Description description = getClass().getAnnotation(Description.class);
        if (description != null) {
            return description.modeLayout();
        }

        return ModeLayout.TABS;
    }

    public String getName() {
        return mNameProperty.get();
    }

    public StringProperty nameProperty() {
        return mNameProperty;
    }

    public void setName(String name) {
        mNameProperty.set(name);
    }

    protected int getPosition() {
        Description description = getClass().getAnnotation(Description.class);
        if (description != null) {
            return description.position();
        }
        return Integer.MAX_VALUE;
    }

    protected String parentId() {
        Description description = getClass().getAnnotation(Description.class);
        if (description != null) {
            return description.parentId();
        }
        return getClass().getName();
    }

    protected String preferredId() {
        Description description = getClass().getAnnotation(Description.class);
        if (description != null) {
            return description.preferredId();
        }
        return getClass().getName();
    }

    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = ElementType.TYPE)
    public @interface Description {

        String iconBase() default "";

        ModeLayout modeLayout() default ModeLayout.TABS;

        boolean openAtStartup() default true;

        String parentId() default "";

        int position() default Integer.MAX_VALUE;

        String preferredId();

    }

}
