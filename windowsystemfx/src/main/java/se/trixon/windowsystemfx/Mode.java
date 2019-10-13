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
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Patrik Karlström
 */
public abstract class Mode {

    private static final Random RANDOM = new Random();

    private final StringProperty mNameProperty = new SimpleStringProperty();
    private SplitPane mSplitPane;
    private StackPane mStackPane;
    private TabPane mTabPane;

    public Mode() {
    }

    public void add(Mode mode) {
        getItems().add(mode.getRegion());
    }

    public void add(Window window) {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
            case STACK:
                getItems().add(window.getNode());
                break;

            case TABS:
                var tab = new Tab(window.getName(), window.getNode());
                tab.setGraphic(new Rectangle(16, 16, randomColor()));

                getTabPane().getTabs().add(tab);
                tab.textProperty().bind(window.nameProperty());
                break;
        }
    }

    public String getName() {
        return mNameProperty.get();
    }

    public Region getRegion() {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
                return getSplitPane();

            case STACK:
                return getStackPane();

            case TABS:
                return getTabPane();

            default:
                return null;
        }
    }

    public StringProperty nameProperty() {
        return mNameProperty;
    }

    public void setName(String name) {
        mNameProperty.set(name);
    }

    protected ModeLayout getLayout() {
        Description description = getClass().getAnnotation(Description.class);
        if (description != null) {
            return description.modeLayout();
        }

        return ModeLayout.TABS;
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

    private ObservableList<Node> getItems() {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
                return getSplitPane().getItems();

            case STACK:
                return getStackPane().getChildren();

            case TABS:
                return getTabPane().getChildrenUnmodifiable();

            default:
                return null;
        }
    }

    private SplitPane getSplitPane() {
        if (mSplitPane == null) {
            mSplitPane = new SplitPane();
            if (getLayout() == ModeLayout.SPLIT_HORIZONTAL) {
                mSplitPane.setOrientation(Orientation.HORIZONTAL);
            } else {
                mSplitPane.setOrientation(Orientation.VERTICAL);
            }
        }

        return mSplitPane;
    }

    private StackPane getStackPane() {
        if (mStackPane == null) {
            mStackPane = new StackPane();
        }
        return mStackPane;
    }

    private TabPane getTabPane() {
        if (mTabPane == null) {
            mTabPane = new TabPane();
        }
        return mTabPane;
    }

    private Color randomColor() {
        return Color.rgb(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface Description {

        public String preferredId();

        public String parentId() default "";

        public ModeLayout modeLayout() default ModeLayout.TABS;

        public int position() default Integer.MAX_VALUE;
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public static @interface Registration {

        boolean openAtStartup();
    }
}
