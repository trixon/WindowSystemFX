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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;

/**
 *
 * @author Patrik Karlström
 */
public class WindowManager {

    /**
     * A map with all modes with ID as key
     */
    private HashMap<String, Mode> mIdModes = new HashMap<>();
    /**
     * A list of modes per Mode(id)
     */
    private final HashMap<String, ArrayList<Mode>> mModeModes = new HashMap<>();
    /**
     * A list of windows per Mode(id)
     */
    private final HashMap<String, ArrayList<Window>> mModeWindows = new HashMap<>();
    /**
     * A list of all modes
     */
    private final ObservableList<Mode> mModes = FXCollections.observableArrayList();
    /**
     * A list property of all modes
     */
    private final ListProperty<Mode> mModesProperty = new SimpleListProperty<>(mModes);
    /**
     * A comparator for the position
     */
    private Comparator<WindowSystemComponent> mPositionComparator = Comparator.comparing(WindowSystemComponent::getPosition);
    /**
     * The root UI object, typically a SplitPane
     */
    private Region mRootPane;
    /**
     * A list of all windows
     */
    private final ObservableList<Window> mWindows = FXCollections.observableArrayList();
    /**
     * A list property of all windows
     */
    private final ListProperty<Window> mWindowsProperty = new SimpleListProperty<>(mWindows);

    public static WindowManager getInstance() {
        return Holder.INSTANCE;
    }

    private WindowManager() {
        Lookup.getDefault().lookupResult(Window.class).addLookupListener((LookupEvent ev) -> {
            //populateWindows();
        });
    }

    public Mode getModeForId(String id) {
        return mIdModes.get(id);
    }

    public Region getRoot() {
        return mRootPane;
    }

    public void init() {
        populateModes();
        populateWindows();
    }

    public ListProperty<Mode> modesProperty() {
        return mModesProperty;
    }

    public ListProperty<Window> windowsProperty() {
        return mWindowsProperty;
    }

    private void populateModes() {
        var modes = new ArrayList<>(Lookup.getDefault().lookupAll(Mode.class));
        mModes.addAll(modes);

        for (Mode mode : modes) {
            if (mode.parentId().equals("")) {
                mRootPane = mode.getRegion();
            }
            mModeModes.computeIfAbsent(mode.parentId(), k -> new ArrayList<>()).add(mode);
            mIdModes.put(mode.preferredId(), mode);
        }

        for (Map.Entry<String, ArrayList<Mode>> entry : mModeModes.entrySet()) {
            Mode mode = getModeForId(entry.getKey());
            ArrayList<Mode> list = entry.getValue();
            list.sort(mPositionComparator);

            if (mode != null) {
                mode.addModes(list);
            }
        }
    }

    private void populateWindows() {
        var windows = new ArrayList<>(Lookup.getDefault().lookupAll(Window.class));
        mWindows.addAll(windows);

        for (Window window : windows) {
            mModeWindows.computeIfAbsent(window.parentId(), k -> new ArrayList<>()).add(window);
        }

        for (Map.Entry<String, ArrayList<Window>> entry : mModeWindows.entrySet()) {
            Mode mode = getModeForId(entry.getKey());
            ArrayList<Window> list = entry.getValue();
            list.sort(mPositionComparator);
            if (mode != null) {
                mode.addWindows(list);
            }
        }
    }

    private static class Holder {

        private static final WindowManager INSTANCE = new WindowManager();
    }
}
