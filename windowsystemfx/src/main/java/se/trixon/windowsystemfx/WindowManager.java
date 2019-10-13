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
import java.util.HashMap;
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

    private HashMap<String, Mode> mIdModes = new HashMap<>();
    private final ObservableList<Mode> mModes = FXCollections.observableArrayList();
    private final ListProperty<Mode> mModesProperty = new SimpleListProperty<>(mModes);
    private Region mRootPane;
    private final ObservableList<Window> mWindows = FXCollections.observableArrayList();
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
        for (Mode mode : modes) {
            if (mode.parentId().equals("")) {
                mRootPane = mode.getRegion();
            }
            mIdModes.put(mode.preferredId(), mode);
        }

        for (Mode mode : modes) {
            Mode parentMode = mIdModes.get(mode.parentId());
            if (parentMode != null) {
                parentMode.add(mode);
            }
        }

        mModes.addAll(modes);
    }

    private void populateWindows() {
        var windows = new ArrayList<>(Lookup.getDefault().lookupAll(Window.class));
        mWindows.addAll(windows);

        for (Window window : windows) {
            try {
                getModeForId(window.parentId()).add(window);
            } catch (Exception e) {
                System.out.println(window);
                System.err.println(e);
            }
        }
    }

    private static class Holder {

        private static final WindowManager INSTANCE = new WindowManager();
    }
}
